/**  
*   
* 项目名称：sxcms  
* 类名称：CheckEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月25日 下午9:09:04  
* 修改人：jianghu  
* 修改时间：2017年11月25日 下午9:09:04  
* 修改备注： 下午9:09:04
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: CheckEntity
* @Description: TODO
* @author jianghu
* @date 2017年11月25日 下午9:09:04

*/
public class CheckEntity {

	private int c_id;
	private int a_aid;
	private int a_acheck;
	private String a_acheckstate;
	private int a_uid;
	private String a_checkp;
	private String a_ctime;
	private String a_auhtorname;
	
	
	
	
	public CheckEntity() {
		// TODO Auto-generated constructor stub
	}
	public CheckEntity(int c_id, int a_aid, int a_acheck, String a_acheckstate, int a_uid, String a_checkp, String a_ctime,
			String a_auhtorname) {
		super();
		this.c_id = c_id;
		this.a_aid = a_aid;
		this.a_acheck = a_acheck;
		this.a_acheckstate = a_acheckstate;
		this.a_uid = a_uid;
		this.a_checkp = a_checkp;
		this.a_ctime = a_ctime;
		this.a_auhtorname = a_auhtorname;
	}
	public String getA_auhtorname() {
		return a_auhtorname;
	}
	public void setA_auhtorname(String a_auhtorname) {
		if (null == a_auhtorname || a_auhtorname.trim().length() == 0) {
			this.a_auhtorname = "";
		}
		this.a_auhtorname = a_auhtorname;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getA_aid() {
		return a_aid;
	}
	public void setA_aid(int a_aid) {
		this.a_aid = a_aid;
	}
	public int getA_acheck() {
		return a_acheck;
	}
	public void setA_acheck(int a_acheck) {
		this.a_acheck = a_acheck;
	}

	public String getA_acheckstate() {
		return a_acheckstate;
	}
	public void setA_acheckstate(String a_acheckstate) {
		if (null == a_acheckstate || a_acheckstate.trim().length() == 0) {
			this.a_acheckstate = "";
		}
		this.a_acheckstate = a_acheckstate;
	}
	public int getA_uid() {
		return a_uid;
	}
	public void setA_uid(int a_uid) {
		this.a_uid = a_uid;
	}
	public String getA_checkp() {
		return a_checkp;
	}
	public void setA_checkp(String a_checkp) {
		if (null == a_checkp || a_checkp.trim().length() == 0) {
			this.a_checkp = "";
		}
		this.a_checkp = a_checkp;
	}
	public String getA_ctime() {
		return a_ctime;
	}
	public void setA_ctime(String a_ctime) {
		if (null == a_ctime || a_ctime.trim().length() == 0) {
			this.a_ctime = "";
		}
		this.a_ctime = a_ctime;
	}

	
	
	
}
