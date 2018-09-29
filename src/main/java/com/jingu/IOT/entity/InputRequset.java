/**  
*   
* 项目名称：IOT  
* 类名称：InputRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 下午7:10:47  
* 修改人：jianghu  
* 修改时间：2017年10月10日 下午7:10:47  
* 修改备注： 下午7:10:47
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

/**

* @ClassName: InputRequset
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 下午7:10:47

*/
public class InputRequset extends InputEntity {

	private String ckuid;
	private String cksid;
	private List<InputEntity> inputEntity;
	private PointEntity pointEntity;

	private List<Integer> tp_ids;

	private List<Integer> c_ids;

	private String beginTime;

	private String endTime;

	private int type; //类型
	private int ckdata;//检查日期
	private int interval_type = 0;//0:day 1:month:2:year
	private int order = 0;//0 desc 1 asc




	public String getCkuid() {
		return ckuid;
	}
	public void setCkuid(String ckuid) {
		if (null == ckuid || ckuid.trim().length() == 0) {
			this.ckuid = "";
		}
		this.ckuid = ckuid;
	}
	public String getCksid() {
		return cksid;
	}
	public void setCksid(String cksid) {
		if (null == cksid || cksid.trim().length() == 0) {
			this.cksid = "";
		}
		this.cksid = cksid;
	}
	public List<InputEntity> getInputEntity() {
		return inputEntity;
	}
	public void setInputEntity(List<InputEntity> inputEntity) {
		this.inputEntity = inputEntity;
	}
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}

	public List<Integer> getTp_ids() {
		return tp_ids;
	}

	public void setTp_ids(List<Integer> tp_ids) {
		this.tp_ids = tp_ids;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCkdata() {
		return ckdata;
	}

	public void setCkdata(int ckdata) {
		this.ckdata = ckdata;
	}

	public int getInterval_type() {
		return interval_type;
	}

	public void setInterval_type(int interval_type) {
		this.interval_type = interval_type;
	}

	public List<Integer> getC_ids() {
		return c_ids;
	}

	public void setC_ids(List<Integer> c_ids) {
		this.c_ids = c_ids;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
