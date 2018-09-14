package com.jingu.IOT.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jingu.IOT.service.ShopRetrospectService;

@RestController
public class ShopRetrospectController {

	@Resource
	private ShopRetrospectService service;

	/**
	 * 获取设备
	 * 
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/shopGetDevice", method = RequestMethod.POST)
	public List<Map<String, Object>> shopGetDevice() {
		return service.getDevice();
	}

	/**
	 * 根据设备号获取摄像头
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/shopGetIPCByDeviceId", method = RequestMethod.POST)
	public List<Map<String, Object>> shopGetIPCByDeviceId(String deviceId) {
		return service.getIPCByDeviceId(deviceId);
	}

	/**
	 * 根据mapingDeviceId查监视点
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/shopGetMonitorByDeviceId", method = RequestMethod.POST)
	public List<Map<String, Object>> shopGetMonitorByDeviceId(String mapingDeviceId) {
		return service.getMonitorByDeviceId(mapingDeviceId);
	}

	/**
	 * 获取生产计划信息
	 * 
	 * @param deviceId
	 *            设备id
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/shopGetProduceByDeviceId", method = RequestMethod.POST)
	public List<Map<String, Object>> shopGetProduceByDeviceId(String deviceId) {
		return service.getProduceByDeviceId(deviceId);
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
	@CrossOrigin
	@RequestMapping(value = "/shopGetImg", method = RequestMethod.POST)
	public List<Map<String, Object>> shopGetImg(String deviceId, String beginDate, String endDate) {
		return service.getImg(deviceId, beginDate, endDate);
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
	@CrossOrigin
	@RequestMapping(value = "/shopGetSF", method = RequestMethod.POST)
	public List<Map<String, Object>> shopGetSF(String deviceId, String beginDate, String endDate) {
		return service.getSF(deviceId, beginDate, endDate);
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
	@CrossOrigin
	@RequestMapping(value = "/shopGetNY", method = RequestMethod.POST)
	public List<Map<String, Object>> shopGetNY(String deviceId, String beginDate, String endDate) {
		return service.getNY(deviceId, beginDate, endDate);
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
	@CrossOrigin
	@RequestMapping(value = "/getSensorKQWDInfo", method = RequestMethod.POST)
	public List<Map<String, Object>> getSensorKQWDInfo(String deviceId, String beginTime, String endTime) {
		return service.getSensorKQWDInfo(deviceId, beginTime, endTime);
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
	@CrossOrigin
	@RequestMapping(value = "/getSensorKQSDInfo", method = RequestMethod.POST)
	public List<Map<String, Object>> getSensorKQSDInfo(String deviceId, String beginTime, String endTime) {
		return service.getSensorKQSDInfo(deviceId, beginTime, endTime);
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
	@CrossOrigin
	@RequestMapping(value = "/getSensorGZDInfo", method = RequestMethod.POST)
	public List<Map<String, Object>> getSensorGZDInfo(String deviceId, String beginTime, String endTime) {
		return service.getSensorGZDInfo(deviceId, beginTime, endTime);
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
	@CrossOrigin
	@RequestMapping(value = "/getSensorTNWDInfo", method = RequestMethod.POST)
	public List<Map<String, Object>> getSensorTNWDInfo(String deviceId, String beginTime, String endTime) {
		return service.getSensorTNWDInfo(deviceId, beginTime, endTime);
	}

}
