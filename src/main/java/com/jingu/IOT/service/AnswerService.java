/**  
*   
* 项目名称：IOT  
* 类名称：AnswerService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月18日 下午2:51:35  
* 修改人：jianghu  
* 修改时间：2017年10月18日 下午2:51:35  
* 修改备注： 下午2:51:35
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jingu.IOT.entity.AnswerQuestion;
import com.jingu.IOT.util.ToolUtil;

import net.sf.json.JSONObject;

/**

* @ClassName: AnswerService
* @Description: TODO
* @author jianghu
* @date 2017年10月18日 下午2:51:35

*/
@Component
public class AnswerService {

	public int addAnswerQuestion(AnswerQuestion aq){
		RestTemplate restTemplate=new RestTemplate();
		JSONObject jsonObject=restTemplate.postForObject(ToolUtil.CMSURL+"/addAnswerQuestion", aq, JSONObject.class);
		if(jsonObject.getBoolean("success")){
			return 1;
		}
		return 0;
	}

	public List<Map<String, Object>> listQuestion(AnswerQuestion aq){
		RestTemplate restTemplate=new RestTemplate();
		JSONObject jsonObject=restTemplate.postForObject(ToolUtil.CMSURL+"/listQuestion", aq, JSONObject.class);
		if(jsonObject.getBoolean("success")){
			return jsonObject.getJSONArray("object");
		}
		return null;
	}
	
	public List<Map<String, Object>> listAnswerQuestion(AnswerQuestion aq){
		RestTemplate restTemplate=new RestTemplate();
		JSONObject jsonObject=restTemplate.postForObject(ToolUtil.CMSURL+"/listAnswerQuestion", aq, JSONObject.class);
		if(jsonObject.getBoolean("success")){
			return jsonObject.getJSONArray("object");
		}
		return null;
	}
}
