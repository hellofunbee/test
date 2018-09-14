/**  
*   
* 项目名称：sxcms  
* 类名称：AnswerQuestionRequset  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月18日 下午12:08:46  
* 修改人：jianghu  
* 修改时间：2017年10月18日 下午12:08:46  
* 修改备注： 下午12:08:46
* @version   
*   
*/ 
package com.jingu.IOT.requset;

import com.jingu.IOT.entity.AnswerQuestion;

/**

* @ClassName: AnswerQuestionRequset
* @Description: TODO
* @author jianghu
* @date 2017年10月18日 下午12:08:46

*/
public class AnswerQuestionRequset extends AnswerQuestion {

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
