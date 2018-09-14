/**  
*   
* 项目名称：IOT  
* 类名称：RunEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月20日 下午5:14:48  
* 修改人：jianghu  
* 修改时间：2017年10月20日 下午5:14:48  
* 修改备注： 下午5:14:48
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jingu.IOT.service.RuleService;

/**

* @ClassName: RunEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月20日 下午5:14:48

*/
public class RunEntity implements Runnable {

	private RuleService ruleService;
	private RuleEntity ruleEntity;
	
	
	@Autowired
	public RunEntity(RuleService ruleService) {
		this.ruleService = ruleService;
		if(ruleEntity ==null){
			ruleEntity = new RuleEntity();
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("智能控制运行中.....");
		
			List<RuleEntity> listRule = ruleService.listRule(ruleEntity);
			MotorHBM hbm = new MotorHBM();
			for (RuleEntity ruleEntity : listRule) {
				hbm.setCtrlType(Integer.parseInt(ruleEntity.getCtrlType()));
				hbm.setRaiseGroupId((ruleEntity.getSwitchGroupId()));
				hbm.setRaiseSwitchId((ruleEntity.getSwitchId()));
				hbm.setDistanceOrDuration((int) ruleEntity.getDuration());
//				boolean motorsCtrl2 = Client.motorsCtrl2(ruleEntity.getIp(), point.getPort(), point.getDeviceId(), hbm.toByteCmd(), (byte)0x17);
//				System.out.println(motorsCtrl2);
//				if(motorsCtrl2){
//					System.out.println("命令发送成功");
//					//return new IOTResult(true,"命令发送成功",null,0);
//				}
//				System.out.println("命令发送失败");
				//return new IOTResult(false,"命令发送失败",null,10);
			}
			
//			hbm.setCtrlType(Integer.parseInt(controlSetting.get("ctrl_type").toString()));
//			hbm.setRaiseGroupId((controlSetting.get("ctrl_raise_groupId").toString()));
//			hbm.setRaiseSwitchId((controlSetting.get("ctrl_raise_switchId").toString()));
//			hbm.setSkinGroupId((controlSetting.get("ctrl_down_groupId").toString()));
//			hbm.setSkinSwitchId((controlSetting.get("ctrl_down_switchId").toString()));
//			hbm.setPosSensorCH(Integer.parseInt(controlSetting.get("ctrl_channel").toString()));
//			@CrossOrigin
//			@RequestMapping(value = "/controlDev", method = RequestMethod.POST)
//			public IOTResult ControlDev(@RequestBody ControlRequset cr){
//				if(cr.getCksid()==null || cr.getCksid().trim().length()<1||cr.getCkuid()==null||cr.getCkuid().trim().length()<1){
//					return  new IOTResult(false,"信息不规范",null,1);
//				}
//				// 注册登陆按照什么来????
//				String check = toolUtil.getCheck(ToolUtil.IOT+cr.getCkuid());
//				if(check ==null ||!cr.getCksid().equals(check)){
//					return  new IOTResult(false,"登陆失效",null,2);
//				}
//				// 检测是否有这个点
//				long uid = toolUtil.getbase_uidSid(cr.getCkuid(), cr.getCksid());
//				PointEntity pointEntity = cr.getPointEntity();
//				pointEntity.setDeviceId(cr.getCtrl_deviceId());
//				pointEntity.setUid(uid);
//				pointEntity.setRole(String.valueOf(uid));
//				PointEntity point = pointService.getPoint(pointEntity);
//				if(point ==null ){
//					return new IOTResult(false,"节点不存在",null,3);
//				}
//				
//				Map<String, Object> controlSetting = settingService.getControlSetting(cr);
//				if(controlSetting ==null || controlSetting.isEmpty()){
//					return new IOTResult(false,"控制设备不存在",null,0);
//				}
////		   	 b=new byte[12];
////			     b[0]=(byte)this.getCtrlType();
////		   	 b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
////			     b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
////			     b[3]=(byte)Integer.parseInt(this.getSkinGroupId());
////			     b[4]=(byte)Integer.parseInt(this.getSkinSwitchId());
////			     b[5]=(byte)this.getDirection();
////			     b[6]=(byte)this.getDistanceOrDuration();
////			     b[7]=(byte)this.getPosSensorCH();
////			     byte[] maxB = PublicMethod.int2bytes(Integer.parseInt(this.getMaxValue()));
////			     System.arraycopy(maxB, 0, b, 8, 2);
////			     byte[] minB = PublicMethod.int2bytes(Integer.parseInt(this.getMinValue()));
////			     System.arraycopy(minB, 0, b, 10, 2);
////		    }
////		    if((byte)this.getCtrlType()==0x2){
////		   	 b=new byte[9];
////		   	 b[0]=(byte)this.getCtrlType();
////		   	 b[1]=(byte)Integer.parseInt(this.getRaiseGroupId());
////			     b[2]=(byte)Integer.parseInt(this.getRaiseSwitchId());
////			     byte[] durationByte=PublicMethod.int4bytes(this.getDistanceOrDuration());
////			     System.arraycopy(durationByte, 0, b, 3, 4);
////		    }
////		    return b;
//		//   }
//				MotorHBM hbm = cr.getHbm();
//				hbm.setCtrlType(Integer.parseInt(controlSetting.get("ctrl_type").toString()));
//				hbm.setRaiseGroupId((controlSetting.get("ctrl_raise_groupId").toString()));
//				hbm.setRaiseSwitchId((controlSetting.get("ctrl_raise_switchId").toString()));
//				hbm.setSkinGroupId((controlSetting.get("ctrl_down_groupId").toString()));
//				hbm.setSkinSwitchId((controlSetting.get("ctrl_down_switchId").toString()));
//				hbm.setPosSensorCH(Integer.parseInt(controlSetting.get("ctrl_channel").toString()));
//				boolean motorsCtrl2 = Client.motorsCtrl2(point.getIp(), point.getPort(), point.getDeviceId(), hbm.toByteCmd(), (byte)0x17);
//				System.out.println(motorsCtrl2);
//				if(motorsCtrl2){
//					return new IOTResult(true,"命令发送成功",null,0);
//				}
//				return new IOTResult(false,"命令发送失败",null,10);
			
			
			
			
		}
	}

}
