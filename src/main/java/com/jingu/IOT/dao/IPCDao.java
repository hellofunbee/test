/**  
*   
* 项目名称：IOT  
* 类名称：IPCDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月5日 上午11:10:00  
* 修改人：jianghu  
* 修改时间：2017年9月5日 上午11:10:00  
* 修改备注： 上午11:10:00
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jingu.IOT.entity.IPCEntity;
import com.jingu.IOT.entity.IPCPointEntity;
import com.jingu.IOT.entity.IPCProxyEntity;
import com.jingu.IOT.requset.IPCPointRequset;

/**

* @ClassName: IPCDao
* @Description: TODO
* @author jianghu
* @date 2017年9月5日 上午11:10:00

*/
@Component
public class IPCDao {
	
	@Resource
	@Qualifier("primaryJdbcTemplate")
	private  JdbcTemplate jdbcTemplate;

//	@Autowired
//	public IPCDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	public int addIPC(IPCEntity ipcEntity){
		String sql =" insert into t_vartriver_ipc (id,s_nod,deviceid,s_ip,s_port,s_username,s_password,s_stream,s_online,orderNo,mapingDeviceId,ipcProxyId,stauts,s_power,ipcCtrlProxyId,streamType,name,monitorid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		return jdbcTemplate.update(sql,ipcEntity.getId(),ipcEntity.getS_nod(),ipcEntity.getDeviceId(),ipcEntity.getS_ip(),ipcEntity.getS_port(),ipcEntity.getS_username(),ipcEntity.getS_password(),ipcEntity.getS_stream(),ipcEntity.getS_online(),ipcEntity.getOrderNo(),ipcEntity.getMapingDeviceId(),ipcEntity.getIpcProxyId(),ipcEntity.getStatus(),ipcEntity.getS_power(),ipcEntity.getIpcCtrlProxyId(),ipcEntity.getStreamType(),ipcEntity.getName(),ipcEntity.getMonitorid());
	}


	
	
//	// 插入代理信息
//	public int addIPCProxy(IPCProxyEntity ipcEntity){
//		String sql ="insert into t_vartriver_ipcproxy(id,deviceId,s_host,s_hostport,s_proxy) value(?,?,?,?,?) ";
//		return jdbcTemplate.update(sql,ipcEntity.getId(),ipcEntity.getDeviceId(),ipcEntity.getS_host(),ipcEntity.getS_hostport(),ipcEntity.getS_proxy());
//	}
//	public int updateIPCProxy(IPCProxyEntity ipcEntity){
//		String sql ="update t_vartriver_ipcproxy set id = ?";
//		List<Object> list = new ArrayList<>();
//		list.add(ipcEntity.getId());
//		if(ipcEntity!=null){
//			if(ipcEntity.getS_host()!=null && ipcEntity.getS_host().trim().length()>0){
//				sql += " , s_host =? ";
//				list.add(ipcEntity.getS_host());
//			}
//			if(ipcEntity.getDeviceId()!=null && ipcEntity.getDeviceId().trim().length()>0){
//				sql += " , deviceId =? ";
//				list.add(ipcEntity.getDeviceId());
//			}
//			if(ipcEntity.getS_hostport()>0){
//				sql += " , s_hostport =? ";
//				list.add(ipcEntity.getS_hostport());
//			}
//			if(ipcEntity.getS_proxy()!=null &&ipcEntity.getS_proxy().trim().length()>0){
//				sql += " , s_proxy =? ";
//				list.add(ipcEntity.getS_proxy());
//			}
//
//		}
//		return jdbcTemplate.update(sql,ipcEntity.getId(),ipcEntity.getDeviceId(),ipcEntity.getS_host(),ipcEntity.getS_hostport(),ipcEntity.getS_proxy());
//	}
	
	
	
	// 添加代理
	public int addIPCProxy(IPCProxyEntity pe){
		// s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;
		String sql ="insert into t_vartriver_ipcproxy (id,deviceId,mapingDeviceId,s_host,s_hostport,s_proxy,s_proxyport,s_pwr,s_pwrval,s_timeout,stauts,username,password,type) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,pe.getId(),pe.getDeviceId(),pe.getMapingDeviceId(),pe.getS_host(),pe.getS_hostport(),pe.getS_proxy(),pe.getS_proxyport(),pe.getS_pwr(),pe.getS_pwrval(),pe.getS_timeout(),pe.getStatus(),pe.getUsername(),pe.getPassword(),pe.getType());
		
	}
	
	// 修改代理
	public int updateIPCProxy(IPCProxyEntity pe){
		// s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;
		String sql ="update t_vartriver_ipcproxy set id =? ";
		List<Object> list = new ArrayList<>();
		list.add(pe.getId());
		if(pe.getS_host()!=null && pe.getS_host().trim().length()>0){
			sql +=" , s_host =?";
			list.add(pe.getS_host());
		}
		if(pe.getDeviceId()!=null && pe.getDeviceId().trim().length()>0){
			sql +=" , deviceId =?";
			list.add(pe.getDeviceId());
		}
		if(pe.getS_proxy()!=null && pe.getS_proxy().trim().length()>0){
			sql +=" , s_proxy =?";
			list.add(pe.getS_proxy());
		}
		if(pe.getS_timeout()!=null && pe.getS_timeout().trim().length()>0){
			sql +=" , s_timeout =?";
			list.add(pe.getS_timeout());
		}
		if(pe.getS_hostport() >0){
			sql +=" , s_hostport =?";
			list.add(pe.getS_hostport());
		}
		if(pe.getS_proxyport()>0){
			sql +=" , s_proxyport =?";
			list.add(pe.getS_proxyport());
		}
		if(pe.getS_pwr()>0){
			sql +=" , s_pwr =?";
			list.add(pe.getS_pwr());
		}
		if(pe.getS_pwrval()>0){
			sql +=" , s_pwrval =?";
			list.add(pe.getS_pwrval());
		}
		if(pe.getStatus()>0){
			sql +=" , status =?";
			list.add(pe.getStatus());
		}
		sql +=" where id =?";
		list.add(pe.getId());
		return jdbcTemplate.update(sql,list.toArray());
	}
	// 修改代理
	public int updateIPCProxyByMapingDeviceId(IPCProxyEntity pe){
		// s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;
		String sql ="update t_vartriver_ipcproxy set mapingDeviceId =? ";
		List<Object> list = new ArrayList<>();
		list.add(pe.getMapingDeviceId());
		if(pe.getS_host()!=null && pe.getS_host().trim().length()>0){
			sql +=" , s_host =?";
			list.add(pe.getS_host());
		}
		if(pe.getDeviceId()!=null && pe.getDeviceId().trim().length()>0){
			sql +=" , deviceId =?";
			if(pe.getDeviceId().trim().length()==14){
				String substring = pe.getDeviceId().substring(0, 11);
				list.add(substring);
			}else{
				list.add(pe.getDeviceId());
				
			}
		}
		if(pe.getS_proxy()!=null && pe.getS_proxy().trim().length()>0){
			sql +=" , s_proxy =?";
			list.add(pe.getS_proxy());
		}
		if(pe.getS_timeout()!=null && pe.getS_timeout().trim().length()>0){
			sql +=" , s_timeout =?";
			list.add(pe.getS_timeout());
		}
		if(pe.getS_hostport() >0){
			sql +=" , s_hostport =?";
			list.add(pe.getS_hostport());
		}
		if(pe.getS_proxyport()>0){
			sql +=" , s_proxyport =?";
			list.add(pe.getS_proxyport());
		}
		if(pe.getS_pwr()>0){
			sql +=" , s_pwr =?";
			list.add(pe.getS_pwr());
		}
		if(pe.getS_pwrval()>0){
			sql +=" , s_pwrval =?";
			list.add(pe.getS_pwrval());
		}
		if(pe.getStatus()>0){
			sql +=" , status =?";
			list.add(pe.getStatus());
		}
		sql +=" where mapingDeviceId =?";
		list.add(pe.getMapingDeviceId());
		return jdbcTemplate.update(sql,list.toArray());
	}
	
	
	
	// 查询代理
	public List<Map<String, Object>> listIPCProxy(IPCProxyEntity pe){
		// s_host:192.168.0.234;s_rport:8000;s_lport:9001;s_pwr:1;s_pwrval:0;s_timeout:86400;
		String sql ="select * from  t_vartriver_ipcproxy where 1=1 ";
		List<Object> list = new ArrayList<>();
		if(pe.getId() >0){
			sql +=" and id =?";
			list.add(pe.getId());
		}
		if(pe.getS_host()!=null && pe.getS_host().trim().length()>0){
			sql +=" and s_host =?";
			list.add(pe.getS_host());
		}
		if(pe.getDeviceId()!=null && pe.getDeviceId().trim().length()>0){
			sql +=" and deviceId =?";
			list.add(pe.getDeviceId());
		}
		if(pe.getS_proxy()!=null && pe.getS_proxy().trim().length()>0){
			sql +=" and s_proxy =?";
			list.add(pe.getS_proxy());
		}
		if(pe.getS_timeout()!=null && pe.getS_timeout().trim().length()>0){
			sql +=" and s_timeout =?";
			list.add(pe.getS_timeout());
		}
		if(pe.getS_hostport() >0){
			sql +=" and s_hostport =?";
			list.add(pe.getS_hostport());
		}
		if(pe.getS_proxyport()>0){
			sql +=" and s_proxyport =?";
			list.add(pe.getS_proxyport());
		}
		if(pe.getS_pwr()>0){
			sql +=" and s_pwr =?";
			list.add(pe.getS_pwr());
		}
		if(pe.getS_pwrval()>0){
			sql +=" and s_pwrval =?";
			list.add(pe.getS_pwrval());
		}
		if(pe.getStatus()>0){
			sql +=" and status =?";
			list.add(pe.getStatus());
		}
		if(pe.getMapingDeviceId()!=null && pe.getMapingDeviceId().trim().length()>0){
			sql +=" and mapingDeviceId =?";
			list.add(pe.getMapingDeviceId());
		}
		if(pe.getType()>0){
			sql +=" and type =?";
			list.add(pe.getType());
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	public int deleteIPC(IPCEntity ipcEntity){
		String sql =" delete from t_vartriver_ipc where mapingDeviceId =?";
		return jdbcTemplate.update(sql,ipcEntity.getMapingDeviceId());
	}
	public int updateIPC(IPCEntity ipcEntity){
		List<Object> list = new ArrayList<Object>();
		String sql =" update t_vartriver_ipc set id= ?";
		list.add(ipcEntity.getId());
		if(ipcEntity.getDeviceId()!=null&&ipcEntity.getDeviceId().trim().length()>0){
			sql +=" , deviceId =?";
			list.add(ipcEntity.getDeviceId());
		}
		if(ipcEntity.getS_ip()!=null&&ipcEntity.getS_ip().trim().length()>0){
			sql +=" , s_ip =?";
			list.add(ipcEntity.getS_ip());
		}
		if(ipcEntity.getS_port()>0){
			sql +=" , s_port =?";
			list.add(ipcEntity.getS_port());
		}
		if(ipcEntity.getS_password()!=null&&ipcEntity.getS_password().trim().length()>0){
			sql +=" , s_password =?";
			list.add(ipcEntity.getS_password());
		}
		if(ipcEntity.getS_username()!=null&&ipcEntity.getS_username().trim().length()>0){
			sql +=" , s_username =?";
			list.add(ipcEntity.getS_username());
		}
		if(ipcEntity.getIpcCtrlProxyId()>0){
			sql +=" , ipcCtrlProxyId =?";
			list.add(ipcEntity.getIpcCtrlProxyId());
		}
		if(ipcEntity.getIpcProxyId()>0){
			sql +=" , ipcProxyId =?";
			list.add(ipcEntity.getIpcProxyId());
		}
		if(ipcEntity.getMonitorid()>0){
			sql +=" , monitorid =?";
			list.add(ipcEntity.getMonitorid());
		}
		if(ipcEntity.getOrderNo()>0){
			sql +=" , orderNo =?";
			list.add(ipcEntity.getOrderNo());
		}
		if(ipcEntity.getS_nod()>-1){
			sql +=" , s_nod =?";
			list.add(ipcEntity.getS_nod());
		}
		if(ipcEntity.getS_online()>-1){
			sql +=" , s_online =?";
			list.add(ipcEntity.getS_online());
		}
		if(ipcEntity.getS_power()>-1){
			sql +=" , s_power =?";
			list.add(ipcEntity.getS_power());
		}
		if(ipcEntity.getS_stream()>0){
			sql +=" , s_stream =?";
			list.add(ipcEntity.getS_stream());
		}
		if(ipcEntity.getStatus()>0){
			sql +=" , s_status =?";
			list.add(ipcEntity.getStatus());
		}
		if(ipcEntity.getStreamType()>0){
			sql +=" , streamType =?";
			list.add(ipcEntity.getStreamType());
		}
		if(ipcEntity.getMapingDeviceId()!=null && ipcEntity.getMapingDeviceId().trim().length()>0){
			sql +=" , mapingDeviceId =?";
			list.add(ipcEntity.getMapingDeviceId());
		}
		if(ipcEntity.getName()!=null && ipcEntity.getName().trim().length()>0){
			sql +=" , name =?";
			list.add(ipcEntity.getName());
		}
		if(ipcEntity.getMvideoname()!=null && ipcEntity.getMvideoname().trim().length()>0){
			sql +=" , mvideoname =?";
			list.add(ipcEntity.getMvideoname());
		}
		if(ipcEntity.getSvideoname()!=null && ipcEntity.getSvideoname().trim().length()>0){
			sql +=" , svideoname =?";
			list.add(ipcEntity.getSvideoname());
		}
		if(ipcEntity.getMchannel()>0){
			sql +=" , mchannel =?";
			list.add(ipcEntity.getMchannel());
		}
		if(ipcEntity.getMresolution()>0){
			sql +=" , mresolution =?";
			list.add(ipcEntity.getMresolution());
		}
		if(ipcEntity.getMvideobitrate()>0){
			sql +=" , mvideobitrate =?";
			list.add(ipcEntity.getMvideobitrate());
		}
		if(ipcEntity.getMvideoframeRate()>0){
			sql +=" , mvideoframeRate =?";
			list.add(ipcEntity.getMvideoframeRate());
		}
		if(ipcEntity.getMencodetype()>0){
			sql +=" , mencodetype =?";
			list.add(ipcEntity.getMencodetype());
		}
		if(ipcEntity.getMencodeefficiency()>0){
			sql +=" , mencodeefficiency =?";
			list.add(ipcEntity.getMencodeefficiency());
		}
		if(ipcEntity.getSchannel()>0){
			sql +=" , schannel =?";
			list.add(ipcEntity.getSchannel());
		}
		if(ipcEntity.getSresolution()>0){
			sql +=" , sresolution =?";
			list.add(ipcEntity.getSresolution());
		}
		if(ipcEntity.getSvideobitrate()>0){
			sql +=" , svideobitrate =?";
			list.add(ipcEntity.getSvideobitrate());
		}
		if(ipcEntity.getSvideoframeRate()>0){
			sql +=" , svideoframeRate =?";
			list.add(ipcEntity.getSvideoframeRate());
		}
		if(ipcEntity.getSencodetype()>0){
			sql +=" , sencodetype =?";
			list.add(ipcEntity.getSencodetype());
		}
		if(ipcEntity.getSencodeefficiency()>0){
			sql +=" , sencodeefficiency =?";
			list.add(ipcEntity.getSencodeefficiency());
		}
		sql +=" where id= ? ";
		list.add(ipcEntity.getId());
		return jdbcTemplate.update(sql,list.toArray());
	}
	
	public int updateIPC2(Map<String, String> map){
		String sql = "update t_vartriver_ipc set s_nod =? , s_online = ? , s_power =?, s_stream = ? where deviceId =? and s_ip =?";
		return jdbcTemplate.update(sql,map.get("s_nod"),map.get("s_online"),map.get("s_power"),map.get("s_stream"),map.get("deviceId"),map.get("s_ip"));
	}
	
	
	public List<Map<String, Object>> listIPC(IPCEntity ipcEntity){
		List<Object> list = new ArrayList<Object>();
		String sql =" select * from t_vartriver_ipc where 1=1 ";
		if(ipcEntity.getDeviceId()!=null && ipcEntity.getDeviceId().trim().length()>0){
			sql +=" and deviceId = left(?,11)";
			list.add(ipcEntity.getDeviceId());
		}
		if(ipcEntity.getS_ip()!=null && ipcEntity.getS_ip().trim().length()>0){
			sql +=" and s_ip =?";
			list.add(ipcEntity.getS_ip());
		}
		if(ipcEntity.getS_password()!=null&&ipcEntity.getS_password().trim().length()>0){
			sql +=" and s_password =?";
			list.add(ipcEntity.getS_password());
		}
		if(ipcEntity.getS_username()!=null && ipcEntity.getS_username().trim().length()>0){
			sql +=" and s_username =?";
			list.add(ipcEntity.getS_username());
		}
		if(ipcEntity.getIpcCtrlProxyId()>0){
			sql +=" and ipcCtrlProxyId =?";
			list.add(ipcEntity.getIpcCtrlProxyId());
		}
		if(ipcEntity.getIpcProxyId()>0){
			sql +=" and ipcProxyId =?";
			list.add(ipcEntity.getIpcProxyId());
		}
		if(ipcEntity.getMonitorid()>0){
			sql +=" and monitorid =?";
			list.add(ipcEntity.getMonitorid());
		}
		if(ipcEntity.getOrderNo()>0){
			sql +=" and orderNo =?";
			list.add(ipcEntity.getOrderNo());
		}
		if(ipcEntity.getS_nod()>0){
			sql +=" and s_nod =?";
			list.add(ipcEntity.getS_nod());
		}
		if(ipcEntity.getS_online()>0){
			sql +=" and s_online =?";
			list.add(ipcEntity.getS_online());
		}
		if(ipcEntity.getS_power()>0){
			sql +=" and s_power =?";
			list.add(ipcEntity.getS_power());
		}
		if(ipcEntity.getS_stream()>0){
			sql +=" and s_stream =?";
			list.add(ipcEntity.getS_stream());
		}
		if(ipcEntity.getStatus()>0){
			sql +=" and s_status =?";
			list.add(ipcEntity.getStatus());
		}
		if(ipcEntity.getStreamType()>0){
			sql +=" and streamType =?";
			list.add(ipcEntity.getStreamType());
		}
		if(ipcEntity.getId()>0){
			sql +=" and id =?";
			list.add(ipcEntity.getId());
		}
		if(ipcEntity.getMapingDeviceId()!=null && ipcEntity.getMapingDeviceId().trim().length()>0){
			sql +=" and mapingDeviceId =?";
			list.add(ipcEntity.getMapingDeviceId());
		}
		if(ipcEntity.getName()!=null && ipcEntity.getName().trim().length()>0){
			sql +=" and name =?";
			list.add(ipcEntity.getName());
		}
		if(ipcEntity.getMvideoname()!=null && ipcEntity.getMvideoname().trim().length()>0){
			sql +=" and mvideoname =?";
			list.add(ipcEntity.getMvideoname());
		}
		if(ipcEntity.getSvideoname()!=null && ipcEntity.getSvideoname().trim().length()>0){
			sql +=" and svideoname =?";
			list.add(ipcEntity.getSvideoname());
		}
		if(ipcEntity.getMchannel()>0){
			sql +=" and mchannel =?";
			list.add(ipcEntity.getMchannel());
		}
		if(ipcEntity.getMresolution()>0){
			sql +=" and mresolution =?";
			list.add(ipcEntity.getMresolution());
		}
		if(ipcEntity.getMvideobitrate()>0){
			sql +=" and mvideobitrate =?";
			list.add(ipcEntity.getMvideobitrate());
		}
		if(ipcEntity.getMvideoframeRate()>0){
			sql +=" and mvideoframeRate =?";
			list.add(ipcEntity.getMvideoframeRate());
		}
		if(ipcEntity.getMencodetype()>0){
			sql +=" and mencodetype =?";
			list.add(ipcEntity.getMencodetype());
		}
		if(ipcEntity.getMencodeefficiency()>0){
			sql +=" and mencodeefficiency =?";
			list.add(ipcEntity.getMencodeefficiency());
		}
		if(ipcEntity.getSchannel()>0){
			sql +=" and schannel =?";
			list.add(ipcEntity.getSchannel());
		}
		if(ipcEntity.getSresolution()>0){
			sql +=" and sresolution =?";
			list.add(ipcEntity.getSresolution());
		}
		if(ipcEntity.getSvideobitrate()>0){
			sql +=" and svideobitrate =?";
			list.add(ipcEntity.getSvideobitrate());
		}
		if(ipcEntity.getSvideoframeRate()>0){
			sql +=" and svideoframeRate =?";
			list.add(ipcEntity.getSvideoframeRate());
		}
		if(ipcEntity.getSencodetype()>0){
			sql +=" and sencodetype =?";
			list.add(ipcEntity.getSencodetype());
		}
		if(ipcEntity.getSencodeefficiency()>0){
			sql +=" and sencodeefficiency =?";
			list.add(ipcEntity.getSencodeefficiency());
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
		
	}
	
	// 获得ipc
	public  Map<String, Object> getIPCById(IPCEntity ipcEntity){
		String sql =" select * from t_vartriver_ipc where id =?";
		try {
			return jdbcTemplate.queryForMap(sql,ipcEntity.getId());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	// 获得ipc
	public  List<IPCEntity> getIPC(IPCEntity ipcEntity){
		String sql =" select * from t_vartriver_ipc where id =?";
		return jdbcTemplate.query(sql,new IPCEntity(),ipcEntity.getId());
	}
	
	// 添加监视点
	public int addIPCPoint(IPCPointEntity ipce){
		String sql =" insert into T_VARTRIVER_Monitor (deviceid,monitorId,monitorName,beginTime,endTime,rateSecond,cycleDay,imgUrl,createTime,success) values(?,?,?,?,?,?,?,?,UNIX_TIMESTAMP(),?)";
		return jdbcTemplate.update(sql,ipce.getDeviceId(),ipce.getMonitorId(),ipce.getMonitorName(),ipce.getBeginTime(),ipce.getEndTime(),ipce.getRateSecond(),ipce.getCycleDay(),ipce.getImgUrl(),ipce.getSuccess());
	}
	
	public int addIPCPointList(List<IPCPointEntity> ipce){
		try {
			String sql =" insert into T_VARTRIVER_Monitor (deviceid,monitorId,monitorName,beginTime,endTime,rateSecond,cycleDay,imgUrl,createTime,success) values(?,?,?,?,?,?,?,?,UNIX_TIMESTAMP(),?)";
			int[]  a = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					IPCPointEntity ipc = ipce.get(i);
				
					ps.setString(1, ipc.getDeviceId());
					ps.setInt(2, ipc.getMonitorId());
					ps.setString(3, "监视点"+i);
					ps.setString(4, ipc.getBeginTime());
					ps.setString(5, ipc.getEndTime());
					ps.setInt(6, ipc.getRateSecond());
					ps.setInt(7, ipc.getCycleDay());
					ps.setString(8, "");
					ps.setInt(9, 1);
				}
				
				@Override
				public int getBatchSize() {
					return ipce.size();
				}
			});
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
		
	}
	
	
	
	
	// 删除监视点
	public Map<String, Object> getIPCPoint(IPCPointEntity ipce){
		String sql =" select * from T_VARTRIVER_Monitor where id =?";
		return jdbcTemplate.queryForMap(sql,ipce.getId());
	}
	
	// 删除监视点
	public int deleteIPCPoint(IPCPointEntity ipce){
		String sql =" delete from T_VARTRIVER_Monitor where id =?";
		return jdbcTemplate.update(sql,ipce.getId());
	}
	
	// 修改监视点
	public int updateIPCPoint(IPCPointEntity ipce){
		List<Object> list = new ArrayList<Object>();
		String sql ="update  T_VARTRIVER_Monitor set id= ?";
		list.add(ipce.getId());
		if(ipce.getMonitorName()!=null &&ipce.getMonitorName().trim().length()>0){
			sql +=" , monitorName =?";
			list.add(ipce.getMonitorName());
		}
		if(ipce.getBeginTime()!=null &&ipce.getBeginTime().trim().length()>0){
			sql +=" , beginTime =?";
			list.add(ipce.getBeginTime());
		}
		if(ipce.getEndTime()!=null &&ipce.getEndTime().trim().length()>0){
			sql +=" , endTime =?";
			list.add(ipce.getEndTime());
		}
		if(ipce. getCreateTime()!=null &&ipce.getCreateTime().trim().length()>0){
			sql +=" , createTime =?";
			list.add(ipce.getMonitorName());
		}
		if(ipce.getCycleDay()>0){
			sql +=" , cycleDay =?";
			list.add(ipce.getCycleDay());
		}
		if(ipce.getImgUrl()!=null &&ipce.getImgUrl().trim().length()>0){
			sql +=" , imgUrl =?";
			list.add(ipce.getImgUrl());
		}
		if(ipce.getMonitorId() >0){
			sql +=" , monitorId =?";
			list.add(ipce.getMonitorId());
		}
		if(ipce.getRateSecond() >0){
			sql +=" , rateSecond =?";
			list.add(ipce.getRateSecond());
		}
		if(ipce.getSuccess() >0){
			sql +=" , success =?";
			list.add(ipce.getSuccess());
		}
		if(ipce.getDeviceId()!=null && ipce.getDeviceId().trim().length() >0){
			sql +=" , deviceId =?";
			list.add(ipce.getDeviceId());
		}
		sql += " where id = ?";
		list.add(ipce.getId());
		return jdbcTemplate.update(sql,list.toArray());
	}
	// 查找监视点
	public List<Map<String, Object>> listIPCPoint(IPCPointEntity ipce){
		List<Object> list = new ArrayList<Object>();
		String sql ="select t.*,tip.s_username,tip.s_password from T_VARTRIVER_Monitor t left join t_vartriver_ipc tip  on tip.mapingDeviceId =t.deviceId where 1=1 ";
		if(ipce.getMonitorName()!=null &&ipce.getMonitorName().trim().length()>0){
			sql +=" and t.monitorName =?";
			list.add(ipce.getMonitorName());
		}
		if(ipce.getBeginTime()!=null &&ipce.getBeginTime().trim().length()>0){
			sql +=" and t.beginTime =?";
			list.add(ipce.getBeginTime());
		}
		if(ipce.getEndTime()!=null &&ipce.getEndTime().trim().length()>0){
			sql +=" and t.endTime =?";
			list.add(ipce.getEndTime());
		}
		if(ipce. getCreateTime()!=null &&ipce.getCreateTime().trim().length()>0){
			sql +=" and t.createTime =?";
			list.add(ipce.getMonitorName());
		}
		if(ipce.getCycleDay()>0){
			sql +=" and t.cycleDay =?";
			list.add(ipce.getCycleDay());
		}
		if(ipce.getImgUrl()!=null &&ipce.getImgUrl().trim().length()>0){
			sql +=" and t.imgUrl =?";
			list.add(ipce.getImgUrl());
		}
		if(ipce.getMonitorId() >0){
			sql +=" and t.monitorId =?";
			list.add(ipce.getMonitorId());
		}
		if(ipce.getRateSecond() >0){
			sql +=" and t.rateSecond =?";
			list.add(ipce.getRateSecond());
		}
		if(ipce.getSuccess() >0){
			sql +=" and t.success =?";
			list.add(ipce.getSuccess());
		}
		if(ipce.getDeviceId()!=null && ipce.getDeviceId().trim().length() >0){
			sql +=" and t.deviceId =?";
			list.add(ipce.getDeviceId());
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	// 核查监视点
	public List<Map<String, Object>> ckIPCPoint(IPCPointEntity ipce){
		List<Object> list = new ArrayList<Object>();
		String sql ="select * from T_VARTRIVER_Monitor where 1=1 ";
		if(ipce.getMonitorName()!=null &&ipce.getMonitorName().trim().length()>0){
			sql +=" and monitorName =?";
			list.add(ipce.getMonitorName());
		}
		if(ipce. getBeginTime()!=null &&ipce.getBeginTime().trim().length()>0){
			sql +=" and createTime < ?";
			list.add(ipce.getBeginTime());
		}
		if(ipce.getSuccess() >0){
			sql +=" and success =?";
			list.add(ipce.getSuccess());
		}
		if(ipce.getDeviceId()!=null && ipce.getDeviceId().trim().length() >0){
			sql +=" and deviceId =?";
			list.add(ipce.getDeviceId());
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	
	// 查看监视点图片 
	public List<Map<String, Object>> listIPCPointIMG(IPCPointEntity ipce){
		List<Object> list = new ArrayList<Object>();
		String sql ="select * from t_vartriver_img where 1=1 ";
		if(ipce.getDeviceId()!=null&& ipce.getDeviceId().trim().length()>0){
			sql += " and deviceId =?";
			list.add(ipce.getDeviceId());
		}
		if(ipce.getMonitorId()>0){
			sql += " and monitorId =?";
			list.add(ipce.getMonitorId());
		}
		if(ipce.getBeginTime()!=null && ipce.getBeginTime().trim().length()>0){
			sql += " and  left(infoDataTime,10) >= ?";
			list.add(ipce.getBeginTime());
		}
		if(ipce.getEndTime()!=null && ipce.getEndTime().trim().length()>0){
			sql += " and left(infoDataTime,10)<= ?";
			list.add(ipce.getEndTime());
		}
		if(ipce.getStart() > 0){
			if(ipce.getApp() > 0){
				if(ipce.getApptime()!=null && ipce.getApptime().trim().length()>0){
					sql += " and left(infoDataTime,10) = ?";
					list.add(ipce.getApptime());
				}
				sql += " limit 0,30";
			}else{
				sql += " limit "+(ipce.getStart()-1)*ipce.getPagesize()+","+ipce.getPagesize();
			}
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}

	public List<Map<String, Object>> getCtrlProxy(IPCProxyEntity pe) {
		String sql ="select t.id,t.mapingDeviceId,t.deviceId,t.s_host,t.s_hostport,t.s_proxy,t.s_pwr,t.s_pwrval,t.username,t.password,p.s_stream,p.s_online,p.name,tp.tp_name,p.s_nod,p.s_power,p.id ipcid from t_vartriver_ipcproxy t LEFT JOIN t_vartriver_ipc p on p.deviceId = t.deviceId left join t_point tp on tp.tp_id = p.id  where t.mapingDeviceId =? and type =?";
		return jdbcTemplate.queryForList(sql,pe.getDeviceId(),pe.getType());
	}



	/**
	 * 2017年11月3日
	 * jianghu
	 * @param ipcPointRequset
	 * @return
	 * TODO
	 */
	public int listIPCPointIMGCount(IPCPointRequset ipce) {
		List<Object> list = new ArrayList<Object>();
		String sql ="select count(*) from t_vartriver_img where 1=1 ";
		if(ipce.getDeviceId()!=null&& ipce.getDeviceId().trim().length()>0){
			sql += " and deviceId =?";
			list.add(ipce.getDeviceId());
		}
		if(ipce.getMonitorId()>0){
			sql += " and monitorId =?";
			list.add(ipce.getMonitorId());
		}
		if(ipce.getBeginTime()!=null && ipce.getBeginTime().trim().length()>0){
			sql += " and  left(infoDataTime,10)> ?";
			list.add(ipce.getBeginTime());
		}
		if(ipce.getEndTime()!=null && ipce.getEndTime().trim().length()>0){
			sql += " and left(infoDataTime,10)< ?";
			list.add(ipce.getEndTime());
		}
		return jdbcTemplate.queryForObject(sql,Integer.class,list.toArray());
		
	}




	/**
	 * 2017年12月2日
	 * jianghu
	 * TODO
	 */
	public int getMaxProxyId() {
		// TODO Auto-generated method stub
		String sql = "select Max(id) from t_vartriver_ipcproxy ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
		
	}




	/**
	 * 2017年12月23日
	 * jianghu
	 * @param deviceId
	 * @return
	 * TODO
	 */
	public int getMaxMonitorId(String deviceId) {
		// TODO Auto-generated method stub
		String sql =" select max(monitorId) from  T_VARTRIVER_Monitor  where deviceId =? ";
		return jdbcTemplate.queryForObject(sql, Integer.class,deviceId);
	}




	/**
	 * 2017年12月23日
	 * jianghu
	 * @param deviceId
	 * @param monitorId
	 * @return
	 * TODO
	 */
	public int getIPCPointId(String deviceId, int monitorId) {
		// TODO Auto-generated method stub
		String sql = "select id from T_VARTRIVER_Monitor where deviceId =? and  monitorId =?";
		return jdbcTemplate.queryForObject(sql, Integer.class,deviceId,monitorId);
	}




	/**
	 * 2017年12月23日
	 * jianghu
	 * @param ipce
	 * @return
	 * TODO
	 */
//	public int updateAppIPCPoint(IPCPointEntity ipce) {
//		// TODO Auto-generated method stub
//		String sql ="update T_VARTRIVER_Monitor set "; 
//		return 0;
//	}




	/**
	 * 2017年12月23日
	 * jianghu
	 * @param ipcPointRequset
	 * TODO
	 * @return 
	 */
	public List<Map<String, Object>> getAppIPCPoint(IPCPointRequset ipce) {
		// TODO Auto-generated method stub
		String sql =" select * from T_VARTRIVER_Monitor where 1=1";
		List<Object> list = new ArrayList<>();
		if(ipce.getMonitorName()!=null &&ipce.getMonitorName().trim().length()>0){
			sql +=" and t.monitorName =?";
			list.add(ipce.getMonitorName());
		}
		if(ipce.getBeginTime()!=null &&ipce.getBeginTime().trim().length()>0){
			sql +=" and t.beginTime =?";
			list.add(ipce.getBeginTime());
		}
		if(ipce.getEndTime()!=null &&ipce.getEndTime().trim().length()>0){
			sql +=" and t.endTime =?";
			list.add(ipce.getEndTime());
		}
		if(ipce. getCreateTime()!=null &&ipce.getCreateTime().trim().length()>0){
			sql +=" and t.createTime =?";
			list.add(ipce.getMonitorName());
		}
		if(ipce.getCycleDay()>0){
			sql +=" and t.cycleDay =?";
			list.add(ipce.getCycleDay());
		}
		if(ipce.getImgUrl()!=null &&ipce.getImgUrl().trim().length()>0){
			sql +=" and t.imgUrl =?";
			list.add(ipce.getImgUrl());
		}
		if(ipce.getMonitorId() >0){
			sql +=" and t.monitorId =?";
			list.add(ipce.getMonitorId());
		}
		if(ipce.getRateSecond() >0){
			sql +=" and t.rateSecond =?";
			list.add(ipce.getRateSecond());
		}
		if(ipce.getSuccess() >0){
			sql +=" and t.success =?";
			list.add(ipce.getSuccess());
		}
		if(ipce.getDeviceId()!=null && ipce.getDeviceId().trim().length() >0){
			sql +=" and t.deviceId =?";
			list.add(ipce.getDeviceId());
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	public Map<String, Object> listLastIPCPoint(String deviceId) {
		String sql ="select * from t_vartriver_img where deviceId =? and infoDataTime = ( SELECT MAX(infoDataTime) FROM `t_vartriver_img`where deviceId =? )";
		return jdbcTemplate.queryForMap(sql,deviceId,deviceId);
	}
	
//	public Map<String, Object> listLastIPCPointImg(String deviceId,int monitorId) {
//		String sql =" SELECT * FROM `t_vartriver_img` where deviceId =? and monitorId =? ORDER BY infoDataTime desc LIMIT 0,1";
//		return jdbcTemplate.queryForMap(sql,deviceId,monitorId);
//	}
	

}
