/**  
*   
* 项目名称：IOT  
* 类名称：InputService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 下午7:04:40  
* 修改人：jianghu  
* 修改时间：2017年10月10日 下午7:04:40  
* 修改备注： 下午7:04:40
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.InputDao;
import com.jingu.IOT.entity.InputEntity;

/**

* @ClassName: InputService
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 下午7:04:40

*/
@Component
public class InputService {

	private InputDao inputDao;

	@Autowired
	public InputService(InputDao inputDao) {
		this.inputDao = inputDao;
	}
	
	public int addInput(InputEntity ie) {
		return inputDao.addInput(ie);
	}
	public int addInputList(List<InputEntity> ie) {
		return inputDao.addInputList(ie);
	}
	public int deleteInput(InputEntity ie) {
		return inputDao.deleteInput(ie);
	}
	public int updateInput(InputEntity ie) {
		return inputDao.updateInput(ie);
	}
	public List<Map<String, Object>> listInput(InputEntity ie) {
		return inputDao.listInput(ie);
	}
	public int listInputCount(InputEntity ie) {
		return inputDao.listInputCount(ie);
	}
}
