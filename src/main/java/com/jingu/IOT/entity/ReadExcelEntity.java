/**  
*   
* 项目名称：IOT  
* 类名称：ReadExcelEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年3月29日 下午5:40:25  
* 修改人：jianghu  
* 修改时间：2018年3月29日 下午5:40:25  
* 修改备注： 下午5:40:25
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: ReadExcelEntity
* @Description: TODO
* @author jianghu
* @date 2018年3月29日 下午5:40:25

*/
public class ReadExcelEntity {

	private String fileName;
	private String ckuid;
	private String cksid;
	private int type;
	
	
	
	public ReadExcelEntity(String fileName, String ckuid, String cksid) {
		super();
		this.fileName = fileName;
		this.ckuid = ckuid;
		this.cksid = cksid;
	}
	
	public ReadExcelEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCkuid() {
		return ckuid;
	}
	public void setCkuid(String ckuid) {
		this.ckuid = ckuid;
	}
	public String getCksid() {
		return cksid;
	}
	public void setCksid(String cksid) {
		this.cksid = cksid;
	}
	
	
}
