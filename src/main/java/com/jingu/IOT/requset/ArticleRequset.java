/**  
*   
* 项目名称：sxcms  
* 类名称：ArticleRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月17日 上午9:46:48  
* 修改人：jianghu  
* 修改时间：2017年10月17日 上午9:46:48  
* 修改备注： 上午9:46:48
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.ArticleEntity;

/**

* @ClassName: ArticleRequset
* @Description: TODO
* @author jianghu
* @date 2017年10月17日 上午9:46:48

*/
public class ArticleRequset extends ArticleEntity {

	private String ckuid;
	private String cksid;
	public String getCkuid() {
		return ckuid;
	}
	public void setCkuid(String ckuid) {
		if (null == ckuid || ckuid.trim().length() == 0) {
			this.ckuid = "";
		}
		this.ckuid = ckuid;
	}
	public String getCksid() {
		return cksid;
	}
	public void setCksid(String cksid) {
		if (null == cksid || cksid.trim().length() == 0) {
			this.cksid = "";
		}
		this.cksid = cksid;
	}
	
	
}
