/**  
*   
* 项目名称：IOT  
* 类名称：AlarmRuleEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月13日 下午1:54:49  
* 修改人：jianghu  
* 修改时间：2018年4月13日 下午1:54:49  
* 修改备注： 下午1:54:49
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: AlarmRuleEntity
* @Description: TODO
* @author jianghu
* @date 2018年4月13日 下午1:54:49

*/
public class AlarmRuleEntity {

	private Integer ala_id;
	private String ala_name;
	private String ala_channel;
	private String deviceid;
	private String ala_range;
	private String ala_state;
	private Double ala_up;
	private Double ala_low;
	private Integer ala_producer;
	private Integer ala_supervisor;
	private String	ala_content;
	private String ala_index;
		private String ala_grade;
	
	
	
	public String getAla_grade() {
		return ala_grade;
	}

	public void setAla_grade(String ala_grade) {
		this.ala_grade = ala_grade;
	}

	public AlarmRuleEntity() {
		super();
	}
	
	public AlarmRuleEntity(Integer ala_id, String ala_name, String ala_channel, String deviceid, String range,
			String ala_state, Double ala_up, Double ala_low, Integer ala_producer, Integer ala_supervisor,
			String ala_content, String ala_index,String ala_grade) {
		super();
		this.ala_id = ala_id;
		this.ala_name = ala_name;
		this.ala_channel = ala_channel;
		this.deviceid = deviceid;
		this.ala_range = range;
		this.ala_state = ala_state;
		this.ala_up = ala_up;
		this.ala_low = ala_low;
		this.ala_producer = ala_producer;
		this.ala_supervisor = ala_supervisor;
		this.ala_content = ala_content;
		this.ala_index = ala_index;
		this.ala_grade = ala_grade;
	}

	public Integer getAla_id() {
		return ala_id;
	}

	public void setAla_id(Integer ala_id) {
		this.ala_id = ala_id;
	}

	public String getAla_name() {
		return ala_name;
	}

	public void setAla_name(String ala_name) {
		this.ala_name = ala_name;
	}

	public String getAla_channel() {
		return ala_channel;
	}

	public void setAla_channel(String ala_channel) {
		this.ala_channel = ala_channel;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getAla_range() {
		return ala_range;
	}

	public void setAla_range(String ala_range) {
		this.ala_range = ala_range;
	}

	public String getAla_state() {
		return ala_state;
	}

	public void setAla_state(String ala_state) {
		this.ala_state = ala_state;
	}

	public Double getAla_up() {
		return ala_up;
	}

	public void setAla_up(Double ala_up) {
		this.ala_up = ala_up;
	}

	public Double getAla_low() {
		return ala_low;
	}

	public void setAla_low(Double ala_low) {
		this.ala_low = ala_low;
	}

	public Integer getAla_producer() {
		return ala_producer;
	}

	public void setAla_producer(Integer ala_producer) {
		this.ala_producer = ala_producer;
	}

	public Integer getAla_supervisor() {
		return ala_supervisor;
	}

	public void setAla_supervisor(Integer ala_supervisor) {
		this.ala_supervisor = ala_supervisor;
	}

	public String getAla_content() {
		return ala_content;
	}

	public void setAla_content(String ala_content) {
		this.ala_content = ala_content;
	}

	public String getAla_index() {
		return ala_index;
	}

	public void setAla_index(String ala_index) {
		this.ala_index = ala_index;
	}
}
