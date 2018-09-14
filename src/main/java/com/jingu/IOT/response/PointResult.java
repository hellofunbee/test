/**  
*   
* 项目名称：IOT  
* 类名称：PointResult  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月26日 上午10:17:31  
* 修改人：jianghu  
* 修改时间：2017年10月26日 上午10:17:31  
* 修改备注： 上午10:17:31
* @version   
*   
*/ 
package com.jingu.IOT.response;

/**

* @ClassName: PointResult
* @Description: TODO
* @author jianghu
* @date 2017年10月26日 上午10:17:31

*/
public class PointResult {

	private boolean success;
	private Object  object;
	
	
	
	
	public PointResult(boolean success, Object object) {
		super();
		this.success = success;
		this.object = object;
	}
	
	
	public PointResult() {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {

		this.object = object;
	}
	
	
	
}
