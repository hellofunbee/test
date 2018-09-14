/**  
*   
* 项目名称：sxcms  
* 类名称：AnswerQuestion  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月17日 下午8:20:37  
* 修改人：jianghu  
* 修改时间：2017年10月17日 下午8:20:37  
* 修改备注： 下午8:20:37
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: AnswerQuestion
* @Description: TODO
* @author jianghu
* @date 2017年10月17日 下午8:20:37

*/
public class AnswerQuestion {

	private int aq_id;
	private int aq_qid;
	private String aq_content;
	private int aq_uid;
	private int aq_pid;
	private int aq_type;
	private int aq_state;
	
	
	public int getAq_type() {
		return aq_type;
	}
	public void setAq_type(int aq_type) {
		this.aq_type = aq_type;
	}
	public int getAq_state() {
		return aq_state;
	}
	public void setAq_state(int aq_state) {
		this.aq_state = aq_state;
	}
	public int getAq_id() {
		return aq_id;
	}
	public void setAq_id(int aq_id) {
		this.aq_id = aq_id;
	}
	public int getAq_qid() {
		return aq_qid;
	}
	public void setAq_qid(int aq_qid) {
		this.aq_qid = aq_qid;
	}
	public String getAq_content() {
		return aq_content;
	}
	public void setAq_content(String aq_content) {
		if (null == aq_content || aq_content.trim().length() == 0) {
			this.aq_content = "";
		}
		this.aq_content = aq_content;
	}
	public int getAq_uid() {
		return aq_uid;
	}
	public void setAq_uid(int aq_uid) {
		this.aq_uid = aq_uid;
	}
	public int getAq_pid() {
		return aq_pid;
	}
	public void setAq_pid(int aq_pid) {
		this.aq_pid = aq_pid;
	}
	
	
}
