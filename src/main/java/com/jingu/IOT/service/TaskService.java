/**
 * 项目名称：IOT
 * 类名称：RuleService
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月24日 下午4:55:27
 * 修改人：jianghu
 * 修改时间：2017年10月24日 下午4:55:27
 * 修改备注： 下午4:55:27
 *
 * @version
 */
package com.jingu.IOT.service;

import com.jingu.IOT.entity.*;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class TaskService {

    @Autowired
    private ToolUtil toolUtil;
    @Autowired
    private GatherService gatherService;
    @Autowired
    private CtrlService ctrlService;
    @Autowired
    SMSService smsService;

    @Autowired
    RelationShipService relationShipService;
    @Autowired
    MainDeviceService mainDeviceService;

    /**
     * 预警的实时监控
     *
     * @throws UnsupportedEncodingException
     */
    @Scheduled(fixedRate = 1000 * 60)
    public void alarm() throws UnsupportedEncodingException {

        List<Map<String, Object>> alarmList = toolUtil.getAlarmList(ToolUtil.ALARM);
        if (alarmList == null) {
            System.out.println("暂时没有报警监控");
            return;
        }
        //直接循环查找
        for (Map ala : alarmList) {

            Map params = new HashMap();
            params.put("field", "channel" + ala.get("ala_channel"));
            params.put("table_name", "t_vartriver_" + ((String) ala.get("deviceid")).replace(".", ""));
            Map val_map = gatherService.findTopval(params);//监控数据
            if (val_map == null)
                continue;

            MainDeviceEntity md = new MainDeviceEntity();
            md.setDeviceId((String) ala.get("deviceId"));
            Map device = mainDeviceService.getMainDeviceByDeviceId(md);
            if (device == null)
                continue;

            Double c_val = Double.parseDouble((String) val_map.get("val"));

            Double ala_up = (Double) ala.get("ala_up");
            Double ala_low = (Double) ala.get("ala_low");
            if (c_val > ala_up || c_val < ala_low) {
                // ala_producer=1,
                // ala_supervisor=1,
                List<String> phones = new ArrayList<>();

                if (ala.get("ala_producer").equals(1)) {
                    Map producer = relationShipService.findProducerByDviceId((String) ala.get("deviceid"));

                    if (producer != null && producer.get("tu_phone") != null)
                        phones.add((String) producer.get("tu_phone"));
                }
                if (ala.get("ala_supervisor").equals(1)) {
                    Map supervisor = relationShipService.findSupervisorByDviceId((String) ala.get("deviceid"));
                    if (supervisor != null && supervisor.get("tu_phone") != null)
                        phones.add((String) supervisor.get("tu_phone"));
                }

                if (phones.size() > 0) {
                    MessageEntity m = new MessageEntity();
                    m.setM_title("【监控报警】");
                    m.setM_content("设备【" + device.get("name") + "】" + ala.get("ala_content"));
                    smsService.sendSMS(phones, m);
                }
            }
        }
    }

    /**
     * 智能控制的实时监控
     * @throws UnsupportedEncodingException
     */
    @Scheduled(fixedRate = 1000 * 60)
    public void ctrlMonitorDevice() throws UnsupportedEncodingException {
        List<MonitorEntity> ruleList = toolUtil.getMonitorList(ToolUtil.MONITOR);
        if (ruleList == null) {
            System.out.println("暂时没有监控");
            return;
        }
        Map ctrls_map = new HashMap();
        //根据rule 找到其ctrl 信息

        StringBuffer ctrl_ids = new StringBuffer();
        for (MonitorEntity m : ruleList) {
            int ctrl_id = m.getCtrl_id();
            ctrl_ids.append(ctrl_id);
            ctrl_ids.append(",");
        }
        ctrl_ids.append(0);

        List<Map<String, Object>> ctrls = ctrlService.findByCtrl_ids(ctrl_ids.toString());

        //封装成 ctrl_id:ctrl map 方便取值

        if (ctrls == null || ctrls.size() == 0) {
            return;
        }
        for (Map m : ctrls) {
            ctrls_map.put(m.get("ctrl_id"), m);
        }

        //获取top数据 ，
        //匹配数据是否符合规则
        //检查当前开关状态
        //不符合执行规则（控制），符合规则的则关闭开关

        List<Map> typedMos = new ArrayList<>();
        System.out.println(ruleList.size());
        //找出所有监控类别
        for (MonitorEntity mo : ruleList) {
            //按照设备，传感器进行分组
            Map m = new HashMap();
            m.put("deviceId", mo.getMo_deviceId());
            m.put("channel", mo.getMo_channel());
            if (typedMos.indexOf(m) == -1) {
                typedMos.add(m);
            }
        }
        System.out.println(typedMos.size());
        //监控类别分组
        for (Map m : typedMos) {
            List<MonitorEntity> cmos = new ArrayList<>();
            for (MonitorEntity mo : ruleList) {
                if (mo.getMo_deviceId().equals(m.get("deviceId")) &&
                        mo.getMo_channel().equals(m.get("channel"))) {
                    cmos.add(mo);
                }
            }
            m.put("mos", cmos);
        }
        for (Map senser : typedMos) {
            Map params = new HashMap();
            params.put("field", "channel" + senser.get("channel"));
            params.put("table_name", "t_vartriver_" + ((String) senser.get("deviceId")).replace(".", ""));
            Map val_map = gatherService.findTopval(params);//监控数据
            if (val_map == null)
                continue;

            Double c_val = Double.parseDouble((String) val_map.get("val"));

            List<MonitorEntity> mos = (List<MonitorEntity>) senser.get("mos");
            //正序排序
            if (mos == null || mos.size() == 0)
                continue;
            //低于设置温度
            Collections.sort(mos, new Comparator<MonitorEntity>() {
                public int compare(MonitorEntity a, MonitorEntity b) {
                    double v = a.getMo_lower() - b.getMo_lower();
                    return new Double(v).intValue();
                }
            });
            for (MonitorEntity m : mos) {
                Double mo_lower = m.getMo_lower();
                int order_less = m.getOrder_less();
                int duration = m.getDuration();
                Map ctrl = (Map) ctrls_map.get(m.getCtrl_id());
                /**
                 * 1）温度：一种温度低了，比如当温度15（可设）度时，全关上；
                 * 另一种时当温度高于35（可设）度时全开启。120s
                 （2）其他 ：规则为5条：土壤水分传感器数值低于设置值1，开启时间1；
                 土壤水分传感器数值低于设置值n，开启时间n；
                 土壤水分传感器数值低于设置值5，开启时间5。
                 */

                if (c_val >= mo_lower) continue;

                if (order_less == 1) {//开启 duration 秒
                    System.out.println("当前值过【低】，需要开启：" + duration + "秒");
                } else if (order_less == 2) {//关闭 duration 秒
                    System.out.println("当前值过【低】，需要关闭：" + duration + "秒");
                }


            }
            //高于设定温度
            Collections.sort(mos, new Comparator<MonitorEntity>() {
                public int compare(MonitorEntity a, MonitorEntity b) {
                    double v = a.getMo_high() - b.getMo_high();
                    return new Double(v).intValue();
                }
            });

            for (MonitorEntity m : mos) {
                Double mo_high = m.getMo_high();
                int order_more = m.getOrder_more();
                int duration = m.getDuration();
                Map ctrl = (Map) ctrls_map.get(m.getCtrl_id());
                System.out.println("**********" + ctrl.toString());

                if (c_val <= mo_high) continue;

                if (order_more == 1) {//开启 duration 秒

                    System.out.println("当前值过【高】，需要开启：" + duration + "秒");
                } else if (order_more == 2) {//关闭 duration 秒
                    System.out.println("当前值过【高】，需要开启：" + duration + "秒");
                }
            }
        }
    }


    @Scheduled(fixedRate = 1000 * 60)
    public void ctrlDevice() throws UnsupportedEncodingException {
        List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
        if (ruleList == null) {
            System.out.println("暂时没有定时任务执行");
        } else {
            MotorHBM motorHBM = new MotorHBM();
            Calendar calendar = Calendar.getInstance();
            Date time = calendar.getTime();
            SimpleDateFormat dfDateFormat = new SimpleDateFormat("HH:mm:ss");
            String format = dfDateFormat.format(time);
            String substring = format.substring(0, 5);

            System.out.println("当前时间:" + substring);
            for (RuleEntity ruleEntity : ruleList) {
                System.out.println(ruleEntity);
                if (ruleEntity.getExecTime().equals(substring)) {
                    // 调用开关控制方法
                    motorHBM.setDistanceOrDuration(-1); // 常开
                    call_async(ruleEntity, motorHBM);
                }
                if (ruleEntity.getEndTime().equals(substring)) {
                    // 调用开关控制方法
                    motorHBM.setDistanceOrDuration(0); // 关
                    call_async(ruleEntity, motorHBM);
                }
            }
        }

    }

    private void call_async(RuleEntity ruleEntity, MotorHBM motorHBM) {
        String url = ToolUtil.IOTURL + "/controlDev";
        //http://tdb.wlqxz.com/index.php?m=Home&c=Index&a=acceptWriteState
        AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ruleEntity.setAdmin(String.valueOf("IOTDevice"));
        //Greeting greet = new Greeting(100, "abc");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ruleEntity", ruleEntity);
        map.put("hbm", motorHBM);
        HttpEntity entity = new HttpEntity(map, header);
        ListenableFuture<ResponseEntity<String>> result = asyncRestTemplate.postForEntity(url, entity, String.class);
        ListenableFutureCallback<ResponseEntity<IOTResult>> future = new ExListenableFutureCallback<ResponseEntity<IOTResult>>(ruleEntity) {
            @Override
            public void onSuccess(ResponseEntity<IOTResult> result) {
                System.out.println("成功了没" + result.getBody().isSuccess());
                System.out.println(result.getBody() + " " + this.getParam());

                if ("success".equals(result.getBody())) {
                    System.out.println("这个会调么");
                }
/*            	else{
                    try {
						Thread.sleep(1000*5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						 System.out.println("wrong");
						e.printStackTrace();
					}
            		call_async(ruleEntity);
            	}*/
            }

            @Override
            public void onFailure(Throwable t) {
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("wrong");
            }
        };
    }


}
