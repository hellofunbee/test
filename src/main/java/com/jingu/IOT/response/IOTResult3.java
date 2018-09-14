/**  
*   
* 项目名称：IOT  
* 类名称：IOTResult3  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月9日 下午4:18:52  
* 修改人：jianghu  
* 修改时间：2018年4月9日 下午4:18:52  
* 修改备注： 下午4:18:52
* @version   
*   
*/ 
package com.jingu.IOT.response;

/**

* @ClassName: IOTResult3
* @Description: TODO
* @author jianghu
* @date 2018年4月9日 下午4:18:52

*/
public class IOTResult3<T> extends IOTResult {

	private T value;

	public IOTResult3(boolean success, String msg, Object object, int state, T value) {
		super(success, msg, object, state);
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	
}
