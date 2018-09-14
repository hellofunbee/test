/**  
*   
* 项目名称：IOT  
* 类名称：IOTResult2  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月1日 下午1:15:21  
* 修改人：jianghu  
* 修改时间：2017年11月1日 下午1:15:21  
* 修改备注： 下午1:15:21
* @version   
*   
*/ 
package com.jingu.IOT.response;

/**

* @ClassName: IOTResult2
* @Description: TODO
* @author jianghu
* @date 2017年11月1日 下午1:15:21

*/
public class IOTResult2 extends IOTResult {

	private int totalpage;
	private int totalcount;
	
	
	
	public IOTResult2(boolean success, String msg, Object object, int state, int totalpage, int totalcount) {
		super(success, msg, object, state);
		this.totalpage = totalpage;
		this.totalcount = totalcount;
	}
	
	
	
	public IOTResult2() {
		super();
		// TODO Auto-generated constructor stub
	}



	public IOTResult2(boolean success, String msg, Object object, int state) {
		super(success, msg, object, state);
		// TODO Auto-generated constructor stub
	}



	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	
	
}
