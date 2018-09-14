/**  
*   
* 项目名称：IOT  
* 类名称：UserService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月6日 上午10:21:42  
* 修改人：jianghu  
* 修改时间：2017年9月6日 上午10:21:42  
* 修改备注： 上午10:21:42
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.jingu.IOT.dao.UserDao;
import com.jingu.IOT.entity.RelationShipEntity;
import com.jingu.IOT.entity.UserEntity;
import com.jingu.IOT.entity.UserEntity2;
import com.jingu.IOT.requset.UserReq;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.ToolUtil;

import net.sf.json.JSONObject;

/**

* @ClassName: UserService
* @Description: TODO
* @author jianghu
* @date 2017年9月6日 上午10:21:42

*/
@Component
public class UserService {

	private UserDao userDao;
	private RelationShipService relationShipService;
	
	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserEntity getUserByPwd(UserEntity userEntity) {
		List<UserEntity> userByPwd = userDao.getUserByPwd(userEntity);
		if(userByPwd==null || userByPwd.isEmpty()){
			return null;
		}
		return userByPwd.get(0);
	}
	
	public JSONObject getProfessorByPwd(UserEntity userEntity) {
		RestTemplate restTemplate=new RestTemplate();
		UserEntity2 userEntity2 = new UserEntity2();
		userEntity2.setU_name(userEntity.getTu_username());
		userEntity2.setPassword(userEntity.getTu_pwd());
		JSONObject jsonObject=restTemplate.postForObject(ToolUtil.CMSURL+"/userLogin", userEntity2, JSONObject.class);
		if(jsonObject.getBoolean("success")){
			JSONObject jsonObject2 = jsonObject.getJSONObject("object");
			return jsonObject2;
		}
		return null;
	}

	
	public UserEntity getUserByName(UserEntity userEntity) {
		List<UserEntity> userByName = userDao.getUserByName(userEntity);
		if(userByName ==null || userByName.isEmpty()){
			return null;
		}
		return userByName.get(0);
	}

	public UserEntity getUserByUid(UserEntity userEntity) {
		List<UserEntity> userByPwd = userDao.getUserByPwd(userEntity);
		if(userByPwd ==null || userByPwd.isEmpty()){
			return null;
		}
		return userByPwd.get(0);
	}
	public int addUser(UserEntity userEntity) {
		return userDao.addUser(userEntity);
	}
	
	public int updateUser(UserEntity userEntity) {
		
		return userDao.updateUser(userEntity);
	}
	public List<UserEntity> listUser(UserEntity userEntity) {
		return userDao.listUser(userEntity);
	}
	public  int listUserCount(UserEntity userEntity) {
		return userDao.listUserCount(userEntity);
	}
	public List<Map<String,Object>> listProfessor(UserEntity userEntity) {
		RestTemplate restTemplate=new RestTemplate();
		JSONObject jsonObject=restTemplate.postForObject(ToolUtil.CMSURL+"/listProfessor2", userEntity, JSONObject.class);
		if(jsonObject.getBoolean("success")){
			return jsonObject.getJSONArray("object");
		}
		return null;
	}
	
	public int deleteUserByUid(UserEntity userEntity) {
		return userDao.deleteUserByUid(userEntity);
	}
	public int ckAdmin(long uid){
		return userDao.ckAdmin(uid);
	}
	public int ckSuperAdmin(long uid){
		return userDao.ckSuperAdmin(uid);
	}
	
	public List<Map<String, Object>> listUser2(UserEntity2 use) {
		return userDao.listUser2(use);
	}
	public int listUserCount2(UserReq use) {
		return userDao.listUserCount2(use);
	}

	/**
	 * 2017年12月18日
	 * jianghu
	 * @param u_uname
	 * @param password
	 * TODO
	 */
	public int ckAuthor(String u_uname, String password) {
		return userDao.ckAuthor(u_uname,password);
		// TODO Auto-generated method stub
		
	}

	/**
	 * 2017年12月18日
	 * jianghu
	 * @param uid
	 * TODO
	 */
	public int getMsgCount(long uid) {
		// TODO Auto-generated method stub
		return userDao.getMsgCount(uid);
		
	}


}
