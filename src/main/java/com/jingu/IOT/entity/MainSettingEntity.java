/**  
*   
* 项目名称：IOT  
* 类名称：MainSettingEntity  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月15日 下午2:24:58  
* 修改人：jianghu  
* 修改时间：2017年9月15日 下午2:24:58  
* 修改备注： 下午2:24:58
* @version   
*   
*/ 
package com.jingu.IOT.entity;

import java.util.Map;

import groovyjarjarantlr.collections.List;

/**

* @ClassName: MainSettingEntity
* @Description: TODO
* @author jianghu
* @date 2017年9月15日 下午2:24:58

*/
public class MainSettingEntity extends PointEntity {

	private Map<String, String> config;
	private Map<String, String> oldconfig;
	private List valueList;
	private List minList;
	private List maxList;

	
	
	public Map<String, String> getOldconfig() {
		return oldconfig;
	}
	public void setOldconfig(Map<String, String> oldconfig) {
		this.oldconfig = oldconfig;
	}
	public Map<String, String> getConfig() {
		return config;
	}
	public void setConfig(Map<String, String> config) {
		this.config = config;
	}
	public List getValueList() {
		return valueList;
	}
	public void setValueList(List valueList) {
		this.valueList = valueList;
	}
	public List getMinList() {
		return minList;
	}
	public void setMinList(List minList) {
		this.minList = minList;
	}
	public List getMaxList() {
		return maxList;
	}
	public void setMaxList(List maxList) {
		this.maxList = maxList;
	}
	
	
}
