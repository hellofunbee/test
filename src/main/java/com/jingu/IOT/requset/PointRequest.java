/**  
*   
* 项目名称：IOT  
* 类名称：Pointrequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月5日 下午4:44:22  
* 修改人：jianghu  
* 修改时间：2017年9月5日 下午4:44:22  
* 修改备注： 下午4:44:22
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.PointEntity;

/**

* @ClassName: Pointrequest
* @Description: TODO
* @author jianghu
* @date 2017年9月5日 下午4:44:22

*/
public class PointRequest extends PointEntity{
	private String ckuid;
	private String cksid;
	private int pagesize = 20;
	private int start ;
	private int app;
	private String p_time;
	
	public String getP_time() {
		return p_time;
	}
	public void setP_time(String p_time) {
		this.p_time = p_time;
	}
	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPagesize() {
		return pagesize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {

		this.start = start;
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
