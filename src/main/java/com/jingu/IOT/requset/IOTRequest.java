/**  
*   
* 项目名称：IOT  
* 类名称：IOTRequest  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月13日 下午4:14:38  
* 修改人：jianghu  
* 修改时间：2018年4月13日 下午4:14:38  
* 修改备注： 下午4:14:38
* @version   
*   
*/ 
package com.jingu.IOT.requset;

/**

* @ClassName: IOTRequest
* @Description: TODO
* @author jianghu
* @date 2018年4月13日 下午4:14:38

*/
public class IOTRequest<T> {

	private String ckuid;
	private String cksid;
	private T param;
	private Integer pageNum;
	private Integer pageSize;
	
	
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public String getCkuid() {
		return ckuid;
	}

	public void setCkuid(String ckuid) {
		this.ckuid = ckuid;
	}

	public String getCksid() {
		return cksid;
	}

	public void setCksid(String cksid) {
		this.cksid = cksid;
	}

	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	
	
}
