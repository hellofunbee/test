/**  
*   
* 项目名称：IOT  
* 类名称：ControllerRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月12日 上午9:50:26  
* 修改人：jianghu  
* 修改时间：2017年10月12日 上午9:50:26  
* 修改备注： 上午9:50:26
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.ControlEntity;
import com.jingu.IOT.entity.MotorHBM;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.RuleEntity;

/**

* @ClassName: ControllerRequset
* @Description: TODO
* @author jianghu
* @date 2017年10月12日 上午9:50:26

*/
public class ControlRequset extends ControlEntity {

	private String ckuid;
	private String cksid;
	private PointEntity pointEntity;
	private MotorHBM hbm;
	private String admin;
	private RuleEntity ruleEntity;
	private int model;
	private int app;
	
	
	
	
	
	
	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
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
	public RuleEntity getRuleEntity() {
		return ruleEntity;
	}
	public void setRuleEntity(RuleEntity ruleEntity) {
		this.ruleEntity = ruleEntity;
	}
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
	public PointEntity getPointEntity() {
		return pointEntity;
	}
	public void setPointEntity(PointEntity pointEntity) {
		this.pointEntity = pointEntity;
	}
	public MotorHBM getHbm() {
		return hbm;
	}
	public void setHbm(MotorHBM hbm) {
		this.hbm = hbm;
	}
	
	
}
