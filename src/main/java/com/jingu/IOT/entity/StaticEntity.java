/**  
*   
* 项目名称：IOT  
* 类名称：StaticEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月29日 下午5:19:37  
* 修改人：jianghu  
* 修改时间：2017年11月29日 下午5:19:37  
* 修改备注： 下午5:19:37
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.io.Serializable;
import java.util.List;

/**

* @ClassName: StaticEntity
* @Description: TODO
* @author jianghu
* @date 2017年11月29日 下午5:19:37

*/
public class StaticEntity implements Serializable {
	
	private List<String> deviceList;
	
	private List<String> channelList;
	
	private String beginTime;
	
	private String endTime;
	
	

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<String> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<String> deviceList) {
		this.deviceList = deviceList;
	}

	public List<String> getChannelList() {
		return channelList;
	}

	public void setChannelList(List<String> channelList) {
		this.channelList = channelList;
	}
	
	
	

}
