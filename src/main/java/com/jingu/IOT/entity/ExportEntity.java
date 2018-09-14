/**  
*   
* 项目名称：IOT  
* 类名称：ExportEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月11日 下午2:29:11  
* 修改人：jianghu  
* 修改时间：2017年10月11日 下午2:29:11  
* 修改备注： 下午2:29:11
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: ExportEntity
* @Description: TODO
* @author jianghu
* @date 2017年10月11日 下午2:29:11

*/
public class ExportEntity {

	private int e_id,
	e_sex,
	e_age,
	e_type,
	e_state;
	
	private String e_name,
	e_position,             //职务  学历
	e_field, 
	e_picture,
	e_phone,
	e_email,
	e_brief,
	e_class1,
	e_class2,
	e_serproject,
	e_seredproject,
	e_user;

	public String getE_picture() {
		return e_picture;
	}

	public void setE_picture(String e_picture) {
		if (null == e_picture || e_picture.trim().length() == 0) {
			this.e_picture = "";
		}
		this.e_picture = e_picture;
	}

	public int getE_id() {
		return e_id;
	}

	public void setE_id(int e_id) {
		this.e_id = e_id;
	}

	public int getE_sex() {
		return e_sex;
	}

	public void setE_sex(int e_sex) {
		this.e_sex = e_sex;
	}

	public int getE_age() {
		return e_age;
	}

	public void setE_age(int e_age) {
		this.e_age = e_age;
	}

	public int getE_type() {
		return e_type;
	}

	public void setE_type(int e_type) {
		this.e_type = e_type;
	}

	public int getE_state() {
		return e_state;
	}

	public void setE_state(int e_state) {
		this.e_state = e_state;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		if (null == e_name || e_name.trim().length() == 0) {
			this.e_name = "";
		}
		this.e_name = e_name;
	}

	public String getE_position() {
		return e_position;
	}

	public void setE_position(String e_position) {
		if (null == e_position || e_position.trim().length() == 0) {
			this.e_position = "";
		}
		this.e_position = e_position;
	}

	public String getE_field() {
		return e_field;
	}

	public void setE_field(String e_field) {
		if (null == e_field || e_field.trim().length() == 0) {
			this.e_field = "";
		}
		this.e_field = e_field;
	}

	public String getE_phone() {
		return e_phone;
	}

	public void setE_phone(String e_phone) {
		if (null == e_phone || e_phone.trim().length() == 0) {
			this.e_phone = "";
		}
		this.e_phone = e_phone;
	}

	public String getE_email() {
		return e_email;
	}

	public void setE_email(String e_email) {
		if (null == e_email || e_email.trim().length() == 0) {
			this.e_email = "";
		}
		this.e_email = e_email;
	}

	public String getE_brief() {
		return e_brief;
	}

	public void setE_brief(String e_brief) {
		if (null == e_brief || e_brief.trim().length() == 0) {
			this.e_brief = "";
		}
		this.e_brief = e_brief;
	}

	public String getE_class1() {
		return e_class1;
	}

	public void setE_class1(String e_class1) {
		if (null == e_class1 || e_class1.trim().length() == 0) {
			this.e_class1 = "";
		}
		this.e_class1 = e_class1;
	}

	public String getE_class2() {
		return e_class2;
	}

	public void setE_class2(String e_class2) {
		if (null == e_class2 || e_class2.trim().length() == 0) {
			this.e_class2 = "";
		}
		this.e_class2 = e_class2;
	}

	public String getE_serproject() {
		return e_serproject;
	}

	public void setE_serproject(String e_serproject) {
		if (null == e_serproject || e_serproject.trim().length() == 0) {
			this.e_serproject = "";
		}
		this.e_serproject = e_serproject;
	}

	public String getE_seredproject() {
		return e_seredproject;
	}

	public void setE_seredproject(String e_seredproject) {
		if (null == e_seredproject || e_seredproject.trim().length() == 0) {
			this.e_seredproject = "";
		}
		this.e_seredproject = e_seredproject;
	}

	public String getE_user() {
		return e_user;
	}

	public void setE_user(String e_user) {
		if (null == e_user || e_user.trim().length() == 0) {
			this.e_user = "";
		}
		this.e_user = e_user;
	}
	
}
