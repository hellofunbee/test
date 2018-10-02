package com.jingu.IOT.service;

import com.jingu.IOT.entity.ControlEntity;
import com.jingu.IOT.entity.MotorHBM;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.PageData;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 与设备交互（控制）的中间层
 */
@Component
public class ClientServie {
    @Autowired
    SettingService settingService;
    @Autowired
    PointService pointService;
    @Autowired
    ToolUtil toolUtil;

    //用到两个状态：s_state、is_running
    //s_state 是开关的固有状态 是开还是关
    //is_running 表示 现在程序正在对这个开关做出控制 ，当程序控制完成 is_running 状态改变

    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

    /**
     * 控制开关 ctrl_type:1 -->卷帘 设置开启     distanceOrduration 秒
     * 控制开关 ctrl_type:1 -->电磁阀 设开关 distanceOrduration -1，0,或者 开启后 distanceOrduration 秒关闭
     *
     * @param ctrl
     * @param distanceOrduration
     * @return
     */

    public IOTResult switchIt(Map ctrl, int distanceOrduration, int sencond, boolean hasNext) {
        MotorHBM hbm = new MotorHBM();
        int openLevel = distanceOrduration;


        //直流
        if ((Integer) ctrl.get("ctrl_type") == 1) {
            //计算开启度
            if (distanceOrduration > 0) {
                openLevel = distanceOrduration * 100 / ToolUtil.TotalS;
            }
            hbm.setSkinGroupId((ctrl.get("ctrl_down_groupId").toString()));
            hbm.setSkinSwitchId((ctrl.get("ctrl_down_switchId").toString()));
            hbm.setPosSensorCH(Integer.parseInt(ctrl.get("ctrl_channel").toString()));//电机位置传感器通道号 1 ~ 16。
        }
        //电磁阀

        hbm.setCtrlType(Integer.parseInt(ctrl.get("ctrl_type").toString()));
        hbm.setRaiseGroupId((ctrl.get("ctrl_raise_groupId").toString()));
        hbm.setRaiseSwitchId((ctrl.get("ctrl_raise_switchId").toString()));

        hbm.setDistanceOrDuration(openLevel);//控制行程，0 ~ 100：表示整个行程的1% ~ 100%。 0关  -1 常开


        PointEntity point = new PointEntity();
        point.setDeviceId((String) ctrl.get("ctrl_deviceId"));

        PointEntity devicePoint = pointService.getPoint(point);


        boolean result = Client.motorsCtrl2(devicePoint.getIp(), devicePoint.getPort(), devicePoint.getDeviceId(), hbm.toByteCmd(), (byte) 0x17);

        if (result) {
            // 改变开关状态
            ControlEntity ctrl_bean = new ControlEntity();
            ctrl_bean.setCtrl_id((Integer) ctrl.get("ctrl_id"));


            //只要不是关闭状态 就设置成正在执行
            if (distanceOrduration != 0) {
                ctrl_bean.setIs_running(1);
                ctrl_bean.setS_state(1);
            } else {
                ctrl_bean.setS_state(2);
            }


            int status = settingService.updateControlSetting(ctrl_bean);
            //开启 x 秒后 或者 关闭 X秒后执行此操作
            if (hasNext)
                exe(new Runnable() {
                    @Override
                    public void run() {
                        ctrl_bean.setIs_running(2);
                        ctrl_bean.setS_state(2);//关闭

                        if (hbm.getCtrlType() == 1) {
                            int status = settingService.updateControlSetting(ctrl_bean);

                        } else {
                            //开完就关 关完就开
                            //如果是设置了 --定时关闭 在这里只做状态更新
                            if (hbm.getDistanceOrDuration() > 0) {
                                settingService.updateControlSetting(ctrl_bean);
                                return;
                            }

                            //常开到时间后 关闭
                            if (hbm.getDistanceOrDuration() == -1) {
                                hbm.setDistanceOrDuration(0);

                                //关闭到时间后 开启
                            } else if (hbm.getDistanceOrDuration() == 0) {
                                ctrl_bean.setS_state(1);//常开
                                hbm.setDistanceOrDuration(-1);
                            }

                            boolean result = Client.motorsCtrl2(devicePoint.getIp(), devicePoint.getPort(), devicePoint.getDeviceId(), hbm.toByteCmd(), (byte) 0x17);
                            if (result) {
                                int status = settingService.updateControlSetting(ctrl_bean);
                            } else {
                                throw new RuntimeException("1");
                            }
                        }

                        System.out.println("执行完成..");

                    }
                }, sencond);

            System.out.println("执行开始..");

            return new IOTResult(true, "执行成功", hbm, 1);
        } else {
            return new IOTResult(false, "socket 异常", null, 0);
        }
    }

    private IOTResult exe(Runnable runnable, int second) {
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        try {
            service.scheduleAtFixedRate(runnable, 0, second, TimeUnit.SECONDS);
            return new IOTResult(true, "执行成功", null, 1);
        } catch (Exception e) {

            return new IOTResult(false, "执行异常", null, 0);
        }
    }


    /**
     * 自动控制 ---直流电机（）
     *
     * @param ctrl
     * @return
     */
    public IOTResult autoCtrl(PageData ctrl) {
        //auto_type;//1：上升 2：下降 3：开启度 4；关闭
        int auto_type = (int) ctrl.get("auto_type");

        Map c_ctrl = settingService.getControlSetting((Integer) ctrl.get("ctrl_id"));

        if (c_ctrl == null)
            return new IOTResult(false, "控制失败，没有发现控制", null, 0);
        else if (c_ctrl.get("is_running").equals(1)) {
            // TODO: 2018/10/2
//            service.shutdownNow();
        }
        //卷帘的参数
        int duation = 0;
        int direction = 0; //0x1：上升，0x2：下降，0x3:停止
        int distanceOrduration = (int) ctrl.get("distanceOrduration");//开启度暂时不用，但仍作为参数上传

        MotorHBM hbm = new MotorHBM();
        switch (auto_type) {
            case 1:
                direction = 0x1;
                break;
            case 2:
                direction = 0x2;
                break;
            case 3:
                direction = 0x2;
                duation = ((int) ctrl.get("distanceOrduration") * ToolUtil.TotalS) / 100;
                break;
            case 4:
                direction = 0x3;
                break;
            default:
                break;

        }

        hbm.setSkinGroupId((ctrl.get("ctrl_down_groupId").toString()));
        hbm.setSkinSwitchId((ctrl.get("ctrl_down_switchId").toString()));
        hbm.setPosSensorCH(Integer.parseInt(ctrl.get("ctrl_channel").toString()));//电机位置传感器通道号 1 ~ 16。
        hbm.setCtrlType(Integer.parseInt(ctrl.get("ctrl_type").toString()));
        hbm.setRaiseGroupId((ctrl.get("ctrl_raise_groupId").toString()));
        hbm.setRaiseSwitchId((ctrl.get("ctrl_raise_switchId").toString()));
        hbm.setDistanceOrDuration(duation);
        hbm.setDirection(direction);

        PointEntity point = new PointEntity();
        point.setDeviceId((String) ctrl.get("ctrl_deviceId"));
        PointEntity devicePoint = pointService.getPoint(point);

        boolean result = Client.motorsCtrl2(devicePoint.getIp(), devicePoint.getPort(), devicePoint.getDeviceId(), hbm.toByteCmd(), (byte) 0x17);

        if (result) {
            ControlEntity ctrl_bean = new ControlEntity();
            ctrl_bean.setCtrl_id((Integer) ctrl.get("ctrl_id"));

            //只要不是关闭状态 就设置成正在执行
            if (direction != 0x3) {
                ctrl_bean.setIs_running(1);
                ctrl_bean.setS_state(1);
            } else {
                ctrl_bean.setS_state(2);
            }
            int status = settingService.updateControlSetting(ctrl_bean);
            if (auto_type == 3) {
                //如果是开启度 则要在彻底关闭后再开启
                hbm.setDirection(0x1);
                boxRun(hbm, devicePoint, ctrl_bean, ToolUtil.TotalS);
            }
            return new IOTResult(true, "执行成功", hbm, 1);
        } else {
            return new IOTResult(false, "socket 异常", null, 0);
        }
    }

    public IOTResult autoCtrl_off_on(PageData ctrl) {
        //auto_type;//1：开启 2：关闭
        int auto_type = (int) ctrl.get("auto_type");
        int distanceOrDuration = 0;

        Map c_ctrl = settingService.getControlSetting((Integer) ctrl.get("ctrl_id"));

        if (c_ctrl == null)
            return new IOTResult(false, "控制失败，没有发现控制", null, 0);

        //继电器
        // -1一直开，0立即关

        MotorHBM hbm = new MotorHBM();
        switch (auto_type) {
            case 1:
                distanceOrDuration = -1;
                break;
            case 2:
                distanceOrDuration = 0;
                break;
            default:
                return new IOTResult(false, "执行失败", null, 0);
        }
        hbm.setCtrlType(Integer.parseInt(ctrl.get("ctrl_type").toString()));
        hbm.setRaiseGroupId((ctrl.get("ctrl_raise_groupId").toString()));
        hbm.setRaiseSwitchId((ctrl.get("ctrl_raise_switchId").toString()));
        hbm.setDistanceOrDuration(distanceOrDuration);


        PointEntity point = new PointEntity();
        point.setDeviceId((String) ctrl.get("ctrl_deviceId"));
        PointEntity devicePoint = pointService.getPoint(point);

        boolean result = Client.motorsCtrl2(devicePoint.getIp(), devicePoint.getPort(), devicePoint.getDeviceId(), hbm.toByteCmd(), (byte) 0x17);

        if (result) {
            ControlEntity ctrl_bean = new ControlEntity();
            ctrl_bean.setCtrl_id((Integer) ctrl.get("ctrl_id"));

            //只要不是关闭状态 就设置成正在执行
            if (distanceOrDuration == -1) {
                ctrl_bean.setS_state(1);
            } else {
                ctrl_bean.setS_state(2);
            }
            int status = settingService.updateControlSetting(ctrl_bean);

            return new IOTResult(true, "执行成功", hbm, 1);
        } else {
            return new IOTResult(false, "执行失败，socket 异常", null, 0);
        }
    }


    /**
     * 开启 duation 秒后 关闭
     *
     * @param hbm
     * @param devicePoint
     * @param ctrl_bean
     * @param duation
     */
    private void boxRun(final MotorHBM hbm, final PointEntity devicePoint, final ControlEntity ctrl_bean, int duation) {

        exe(new Runnable() {
            @Override
            public void run() {
                if (hbm.getDirection() == 0x3) {
                    ctrl_bean.setIs_running(2);
                    ctrl_bean.setS_state(2);
                } else {
                    ctrl_bean.setIs_running(1);
                    ctrl_bean.setS_state(1);
                }
                boolean result = Client.motorsCtrl2(devicePoint.getIp(), devicePoint.getPort(), devicePoint.getDeviceId(), hbm.toByteCmd(), (byte) 0x17);
                if (result) {
                    settingService.updateControlSetting(ctrl_bean);

                    if (ctrl_bean.getIs_running() == 1) {
                        hbm.setDirection(0x3);//到时间后关闭
                        boxRun(hbm, devicePoint, ctrl_bean, hbm.getDistanceOrDuration());

                    }
                } else {
                    //throw new RuntimeException("1");
                }

                if (ctrl_bean.getIs_running() == 1)
                    System.out.println("正在执行....上拉");
                if (ctrl_bean.getIs_running() == 2)
                    System.out.println("执行完成..");

            }
        }, duation);
    }


}
