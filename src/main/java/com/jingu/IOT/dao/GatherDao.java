/**  
*   
* 项目名称：IOT  
* 类名称：GatherDao  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月28日 下午3:37:03  
* 修改人：jianghu  
* 修改时间：2017年8月28日 下午3:37:03  
* 修改备注： 下午3:37:03
* @version   
*   
*/ 
package com.jingu.IOT.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.SettingEntity;
import com.jingu.IOT.entity.VAREntity;
import com.jingu.IOT.requset.PointRequest;

/**

* @ClassName: GatherDao
* @Description: TODO
* @author jianghu
* @date 2017年8月28日 下午3:37:03

*/
@Component
public class GatherDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;

//	@Autowired
//	public GatherDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	public List<Map<String, Object>> getGatherSettings(VAREntity varEntity){
		String sql="select * from t_vartriver_parsedata tp where tp.deviceId=?";
		return jdbcTemplate.queryForList(sql,varEntity.getDeviceId());
	}

	public int addGatherSettings(List<SettingEntity> list){
		String sql = " insert into t_vartriver_parsedata (id,channel,deviceId,name,beginPosition,len,dataType,decimalFormat,fieldName,formula,unit,orderIndex,listDisplay,statDisplay,lowerLimit,upperLimit,diffPercent,chartID,chartDisplay,chartOrderIndex) values ";
		StringBuffer stringBuffer = new StringBuffer(sql);
		for (SettingEntity settingEntity : list) {
			stringBuffer.append("('").append(settingEntity.getId()).append("',").append(settingEntity.getChannel()).append(",'").append(settingEntity.getDeviceId()).append("','").append(settingEntity.getName()).append("',").
			append(settingEntity.getBeginPosition()).append(",").append(settingEntity.getLen()).append(",'").append(settingEntity.getDataType()).append("','").append(settingEntity.getDecimalFormat()).append("','").append(settingEntity.getFieldName()).append("','").
			append(settingEntity.getFormula()).append("','").append(settingEntity.getUnit()).append("',").append(settingEntity.getOrderIndex()).append(",").append(settingEntity.getListDisplay()).append(",").append(settingEntity.getStatDisplay()).append(",").append(settingEntity.getLowerLimit()).
			append(",").append(settingEntity.getUpperLimit()).append(",").append(settingEntity.getDiffPercent()).append(",").append(settingEntity.getChartID()).append(",").append(settingEntity.getChartDisplay()).append(",").append(settingEntity.getChartOrderIndex()).append("),");
		}
		StringBuffer delete = stringBuffer.delete(stringBuffer.length()-1, stringBuffer.length());
		return jdbcTemplate.update(delete.toString());
		
	}
	
	public int deleteSettingByDeviceId(PointEntity se){
		String sql ="delete from t_vartriver_parsedata where deviceId=? ";
		return jdbcTemplate.update(sql,se.getDeviceId());
	}
	
	// 
	public int deleteSettingById(PointEntity se){
		String sql ="delete from t_vartriver_parsedata where id in ("+se.getIds()+") ";
		return jdbcTemplate.update(sql);
	}
	
	public int getSettingCountByDeviceId(PointEntity se){
		String sql ="select count(*) from t_vartriver_parsedata where deviceId=?";
		return jdbcTemplate.queryForObject(sql,Integer.class,se.getDeviceId());
	}
	
	
	public int deleteSetting(SettingEntity se){
		String sql ="delete from t_vartriver_parsedata where id=?";
		return jdbcTemplate.update(sql,se.getId());
	}
	public int updateSetting(SettingEntity se){
		String sql ="update t_vartriver_parsedata set id =? ";
		ArrayList<Object> list = new ArrayList<>();
		list.add(se.getId());
		if(se.getChannel()>0){
			sql +=" , channel =?";
			list.add(se.getChannel());
		}
		if(se.getDeviceId()!=null && se.getDeviceId().trim().length()>0){
			sql +=" , deviceId =?";
			list.add(se.getDeviceId());
		}
		if(se.getName()!=null && se.getName().trim().length()>0){
			sql +=" , name =?";
			list.add(se.getName());
		}
		if(se.getBeginPosition()>0){
			sql +=" , beginPosition =?";
			list.add(se.getBeginPosition());
		}
		if(se.getLen()>0){
			sql +=" , len =?";
			list.add(se.getLen());
		}
		if(se.getDataType()!=null && se.getDataType().trim().length()>0){
			sql +=" , dataType =?";
			list.add(se.getDataType());
		}
		if(se.getDecimalFormat()!=null && se.getDecimalFormat().trim().length()>0){
			sql +=" , decimalFormat =?";
			list.add(se.getDecimalFormat());
		}
		if(se.getFieldName()!=null && se.getFieldName().trim().length()>0){
			sql +=" , fieldName =?";
			list.add(se.getFieldName());
		}
		if(se.getFormula()!=null &&se.getFormula().trim().length()>0){
			sql +=" , formula =?";
			list.add(se.getChannel());
		}
		if(se.getUnit()!=null && se.getUnit().trim().length()>0){
			sql +=" , unit =?";
			list.add(se.getUnit());
		}
		if(se.getOrderIndex()>0){
			sql +=" , orderIndex =?";
			list.add(se.getOrderIndex());
		}
		if(se.getListDisplay()>0){
			sql +=" , listDisplay =?";
			list.add(se.getListDisplay());
		}
		if(se.getStatDisplay()>0){
			sql +=" , statDisplay =?";
			list.add(se.getStatDisplay());
		}
		if(se.getLowerLimit()>0){
			sql +=" , lowerLimit =?";
			list.add(se.getLowerLimit());
		}
		if(se.getUpperLimit()>0){
			sql +=" , upperLimit =?";
			list.add(se.getUpperLimit());
		}
		if(se.getDiffPercent()>0){
			sql +=" , diffPercent =?";
			list.add(se.getDiffPercent());
		}
		if(se.getChartID()>0){
			sql +=" , chartID =?";
			list.add(se.getChartID());
		}
		if(se.getChartDisplay()>0){
			sql +=" , chartDisplay =?";
			list.add(se.getChartDisplay());
		}
		if(se.getChartOrderIndex()>0){
			sql +=" , chartOrderIndex =?";
			list.add(se.getChartOrderIndex());
		}
		sql +=" where id =?";
		return jdbcTemplate.update(sql,se.getId());
	}
	
	// 展示采集数据
	public List<Map<String, Object>> listSensorInfo(PointRequest pEntity){
		//t_vartriver_10002174
		String deviceId = pEntity.getDeviceId();
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_"+replace;
		String sql = "SELECT group_concat(t.fieldName) name from t_vartriver_parsedata t where t.deviceId =?";
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql,deviceId);
		if(queryForList==null || queryForList.isEmpty()){
			return null;
		}
		String string = queryForList.get(0).get("name").toString();
		String sql1 = "select  t."+string+",t.infoDataTime from "+table+" t order by infoDataTime desc";
		if(pEntity.getStart() >0){
			if(pEntity.getApp() ==1){
				sql1 = "select  t."+string+",t.infoDataTime from "+table+" t where left(infodatatime,10) = '"+pEntity.getP_time()+"' order by infoDataTime desc";
				sql1 += " limit "+(pEntity.getStart()-1)*pEntity.getPagesize()+","+pEntity.getPagesize();
				return jdbcTemplate.queryForList(sql1);
			}
			sql1 += " limit "+(pEntity.getStart()-1)*pEntity.getPagesize()+","+pEntity.getPagesize();
		}
		List<Map<String,Object>> queryForList2 = jdbcTemplate.queryForList(sql1);
		return queryForList2;
	}
	
	// 展示采集数据
	public List<Map<String, Object>> listSensorInfoFirst(PointRequest pEntity){
		String deviceId = pEntity.getDeviceId();
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_"+replace;
		String sql = "SELECT group_concat(t.fieldName) name from t_vartriver_parsedata t where t.deviceId =?";
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql,deviceId);
		if(queryForList==null || queryForList.isEmpty()){
			return null;
		}
		String string = queryForList.get(0).get("name").toString();
		String sql1 = "select  t."+string+",t.infoDataTime from "+table+" t order by infoDataTime desc limit 0,1";
		List<Map<String,Object>> queryForList2 = jdbcTemplate.queryForList(sql1);
		return queryForList2;
	}
	
	// 展示采集数据单位 // 列表显示
	public List<Map<String, Object>> listSensorInfoListUnit(PointEntity pEntity){
		String sql ="select fieldName,unit,name,orderIndex,listDisplay,statDisplay,chartDisplay,chartID,chartOrderIndex from t_vartriver_parsedata where deviceId=? and listDisplay =1 order by  orderIndex";
		return jdbcTemplate.queryForList(sql,pEntity.getDeviceId());
	}
	// 展示采集数据单位 // 图标显示
	public List<Map<String, Object>> listSensorInfoChartUnit(PointEntity pEntity){
		String sql ="select fieldName,unit,name,orderIndex,listDisplay,statDisplay,chartDisplay,chartID,chartOrderIndex from t_vartriver_parsedata where deviceId=? and chartDisplay =1 order by  chartOrderIndex";
		return jdbcTemplate.queryForList(sql,pEntity.getDeviceId());
	}
	
	// 展示采集数据单位 // 图标显示
	public List<Map<String, Object>> listStateDisplay(PointEntity pEntity){
		String sql ="select fieldName,unit,name,orderIndex,listDisplay,statDisplay,chartDisplay,chartID,chartOrderIndex from t_vartriver_parsedata where deviceId=? and statDisplay =1 order by  orderIndex";
		return jdbcTemplate.queryForList(sql,pEntity.getDeviceId());
	}
	
	public List<Map<String, Object>> listwaring(PointEntity pEntity){
		String sql ="select * from t_vartriver_devicestatus where deviceId= ? ";
		return jdbcTemplate.queryForList(sql,pEntity.getDeviceId());
	}
	
	public List<Map<String, Object>> listStaticData(List<String> list){
		String sql ="select coalesce(GROUP_CONCAT(fieldName),'') field from t_vartriver_parsedata where deviceId= ? ";
		String s ="";
		if(list.size()>1){
			sql +="and (";
			for (int i = 0; i < list.size()-1; i++) {
				s +=" or name = ?";
			}
			String substring = s.substring(3);
			sql += substring+")";
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
	
	
	
	
	
	
	public int listSensorInfoCount(PointRequest pr) {
		// TODO Auto-generated method stub
		String deviceId = pr.getDeviceId();
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_"+replace;
		String sql = " select count(*) from "+table;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	
/*	public static void main(String[] args) {
		String ip ="10.00.74.21";
		String replace = ip.replace(".", "");
		System.out.println(replace);
	}*/
	
	
	public List<Map<String, Object>> listChannel(PointEntity pEntity){
		String sql ="select name,fieldName from t_vartriver_parsedata where deviceId= ?";
		return jdbcTemplate.queryForList(sql,pEntity.getDeviceId());
	}
	
	public List<Map<String, Object>> listStateInfo(String field ,String deviceId,String beginTime,String endTime){
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_"+replace;
		boolean flag = false;
		String sql ="";
		if(field==null||field.trim().length()<1){
			 sql ="select infoDataTime from "+table +" where 1=1";

		}else{
			sql ="select infoDataTime,"+field+" from "+table +" where 1=1";
			
		}
		ArrayList<Object> arrayList = new ArrayList<>();
		if(beginTime!=null && beginTime.trim().length()>0){
			sql +=" and infoDataTime >= ?";
			arrayList.add(beginTime);
			flag= true;
		}
		if(endTime!=null && endTime.trim().length()>0){
			sql +=" and infoDataTime <= ?";
			arrayList.add(endTime);
			flag =true;
		}
		if(flag){
			return jdbcTemplate.queryForList(sql,arrayList.toArray());
		}
		return jdbcTemplate.queryForList(sql);
	}

	/**
	 * 2017年11月29日
	 * jianghu
	 * @param deviceId
	 * @return
	 * TODO
	 */
	public List<Map<String, Object>> listChannelByDeviceId(String deviceId) {
		String sql ="select name,fieldName from t_vartriver_parsedata where deviceId= ? and statDisplay=1 ";
		return jdbcTemplate.queryForList(sql,deviceId);
	}
}	
