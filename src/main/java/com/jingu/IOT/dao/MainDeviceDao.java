/**  
*   
* 项目名称：IOT  
* 类名称：MainDeviceDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月4日 上午9:50:25  
* 修改人：jianghu  
* 修改时间：2017年9月4日 上午9:50:25  
* 修改备注： 上午9:50:25
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import com.jingu.IOT.entity.MainDeviceEntity;
import com.jingu.IOT.requset.PointRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**

* @ClassName: MainDeviceDao
* @Description: TODO
* @author jianghu
* @date 2017年9月4日 上午9:50:25

*/
@Component
public class MainDeviceDao {
	
	@Resource
	@Qualifier("primaryJdbcTemplate")
	private  JdbcTemplate jdbcTemplate;

//	@Autowired
//	public MainDeviceDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	
	public int addMainDevice(MainDeviceEntity mde){
		String sql ="insert into t_vastriver_ip (orderNo,NIP,NPort,IP,Port,proxyIp,proxyPort,x,y,zoom,DeviceId,name,videoPort,softWareUpdatePort,useIPConnect,videoPlayPort,ctrPort,title,smartCtrl,benTian,yuYang,id,province,city,district,supervisername,producername,exportorname,superviserid,producerid,exportorid,siteid,groupid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,mde.getOrderNo(),mde.getNip(),mde.getNport(),mde.getIp(),
				mde.getPort(),mde.getProxyIp(),mde.getProxyPort(),mde.getX(),mde.getY(),mde.getZoom(),mde.getDeviceId(),mde.getName(),mde.getVideoPort(),mde.getSoftWareUpdatePort(),mde.getUseIPConnect(),mde.getVideoPlayPort(),
				mde.getCtrPort(),mde.getTitle(),mde.getSmartCtrl(),mde.getBenTian(),mde.getYuYang(),mde.getId(),mde.getProvince(),mde.getCity(),mde.getDistrict(),mde.getSuperivsername(),mde.getProducername(),mde.getExportorname(),mde.getSuperviserid(),mde.getProducerid(),mde.getExportorid(),mde.getSiteid(),mde.getGroupid());
	}
	public int updateMainDevice(MainDeviceEntity mde){
		String sql = "update t_vastriver_ip set id = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(mde.getId());
		if(mde.getBenTian()>0){
			sql += " , benTian = ?";
			list.add(mde.getBenTian());
		}
		if(mde.getCtrPort()>0){
			sql += " , ctrPort = ?";
			list.add(mde.getCtrPort());
		}
		if(mde.getIp()!=null && mde.getIp().trim().length()>0){
			sql += " , IP = ?";
			list.add(mde.getIp());
		}
		if(mde.getName()!=null && mde.getName().trim().length()>0){
			sql += " , name = ?";
			list.add(mde.getName());
		}
		if(mde.getNip()!=null && mde.getNip().trim().length()>0){
			sql += " , NIP = ?";
			list.add(mde.getNip());
		}
		if(mde.getNport()>0){
			sql += " , NPort = ?";
			list.add(mde.getNport());
		}
		if(mde.getOrderNo()>0){
			sql += " , orderNo = ?";
			list.add(mde.getOrderNo());
		}
		if(mde.getPort()>0){
			sql += " , port = ?";
			list.add(mde.getPort());
		}
		if(mde.getProxyIp()!=null&& mde.getProxyIp().trim().length()>0){
			sql += " , proxyIp = ?";
			list.add(mde.getProxyIp());
		}
		if(mde.getProxyPort()>0){
			sql += " , proxyPort = ?";
			list.add(mde.getProxyPort());
		}
		if(mde.getSmartCtrl()>0){
			sql += " , smartCtrl = ?";
			list.add(mde.getSmartCtrl());
		}
		if(mde.getSoftWareUpdatePort()>0){
			sql += " , softWareUpdatePort = ?";
			list.add(mde.getSoftWareUpdatePort());
		}
		if(mde.getTitle()!=null &&mde.getTitle().trim().length()>0){
			sql += " , title = ?";
			list.add(mde.getTitle());
		}
		if(mde.getUseIPConnect()>0){
			sql += " , useIPConnect = ?";
			list.add(mde.getUseIPConnect());
		}
		if(mde.getVideoPlayPort()>0){
			sql += " , videoPlayPort = ?";
			list.add(mde.getVideoPlayPort());
		}
		if(mde.getVideoPort()>0){
			sql += " , videoPort = ?";
			list.add(mde.getVideoPort());
		}
		if(mde.getX()>0){
			sql += " , x = ?";
			list.add(mde.getX());
		}
		if(mde.getY()>0){
			sql += " , y = ?";
			list.add(mde.getY());
		}
		if(mde.getYuYang()>0){
			sql += " , yuYang = ?";
			list.add(mde.getYuYang());
		}
		if(mde.getZoom()>0){
			sql += " , zoom = ?";
			list.add(mde.getZoom());
		}
		if(mde.getSuperviserid()>0){
			sql += " , superviserid = ?";
			list.add(mde.getSuperviserid());
		}
		if(mde.getGroupid()>0){
			sql += " , groupid = ?";
			list.add(mde.getGroupid());
		}
		if(mde.getSiteid()>0){
			sql += " , siteid = ?";
			list.add(mde.getSiteid());
		}
		if(mde.getProducerid()>0){
			sql += " , producerid = ?";
			list.add(mde.getProducerid());
		}
		if(mde.getExportorid()>0){
			sql += " , exportorid = ?";
			list.add(mde.getExportorid());
		}
		if(mde.getProvince()!=null && mde.getProvince().trim().length()>0){
			sql +=" , province=?";
			list.add(mde.getProvince());
		}
		if(mde.getCity()!=null && mde.getCity().trim().length()>0){
			sql +=" , city=?";
			list.add(mde.getCity());
		}
		if(mde.getDistrict()!=null && mde.getDistrict().trim().length()>0){
			sql +=" , district=?";
			list.add(mde.getDistrict());
		}
		if(mde.getSuperivsername()!=null && mde.getSuperivsername().trim().length()>0){
					//supervisername
			sql +=" , supervisername = ?";
					//superivsername
			list.add(mde.getSuperivsername());
		}
		if(mde.getProducername()!=null && mde.getProducername().trim().length()>0){
			sql +=" , producername = ?";
			list.add(mde.getProducername());
		}
		if(mde.getExportorname()!=null && mde.getExportorname().trim().length()>0){
			sql +=" , exportorname = ?";
			list.add(mde.getExportorname());
		}
		sql +=" where id = ?";
		list.add(mde.getId());
		return jdbcTemplate.update(sql,list.toArray());

	}
	public  List<MainDeviceEntity> getMainDeviceById(MainDeviceEntity mde){
		String sql = "select ip.*,s.a_name provincename,sa.a_name cityname,sar.a_name districtname from t_vastriver_ip ip left join s_area s on s.ar_id =ip.province left join s_area sa on sa.ar_id = ip.city left join s_area sar on sar.ar_id = ip.district  where ip.id =?";
		return jdbcTemplate.query(sql,new MainDeviceEntity(),mde.getId());
	}
	
	public  Map<String, Object> findById(MainDeviceEntity mde){
		String sql = "select * from t_vastriver_ip where DeviceId =?";
		return jdbcTemplate.queryForMap(sql,mde.getDeviceId());
	}
	
	
	public  int deleteMainDeviceById(PointRequest mdr){
		String sql = "delete from t_vastriver_ip where id =?";
		return jdbcTemplate.update(sql,mdr.getTp_id());
	}
	
	
	public List<Map<String, Object>> listMainDevice(MainDeviceEntity mde){
		String sql = "select * from  t_vastriver_ip where 1=1";
		List<Object> list = new ArrayList<Object>();
		if(mde.getBenTian()>0){
			sql += " and benTian = ?";
			list.add(mde.getBenTian());
		}
		if(mde.getCtrPort()>0){
			sql += " and ctrPort = ?";
			list.add(mde.getCtrPort());
		}
		if(mde.getIp()!=null && mde.getIp().trim().length()>0){
			sql += " and IP = ?";
			list.add(mde.getIp());
		}
		if(mde.getName()!=null && mde.getName().trim().length()>0){
			sql += " and name = ?";
			list.add(mde.getName());
		}
		if(mde.getNip()!=null && mde.getNip().trim().length()>0){
			sql += " and NIP = ?";
			list.add(mde.getNip());
		}
		if(mde.getNport()>0){
			sql += " and NPort = ?";
			list.add(mde.getNport());
		}
		if(mde.getOrderNo()>0){
			sql += " and orderNo = ?";
			list.add(mde.getOrderNo());
		}
		if(mde.getPort()>0){
			sql += " and port = ?";
			list.add(mde.getPort());
		}
		if(mde.getProxyIp()!=null&& mde.getProxyIp().trim().length()>0){
			sql += " and proxyIp = ?";
			list.add(mde.getProxyIp());
		}
		if(mde.getProxyPort()>0){
			sql += " and proxyPort = ?";
			list.add(mde.getProxyPort());
		}
		if(mde.getSmartCtrl()>0){
			sql += " and smartCtrl = ?";
			list.add(mde.getSmartCtrl());
		}
		if(mde.getSoftWareUpdatePort()>0){
			sql += " and softWareUpdatePort = ?";
			list.add(mde.getSoftWareUpdatePort());
		}
		if(mde.getTitle()!=null &&mde.getTitle().trim().length()>0){
			sql += " and title = ?";
			list.add(mde.getTitle());
		}
		if(mde.getUseIPConnect()>0){
			sql += " and useIPConnect = ?";
			list.add(mde.getUseIPConnect());
		}
		if(mde.getVideoPlayPort()>0){
			sql += " and videoPlayPort = ?";
			list.add(mde.getVideoPlayPort());
		}
		if(mde.getVideoPort()>0){
			sql += " and videoPort = ?";
			list.add(mde.getVideoPort());
		}
		if(mde.getX()>0){
			sql += " and x = ?";
			list.add(mde.getX());
		}
		if(mde.getY()>0){
			sql += " and y = ?";
			list.add(mde.getY());
		}
		if(mde.getYuYang()>0){
			sql += " and yuYang = ?";
			list.add(mde.getYuYang());
		}
		if(mde.getZoom()>0){
			sql += " and zoom = ?";
			list.add(mde.getZoom());
		}
		if(mde.getSuperviserid()>0){
			sql += " and superviserid = ?";
			list.add(mde.getSuperviserid());
		}
		if(mde.getProducerid()>0){
			sql += " and producerid = ?";
			list.add(mde.getProducerid());
		}
		if(mde.getExportorid()>0){
			sql += " and exportorid = ?";
			list.add(mde.getExportorid());
		}
		if(mde.getGroupid()>0){
			sql += " and groupid = ?";
			list.add(mde.getGroupid());
		}
		if(mde.getSiteid()>0){
			sql += " and siteid = ?";
			list.add(mde.getSiteid());
		}
		if(mde.getProvince()!=null && mde.getProvince().trim().length()>0){
			sql +=" and province=?";
			list.add(mde.getProvince());
		}
		if(mde.getCity()!=null && mde.getCity().trim().length()>0){
			sql +=" and city=?";
			list.add(mde.getCity());
		}
		if(mde.getDistrict()!=null && mde.getDistrict().trim().length()>0){
			sql +=" and district=?";
			list.add(mde.getDistrict());
		}
		if(mde.getSuperivsername()!=null && mde.getSuperivsername().trim().length()>0){
			sql +=" and superivsername=?";
			list.add(mde.getSuperivsername());
		}
		if(mde.getProducername()!=null && mde.getProducername().trim().length()>0){
			sql +=" and producername=?";
			list.add(mde.getProducername());
		}
		if(mde.getExportorname()!=null && mde.getExportorname().trim().length()>0){
			sql +=" and exportorname=?";
			list.add(mde.getExportorname());
		}
		if(mde.getId()>0){
			sql +=" and id=?";
			list.add(mde.getId());
		}
		return jdbcTemplate.queryForList(sql,list.toArray());

	}
	
	public Map<String, Object> getMainDeviceByDeviceId(MainDeviceEntity mde){
		String sql = "select ip.Softwareversion,ip.HardwareVersion,ip.supervisername,ip.producername,ip.exportorname,tp.tp_name groupname,tpo.tp_name sitename,s.a_name province,sa.a_name city,se.a_name district from t_vastriver_ip ip left join s_area s on s.ar_id =ip.province left join s_area sa on sa.ar_id = ip.city left join s_area se on se.ar_id = ip.district left join t_point tp on ip.groupid =tp.tp_id left join t_point tpo on tpo.tp_id = ip.siteid  where ip.DeviceId =?";
		return jdbcTemplate.queryForMap(sql,mde.getDeviceId());
	}
	public Map<String, Object> getMainDeviceId(MainDeviceEntity mde){
		String sql = "select DeviceId,name,supervisername,exportorname,if(UNIX_TIMESTAMP()-UNIX_TIMESTAMP(infoDataTime)<180,1,2) state from t_vastriver_ip  where id =? ";
		return jdbcTemplate.queryForMap(sql,mde.getId());
	}
	
	public int addMainDeviceList(List<MainDeviceEntity> mde){
		String sql = "insert into t_vastriver_ip (id,deviceId) value ";
//		String string = "";
//		for (MainDeviceEntity mainDeviceEntity : mde) {
//			string +=" , ("+mainDeviceEntity.getId()+","+mainDeviceEntity.getDeviceId()+")";
//		}
//		String substring = string.substring(1);
//		sql += substring;
//		System.out.println(sql);
		return jdbcTemplate.update(sql);
	}
	
	public int addMainDeviceList2(MainDeviceEntity mde){
		String sql = "insert into t_vastriver_ip (id,deviceId) value (?,?)";
//		String string = "";
//		for (MainDeviceEntity mainDeviceEntity : mde) {
//			string +=" , ("+mainDeviceEntity.getId()+","+mainDeviceEntity.getDeviceId()+")";
//		}
//		String substring = string.substring(1);
//		sql += substring;
//		System.out.println(sql);
		return jdbcTemplate.update(sql,mde.getId(),mde.getDeviceId());
	}
	
	
	
	
	
	
//	public Map<String, Object> getMainDeviceByDeviceId(MainDeviceEntity mde){
//		String sql = "select ip.deviceId,ip.supervisername,ip.producername,ip.exportorname,if()  where ip.DeviceId =?";
//		return jdbcTemplate.queryForMap(sql,mde.getDeviceId());
//	}
	//可能要联合查询
	
}
