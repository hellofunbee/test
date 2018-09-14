package com.jingu.IOT.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.ShopRetrospectDao;

@Component
public class ShopRetrospectService {

	@Resource
	private ShopRetrospectDao dao;

	/**
	 * 获取设备
	 * 
	 * @return
	 */
	public List<Map<String, Object>> getDevice() {
		return dao.getDevice();
	}

	/**
	 * 根据设备号获取摄像头
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	public List<Map<String, Object>> getIPCByDeviceId(String deviceId) {
		return dao.getIPCByDeviceId(deviceId);
	}

	/**
	 * 根据mapingDeviceId查监视点
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	public List<Map<String, Object>> getMonitorByDeviceId(String deviceId) {
		return dao.getMonitorByDeviceId(deviceId);
	}

	/**
	 * 获取生产计划信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	public List<Map<String, Object>> getProduceByDeviceId(String deviceId) {
		return dao.getProduceByDeviceId(deviceId);
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
		return dao.getImg(deviceId, beginDate, endDate);
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
		return dao.getSF(deviceId, beginDate, endDate);
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
		return dao.getNY(deviceId, beginDate, endDate);
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
		return dao.getSensorKQWDInfo(deviceId, beginTime, endTime);
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
		return dao.getSensorKQSDInfo(deviceId, beginTime, endTime);
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
		return dao.getSensorGZDInfo(deviceId, beginTime, endTime);
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
		return dao.getSensorTNWDInfo(deviceId, beginTime, endTime);
	}
}
