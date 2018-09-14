/**  
*   
* 项目名称：sxcms  
* 类名称：ProjectEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月16日 下午2:24:07  
* 修改人：jianghu  
* 修改时间：2017年10月16日 下午2:24:07  
* 修改备注： 下午2:24:07
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

import com.jingu.IOT.inter.Timing;


/**

* @ClassName: ProjectEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月16日 下午2:24:07

*/
public class ProjectEntity implements Timing {

	private int p_id,p_class1,p_class2,p_check,p_state,p_type,p_uid,
	pagesize =10,
	start,
	a_adminid;
	
	private String p_name,
	p_charge,
	p_chargepic,
	p_chargepos,
	p_chargeemail,
	p_videourl,
	p_cover,
	p_content,
	p_member,
	p_authorname,
	p_typename,
	a_adminname="",
	p_subtitles="";
	
	
	
	
	public String getP_subtitles() {
		return p_subtitles;
	}

	public void setP_subtitles(String p_subtitles) {
		if (null == p_subtitles || p_subtitles.trim().length() == 0) {
			this.p_subtitles = "";
		}
		this.p_subtitles = p_subtitles;
	}

	public int getA_adminid() {
		return a_adminid;
	}

	public void setA_adminid(int a_adminid) {
		this.a_adminid = a_adminid;
	}

	public String getA_adminname() {
		return a_adminname;
	}

	public void setA_adminname(String a_adminname) {
		if (null == a_adminname || a_adminname.trim().length() == 0) {
			this.a_adminname = "";
		}
		this.a_adminname = a_adminname;
	}


	private List<FileText> content;
	private List<MemberEntity> member;
	
	

	public String getP_typename() {
		return p_typename;
	}

	public void setP_typename(String p_typename) {
		if (null == p_typename || p_typename.trim().length() == 0) {
			this.p_typename = "";
		}
		this.p_typename = p_typename;
	}

	public String getP_authorname() {
		return p_authorname;
	}

	public void setP_authorname(String p_authorname) {
		if (null == p_authorname || p_authorname.trim().length() == 0) {
			this.p_authorname = "";
		}
		this.p_authorname = p_authorname;
	}

	public int getP_uid() {
		return p_uid;
	}

	public void setP_uid(int p_uid) {
		this.p_uid = p_uid;
	}

	public int getPagesize() {
		return pagesize;
	}
//
//	public void setPagesize(int pagesize) {
//		this.pagesize = pagesize;
//	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}


	
	public String getP_member() {
		return p_member;
	}

	public void setP_member(String p_member) {
		if (null == p_member || p_member.trim().length() == 0) {
			this.p_member = "";
		}
		this.p_member = p_member;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
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

	public int getP_check() {
		return p_check;
	}

	public void setP_check(int p_check) {
		this.p_check = p_check;
	}

	public int getP_state() {
		return p_state;
	}

	public void setP_state(int p_state) {
		this.p_state = p_state;
	}

	public int getP_type() {
		return p_type;
	}

	public void setP_type(int p_type) {
		this.p_type = p_type;
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

	public String getP_charge() {
		return p_charge;
	}

	public void setP_charge(String p_charge) {
		if (null == p_charge || p_charge.trim().length() == 0) {
			this.p_charge = "";
		}
		this.p_charge = p_charge;
	}

	public String getP_chargepic() {
		return p_chargepic;
	}

	public void setP_chargepic(String p_chargepic) {
		if (null == p_chargepic || p_chargepic.trim().length() == 0) {
			this.p_chargepic = "";
		}
		this.p_chargepic = p_chargepic;
	}

	public String getP_chargepos() {
		return p_chargepos;
	}

	public void setP_chargepos(String p_chargepos) {
		if (null == p_chargepos || p_chargepos.trim().length() == 0) {
			this.p_chargepos = "";
		}
		this.p_chargepos = p_chargepos;
	}

	public String getP_chargeemail() {
		return p_chargeemail;
	}

	public void setP_chargeemail(String p_chargeemail) {
		if (null == p_chargeemail || p_chargeemail.trim().length() == 0) {
			this.p_chargeemail = "";
		}
		this.p_chargeemail = p_chargeemail;
	}

	public String getP_videourl() {
		return p_videourl;
	}

	public void setP_videourl(String p_videourl) {
		if (null == p_videourl || p_videourl.trim().length() == 0) {
			this.p_videourl = "";
		}
		this.p_videourl = p_videourl;
	}

	public String getP_cover() {
		return p_cover;
	}

	public void setP_cover(String p_cover) {
		if (null == p_cover || p_cover.trim().length() == 0) {
			this.p_cover = "";
		}
		this.p_cover = p_cover;
	}


	public List<FileText> getContent() {
		return content;
	}

	public void setContent(List<FileText> content) {
		this.content = content;
	}

	public String getP_content() {
		return p_content;
	}

	public void setP_content(String p_content) {
		if (null == p_content || p_content.trim().length() == 0) {
			this.p_content = "";
		}
		this.p_content = p_content;
	}

	public List<MemberEntity> getMember() {
		return member;
	}

	public void setMember(List<MemberEntity> member) {
		this.member = member;
	}

	/* (non-Javadoc)
	 * @see com.jingu.sxcms.inter.Timing#getTime()
	 */
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return null;
	}








	
	
}
