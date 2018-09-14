/**  
*   
* 项目名称：sxcms  
* 类名称：HomePageSerivice  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年10月19日 下午5:21:07  
* 修改人：jianghu  
* 修改时间：2017年10月19日 下午5:21:07  
* 修改备注： 下午5:21:07
* @version   
*   
*/ 
package com.jingu.IOT.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jingu.IOT.dao.HomePageDao;
import com.jingu.IOT.entity.HomePageEntity;

/**

* @ClassName: HomePageSerivice
* @Description: TODO
* @author jianghu
* @date 2017年10月19日 下午5:21:07

*/
@Component
public class HomePageService {

	private HomePageDao homePageDao;

	@Autowired
	public HomePageService(HomePageDao homePageDao) {
		this.homePageDao = homePageDao;
	}
	
	public int addHomePagePicture(HomePageEntity he){
		return homePageDao.addHomePagePicture(he);
	}
	
	public int deleteHomePagePicture(HomePageEntity he){
		return homePageDao.deleteHomePagePicture(he);
	}
	
	public int updateHomePagePicture(HomePageEntity he){
		return homePageDao.updateHomePagePicture(he);
	}
	
	public List<Map<String, Object>> listHomePagePicture(HomePageEntity he){
		return homePageDao.listHomePagePicture(he);
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
		return homePageDao.ckClass(c_id);
	}
}
