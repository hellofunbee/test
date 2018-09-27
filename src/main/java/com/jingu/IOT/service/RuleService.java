/**  
*   
* 项目名称：IOT  
* 类名称：RuleService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月24日 下午4:55:27  
* 修改人：jianghu  
* 修改时间：2017年10月24日 下午4:55:27  
* 修改备注： 下午4:55:27
* @version   
*   
*/ 
package com.jingu.IOT.service;

import com.jingu.IOT.dao.RuleDao;
import com.jingu.IOT.entity.ExListenableFutureCallback;
import com.jingu.IOT.entity.MonitorEntity;
import com.jingu.IOT.entity.MotorHBM;
import com.jingu.IOT.entity.RuleEntity;
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
import java.util.stream.Collectors;

/**

* @ClassName: RuleService
* @Description: TODO
* @author jianghu
* @date 2017年10月24日 下午4:55:27

*/
@Component
public class RuleService {

	private RuleDao ruleDao;
	private ToolUtil toolUtil;

	@Autowired
	public RuleService(RuleDao ruleDao,ToolUtil toolUtil) {
		this.ruleDao = ruleDao;
		this.toolUtil = toolUtil;
	}
	public int addRule(RuleEntity re){


		return ruleDao.addRule(re);
	}
	public int updateRuleList(RuleEntity re) throws UnsupportedEncodingException{
		List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
		Iterator<RuleEntity> iterator = ruleList.iterator();
		while(iterator.hasNext()){
			RuleEntity next = iterator.next();
			if(next.getR_id()==re.getR_id()){
				iterator.remove();
			}
		}
		List<RuleEntity> ruleList2 = toolUtil.getRuleList(ToolUtil.RULE);
		System.out.println(ruleList2);
		ruleList.add(re);
		toolUtil.setRuleList(ToolUtil.RULE, ruleList);
		return ruleDao.updateRule(re);
	}
	public int deleteRule(RuleEntity re){
		return ruleDao.deleteRule(re);
	}
	public List<RuleEntity> listRule(RuleEntity re){
		return ruleDao.listRule(re);
	}
	
	public int updateRule(RuleEntity re){
		return ruleDao.updateRule(re);
	}
	
	public int deleteRuleList(RuleEntity re) throws UnsupportedEncodingException{
		List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
		if(ruleList==null || ruleList.isEmpty()){
			return 0;
		}
		List<RuleEntity> collect = ruleList.stream().filter(x -> x.getCtrl_id()!=re.getCtrl_id()).collect(Collectors.toList());
		System.out.println(collect);
		toolUtil.setRuleList(ToolUtil.RULE, collect);
		return 1;
	}
	
	@Scheduled(fixedRate=1000 * 60)
	public void ctrlDevice() throws UnsupportedEncodingException{
		List<RuleEntity> ruleList = toolUtil.getRuleList(ToolUtil.RULE);
		if(ruleList ==null){
			System.out.println("暂时没有定时任务执行");
		}else{
			MotorHBM motorHBM = new MotorHBM();
			Calendar calendar = Calendar.getInstance();
			Date time = calendar.getTime();
			SimpleDateFormat dfDateFormat = new SimpleDateFormat("HH:mm:ss");
			String format = dfDateFormat.format(time);
			String substring = format.substring(0, 5);
//			RuleEntity [r_id=12, r_name=12121, r_deviceId=null, switchGroupId=null, switchId=null, ctrlType=null, cycleDay=1, execTime=14:00:00, beginTime=2017-10-10 14:00:00, endTime=2017-11-20 14:00:00, targetDeviceId=null, targetFieldName=null, ruleEnable=2, lastExecDataTime=null, ctrl_id=5, maxValue=0.0, minValue=0.0, duration=60.0, coefficient=0.0, getCtrl_id()=5, getR_id()=12, getR_name()=12121, getR_deviceId()=null, getSwitchGroupId()=null, getSwitchId()=null, getCtrlType()=null, getCycleDay()=1, getExecTime()=14:00:00, getBeginTime()=2017-10-10 14:00:00, getEndTime()=2017-11-20 14:00:00, getTargetDeviceId()=null, getTargetFieldName()=null, getRuleEnable()=2, getLastExecDataTime()=null, getMaxValue()=0.0, getMinValue()=0.0, getDuration()=60.0, getCoefficient()=0.0, getClass()=class com.jingu.IOT.entity.RuleEntity, hashCode()=778754110, toString()=com.jingu.IOT.entity.RuleEntity@2e6ad83e]
//			RuleEntity [r_id=13, r_name=测试, r_deviceId=10.00.21.74, switchGroupId=null, switchId=null, ctrlType=null, cycleDay=1, execTime=14:00:00, beginTime=2017-10-10 14:00:00, endTime=2017-12-10 14:00:00, targetDeviceId=null, targetFieldName=null, ruleEnable=1, lastExecDataTime=null, ctrl_id=5, maxValue=0.0, minValue=0.0, duration=60.0, coefficient=0.0, getCtrl_id()=5, getR_id()=13, getR_name()=测试, getR_deviceId()=10.00.21.74, getSwitchGroupId()=null, getSwitchId()=null, getCtrlType()=null, getCycleDay()=1, getExecTime()=14:00:00, getBeginTime()=2017-10-10 14:00:00, getEndTime()=2017-12-10 14:00:00, getTargetDeviceId()=null, getTargetFieldName()=null, getRuleEnable()=1, getLastExecDataTime()=null, getMaxValue()=0.0, getMinValue()=0.0, getDuration()=60.0, getCoefficient()=0.0, getClass()=class com.jingu.IOT.entity.RuleEntity, hashCode()=252737087, toString()=com.jingu.IOT.entity.RuleEntity@f10763f]
			
			System.out.println("当前时间:"+substring);
			for (RuleEntity ruleEntity : ruleList) {
				System.out.println(ruleEntity);
				if(ruleEntity.getExecTime().equals(substring)){
					// 调用开关控制方法
					motorHBM.setDistanceOrDuration(-1); // 常开
					call_async(ruleEntity,motorHBM);
				}
				if(ruleEntity.getEndTime().equals(substring)){
					// 调用开关控制方法
					motorHBM.setDistanceOrDuration(0); // 关
					call_async(ruleEntity,motorHBM);
				}
			}
		}
		
	}
    private void call_async(RuleEntity ruleEntity,MotorHBM motorHBM){
    	String url=ToolUtil.IOTURL+"/controlDev";
    	//http://tdb.wlqxz.com/index.php?m=Home&c=Index&a=acceptWriteState
        AsyncRestTemplate asyncRestTemplate=new AsyncRestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        ruleEntity.setAdmin(String.valueOf("IOTDevice"));
        //Greeting greet = new Greeting(100, "abc");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("ruleEntity", ruleEntity);
        map.put("hbm", motorHBM);
        HttpEntity entity = new HttpEntity(map, header);
        ListenableFuture<ResponseEntity<String>> result = asyncRestTemplate.postForEntity(url, entity, String.class);
        ListenableFutureCallback<ResponseEntity<IOTResult>> future = new ExListenableFutureCallback<ResponseEntity<IOTResult>>(ruleEntity) {
            @Override
            public void onSuccess(ResponseEntity<IOTResult> result) {
            	System.out.println("成功了没"+result.getBody().isSuccess());
            	System.out.println(result.getBody()+" "+this.getParam());
            	
            	if("success".equals(result.getBody())){
            		System.out.println("这个会调么");
            	}	
//            	}else{
//            		try {
//						Thread.sleep(1000*5);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						 System.out.println("wrong");
//						e.printStackTrace();
//					}
//            		call_async(ruleEntity);
//            	}
            }

            @Override
            public void onFailure(Throwable t) {
            	try {
					Thread.sleep(1000*5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//            	call_async(state);
            	
                System.out.println("wrong");
            }
        };
//        result.addCallback(future);
    }
//    
//    @RequestMapping(value="/asyncCall", method=RequestMethod.POST)
//    public @ResponseBody RoadResult asyncCall(@RequestBody Map<String,String> map){
//    	System.out.println(map);
//    	if(map!=null){
//    		return new RoadResult(true, "添加成功", null, 0);
//    	}
//    	return new RoadResult(false, "添加失败", null, 0);
//    }
	
	
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Date time = calendar.getTime();
		SimpleDateFormat dfDateFormat = new SimpleDateFormat("HH:mm:ss");
		String format = dfDateFormat.format(time);
		String substring = format.substring(0, 5);
		System.out.println(substring);
	}

	public int deleteRuleIds(String ids) {
		// TODO Auto-generated method stub
		return ruleDao.deleteRuleIds(ids);
	}
	public int addMonitor(MonitorEntity mo) {
		return ruleDao.addMonitor(mo);
	}
	public int deleteMonitor(MonitorEntity mo) {
		return ruleDao.deleteMonitor(mo);
	}

	public int deleteByCtrlId(MonitorEntity mo) {
		return ruleDao.deleteByCtrlId(mo);
	}
	public int updateMonitor(MonitorEntity mo) {
		return ruleDao.updateMonitor(mo);
	}
	public List<MonitorEntity> listMonitor(MonitorEntity mo) {
		return ruleDao.listMonitor(mo);
	}
	/**
	 * 2017年11月26日
	 * jianghu
	 * @param moRequest
	 * @return
	 * TODO
	 * @throws UnsupportedEncodingException 
	 */
	public int deleteMonitorList(MonitorEntity moRequest) throws UnsupportedEncodingException {
		List<MonitorEntity> monitorList = toolUtil.getMonitorList(ToolUtil.MONITOR+moRequest.getMo_deviceId());
		if(monitorList==null || monitorList.isEmpty()){
			return 0;
		}
		List<MonitorEntity> collect = monitorList.stream().filter(x -> x.getCtrl_id()!=moRequest.getCtrl_id()).collect(Collectors.toList());
		System.out.println(collect);
		toolUtil.setMonitorList(ToolUtil.MONITOR, collect);
		return 1;
	}
}
