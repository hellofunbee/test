/**  
*   
* 项目名称：IOT  
* 类名称：MessageService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月10日 上午11:13:37  
* 修改人：jianghu  
* 修改时间：2017年10月10日 上午11:13:37  
* 修改备注： 上午11:13:37
* @version   
*   
*/ 
package com.jingu.IOT.service;

import com.jingu.IOT.dao.ClassDao;
import com.jingu.IOT.dao.MessageDao;
import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.entity.MessageEntity;
import com.jingu.IOT.requset.MessageRequset;
import com.jingu.IOT.util.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**

* @ClassName: MessageService
* @Description: TODO
* @author jianghu
* @date 2017年10月10日 上午11:13:37

*/
@Component
public class MessageService {

	private MessageDao messageDao;
	private ClassDao classDao;

	@Autowired
	public MessageService(MessageDao messageDao,ClassDao classDao) {
		this.messageDao = messageDao;
		this.classDao =classDao;
	}

	
	public int addMessage(MessageEntity me) {
		return messageDao.addMessage(me);
	}
	


	public int updateMessage(MessageEntity me) {
		return messageDao.updateMessage(me);
	}
	public List<Map<String, Object>> listMessage(MessageEntity me) {
		return messageDao.listMessage(me);
	}

	//预警信息
	public List<Map<String, Object>> listMessage3(MessageEntity me) {
		List<Map<String,Object>> listDistribution2 = messageDao.listMessage4(me,"m_province");
		for (Map<String, Object> map : listDistribution2) {
			map.remove("m_city");
			map.remove("m_district");
			map.remove("city");
			map.remove("district");
			String m_province = map.get("m_province").toString();
			MessageEntity messageEntity = new MessageEntity();
			messageEntity.setM_province(m_province);
			messageEntity.setM_type(me.getM_type());
			messageEntity.setM_type(me.getM_type());
			messageEntity.setM_class(me.getM_class());
			List<Map<String,Object>> listDistribution22 = messageDao.listMessage4(messageEntity,"m_city");
			for (Map<String, Object> map2 : listDistribution22) {
				map2.remove("m_district");
				map2.remove("m_province");
				map2.remove("district");
				map2.remove("province");
				String m_district = map2.get("m_city").toString();
				messageEntity.setM_city(m_district);
				List<Map<String,Object>> listDistribution3 = messageDao.listMessage4(messageEntity,"m_district");
				for (Map<String, Object> map3 : listDistribution3) {
					map3.remove("m_city");
					map3.remove("m_content");
					map3.remove("m_province");
					map3.remove("city");
					map3.remove("province");
					String d_district = map3.get("m_district").toString();
					messageEntity.setM_district(d_district);
					List<Map<String,Object>> listDistribution4 = messageDao.listMessage5(messageEntity,null);
					map3.put("warnings", listDistribution4);
				}
				messageEntity.setM_district(null);
				map2.put("districts", listDistribution3);
			}
			messageEntity.setM_city(null);
			map.put("citys", listDistribution22);
		}
		return listDistribution2;
	}
	public int deleteMessage(MessageEntity me) {
		return messageDao.deleteMessage(me);
	}


	/**
	 *
	 * @param mr
	 * @return
	 */
	public List<Map<String, Object>> listMessage1Bygroup(MessageRequset mr) {
		ClassEntity classEntity = new ClassEntity();
		MessageEntity messageEntity = new MessageEntity();
		if(mr.getM_type()== Types.MT_SHOUYE){
			classEntity.setC_type(Types.CT_SHOUYE);
		}
		if(mr.getM_type()==Types.MT_ZHENGCE){
			classEntity.setC_type(Types.CT_ZHENGCE);
			classEntity.setC_id(mr.getM_class());
  		}
		messageEntity.setM_type(mr.getM_type());
		messageEntity.setM_title(mr.getM_title());
		//获取 class 一级分类
		List<Map<String,Object>> listClass1 = classDao.listClass1(classEntity);

		for (Map<String, Object> map : listClass1) {
			String c_id = map.get("c_id").toString();
			messageEntity.setM_class(Integer.parseInt(c_id));
//			if(String.valueOf(mr.getC_id()).equals(c_id)){
//				messageEntity.setStart(0);
//			}else{
//				messageEntity.setStart(1);
//				messageEntity.setPageSize(6);
//			}
			List<Map<String,Object>> listMessage = messageDao.listMessage(messageEntity);
			if(listMessage ==null || listMessage.isEmpty()){
//				map.remove(key)
			}
			map.put("list", listMessage);
			
		}
		return listClass1;
	}
	
	public List<Map<String, Object>> listHomePageMessage(MessageRequset mr) {
		MessageEntity messageEntity = new MessageEntity();
		ClassEntity classEntity = new ClassEntity();

		if(mr.getM_type()==4){
			classEntity.setC_type(7);
			messageEntity.setM_type(4);
		}else{
			return null;
		}
		messageEntity.setM_title(mr.getM_title());
		classEntity.setC_id(mr.getM_class());


		List<Map<String,Object>> listClass1 = classDao.listClass1(classEntity);

		for (Map<String, Object> map : listClass1) {
			String c_id = map.get("c_id").toString();
			classEntity.setC_id(0);
			classEntity.setC_rid(Integer.parseInt(c_id));
			List<Map<String,Object>> listClass2 = classDao.listClass2Byrid(classEntity);
			if(mr.getM_class2()>0){
				listClass2 = listClass2.stream().filter(x ->x.get("c_id").toString().equals(String.valueOf(mr.getM_class2()))).collect(Collectors.toList());
			}
			for (Map<String, Object> map2 : listClass2) {
				String c_id2 = map2.get("c_id").toString();
				messageEntity.setM_class(Integer.parseInt(c_id));
				messageEntity.setM_class2(Integer.parseInt(c_id2));
				List<Map<String,Object>> listMessage = messageDao.listMessage(messageEntity);
				map2.put("messageList", listMessage);
			}
			map.put("classList", listClass2);
		}
		return listClass1;
	}
	
	
	
	
	
	
	
	
	// zuo + you -

	public List<Map<String, Object>> getInstanceMessage() {
		return messageDao.getInstanceMessage();
	}


	/**
	 * 2017年12月6日
	 * jianghu
	 * @param c_id
	 * @return
	 * TODO
	 */
	public int ckClass(int c_id) {
		// TODO Auto-generated method stub
		return messageDao.ckClass(c_id);
	}
	
}
