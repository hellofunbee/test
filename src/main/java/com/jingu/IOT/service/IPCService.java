/**  
*   
* 项目名称：IOT  
* 类名称：IPCService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月5日 下午3:01:46  
* 修改人：jianghu  
* 修改时间：2017年9月5日 下午3:01:46  
* 修改备注： 下午3:01:46
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jingu.IOT.dao.IPCDao;
import com.jingu.IOT.dao.PointDao;
import com.jingu.IOT.dao.SettingDao;
import com.jingu.IOT.entity.HkSdkEx;
import com.jingu.IOT.entity.IPCEntity;
import com.jingu.IOT.entity.IPCPointEntity;
import com.jingu.IOT.entity.IPCProxyEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.VideoShemaBean;
import com.jingu.IOT.requset.IPCPointRequset;
import com.jingu.IOT.requset.IPCRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.PointResult;
import com.jingu.IOT.util.Client;
import com.jingu.IOT.util.ToolUtil;

/**

* @ClassName: IPCService
* @Description: TODO
* @author jianghu
* @date 2017年9月5日 下午3:01:46

*/
@Component
public class IPCService {

	private IPCDao ipcDao;
	private SettingDao settingDao;
	private ToolUtil toolUtil;
	private PointDao pointDao;

	@Autowired
	public IPCService(IPCDao ipcDao,SettingDao settingDao,ToolUtil toolUtil,PointDao pointDao) {
		this.ipcDao = ipcDao;
		this.settingDao = settingDao;
		this.toolUtil =toolUtil;
		this.pointDao =pointDao;
	}
	
	public int addIPCProxy(IPCProxyEntity ipcEntity){
		return ipcDao.addIPCProxy(ipcEntity);
	}
	
	public int updateIPCProxy(IPCProxyEntity ipcEntity){
		return ipcDao.updateIPCProxy(ipcEntity);
	}
	public int updateIPCProxyByMapingDeviceId(IPCProxyEntity pe){
		return ipcDao.updateIPCProxyByMapingDeviceId(pe);
	}
	
	public Map<String,Object> getIPCProxy(IPCProxyEntity ipcEntity){
		List<Map<String,Object>> listIPCProxy = ipcDao.listIPCProxy(ipcEntity);
		if(listIPCProxy==null || listIPCProxy.isEmpty()){
			return null;
		}
		return listIPCProxy.get(0);
	}
	
	// 代理列表
	public Map<String, Object> getCtrlProxy(IPCProxyEntity pe){
		List<Map<String,Object>> ctrlProxy = ipcDao.getCtrlProxy(pe);
		if(ctrlProxy==null || ctrlProxy.isEmpty()){
			return null;
		}
		return ctrlProxy.get(0);
				
	}
	
//	public Map<String, Object> getVideoProxy(IPCProxyEntity pe){
//		List<Map<String,Object>> ctrlProxy = ipcDao.getVideoProxy(pe);
//		if(ctrlProxy==null || ctrlProxy.isEmpty()){
//			return null;
//		}
//		return ctrlProxy.get(0);
//				
//	}
	
	// 代理列表
	public List<Map<String, Object>> listProxy(IPCProxyEntity pe){
		return ipcDao.listIPCProxy(pe);
				
	}
	// 获得代理
	public Map<String,Object> getProxy(IPCProxyEntity pe){
		List<Map<String,Object>> listIPCProxy = ipcDao.listIPCProxy(pe);
		if(listIPCProxy==null || listIPCProxy.size()<1){
			return null;
		}
		return listIPCProxy.get(0);		
	}
	
	// 查询代理
	public List<Map<String, Object>> listIPCProxy(IPCProxyEntity pe){
		return ipcDao.listIPCProxy(pe);
	}
	
	@Transactional(value = "primaryTransactionManager")
	public PointResult addIPC(IPCRequest IPCRequest){
		
		IPCProxyEntity ipc = IPCRequest.getIpc();
		String intVal2 = toolUtil.getIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp());
		if(intVal2 ==null || intVal2.equals("0")){
			toolUtil.setIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp(), 9000);
		}
		
		// 尽量封装
		String intVal = toolUtil.getIntVal(ToolUtil.PROXY);
		if(intVal ==null || intVal.equals("0")){
			int maxProxyId = ipcDao.getMaxProxyId();
			toolUtil.setIntVal(ToolUtil.PROXY, maxProxyId);
		}
		// 检查是否能代理上
		Long port1 = toolUtil.incIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp());
		IPCRequest.getIpc().setS_proxy(String.valueOf(port1));
		IPCRequest.getIpc().setS_hostport(8000);
		ipc.setS_host(IPCRequest.getS_ip());
		ipc.setS_pwr(1);
//		IOTResult testProxy = testProxy(IPCRequest);
//		if(!testProxy.isSuccess()){
//			toolUtil.decIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp());
//			return 0;
//		}
		// 查找代理id号和9000开始的端口号
		Long proxyid1 = toolUtil.incIntVal(ToolUtil.PROXY);
		ipc.setId(proxyid1.intValue());
		ipc.setS_hostport(8000);
		ipc.setS_proxy(String.valueOf(port1));
		IPCRequest.setIpcProxyId(proxyid1.intValue());
		ipc.setMapingDeviceId(IPCRequest.getMapingDeviceId());
		ipc.setType(2);
		ipc.setS_pwr(IPCRequest.getS_power());
		ipc.setS_pwrval(1);
		ipc.setS_timeout(String.valueOf(180));
		ipc.setUsername(IPCRequest.getS_username());
		ipc.setPassword(IPCRequest.getS_password());
		
		int addProxy1 = ipcDao.addIPCProxy(ipc);
		Long proxyid2 = toolUtil.incIntVal(ToolUtil.PROXY);
		Long port2 = toolUtil.incIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp());
		ipc.setId(proxyid2.intValue());
		ipc.setS_proxy(String.valueOf(port2));
		ipc.setS_hostport(80);
		ipc.setType(1);
		
		IPCRequest.setIpcProxyId(proxyid2.intValue());
		ipc.setMapingDeviceId(IPCRequest.getMapingDeviceId());
		PointEntity pointEntity = IPCRequest.getPointEntity();
		pointEntity.setTp_type(4);
		String maxId = toolUtil.getMaxId(ToolUtil.TREEID);
		if(maxId ==null){
			int listMaxId = pointDao.listMaxId();
			toolUtil.setMaxId(ToolUtil.TREEID, listMaxId);
		}
		Long maxIdInc = toolUtil.MaxIdInc(ToolUtil.TREEID);
		pointEntity.setDeviceId(IPCRequest.getMapingDeviceId());
		pointEntity.setTp_id(maxIdInc.intValue());
		pointEntity.setTp_name(IPCRequest.getPointEntity().getTp_name());
		IPCRequest.setName(IPCRequest.getPointEntity().getTp_name());
		pointEntity.setTp_type(4);
		IPCRequest.setId(maxIdInc.intValue());
		int addIPC = ipcDao.addIPC(IPCRequest);
		int addProxy2 = ipcDao.addIPCProxy(ipc);
		pointEntity.setRole("");
		int addPoint = pointDao.addPoint(pointEntity);
		
		if(addIPC >0 && addProxy1>0&& addProxy2>0 && addPoint >0){
			return new PointResult(true,pointEntity);
		}
		return new PointResult(false,pointEntity);
	}
	
	
	
	public int updateIPC(IPCEntity ipcEntity){
		return ipcDao.updateIPC(ipcEntity);
	}
	public int updateIPC2(Integer id,Integer status){
		IPCEntity ipcEntity = new IPCEntity();
		ipcEntity.setId(id);
		ipcEntity.setStatus(status);
		return ipcDao.updateIPC(ipcEntity);
	}
	
	public int updateIPC2(Map<String, String> map){
		return ipcDao.updateIPC2(map);
	}
	@Transactional(value = "primaryTransactionManager")
	public int deleteIPC(IPCRequest ipcEntity){
		PointEntity pointEntity = new PointEntity();
		pointEntity.setTp_id(ipcEntity.getId());
		int deletePoint = pointDao.deletePoint(pointEntity);
		int deleteIPC = ipcDao.deleteIPC(ipcEntity);
		if(deletePoint >0 && deleteIPC >0){
			return 1;
		}
		return 0;
	}
	
	
	// 查找vr1000下的ipc
	public List<Map<String, Object>> listIPC(IPCEntity ipcEntity){
		return ipcDao.listIPC(ipcEntity);
	}
	
	
	public  Map<String, Object> getIPCById(IPCEntity ipcEntity){
		return ipcDao.getIPCById(ipcEntity);
	}
	
	
	public IPCEntity  getIPC(IPCEntity ipcEntity){
		List<IPCEntity> ipc = ipcDao.getIPC(ipcEntity);
		if(ipc ==null || ipc.isEmpty()){
			return null;
		}
		return ipc.get(0);
	}
	
	// 添加监视点
	public int addIPCPoint(IPCPointEntity ipce){
		return ipcDao.addIPCPoint(ipce);
	}
	
	public int updateIPCPoint(IPCPointEntity ipce){
		return ipcDao.updateIPCPoint(ipce);
	}
	
//	public int updateAppIPCPoint(IPCPointEntity ipce){
//		return ipcDao.updateAppIPCPoint(ipce);
//	}
	
	// 删除监视点
	public int deleteIPCPoint(IPCPointEntity ipce){
		return ipcDao.deleteIPCPoint(ipce);
	}
	
	// 删除监视点
	public Map<String, Object> getIPCPoint(IPCPointEntity ipce){
		return ipcDao.getIPCPoint(ipce);
	}
	
	// 查找监视点
	public List<Map<String, Object>> listIPCPoint(IPCPointEntity ipce){
		return ipcDao.listIPCPoint(ipce);
	}
	
	// 核查监视点
	public List<Map<String, Object>> ckIPCPoint(IPCPointEntity ipce){
		return ipcDao.ckIPCPoint(ipce);
	}
	
	// 查看监视点图片 
	public List<Map<String, Object>> listIPCPointIMG(IPCPointEntity ipce){
		return ipcDao.listIPCPointIMG(ipce);
	}
	
	private IOTResult testProxy(IPCRequest ipcr){
		IPCProxyEntity ipc = ipcr.getIpc();
		//String config = "s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;";
		String config = "s_host:"+ipc.getS_host()+";s_rport:"+ipc.getS_hostport()+";s_lport:"+ipc.getS_proxy()+";s_pwr:"+ipc.getS_pwr()+";s_pwrval:"+ipc.getS_pwrval()+";s_timeout:120;";
					  //"s_host:192.168.0.234;s_rport:8000;s_lport:9090;s_pwr:1;s_pwrval:0;s_timeout:120;"
					  //"s_host:192.168.0.234;s_rport:8000;s_lport:9099;s_pwr:1;s_pwrval:0;s_timeout:60;";
					  //"s_host:192.168.0.234;s_rport:80;s_lport:9090;s_pwr:1;s_pwrval:0;s_timeout:120;"
//		String config = "s_host:192.168.0.234;s_rport:80;s_lport:9099;s_pwr:1;s_pwrval:0;s_timeout:60;";
		String setIpcProxyEx1 = Client.setIpcProxyEx1("add", config, ipc.getDeviceId(), ipcr.getPointEntity().getIp(), ipcr.getPointEntity().getPort());
//		"admin", "12345", "192.168.0.168", "9001"
//		String config = "s_host:192.168.0.234;s_rport:80;s_lport:9000;s_pwr:1;s_pwrval:0;s_timeout:86400;";
//		String setIpcProxyEx1 = Client.setIpcProxyEx1("add", config, deviceId, ip, port);
//		System.out.println(setIpcProxyEx1);
//		System.out.println(ipcr.getS_username());
//		System.out.println(ipcr.getS_password());
		VideoShemaBean ipcAbility = HkSdkEx.getIpcAbility(ipcr.getS_username(), ipcr.getS_password(), ipcr.getPointEntity().getIp(), String.valueOf(ipc.getS_proxy()));
		if(ipcAbility ==null){
			return new IOTResult(false,"链接不到摄像头",null,11);
		}
		
		return new IOTResult(true,"代理设置成功",null,0);
	}
//	public static void main(String[] args) {
//		Client.setIpcProxyEx(type, config, deviceId)
//	}

	public int listIPCPointIMGCount(IPCPointRequset ipcPointRequset) {
		// TODO Auto-generated method stub
		return ipcDao.listIPCPointIMGCount(ipcPointRequset);
	}

	/**
	 * 2017年11月19日
	 * jianghu
	 * @param ipcRequest
	 * TODO
	 */
	@Transactional(value = "primaryTransactionManager")
	public PointResult addExitstIPC(IPCRequest ipcRequest) {
		
		IPCProxyEntity ipc = ipcRequest.getIpc();
//		String intVal2 = toolUtil.getIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp());
//		if(intVal2 ==null || intVal2.equals("0")){
//			toolUtil.setIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp(), 9000);
//		}
		
		// 尽量封装
//		String intVal = toolUtil.getIntVal(ToolUtil.PROXY);
//		if(intVal ==null || intVal.equals("0")){
//			toolUtil.setIntVal(ToolUtil.PROXY, 1);
//		}
		// 检查是否能代理上
		ipcRequest.getIpc().setS_proxy("");
		ipcRequest.getIpc().setS_hostport(8000);
		ipc.setS_host(ipcRequest.getS_ip());
		ipc.setS_pwr(1);
//		IOTResult testProxy = testProxy(IPCRequest);
//		if(!testProxy.isSuccess()){
//			toolUtil.decIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp());
//			return 0;
//		}
		// 查找代理id号和9000开始的端口号
		String intVal = toolUtil.getIntVal(ToolUtil.PROXY);
		if(intVal ==null || intVal.equals("0")){
			int maxProxyId = ipcDao.getMaxProxyId();
			toolUtil.setIntVal(ToolUtil.PROXY, maxProxyId);
		}
		Long proxyid1 = toolUtil.incIntVal(ToolUtil.PROXY);
		ipc.setId(proxyid1.intValue());
		ipc.setS_hostport(8000);
		ipc.setS_proxy("");
		ipcRequest.setIpcProxyId(proxyid1.intValue());
		ipc.setMapingDeviceId(ipcRequest.getMapingDeviceId());
		ipc.setType(2);
		ipc.setS_pwr(ipcRequest.getS_power());
		ipc.setS_pwrval(1);
		ipc.setS_timeout(String.valueOf(180));
		ipc.setUsername(ipcRequest.getS_username());
		ipc.setPassword(ipcRequest.getS_password());
		ipc.setDeviceId(ipcRequest.getDeviceId());
		int addProxy1 = ipcDao.addIPCProxy(ipc);
		Long proxyid2 = toolUtil.incIntVal(ToolUtil.PROXY);
//		Long port2 = toolUtil.incIntVal(ToolUtil.PORT+IPCRequest.getPointEntity().getIp());
		ipc.setId(proxyid2.intValue());
		ipc.setS_proxy("");
		ipc.setS_hostport(80);
		ipc.setType(1);
		
		ipcRequest.setIpcProxyId(proxyid2.intValue());
		ipc.setMapingDeviceId(ipcRequest.getMapingDeviceId());
		PointEntity pointEntity = ipcRequest.getPointEntity();
		pointEntity.setTp_type(4);
		String maxId = toolUtil.getMaxId(ToolUtil.TREEID);
		if(maxId ==null){
			int listMaxId = pointDao.listMaxId();
			toolUtil.setMaxId(ToolUtil.TREEID, listMaxId);
		}
		Long maxIdInc = toolUtil.MaxIdInc(ToolUtil.TREEID);
		pointEntity.setDeviceId(ipcRequest.getMapingDeviceId());
		pointEntity.setTp_id(maxIdInc.intValue());
		pointEntity.setTp_name(ipcRequest.getPointEntity().getTp_name());
		ipcRequest.setName(ipcRequest.getPointEntity().getTp_name());
		pointEntity.setTp_type(4);
		pointEntity.setRole("");
		ipcRequest.setId(maxIdInc.intValue());
		int addIPC = ipcDao.addIPC(ipcRequest);
		int addProxy2 = ipcDao.addIPCProxy(ipc);
		int addPoint = pointDao.addPoint(pointEntity);
		
		if(addIPC >0 && addProxy1>0&& addProxy2>0 && addPoint >0){
			return new PointResult(true,pointEntity);
		}
		return new PointResult(false,pointEntity);
	}

	/**
	 * 2017年11月19日
	 * jianghu
	 * @param ipcMonitor2
	 * TODO
	 */
	public int addIPCPointList(List<IPCPointEntity> ipce){
		return ipcDao.addIPCPointList(ipce);
	}

	/**
	 * 2017年12月23日
	 * jianghu
	 * @param deviceId
	 * TODO
	 */
	public int getMaxMonitorId(String deviceId) {
		// TODO Auto-generated method stub
		return ipcDao.getMaxMonitorId(deviceId);
		
	}

	/**
	 * 2017年12月23日
	 * jianghu
	 * @param deviceId
	 * @param monitorId
	 * TODO
	 */
	public int getIPCPointId(String deviceId, int monitorId) {
		// TODO Auto-generated method stub
		return ipcDao.getIPCPointId(deviceId,monitorId);
		
	}

	/**
	 * 2017年12月23日
	 * jianghu
	 * @param ipcPointRequset
	 * TODO
	 */
	public List<Map<String, Object>> getAppIPCPoint(IPCPointRequset ipcPointRequset) {
		// TODO Auto-generated method stub
		return ipcDao.getAppIPCPoint(ipcPointRequset);
	}
	
	public Map<String, Object> listLastIPCPointImg(String deviceId) {
		try {
			Map<String, Object> imgMap = ipcDao.listLastIPCPoint(deviceId);
			return imgMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
		
}
