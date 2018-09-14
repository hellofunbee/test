/**  
*   
* 项目名称：IOT  
* 类名称：RelationShipEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月20日 下午1:26:58  
* 修改人：jianghu  
* 修改时间：2017年11月20日 下午1:26:58  
* 修改备注： 下午1:26:58
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: RelationShipEntity
* @Description: TODO
* @author jianghu
* @date 2017年11月20日 下午1:26:58

*/
public class RelationShipEntity {

	private String deviceId,producername,supervisename,ownername,professorname;
	
	private int producerid,superviseid,ownerid,professorid,id,start;
	
	private int pagesize =15;
	
	

	
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getPagesize() {
		return pagesize;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getProducername() {
		return producername;
	}

	public void setProducername(String producername) {
		if (null == producername || producername.trim().length() == 0) {
			this.producername = "";
		}
		this.producername = producername;
	}

	public String getSupervisename() {
		return supervisename;
	}

	public void setSupervisename(String supervisename) {
		if (null == supervisename || supervisename.trim().length() == 0) {
			this.supervisename = "";
		}
		this.supervisename = supervisename;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		if (null == ownername || ownername.trim().length() == 0) {
			this.ownername = "";
		}
		this.ownername = ownername;
	}

	public String getProfessorname() {
		return professorname;
	}

	public void setProfessorname(String professorname) {
		if (null == professorname || professorname.trim().length() == 0) {
			this.professorname = "";
		}
		this.professorname = professorname;
	}

	public int getProducerid() {
		return producerid;
	}

	public void setProducerid(int producerid) {
		this.producerid = producerid;
	}

	public int getSuperviseid() {
		return superviseid;
	}

	public void setSuperviseid(int superviseid) {
		this.superviseid = superviseid;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public int getProfessorid() {
		return professorid;
	}

	public void setProfessorid(int professorid) {
		this.professorid = professorid;
	}
	
	
}
