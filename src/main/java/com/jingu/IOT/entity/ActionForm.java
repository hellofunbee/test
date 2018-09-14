/**  
*   
* 项目名称：nxy  
* 类名称：A  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月8日 上午11:16:36  
* 修改人：jianghu  
* 修改时间：2017年8月8日 上午11:16:36  
* 修改备注： 上午11:16:36
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.io.Serializable;

/**

* @ClassName: A
* @Description: TODO
* @author jianghu
* @date 2017年8月8日 上午11:16:36

*/
public class ActionForm implements Serializable {
	
	/**
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	*/
	
	private static final long serialVersionUID = 1L;
	//private String id;
	private String channel;
	private String order;
	private int action;
	public ActionForm(String channel, String order, int action) {
		super();
		this.channel = channel;
		this.order = order;
		this.action = action;
	}
	public ActionForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		if (null == channel || channel.trim().length() == 0) {
			this.channel = "";
		}
		this.channel = channel;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		if (null == order || order.trim().length() == 0) {
			this.order = "";
		}
		this.order = order;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
	
}
