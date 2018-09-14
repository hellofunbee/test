/**  
*   
* 项目名称：IOT  
* 类名称：ProduceEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 下午4:10:09  
* 修改人：jianghu  
* 修改时间：2017年10月10日 下午4:10:09  
* 修改备注： 下午4:10:09
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: ProduceEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 下午4:10:09

*/
public class ProduceEntity {

	private int p_id,
	p_type,
	p_state,
	p_class1,
	p_class2,
	start,pagesize =20,
	app,
	appPagesize = 6,
	tp_id;
	
	private String p_name,
	p_standrad,
	p_begintime,
	p_endtime,
	p_harvesttime,
	p_harvestarea,
	p_ownername;
	

	public int getTp_id() {
		return tp_id;
	}

	public void setTp_id(int tp_id) {
		this.tp_id = tp_id;
	}

	public int getAppPagesize() {
		return appPagesize;
	}

	public int getApp() {
		return app;
	}

	public void setApp(int app) {
		this.app = app;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPagesize() {
		return pagesize;
	}

	public String getP_ownername() {
		return p_ownername;
	}

	public void setP_ownername(String p_ownername) {
		if (null == p_ownername || p_ownername.trim().length() == 0) {
			this.p_ownername = "";
		}
		this.p_ownername = p_ownername;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public int getP_type() {
		return p_type;
	}

	public void setP_type(int p_type) {
		this.p_type = p_type;
	}

	public int getP_state() {
		return p_state;
	}

	public void setP_state(int p_state) {
		this.p_state = p_state;
	}

	public int getP_class1() {
		return p_class1;
	}

	public void setP_class1(int p_class1) {
		this.p_class1 = p_class1;
	}

	public int getP_class2() {
		return p_class2;
	}

	public void setP_class2(int p_class2) {
		this.p_class2 = p_class2;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		if (null == p_name || p_name.trim().length() == 0) {
			this.p_name = "";
		}
		this.p_name = p_name;
	}

	public String getP_standrad() {
		return p_standrad;
	}

	public void setP_standrad(String p_standrad) {
		if (null == p_standrad || p_standrad.trim().length() == 0) {
			this.p_standrad = "";
		}
		this.p_standrad = p_standrad;
	}

	public String getP_begintime() {
		return p_begintime;
	}

	public void setP_begintime(String p_begintime) {
		if (null == p_begintime || p_begintime.trim().length() == 0) {
			this.p_begintime = "";
		}
		this.p_begintime = p_begintime;
	}

	public String getP_endtime() {
		return p_endtime;
	}

	public void setP_endtime(String p_endtime) {
		if (null == p_endtime || p_endtime.trim().length() == 0) {
			this.p_endtime = "";
		}
		this.p_endtime = p_endtime;
	}

	public String getP_harvesttime() {
		return p_harvesttime;
	}

	public void setP_harvesttime(String p_harvesttime) {
		if (null == p_harvesttime || p_harvesttime.trim().length() == 0) {
			this.p_harvesttime = "";
		}
		this.p_harvesttime = p_harvesttime;
	}

	public String getP_harvestarea() {
		return p_harvestarea;
	}

	public void setP_harvestarea(String p_harvestarea) {
		if (null == p_harvestarea || p_harvestarea.trim().length() == 0) {
			this.p_harvestarea = "";
		}
		this.p_harvestarea = p_harvestarea;
	}
	
	
}
