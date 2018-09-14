/**  
*   
* 项目名称：IOT  
* 类名称：ReadExcelController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年3月29日 下午5:38:10  
* 修改人：jianghu  
* 修改时间：2018年3月29日 下午5:38:10  
* 修改备注： 下午5:38:10
* @version   
*   
*/ 
package com.jingu.IOT.web;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.ReadExcelEntity;
import com.jingu.IOT.requset.ProduceRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.service.ReadExcelService;
import com.jingu.IOT.util.Base64;
import com.jingu.IOT.util.ToolUtil;

/**

* @ClassName: ReadExcelController
* @Description: TODO
* @author jianghu
* @date 2018年3月29日 下午5:38:10
读取excel表格操作接口
*/
@RestController
public class ReadExcelController {

	private ToolUtil toolUtil;
	private ReadExcelService readExcelService;
	
	
	
	@Autowired
	public ReadExcelController(ToolUtil toolUtil, ReadExcelService readExcelService) {
		this.toolUtil = toolUtil;
		this.readExcelService = readExcelService;
	}



	@CrossOrigin
	@RequestMapping(value = "/readExcel", method = RequestMethod.POST)
	public IOTResult readExcel(@RequestBody ReadExcelEntity re){
		if(re.getCksid()==null || re.getCksid().trim().length()<1||re.getCkuid()==null||re.getCkuid()==null){
			return  new IOTResult(false,"信息不规范",null,1);
		}
		// 注册登陆按照什么来????
		String check = toolUtil.getCheck(ToolUtil.IOT+re.getCkuid());
		if(check ==null ||!re.getCksid().equals(check)){
			return  new IOTResult2(false,"登陆失效",null,2,0,0);
		}
		String check2 = toolUtil.getCheck(re.getFileName());
		if(check2 !=null && check2.trim().length()<1){
			return new IOTResult(true,"查看成功",check2,0);
		}
		
		if(re.getType() ==1){
			String encode = Base64.encode(re.getFileName().getBytes());
			String file = "distribute_"+0+"_"+encode;
			if(!new File(ToolUtil.FILEPATH+file).exists()){
				return new IOTResult(false,"暂无相关信息",null,0);
			}
			List<Map<String,Object>> readExcel = readExcelService.readExcel(re.getFileName());
			toolUtil.setCheck(re.getFileName(), readExcel.toString());
			return new IOTResult(true,"查看成功",readExcel,0);
		}
		if(re.getType() ==2){
			String encode = Base64.encode(re.getFileName().getBytes());
			String file = "distribute_"+0+"_"+encode;
			if(!new File(ToolUtil.FILEPATH+file).exists()){
				return new IOTResult(false,"暂无相关信息",null,0);
			}
			List<Object> readExcel = readExcelService.readExcelProcess(re.getFileName());
			toolUtil.setCheck(re.getFileName(), readExcel.toString());
			return new IOTResult(true,"查看成功",readExcel,0);
		}
		return null;
	}
}
