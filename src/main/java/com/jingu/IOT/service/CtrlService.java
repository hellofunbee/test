/**
 * 项目名称：IOT
 * 类名称：CtrlService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年11月22日 下午2:42:48
 * 修改人：jianghu
 * 修改时间：2017年11月22日 下午2:42:48
 * 修改备注： 下午2:42:48
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.dao.AlarmDao;
import com.jingu.IOT.dao.PointDao;
import com.jingu.IOT.dao.SettingDao;
import com.jingu.IOT.entity.ControlEntity;
import com.jingu.IOT.entity.MonitorEntity;
import com.jingu.IOT.entity.MotorHBM;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**

 * @ClassName: CtrlService
 * @Description: TODO
 * @author jianghu
 * @date 2017年11月22日 下午2:42:48

 */
@Component
public class CtrlService {


    private SettingDao settingDao;
    private PointDao pointDao;
    private ToolUtil toolUtil;
    private AlarmDao alarmDao;


    @Autowired
    public CtrlService(SettingDao settingDao, PointDao pointDao, ToolUtil toolUtil, AlarmDao alarmDao) {
        this.settingDao = settingDao;
        this.pointDao = pointDao;
        this.toolUtil = toolUtil;
        this.alarmDao = alarmDao;
    }

    public void findChannelValue(String deviceId, String[] split) throws UnsupportedEncodingException {

        List<Map<String, Object>> listAlarmGroupByDeviceId = alarmDao.listAlarmGroupByDeviceId(deviceId);

        for (Map<String, Object> map : listAlarmGroupByDeviceId) {
            String ala_channel = map.get("ala_channel").toString();
            String substring = ala_channel.substring(7);
            if (substring == null) {
                System.out.println("未上报该字段...");
                continue;
            }
            int parseInt = Integer.parseInt(substring);
            String string = split[parseInt - 1];
            Map<String, Object> grade = alarmDao.getGrade(deviceId, ala_channel, string);
            String ala_id = grade.get("ala_id").toString();
            if (toolUtil.getCheck(ToolUtil.ALARMGRADE + deviceId + ala_channel) == null ||
                    toolUtil.getCheck(ToolUtil.ALARMGRADE + deviceId + ala_channel).trim().length() < 1 ||
                    toolUtil.getCheck(ToolUtil.ALARMGRADE + deviceId + ala_channel).equals(grade.get("ala_id").toString())
                    ) {
                toolUtil.setCheck(ToolUtil.ALARMGRADE + deviceId + ala_channel, ala_id);
                // 发送短信
//				sendMsg(deviceId,grade);
            }

        }
    }


    public int upSwitch(String deviceId, String[] split) throws UnsupportedEncodingException {
        ControlEntity controlEntity = new ControlEntity();
        int distanceOrduration = 0;
        PointEntity pointEntity = new PointEntity();
        pointEntity.setDeviceId(deviceId);
        MotorHBM hbm = new MotorHBM();
        List<MonitorEntity> monitorList = toolUtil.getMonitorList(ToolUtil.MONITOR + deviceId);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    findChannelValue(deviceId, split);
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();


        for (MonitorEntity monitorEntity : monitorList) {
            String mo_channel = monitorEntity.getMo_channel();
            String channel_index = mo_channel.substring(7);

            if (channel_index == null) {
                System.out.println("未上报该字段...");
                return 0;
            }
            int parseInt = Integer.parseInt(channel_index);

            double parseDouble = Double.parseDouble(split[parseInt - 1]);

            System.out.println(split[parseInt - 1]);

            controlEntity.setCtrl_id(monitorEntity.getCtrl_id());

            List<Map<String, Object>> listControlSetting = settingDao.listControlSetting(controlEntity);

            if (parseDouble < monitorEntity.getMo_lower()) {
                // 开启开关
                distanceOrduration = -1;
                controlEntity.setS_state(1);
            }
            if (parseDouble > monitorEntity.getMo_high()) {
                // 开启关闭
                distanceOrduration = 0;
                controlEntity.setS_state(2);
            }
            Map<String, Object> ctrl = listControlSetting.get(0);
            hbm.setDistanceOrDuration(distanceOrduration);
            hbm.setCtrlType(Integer.parseInt(ctrl.get("ctrl_type").toString()));
            hbm.setRaiseGroupId((ctrl.get("ctrl_raise_groupId").toString()));
            hbm.setRaiseSwitchId((ctrl.get("ctrl_raise_switchId").toString()));
            hbm.setSkinGroupId((ctrl.get("ctrl_down_groupId").toString()));
            hbm.setSkinSwitchId((ctrl.get("ctrl_down_switchId").toString()));

            if (ctrl.get("ctrl_type").toString().equals("1")) {
                hbm.setPosSensorCH(Integer.parseInt(ctrl.get("ctrl_channel").toString()));
            }

            List<PointEntity> pointlist = pointDao.getPoint(pointEntity);
            PointEntity devicePoint = pointlist.get(0);

            System.out.println(devicePoint.getIp());
            System.out.println(devicePoint.getPort());
            System.out.println(devicePoint.getDeviceId());
            System.out.println(hbm.toByteCmd());
            // 读取开关状态再决定是否发送命令
            if (ctrl.get("s_state").toString().equals("2") &&
                    distanceOrduration == 0) {
                System.out.println("规则" + monitorEntity.getMo_name() + " 命令已经发送");
//				return 1;
            }
            if (ctrl.get("s_state").toString().equals("1") &&
                    distanceOrduration == -1) {
                System.out.println("规则" + monitorEntity.getMo_name() + " 命令已经发送");
//				return 1;
            }
            boolean motorsCtrl2 = Client.motorsCtrl2(devicePoint.getIp(), devicePoint.getPort(), devicePoint.getDeviceId(), hbm.toByteCmd(), (byte) 0x17);
            if (motorsCtrl2) {
                // 改变开关状态
                int updateControlSetting = settingDao.updateControlSetting(controlEntity);
                System.out.println("执行成功..");
            }
            System.out.println("执行失败..");
        }
        return distanceOrduration;


    }

    /**
     * 根据 ctrl ids 查找
     * @param s
     * @return
     */
    public List<Map<String, Object>> findByCtrl_ids(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return settingDao.findByCtrl_ids(s);
    }
}
