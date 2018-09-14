/**  
*   
* 项目名称：sxcms  
* 类名称：MemberEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月18日 下午4:55:28  
* 修改人：jianghu  
* 修改时间：2017年10月18日 下午4:55:28  
* 修改备注： 下午4:55:28
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: MemberEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月18日 下午4:55:28

*/
public class MemberEntity {

	private String p_memname;
	private String p_mempic;
	private String p_mempos;
	
	
	
	public MemberEntity() {
		// TODO Auto-generated constructor stub
	}
	public MemberEntity(String p_memname, String p_mempic, String p_mempos) {
		this.p_memname = p_memname;
		this.p_mempic = p_mempic;
		this.p_mempos = p_mempos;
	}
	public String getP_memname() {
		return p_memname;
	}
	public void setP_memname(String p_memname) {
		if (null == p_memname || p_memname.trim().length() == 0) {
			this.p_memname = "";
		}
		this.p_memname = p_memname;
	}
	public String getP_mempic() {
		return p_mempic;
	}
	public void setP_mempic(String p_mempic) {
		if (null == p_mempic || p_mempic.trim().length() == 0) {
			this.p_mempic = "";
		}
		this.p_mempic = p_mempic;
	}
	public String getP_mempos() {
		return p_mempos;
	}
	public void setP_mempos(String p_mempos) {
		if (null == p_mempos || p_mempos.trim().length() == 0) {
			this.p_mempos = "";
		}
		this.p_mempos = p_mempos;
	}
	
	
}
