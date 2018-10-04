/**  
*   
* 项目名称：IOT  
* 类名称：ControlEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月11日 下午5:56:59  
* 修改人：jianghu  
* 修改时间：2017年10月11日 下午5:56:59  
* 修改备注： 下午5:56:59
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: ControlEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月11日 下午5:56:59

*/
public class ControlEntity {

	private int ctrl_id;
	private String ctrl_name;
	private String ctrl_nickname;
	private String ctrl_deviceId;
	private String ctrl_mapingdeviceId;
	private String ctrl_min;
	private String ctrl_max;
	private String ctrl_channel="0";
	private int ctrl_type;
	private String ctrl_temperature;
	private String ctrl_water;
	private int ctrl_raise_groupId;
	private int ctrl_raise_switchId;
	private int ctrl_down_groupId;
	private int ctrl_down_switchId;
	private int ctrl_count;
	private int ctrl_picturetype;
	private String ctrl_picturetitle;
	private int state_type;
	private int s_state;
	private int montor_state;
	private int is_running;//是否正在控制中
	private int open_lev = -2;//开启度 -2 默认 -1：无状态 0-100开启度

	
	
	
	public int getMontor_state() {
		return montor_state;
	}
	public void setMontor_state(int montor_state) {
		this.montor_state = montor_state;
	}
	public int getS_state() {
		return s_state;
	}
	public void setS_state(int s_state) {
		this.s_state = s_state;
	}
	public int getState_type() {
		return state_type;
	}
	public void setState_type(int state_type) {
		this.state_type = state_type;
	}
	private int distanceOrDuration;
	
	private int direction;
	
	private boolean success;
	private String msg;
	
	
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		if (null == msg || msg.trim().length() == 0) {
			this.msg = "";
		}
		this.msg = msg;
	}
	public int getDistanceOrDuration() {
		return distanceOrDuration;
	}
	public void setDistanceOrDuration(int distanceOrDuration) {
		this.distanceOrDuration = distanceOrDuration;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getCtrl_id() {
		return ctrl_id;
	}
	public void setCtrl_id(int ctrl_id) {
		this.ctrl_id = ctrl_id;
	}
	public String getCtrl_name() {
		return ctrl_name;
	}
	public void setCtrl_name(String ctrl_name) {
		if (null == ctrl_name || ctrl_name.trim().length() == 0) {
			this.ctrl_name = "";
		}
		this.ctrl_name = ctrl_name;
	}
	public String getCtrl_nickname() {
		return ctrl_nickname;
	}
	public void setCtrl_nickname(String ctrl_nickname) {
		if (null == ctrl_nickname || ctrl_nickname.trim().length() == 0) {
			this.ctrl_nickname = "";
		}
		this.ctrl_nickname = ctrl_nickname;
	}
	public String getCtrl_deviceId() {
		return ctrl_deviceId;
	}
	public void setCtrl_deviceId(String ctrl_deviceId) {
		if (null == ctrl_deviceId || ctrl_deviceId.trim().length() == 0) {
			this.ctrl_deviceId = "";
		}
		this.ctrl_deviceId = ctrl_deviceId;
	}
	public String getCtrl_mapingdeviceId() {
		return ctrl_mapingdeviceId;
	}
	public void setCtrl_mapingdeviceId(String ctrl_mapingdeviceId) {
		if (null == ctrl_mapingdeviceId || ctrl_mapingdeviceId.trim().length() == 0) {
			this.ctrl_mapingdeviceId = "";
		}
		this.ctrl_mapingdeviceId = ctrl_mapingdeviceId;
	}
	public String getCtrl_min() {
		return ctrl_min;
	}
	public void setCtrl_min(String ctrl_min) {
		if (null == ctrl_min || ctrl_min.trim().length() == 0) {
			this.ctrl_min = "";
		}
		this.ctrl_min = ctrl_min;
	}
	public String getCtrl_max() {
		return ctrl_max;
	}
	public void setCtrl_max(String ctrl_max) {
		if (null == ctrl_max || ctrl_max.trim().length() == 0) {
			this.ctrl_max = "";
		}
		this.ctrl_max = ctrl_max;
	}
	public String getCtrl_channel() {
		return ctrl_channel;
	}
	public void setCtrl_channel(String ctrl_channel) {
		if (null == ctrl_channel || ctrl_channel.trim().length() == 0) {
			this.ctrl_channel = "";
		}
		this.ctrl_channel = ctrl_channel;
	}
	public int getCtrl_type() {
		return ctrl_type;
	}
	public void setCtrl_type(int ctrl_type) {
		this.ctrl_type = ctrl_type;
	}
	public String getCtrl_temperature() {
		return ctrl_temperature;
	}
	public void setCtrl_temperature(String ctrl_temperature) {
		if (null == ctrl_temperature || ctrl_temperature.trim().length() == 0) {
			this.ctrl_temperature = "";
		}
		this.ctrl_temperature = ctrl_temperature;
	}
	public String getCtrl_water() {
		return ctrl_water;
	}
	public void setCtrl_water(String ctrl_water) {
		if (null == ctrl_water || ctrl_water.trim().length() == 0) {
			this.ctrl_water = "";
		}
		this.ctrl_water = ctrl_water;
	}
	public int getCtrl_raise_groupId() {
		return ctrl_raise_groupId;
	}
	public void setCtrl_raise_groupId(int ctrl_raise_groupId) {
		this.ctrl_raise_groupId = ctrl_raise_groupId;
	}
	public int getCtrl_raise_switchId() {
		return ctrl_raise_switchId;
	}
	public void setCtrl_raise_switchId(int ctrl_raise_switchId) {
		this.ctrl_raise_switchId = ctrl_raise_switchId;
	}
	public int getCtrl_down_groupId() {
		return ctrl_down_groupId;
	}
	public void setCtrl_down_groupId(int ctrl_down_groupId) {
		this.ctrl_down_groupId = ctrl_down_groupId;
	}
	public int getCtrl_down_switchId() {
		return ctrl_down_switchId;
	}
	public void setCtrl_down_switchId(int ctrl_down_switchId) {
		this.ctrl_down_switchId = ctrl_down_switchId;
	}
	public int getCtrl_count() {
		return ctrl_count;
	}
	public void setCtrl_count(int ctrl_count) {
		this.ctrl_count = ctrl_count;
	}
	public int getCtrl_picturetype() {
		return ctrl_picturetype;
	}
	public void setCtrl_picturetype(int ctrl_picturetype) {
		this.ctrl_picturetype = ctrl_picturetype;
	}
	public String getCtrl_picturetitle() {
		return ctrl_picturetitle;
	}
	public void setCtrl_picturetitle(String ctrl_picturetitle) {
		if (null == ctrl_picturetitle || ctrl_picturetitle.trim().length() == 0) {
			this.ctrl_picturetitle = "";
		}
		this.ctrl_picturetitle = ctrl_picturetitle;
	}

	public int getIs_running() {
		return is_running;
	}

	public void setIs_running(int is_running) {
		this.is_running = is_running;
	}

	public int getOpen_lev() {
		return open_lev;
	}

	public void setOpen_lev(int open_lev) {
		this.open_lev = open_lev;
	}
}
