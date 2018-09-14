/**  
*   
* 项目名称：sxcms  
* 类名称：FileText  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月18日 下午4:45:52  
* 修改人：jianghu  
* 修改时间：2017年10月18日 下午4:45:52  
* 修改备注： 下午4:45:52
* @version   
*   
*/ 
package com.jingu.IOT.entity;

/**

* @ClassName: FileText
* @Description: TODO
* @author jianghu
* @date 2017年10月18日 下午4:45:52

*/
public class FileText {

	private String file;
	private String text;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		if (null == file || file.trim().length() == 0) {
			this.file = "";
		}
		this.file = file;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		if (null == text || text.trim().length() == 0) {
			this.text = "";
		}
		this.text = text;
	}
	
	
}
