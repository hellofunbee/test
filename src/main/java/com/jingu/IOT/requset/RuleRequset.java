/**  
*   
* 项目名称：IOT  
* 类名称：RuleRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月27日 上午11:09:16  
* 修改人：jianghu  
* 修改时间：2017年10月27日 上午11:09:16  
* 修改备注： 上午11:09:16
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import java.util.List;

import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.RuleEntity;

/**

* @ClassName: RuleRequset
* @Description: TODO
* @author jianghu
* @date 2017年10月27日 上午11:09:16

*/
public class RuleRequset extends RuleEntity {

	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = -3049302540849561019L;
	private String ckuid;
	private String cksid;
	private List<RuleEntity> list;
	private int type;
	private String  ids;
	private int app;
	
	
	

	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<RuleEntity> getList() {
		return list;
	}
	public void setList(List<RuleEntity> list) {
		this.list = list;
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
	
	
}
