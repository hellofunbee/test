/**  
*   
* 项目名称：IOT  
* 类名称：CMSService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月23日 下午5:42:01  
* 修改人：jianghu  
* 修改时间：2017年11月23日 下午5:42:01  
* 修改备注： 下午5:42:01
* @version   
*   
*/
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jingu.IOT.dao.CMSDao;
import com.jingu.IOT.entity.ArticleEntity;
import com.jingu.IOT.entity.CheckEntity;
import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.entity.ProjectEntity;
import com.jingu.IOT.entity.UserEntity2;
import com.jingu.IOT.requset.ArticleRequset;
import com.jingu.IOT.requset.ClassRequest;
import com.jingu.IOT.requset.UserReq;
import com.jingu.IOT.util.ToolUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @ClassName: CMSService
 * @Description: TODO
 * @author jianghu
 * @date 2017年11月23日 下午5:42:01
 * 
 */
@Component
public class CMSService {

	private ToolUtil toolUtil;
	private CMSDao dao;

	@Autowired
	public CMSService(ToolUtil toolUtil) {
		this.toolUtil = toolUtil;
	}

	public List<Map<String, Object>> listClass1(ClassEntity c) {
		return dao.listClass1(c);
	}


	public List<Map<String, Object>> listCMSClass2Byrid(ClassEntity c) {
		return dao.listClass2Byrid(c);
	}


	public int listUserCount(UserReq u) {
		// TODO Auto-generated method stub
		return dao.listUserCount(u);
	}
	public List<Map<String, Object>> listUser(UserEntity2 use) {
		return dao.listUser(use);
	}
	public List<Map<String, Object>> listProject(ProjectEntity pe){
		return dao.listProject(pe);
	}
	public List<Map<String, Object>> getProfessorArticle2(ArticleEntity ae) {
		List<Map<String, Object>> listArticle = dao.listArticle(ae);
		UserEntity2 userEntity = new UserEntity2();
		for (Map<String, Object> map : listArticle) {
			Object object = map.get("a_uid");
			userEntity.setU_id(Long.parseLong(object.toString()));
			List<Map<String, Object>> listUser = dao.listUser(userEntity);
			List<Map<String, Object>> listCheck = dao
					.listCheck(new CheckEntity(0, Integer.parseInt(map.get("a_id").toString()), 0, "", 0, "", "", ""));
			if (listCheck == null || listCheck.isEmpty()) {
				map.put("a_checkp", "");
			} else {
				map.put("a_checkp", listCheck.get(0).get("a_checkp"));
			}
			if (listUser != null && !listUser.isEmpty()) {
				Map<String, Object> map2 = listUser.get(0);
				map.put("u_uanme", map2.get("u_uname").toString());
				map.put("u_nickname", map2.get("u_nickname").toString());
				map.put("headimgurl", map2.get("headimgurl").toString());
			}
		}
		return listArticle;
	}



	public int updateCMSUser(UserEntity2 use) {
		return dao.updateUser(use);
	}



	public List<ClassEntity> queryAllClass(ClassRequest c) {
		return dao.queryAllClass(c);
	}

	/**
	 * 2017年12月19日 jianghu
	 * 
	 * @param c
	 * @return TODO
	 */

	
	public int updateUserState(int uid, int tu_state) {
		// TODO Auto-generated method stub
		UserReq userReq = new UserReq();
		userReq.setU_id(Long.parseLong(String.valueOf(uid)));
		userReq.setU_state(tu_state);
		return dao.updateUserState(userReq);
	}
	

	
	
	public int listArticleCount(ArticleRequset ar) {
		// TODO Auto-generated method stub
		return  dao.listArticleCount(ar);
		
	}
	public int updateArticleCount(ArticleRequset ae){
		return dao.updateArticleCount(ae);
	}
	public List<Map<String, Object>> listArticle(ArticleEntity ae){
		List<Map<String, Object>> listArticle = dao.listArticle(ae);
		UserEntity2 userEntity = new UserEntity2();
		for (Map<String, Object> map : listArticle) {
			Object object = map.get("a_uid");
			userEntity.setU_id(Long.parseLong(object.toString()));
			List<Map<String,Object>> listUser = dao.listUser(userEntity);
			List<Map<String,Object>> listCheck = dao.listCheck(new CheckEntity(0,Integer.parseInt(map.get("a_id").toString()),0,"",0,"","",""));
			if(listCheck==null || listCheck.isEmpty()){
				map.put("a_checkp", "");
			}else{
				map.put("a_checkp", listCheck.get(0).get("a_checkp"));
			}
			if(listUser !=null &&  !listUser.isEmpty()){
				Map<String, Object> map2 = listUser.get(0);
				map.put("u_uanme", map2.get("u_uname").toString());
				map.put("u_nickname", map2.get("u_nickname").toString());
				map.put("headimgurl", map2.get("headimgurl").toString());
			}
		}
		return listArticle;
	}
	

}
