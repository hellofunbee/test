/**  
*   
* 项目名称：sxcms  
* 类名称：ArticleEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月16日 下午9:23:36  
* 修改人：jianghu  
* 修改时间：2017年10月16日 下午9:23:36  
* 修改备注： 下午9:23:36
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.List;

/**

* @ClassName: ArticleEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月16日 下午9:23:36

*/
public class ArticleEntity {

	private int a_id,//id主键
	a_type,		// 1 政策解读   2  科技   3 专家服务   (查看性情传id)
	a_state,	//状态
	a_check,	// 审核状态 默认为1   1 未通过  2 已通过
	a_class1,	// 一级分类
	a_class2,	// 二级分裂
	a_uid,		//发布人的id	
	start,		//页数
	pagesize =10,//每页的条数
	u_type,		//类型 区别专家管理员   1 管理员  2 专家
	a_adminid;	// 审核人id
	
	private String a_name="",	//文章名字
	a_authorname="",			//作者名字
	a_url="",					//视频连接
	a_content="",				//内容
	a_reading="",				//解读
	a_cover="",					//封面
	a_time="",					//发布时间
	a_object="",				//面向对象
	a_source="",				//来源
	a_typename="",				//政策解读 科技前沿 专家服务
	a_adminname="",				//审核人名字
	a_questionnaire="", 		//调查问卷
	a_videoinfo="";				//上传的视频
	
	
	
	

	public String getA_videoinfo() {
		return a_videoinfo;
	}

	public void setA_videoinfo(String a_videoinfo) {
		if (null == a_videoinfo || a_videoinfo.trim().length() == 0) {
			this.a_videoinfo = "";
		}
		this.a_videoinfo = a_videoinfo;
	}

	public String getA_questionnaire() {
		return a_questionnaire;
	}

	public void setA_questionnaire(String a_questionnaire) {
		if (null == a_questionnaire || a_questionnaire.trim().length() == 0) {
			this.a_questionnaire = "";
		}
		this.a_questionnaire = a_questionnaire;
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

	public int getA_adminid() {
		return a_adminid;
	}

	public void setA_adminid(int a_adminid) {
		this.a_adminid = a_adminid;
	}

	private List<FileText> content;
	
	
	
	public int getU_type() {
		return u_type;
	}

	public void setU_type(int u_type) {
	
		this.u_type = u_type;
	}

	public List<FileText> getContent() {
		return content;
	}

	public void setContent(List<FileText> content) {
		this.content = content;
	}

	
	
	public String getA_typename() {
		return a_typename;
	}

	public void setA_typename(String a_typename) {
		if (null == a_typename || a_typename.trim().length() == 0) {
			this.a_typename = "";
		}
		this.a_typename = a_typename;
	}

	public String getA_object() {
		return a_object;
	}

	public void setA_object(String a_object) {
		if (null == a_object || a_object.trim().length() == 0) {
			this.a_object = "";
		}
		this.a_object = a_object;
	}

	public String getA_source() {
		return a_source;
	}

	public void setA_source(String a_source) {
		if (null == a_source || a_source.trim().length() == 0) {
			this.a_source = "";
		}
		this.a_source = a_source;
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

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getA_time() {
		return a_time;
	}

	public void setA_time(String a_time) {
		if (null == a_time || a_time.trim().length() == 0) {
			this.a_time = "";
		}
		this.a_time = a_time;
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public int getA_type() {
		return a_type;
	}

	public void setA_type(int a_type) {
		this.a_type = a_type;
	}

	public int getA_state() {
		return a_state;
	}

	public void setA_state(int a_state) {
		this.a_state = a_state;
	}

	public int getA_check() {
		return a_check;
	}

	public void setA_check(int a_check) {
		this.a_check = a_check;
	}

	public int getA_class1() {
		return a_class1;
	}

	public void setA_class1(int a_class1) {
		this.a_class1 = a_class1;
	}

	public int getA_class2() {
		return a_class2;
	}

	public void setA_class2(int a_class2) {
		this.a_class2 = a_class2;
	}

	public int getA_uid() {
		return a_uid;
	}

	public void setA_uid(int a_uid) {
		this.a_uid = a_uid;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		if (null == a_name || a_name.trim().length() == 0) {
			this.a_name = "";
		}
		this.a_name = a_name;
	}

	public String getA_authorname() {
		return a_authorname;
	}

	public void setA_authorname(String a_authorname) {
		if (null == a_authorname || a_authorname.trim().length() == 0) {
			this.a_authorname = "";
		}
		this.a_authorname = a_authorname;
	}

	public String getA_url() {
		return a_url;
	}

	public void setA_url(String a_url) {
		if (null == a_url || a_url.trim().length() == 0) {
			this.a_url = "";
		}
		this.a_url = a_url;
	}

	public String getA_content() {
		return a_content;
	}

	public void setA_content(String a_content) {
		if (null == a_content || a_content.trim().length() == 0) {
			this.a_content = "";
		}
		this.a_content = a_content;
	}

	public String getA_reading() {
		return a_reading;
	}

	public void setA_reading(String a_reading) {
		if (null == a_reading || a_reading.trim().length() == 0) {
			this.a_reading = "";
		}
		this.a_reading = a_reading;
	}

	public String getA_cover() {
		return a_cover;
	}

	public void setA_cover(String a_cover) {
		if (null == a_cover || a_cover.trim().length() == 0) {
			this.a_cover = "";
		}
		this.a_cover = a_cover;
	}

	/* (non-Javadoc)
	 * @see com.jingu.sxcms.inter.Timing#getTime()
	 */
//	@Override
//	public String getTime() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
}
