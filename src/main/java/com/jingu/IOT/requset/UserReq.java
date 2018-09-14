/**  
*   
* 项目名称：sxcms  
* 类名称：UserReq  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月17日 下午3:54:02  
* 修改人：jianghu  
* 修改时间：2017年10月17日 下午3:54:02  
* 修改备注： 下午3:54:02
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import java.util.List;

import com.jingu.IOT.entity.FileText;
import com.jingu.IOT.entity.UserEntity2;

/**

* @ClassName: UserReq
* @Description: TODO
* @author jianghu
* @date 2017年10月17日 下午3:54:02

*/
public class UserReq extends UserEntity2{

	private String ckuid;
	private String cksid;
	private String cverifycode;
	private String code;
	private int reset;
	private List<FileText> p_serproject;
	private List<FileText> p_seredproject;
	private List<FileText> p_seruser;
	private int start;
	private int pagesize=20;
	
	
	
	
	
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {

		this.start = start;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public List<FileText> getP_serproject() {
		return p_serproject;
	}
	public void setP_serproject(List<FileText> p_serproject) {
		this.p_serproject = p_serproject;
	}
	public List<FileText> getP_seredproject() {
		return p_seredproject;
	}
	public void setP_seredproject(List<FileText> p_seredproject) {
		this.p_seredproject = p_seredproject;
	}
	public List<FileText> getP_seruser() {
		return p_seruser;
	}
	public void setP_seruser(List<FileText> p_seruser) {
		this.p_seruser = p_seruser;
	}
	public int getReset() {
		return reset;
	}
	public void setReset(int reset) {
		this.reset = reset;
	}
	public String getCverifycode() {
		return cverifycode;
	}
	public void setCverifycode(String cverifycode) {
		if (null == cverifycode || cverifycode.trim().length() == 0) {
			this.cverifycode = "";
		}
		this.cverifycode = cverifycode;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		if (null == code || code.trim().length() == 0) {
			this.code = "";
		}
		this.code = code;
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
