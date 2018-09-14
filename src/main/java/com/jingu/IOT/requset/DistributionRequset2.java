/**  
*   
* 项目名称：IOT  
* 类名称：DistributionRequset2  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年4月2日 上午11:37:59  
* 修改人：jianghu  
* 修改时间：2018年4月2日 上午11:37:59  
* 修改备注： 上午11:37:59
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.DistributionEntity2;

/**

* @ClassName: DistributionRequset2
* @Description: TODO
* @author jianghu
* @date 2018年4月2日 上午11:37:59

*/
public class DistributionRequset2 extends DistributionEntity2 {

	private String ckuid;
	private String cksid;
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
	
	
}
