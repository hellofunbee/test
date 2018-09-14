/**  
*   
* 项目名称：IOT  
* 类名称：ParaDataEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月9日 上午10:23:21  
* 修改人：jianghu  
* 修改时间：2017年10月9日 上午10:23:21  
* 修改备注： 上午10:23:21
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: ParaDataEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月9日 上午10:23:21

*/
public class ParaDataEntity {
	private String id;
	private int channel;
	private String deviceId;
	private String name;
	private int beginPosition;
	private int len;
	private String dataType;
	private String decimalFormat;
	private String fieldName;
	private String formula;
	private String unit;
	private int orderIndex;
	private int listDisplay;
	private int statDisplay;
	private double lowerLimit;
	private double upperLimit;
	private double diffPercent;
	private String ChartID;
	private int ChartDisplay;
	private int chartOrderIndex;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if (null == id || id.trim().length() == 0) {
			this.id = "";
		}
		this.id = id;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		if (null == deviceId || deviceId.trim().length() == 0) {
			this.deviceId = "";
		}
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (null == name || name.trim().length() == 0) {
			this.name = "";
		}
		this.name = name;
	}
	public int getBeginPosition() {
		return beginPosition;
	}
	public void setBeginPosition(int beginPosition) {
		this.beginPosition = beginPosition;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		if (null == dataType || dataType.trim().length() == 0) {
			this.dataType = "";
		}
		this.dataType = dataType;
	}
	public String getDecimalFormat() {
		return decimalFormat;
	}
	public void setDecimalFormat(String decimalFormat) {
		if (null == decimalFormat || decimalFormat.trim().length() == 0) {
			this.decimalFormat = "";
		}
		this.decimalFormat = decimalFormat;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		if (null == fieldName || fieldName.trim().length() == 0) {
			this.fieldName = "";
		}
		this.fieldName = fieldName;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		if (null == formula || formula.trim().length() == 0) {
			this.formula = "";
		}
		this.formula = formula;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		if (null == unit || unit.trim().length() == 0) {
			this.unit = "";
		}
		this.unit = unit;
	}
	public int getOrderIndex() {
		return orderIndex;
	}
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
	public int getListDisplay() {
		return listDisplay;
	}
	public void setListDisplay(int listDisplay) {
		this.listDisplay = listDisplay;
	}
	public int getStatDisplay() {
		return statDisplay;
	}
	public void setStatDisplay(int statDisplay) {
		this.statDisplay = statDisplay;
	}
	public double getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
	public double getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}
	public double getDiffPercent() {
		return diffPercent;
	}
	public void setDiffPercent(double diffPercent) {
		this.diffPercent = diffPercent;
	}
	public String getChartID() {
		return ChartID;
	}
	public void setChartID(String chartID) {
		if (null == chartID || chartID.trim().length() == 0) {
			ChartID = "";
		}
		ChartID = chartID;
	}
	public int getChartDisplay() {
		return ChartDisplay;
	}
	public void setChartDisplay(int chartDisplay) {
		ChartDisplay = chartDisplay;
	}
	public int getChartOrderIndex() {
		return chartOrderIndex;
	}
	public void setChartOrderIndex(int chartOrderIndex) {
		this.chartOrderIndex = chartOrderIndex;
	}
	
	

}
