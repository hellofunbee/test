package com.jingu.IOT.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jingu.IOT.requset.PointRequest;

@Component
public class ShopRetrospectDao {

	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jt;

	/**
	 * 获取设备
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getDevice() {
		return jt.queryForList("SELECT * from t_vastriver_ip");
	}

	/**
	 * 根据设备号获取摄像头
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	public List<Map<String, Object>> getIPCByDeviceId(String deviceId) {
		String sql = "SELECT * from t_vartriver_ipc where 1=1";
		if (deviceId != null && !deviceId.equals("")) {
			sql += "	and	deviceId='" + deviceId + "'";
		}
		return jt.queryForList(sql);
	}

	/**
	 * 根据mapingDeviceId查监视点
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	public List<Map<String, Object>> getMonitorByDeviceId(String deviceId) {
								//T_VARTRIVER_Monitor     t_vartriver_monitor
		String sql = "SELECT * from T_VARTRIVER_Monitor where 1=1";
		if (deviceId != null && !deviceId.equals("")) {
			sql += "	and	DeviceId='" + deviceId + "'";
		}
		return jt.queryForList(sql);
	}

	/**
	 * 获取生产计划信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	public List<Map<String, Object>> getProduceByDeviceId(String deviceId) {
		String sql = "SELECT * from produce where  1=1 ";
		if (deviceId != null && !deviceId.equals("")) {
			sql += " and	tp_id = (SELECT tp_id from t_point where DeviceId='" + deviceId + "')";
		}
		return jt.queryForList(sql);
	}

	/**
	 * 获取图片
	 * 
	 * @param deviceId
	 *            mapingDeviceId查监视点
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> getImg(String deviceId, String beginDate, String endDate) {
		String sql = "SELECT * from t_vartriver_img " + " " + "where  monitorId in "
				+ " (SELECT monitorId from T_VARTRIVER_Monitor"
				+ " where DeviceId in (SELECT mapingDeviceId from t_vartriver_ipc where deviceId ='" + deviceId
				+ "')  GROUP BY monitorId " + ")" + "and deviceId in" + "("
				+ "	SELECT deviceId from T_VARTRIVER_Monitor "
				+ " where DeviceId in (SELECT mapingDeviceId from t_vartriver_ipc where deviceId ='" + deviceId
				+ "') GROUP BY deviceId " + ") and infoDataTime BETWEEN '" + beginDate + "' and  '" + endDate
				+ "' ORDER BY infoDataTime DESC   LIMIT 0,200 ";
		return jt.queryForList(sql);
	}

	/**
	 * 施肥信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> getSF(String deviceId, String beginDate, String endDate) {
		String sql = "select * from input " + " WHERE in_pid in " + " (SELECT p_id from produce where p_begintime='"
				+ beginDate + "' and p_endtime='" + endDate
				+ "' and  tp_id = (SELECT tp_id from t_point where DeviceId='" + deviceId + "')   )"
				+ " and in_class1=64";

		System.out.println(deviceId);
		System.out.println(beginDate);
		System.out.println(endDate);

		return jt.queryForList(sql);
	}

	/**
	 * 农药信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> getNY(String deviceId, String beginDate, String endDate) {
		String sql = "select * from input " + " WHERE in_pid in " + " (SELECT p_id from produce where p_begintime='"
				+ beginDate + "' and p_endtime='" + endDate
				+ "' and  tp_id = (SELECT tp_id from t_point where DeviceId='" + deviceId + "')   )"
				+ " and in_class1=65";
		return jt.queryForList(sql);
	}

	/**
	 * 查看传感器空气温度
	 * 
	 * @param deviceId
	 *            设备id
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> getSensorKQWDInfo(String deviceId, String beginTime, String endTime) {
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_" + replace;
		Map<String, Object> map = new HashMap<>();
		// #空气温度
		String sql = "select channel7,createTime from " + table + " t where createTime BETWEEN '" + beginTime
				+ "' and '" + endTime + "' order by infoDataTime desc";

		// ExecutorService exc=Executors.newFixedThreadPool(4);

		return jt.queryForList(sql);
	}

	/**
	 * 查看传感器空气湿度信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> getSensorKQSDInfo(String deviceId, String beginTime, String endTime) {
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_" + replace;

		String sql2 = "select channel8,createTime from " + table + " t where createTime BETWEEN '" + beginTime
				+ "' and '" + endTime + "' order by infoDataTime desc";

		return jt.queryForList(sql2);
	}

	/**
	 * 查看传感器光照度信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> getSensorGZDInfo(String deviceId, String beginTime, String endTime) {
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_" + replace;

		String sql2 = "select channel15,createTime from " + table + " t where createTime BETWEEN '" + beginTime
				+ "' and '" + endTime + "' order by infoDataTime desc";

		return jt.queryForList(sql2);
	}

	/**
	 * 查看传感器土壤温度信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @param beginTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> getSensorTNWDInfo(String deviceId, String beginTime, String endTime) {
		String replace = deviceId.replace(".", "");
		String table = "t_vartriver_" + replace;

		String sql2 = "select channel12,createTime from " + table + " t where createTime BETWEEN '" + beginTime
				+ "' and '" + endTime + "' order by infoDataTime desc";

		return jt.queryForList(sql2);
	}
}
