/**  
*   
* 项目名称：sxcms  
* 类名称：AnswerController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月18日 下午12:07:11  
* 修改人：jianghu  
* 修改时间：2017年10月18日 下午12:07:11  
* 修改备注： 下午12:07:11
* @version   
*   
*/ 
package com.jingu.IOT.web;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jingu.IOT.requset.AnswerQuestionRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.AnswerService;
import com.jingu.IOT.util.ToolUtil;

/**

* @ClassName: AnswerController
* @Description: TODO
* @author jianghu
* @date 2017年10月18日 下午12:07:11

*/
@RestController
public class AnswerController {

	private AnswerService answerService;
	private ToolUtil toolUtil;
	@Autowired
	public AnswerController(AnswerService answerService, ToolUtil toolUtil) {
		this.answerService = answerService;
		this.toolUtil = toolUtil;
	}
	
	// 添加问题
	@CrossOrigin
	@RequestMapping(value="/addAnswerQuestion",method=RequestMethod.POST)
	public IOTResult addArticle(@RequestBody AnswerQuestionRequset aqr){
		if(aqr.getCksid()==null ||aqr.getCksid().trim().length()<1||aqr.getCkuid()==null || aqr.getCkuid().trim().length()<1){
			return new IOTResult(false,"请先登录",null,1);
		}
		int certify =0;
		String check = toolUtil.getCheck(ToolUtil.USERS+aqr.getCkuid());
		if(check !=null && check.equals(aqr.getCksid())){
//			return new IOTResult(false,"登陆失效",null,2);
			certify = 1;
		}
		String check1 = toolUtil.getCheck(ToolUtil.IOT+aqr.getCkuid());
		if(check1 !=null && check1.equals(aqr.getCksid())){
//			return new IOTResult(false,"登陆失效",null,2);
			certify = 1;
		}
		int addProject = answerService.addAnswerQuestion(aqr);
		if(addProject >0){
			return new IOTResult(true,"添加成功",null,0);
		}
		return new IOTResult(false,"添加失败",null,0);
		
	}
	
	// 问题
	@CrossOrigin
	@RequestMapping(value="/listQuestion",method=RequestMethod.POST)
	public IOTResult listQuestion(@RequestBody AnswerQuestionRequset aqr){
//		if(aqr.getCksid()==null ||aqr.getCksid().trim().length()<1||aqr.getCkuid()==null || aqr.getCkuid().trim().length()<1){
//			return new IOTResult(false,"信息不规范",null,1);
//		}
//		String check = toolUtil.getCheck(ToolUtil.USERS+aqr.getCkuid());
//		if(check ==null || !check.equals(aqr.getCksid())){
//			return new IOTResult(false,"登陆失效",null,2);
//		}
		List<Map<String,Object>> listAnswerQuestion = answerService.listQuestion(aqr);
		if(listAnswerQuestion != null && !listAnswerQuestion.isEmpty() ){
			return new IOTResult(true,"查看成功",listAnswerQuestion,0);
		}
		return new IOTResult(false,"暂无相关信息",null,0);
		
	}
	
	// 问题和解答
	@CrossOrigin
	@RequestMapping(value="/listAnswerQuestion",method=RequestMethod.POST)
	public IOTResult listAnswerQuestion(@RequestBody AnswerQuestionRequset aqr){
//		if(aqr.getCksid()==null ||aqr.getCksid().trim().length()<1||aqr.getCkuid()==null || aqr.getCkuid().trim().length()<1){
//			return new IOTResult(false,"信息不规范",null,1);
//		}
//		String check = toolUtil.getCheck(ToolUtil.USERS+aqr.getCkuid());
//		if(check ==null || !check.equals(aqr.getCksid())){
//			return new IOTResult(false,"登陆失效",null,2);
//		}
		List<Map<String,Object>> listAnswerQuestion = answerService.listAnswerQuestion(aqr);
		if(listAnswerQuestion != null && !listAnswerQuestion.isEmpty() ){
			return new IOTResult(true,"查看成功",listAnswerQuestion,0);
		}
		return new IOTResult(false,"暂无相关信息",null,0);
		
	}
	
	public static void main(String[] args) throws UnknownHostException {
		
//		String str = "";
//		Pattern pattern = Pattern.compile("^[0-9]+$");  
//		boolean matches = pattern.matcher(str).matches();
//		System.out.println(matches);
		Calendar instance = Calendar.getInstance();
		Date time = instance.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = dateFormat.format(time);
		System.out.println(format);
		instance.add(Calendar.DATE, 30);
		Date time2 = instance.getTime();
		String format2 = dateFormat.format(time2);
		System.out.println(format2);
	}

	
}
