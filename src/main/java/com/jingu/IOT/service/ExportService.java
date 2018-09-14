/**  
*   
* 项目名称：IOT  
* 类名称：ExportService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月11日 下午2:27:54  
* 修改人：jianghu  
* 修改时间：2017年10月11日 下午2:27:54  
* 修改备注： 下午2:27:54
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.jingu.IOT.entity.ExportEntity;
import com.jingu.IOT.util.ToolUtil;

import net.sf.json.JSONObject;

/**

* @ClassName: ExportService
* @Description: TODO
* @author jianghu
* @date 2017年10月11日 下午2:27:54

*/
public class ExportService {

	// 专家列表,可查看详细信息
//	public List<ExportEntity> listExport(ExportEntity ee){
//		RestTemplate restTemplate=new RestTemplate();
//		String url = ToolUtil.EXPORTURL+String.valueOf("/listExport");
//		JSONObject jsonObject=restTemplate.postForObject(url, ee, JSONObject.class);
//		boolean b = jsonObject.getBoolean("success");
//		if(!b){
//			return null;
//		}
//		Object object = jsonObject.get("object");
//		return null;
//	}
//	
//	// 文章列表,可查看详细信息
//	public List<ExportEntity> listPaperByExportId(ExportEntity ee){
//		RestTemplate restTemplate=new RestTemplate();
//		String url = ToolUtil.EXPORTURL+String.valueOf("/listExport");
//		JSONObject jsonObject=restTemplate.postForObject(url, ee, JSONObject.class);
//		boolean b = jsonObject.getBoolean("success");
//		if(!b){
//			return null;
//		}
//		Object object = jsonObject.get("object");
//		return null;
//	}
	
}
