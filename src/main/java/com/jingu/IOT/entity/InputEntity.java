/**  
*   
* 项目名称：IOT  
* 类名称：InputEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 下午6:33:53  
* 修改人：jianghu  
* 修改时间：2017年10月10日 下午6:33:53  
* 修改备注： 下午6:33:53
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: InputEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 下午6:33:53

*/
public class InputEntity {

	private int in_id,
	in_class1,
	in_class2,
	in_pid,
	start,
	tp_id,
	pagesize =20;
	
	private String in_ownername,
	in_mattername,
	in_total,
	in_pname,
	in_pstandrad,
	in_parea,
	in_time,
	p_begintime,
	p_endtime;
	
	private int appPagesize =6;
	private int app;
	
	



	public int getTp_id() {
		return tp_id;
	}
	public void setTp_id(int tp_id) {
		this.tp_id = tp_id;
	}
	public int getApp() {
		return app;
	}
	public void setApp(int app) {
		this.app = app;
	}
	public int getAppPagesize() {
		return appPagesize;
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPagesize() {
		return pagesize;
	}

	public int getIn_id() {
		return in_id;
	}

	public void setIn_id(int in_id) {
		this.in_id = in_id;
	}

	public int getIn_class1() {
		return in_class1;
	}

	public void setIn_class1(int in_class1) {
		this.in_class1 = in_class1;
	}

	public int getIn_class2() {
		return in_class2;
	}

	public void setIn_class2(int in_class2) {
		this.in_class2 = in_class2;
	}

	public int getIn_pid() {
		return in_pid;
	}

	public void setIn_pid(int in_pid) {
		this.in_pid = in_pid;
	}

	public String getIn_ownername() {
		return in_ownername;
	}

	public void setIn_ownername(String in_ownername) {
		if (null == in_ownername || in_ownername.trim().length() == 0) {
			this.in_ownername = "";
		}
		this.in_ownername = in_ownername;
	}

	public String getIn_mattername() {
		return in_mattername;
	}

	public void setIn_mattername(String in_mattername) {
		if (null == in_mattername || in_mattername.trim().length() == 0) {
			this.in_mattername = "";
		}
		this.in_mattername = in_mattername;
	}

	public String getIn_total() {
		return in_total;
	}

	public void setIn_total(String in_total) {
		if (null == in_total || in_total.trim().length() == 0) {
			this.in_total = "";
		}
		this.in_total = in_total;
	}

	public String getIn_pname() {
		return in_pname;
	}

	public void setIn_pname(String in_pname) {
		if (null == in_pname || in_pname.trim().length() == 0) {
			this.in_pname = "";
		}
		this.in_pname = in_pname;
	}

	public String getIn_pstandrad() {
		return in_pstandrad;
	}

	public void setIn_pstandrad(String in_pstandrad) {
		if (null == in_pstandrad || in_pstandrad.trim().length() == 0) {
			this.in_pstandrad = "";
		}
		this.in_pstandrad = in_pstandrad;
	}

	public String getIn_parea() {
		return in_parea;
	}

	public void setIn_parea(String in_parea) {
		if (null == in_parea || in_parea.trim().length() == 0) {
			this.in_parea = "";
		}
		this.in_parea = in_parea;
	}

	public String getIn_time() {
		return in_time;
	}

	public void setIn_time(String in_time) {
		if (null == in_time || in_time.trim().length() == 0) {
			this.in_time = "";
		}
		this.in_time = in_time;
	}
	
}
