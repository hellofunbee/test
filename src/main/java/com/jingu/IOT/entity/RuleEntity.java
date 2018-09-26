/**  
*   
* 项目名称：IOT  
* 类名称：RuleEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月24日 上午11:14:00  
* 修改人：jianghu  
* 修改时间：2017年10月24日 上午11:14:00  
* 修改备注： 上午11:14:00
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**

* @ClassName: RuleEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月24日 上午11:14:00

*/
public class  RuleEntity implements RowMapper<RuleEntity>,Serializable {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = -8083955717338933048L;
	private int r_id,type,ctrl_id,duration;

	private String r_name,
	r_deviceId,
	switchGroupId,
	switchId,
	ctrlType,
	cycleDay,
	execTime,
	beginTime,
	endTime,
	targetDeviceId,
	targetFieldName,
	ruleEnable,
	lastExecDataTime,
	time;
	
	private double maxValue,
	minValue,
	coefficient;
	

	private PointEntity pointEntity;
	private String admin;
	private String execEndTime;
	private String grade;



	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		if (null == time || time.trim().length() == 0) {
			this.time = "";
		}
		this.time = time;
	}
	public String getExecEndTime() {
		return execEndTime;
	}
	public void setExecEndTime(String execEndTime) {
		this.execEndTime = execEndTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		if (null == admin || admin.trim().length() == 0) {
			this.admin = "";
		}
		this.admin = admin;
	}
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}
	
	
	




	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getCtrl_id() {
		return ctrl_id;
	}
	public void setCtrl_id(int ctrl_id) {
		this.ctrl_id = ctrl_id;
	}
	public RuleEntity(int r_id, String r_name, String r_deviceId, String switchGroupId, String switchId,
			String ctrlType, String cycleDay, String execTime, String beginTime, String endTime, String targetDeviceId,
			String targetFieldName, String ruleEnable, String lastExecDataTime, double maxValue, double minValue,
			int duration, double coefficient,int ctrl_id,String execEndtime) {
		super();
		this.r_id = r_id;
		this.r_name = r_name;
		this.r_deviceId = r_deviceId;
		this.switchGroupId = switchGroupId;
		this.switchId = switchId;
		this.ctrlType = ctrlType;
		this.cycleDay = cycleDay;
		this.execTime = execTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.targetDeviceId = targetDeviceId;
		this.targetFieldName = targetFieldName;
		this.ruleEnable = ruleEnable;
		this.lastExecDataTime = lastExecDataTime;
		this.maxValue = maxValue;
		this.minValue = minValue;
		this.duration = duration;
		this.coefficient = coefficient;
		this.ctrl_id = ctrl_id ;
		this.execEndTime = execEndtime;
	}

	
	
	public RuleEntity() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getR_id() {
		return r_id;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		if (null == r_name || r_name.trim().length() == 0) {
			this.r_name = "";
		}
		this.r_name = r_name;
	}

	public String getR_deviceId() {
		return r_deviceId;
	}

	public void setR_deviceId(String r_deviceId) {
		if (null == r_deviceId || r_deviceId.trim().length() == 0) {
			this.r_deviceId = "";
		}
		this.r_deviceId = r_deviceId;
	}

	public String getSwitchGroupId() {
		return switchGroupId;
	}

	public void setSwitchGroupId(String switchGroupId) {
		if (null == switchGroupId || switchGroupId.trim().length() == 0) {
			this.switchGroupId = "";
		}
		this.switchGroupId = switchGroupId;
	}

	public String getSwitchId() {
		return switchId;
	}

	public void setSwitchId(String switchId) {
		if (null == switchId || switchId.trim().length() == 0) {
			this.switchId = "";
		}
		this.switchId = switchId;
	}

	public String getCtrlType() {
		return ctrlType;
	}

	public void setCtrlType(String ctrlType) {
		if (null == ctrlType || ctrlType.trim().length() == 0) {
			this.ctrlType = "";
		}
		this.ctrlType = ctrlType;
	}

	public String getCycleDay() {
		return cycleDay;
	}

	public void setCycleDay(String cycleDay) {
		if (null == cycleDay || cycleDay.trim().length() == 0) {
			this.cycleDay = "";
		}
		this.cycleDay = cycleDay;
	}

	public String getExecTime() {
		return execTime;
	}

	public void setExecTime(String execTime) {
		if (null == execTime || execTime.trim().length() == 0) {
			this.execTime = "";
		}
		this.execTime = execTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		if (null == beginTime || beginTime.trim().length() == 0) {
			this.beginTime = "";
		}
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		if (null == endTime || endTime.trim().length() == 0) {
			this.endTime = "";
		}
		this.endTime = endTime;
	}

	public String getTargetDeviceId() {
		return targetDeviceId;
	}

	public void setTargetDeviceId(String targetDeviceId) {
		if (null == targetDeviceId || targetDeviceId.trim().length() == 0) {
			this.targetDeviceId = "";
		}
		this.targetDeviceId = targetDeviceId;
	}

	public String getTargetFieldName() {
		return targetFieldName;
	}

	public void setTargetFieldName(String targetFieldName) {
		if (null == targetFieldName || targetFieldName.trim().length() == 0) {
			this.targetFieldName = "";
		}
		this.targetFieldName = targetFieldName;
	}

	public String getRuleEnable() {
		return ruleEnable;
	}

	public void setRuleEnable(String ruleEnable) {
		if (null == ruleEnable || ruleEnable.trim().length() == 0) {
			this.ruleEnable = "";
		}
		this.ruleEnable = ruleEnable;
	}

	public String getLastExecDataTime() {
		return lastExecDataTime;
	}

	public void setLastExecDataTime(String lastExecDataTime) {
		if (null == lastExecDataTime || lastExecDataTime.trim().length() == 0) {
			this.lastExecDataTime = "";
		}
		this.lastExecDataTime = lastExecDataTime;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getMinValue() {
		return minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}


	public double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public RuleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return new RuleEntity(rs.getInt("id"),rs.getString("name"),rs.getString("deviceId"),rs.getString("switchGroupId"),rs.getString("switchId"),rs.getString("ctrlType"),rs.getString("cycleDay"),rs.getString("execTime"),rs.getString("beginTime"),rs.getString("endTime"),rs.getString("targetDeviceId"),rs.getString("targetFieldName"),rs.getString("ruleEnable"),rs.getString("lastExecDataTime"),rs.getDouble("maxValue"),rs.getDouble("minValue"),rs.getInt("duration"),rs.getDouble("coefficient"),rs.getInt("ctrl_id"),rs.getString("execEndTime"));
	}



	@Override
	public String toString() {
		return "RuleEntity [r_id=" + r_id + ", r_name=" + r_name + ", r_deviceId=" + r_deviceId + ", switchGroupId="
				+ switchGroupId + ", switchId=" + switchId + ", ctrlType=" + ctrlType + ", cycleDay=" + cycleDay
				+ ", execTime=" + execTime + ", beginTime=" + beginTime + ", endTime=" + endTime + ", targetDeviceId="
				+ targetDeviceId + ", targetFieldName=" + targetFieldName + ", ruleEnable=" + ruleEnable
				+ ", lastExecDataTime=" + lastExecDataTime + ", ctrl_id=" + ctrl_id + ", maxValue=" + maxValue
				+ ", minValue=" + minValue + ", duration=" + duration + ", coefficient=" + coefficient
				+ ", getCtrl_id()=" + getCtrl_id() + ", getR_id()=" + getR_id() + ", getR_name()=" + getR_name()
				+ ", getR_deviceId()=" + getR_deviceId() + ", getSwitchGroupId()=" + getSwitchGroupId()
				+ ", getSwitchId()=" + getSwitchId() + ", getCtrlType()=" + getCtrlType() + ", getCycleDay()="
				+ getCycleDay() + ", getExecTime()=" + getExecTime() + ", getBeginTime()=" + getBeginTime()
				+ ", getEndTime()=" + getEndTime() + ", getTargetDeviceId()=" + getTargetDeviceId()
				+ ", getTargetFieldName()=" + getTargetFieldName() + ", getRuleEnable()=" + getRuleEnable()
				+ ", getLastExecDataTime()=" + getLastExecDataTime() + ", getMaxValue()=" + getMaxValue()
				+ ", getMinValue()=" + getMinValue() + ", getDuration()=" + getDuration() + ", getCoefficient()="
				+ getCoefficient() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

//	public RuleEntity(int r_id, String r_name, String r_deviceId, String switchGroupId, String switchId,
//			String ctrlType, String cycleDay, String execTime, String beginTime, String endTime, String targetDeviceId,
//			String targetFieldName, String ruleEnable, String lastExecDataTime, double maxValue, double minValue,
//			double duration, double coefficient) {
//		super();
//		this.r_id = r_id;
//		this.r_name = r_name;
//		this.r_deviceId = r_deviceId;
//		this.switchGroupId = switchGroupId;
//		this.switchId = switchId;
//		this.ctrlType = ctrlType;
//		this.cycleDay = cycleDay;
//		this.execTime = execTime;
//		this.beginTime = beginTime;
//		this.endTime = endTime;
//		this.targetDeviceId = targetDeviceId;
//		this.targetFieldName = targetFieldName;
//		this.ruleEnable = ruleEnable;
//		this.lastExecDataTime = lastExecDataTime;
//		this.maxValue = maxValue;
//		this.minValue = minValue;
//		this.duration = duration;
//		this.coefficient = coefficient;
//	}



}
