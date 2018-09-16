/**  
*   
* 项目名称：IOT  
* 类名称：CMSController  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月23日 下午5:41:36  
* 修改人：jianghu  
* 修改时间：2017年11月23日 下午5:41:36  
* 修改备注： 下午5:41:36
* @version   
*   
*/
package com.jingu.IOT.web;

import com.jingu.IOT.entity.ArticleEntity;
import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.entity.ProjectEntity;
import com.jingu.IOT.requset.ArticleRequset;
import com.jingu.IOT.requset.ClassRequest;
import com.jingu.IOT.requset.UserReq;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.service.CMSService;
import com.jingu.IOT.util.Base64;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 
 * @ClassName: CMSController
 * @Description: TODO
 * @author jianghu
 * @date 2017年11月23日 下午5:41:36
 * 
 */
@RestController
public class CMSController {

	private CMSService cmsService;

	private List<ClassEntity> arrayList = new ArrayList<>();

	@Autowired
	public CMSController(CMSService cmsService) {
		this.cmsService = cmsService;
	}

	// 查看cms以及分类
	@CrossOrigin
	@RequestMapping(value = "/listCMSClass1", method = RequestMethod.POST)
	public IOTResult listCMSClass1(@RequestBody ClassRequest c) {
		List<Map<String, Object>> list = cmsService.listClass1(c);
		return new IOTResult(true, "查询成功", list, 0);

	}

	// 查看cms分类
	@CrossOrigin
	@RequestMapping(value = "/listCMSClass2Byrid", method = RequestMethod.POST)
	public IOTResult listClass2Byrid(@RequestBody ClassRequest c) {
		List<Map<String, Object>> listCMSClass2Byrid = cmsService.listCMSClass2Byrid(c);
		return new IOTResult(true, "查询成功", listCMSClass2Byrid, 0);
	}

	// 获取专家文章
	@CrossOrigin
	@RequestMapping(value = "/getProfessorArticle", method = RequestMethod.POST)
	public IOTResult getProfessorArticle2(@RequestBody UserReq u) {
		u.setU_type(String.valueOf(2));
		int totalpage = 0;
		int listUserCount = cmsService.listUserCount(u);
		if (listUserCount % u.getPagesize() > 0) {
			totalpage = listUserCount / u.getPagesize() + 1;
		} else {
			totalpage = listUserCount / u.getPagesize();
		}
		List<Map<String, Object>> listUser = cmsService.listUser(u);

		if (listUser != null && !listUser.isEmpty()) {
			ArticleEntity articleEntity = new ArticleEntity();
			Map<String, Object> map = listUser.get(0);
			Object object = map.get("u_id");
			articleEntity.setA_uid(Integer.parseInt(object.toString()));
			List<Map<String, Object>> listArticle = cmsService.listArticle(articleEntity);
			ProjectEntity pe = new ProjectEntity();
			pe.setP_uid((int) u.getU_id());

			List<Map<String, Object>> listProject = cmsService.listProject(pe);
			for (Map<String, Object> map2 : listProject) {
				map2.put("a_id", map2.get("p_id").toString());
				map2.put("a_typename", "双创项目");
				// map2.put("a_class1name","p_")
				map2.put("a_name", map2.get("p_name").toString());
				map2.put("a_cover", map2.get("p_cover").toString());
				map2.put("a_authorname", map2.get("p_authorname").toString());
				map2.put("a_content", map2.get("p_content").toString());
				map2.put("a_time", map2.get("p_time").toString());
				map2.put("a_typename", map2.get("p_typename").toString());
				map2.put("a_type", 4);
				listArticle.add(map2);
			}
			Comparator<Map<String, Object>> byTime = (m1, m2) -> m2.get("a_time").toString()
					.compareTo(m1.get("a_time").toString());

			Collections.sort(listArticle, byTime);
			//
			// boolean addAll = listArticle.addAll(listProject);
			// System.out.println(listArticle);
			for (Map<String, Object> map2 : listArticle) {
				Object object2 = map2.get("a_content");
				if (!map2.get("a_type").toString().equals("1")) {
					System.err.println();
					JSONArray fromObject = JSONArray.fromObject(object2);
					map2.put("a_content", fromObject);
				}
			}
			return new IOTResult2(true, "查看成功", listArticle, 0, totalpage, listUserCount);
		}
		return new IOTResult2(false, "暂无相关信息", null, 0);

	}

	// 修改cms用户
	@CrossOrigin
	@RequestMapping(value = "/updateCMSUser", method = RequestMethod.POST)
	public IOTResult updateCMSUser(@RequestBody UserReq u) {
		if (u.getCkuid() == null || u.getCksid() == null || u.getCkuid().trim().length() < 1
				|| u.getCksid().trim().length() < 1) {
			return new IOTResult(false, "信息不规范", null, 1);
		}

		if (u.getP_seredproject() != null && !u.getP_seredproject().isEmpty()) {
			u.setSeredproject(JSONArray.fromObject(u.getP_seredproject()).toString());
		}
		if (u.getP_serproject() != null && !u.getP_serproject().isEmpty()) {
			u.setSerproject(JSONArray.fromObject(u.getP_serproject()).toString());
		}
		if (u.getP_seruser() != null && !u.getP_seruser().isEmpty()) {
			u.setSeruser(JSONArray.fromObject(u.getP_seruser()).toString());
		}
		int updateUser = cmsService.updateCMSUser(u);
		System.out.println(updateUser);
		if (updateUser > 0) {
			return new IOTResult(true, "保存成功", null, 0);
		}
		return new IOTResult(false, "修改失败", null, 0);
	}

	// 查看cms分类
	@CrossOrigin
	@RequestMapping(value = "/listCMSAllClass", method = RequestMethod.POST)
	public IOTResult listCMSAllClass(@RequestBody ClassRequest c) {
		c.setC_lev(1);
		c.setC_state(1);
		List<ClassEntity> queryAllClass = cmsService.queryAllClass(c);
		for (ClassEntity classEntity : queryAllClass) {
			c.setC_lev(0);
			c.setC_rid(classEntity.getC_id());
			List<ClassEntity> queryAllClass2 = cmsService.queryAllClass(c);
			if (queryAllClass2 == null || queryAllClass2.isEmpty()) {
				classEntity.setList(arrayList);
			} else {
				classEntity.setList(queryAllClass2);
			}
		}
		if (queryAllClass != null && queryAllClass.size() > 0) {
			return new IOTResult(true, "查询成功", queryAllClass, 0);
		}
		return new IOTResult(false, "没有相关信息", null, 0);

	}

	// 查看文章
	@CrossOrigin
	@RequestMapping(value = "/listArticle", method = RequestMethod.POST)
	public IOTResult2 listArticle(@RequestBody ArticleRequset ar) throws UnsupportedEncodingException {

		int listArticleCount = cmsService.listArticleCount(ar);
		int totalpage = 0;
		if (listArticleCount % ar.getPagesize() > 0) {
			totalpage = listArticleCount / ar.getPagesize() + 1;
		} else {
			totalpage = listArticleCount / ar.getPagesize();
		}
		if (ar.getStart() == 0) {
			totalpage = 1;
		}
		List<Map<String, Object>> listArticle = cmsService.listArticle(ar);
		if (listArticle != null && !listArticle.isEmpty()) {
			if (ar.getA_type() != 1) {
				for (Map<String, Object> map : listArticle) {
					Object object2 = map.get("a_questionnaire");
					if (object2 != null && object2.toString().trim().length() > 0) {
						map.put("a_questionnaire", new String(Base64.decode(object2.toString())));
					}
					Object object3 = map.get("a_videoinfo");
					if (object3 != null && object3.toString().trim().length() > 0) {
						map.put("a_videoinfo", new String(Base64.decode(object3.toString())));
					}
					Object object = map.get("a_content");
					String string = object.toString();
					boolean startsWith = string.startsWith("[");
					if (startsWith) {
						JSONArray fromObject = JSONArray.fromObject(object);
						map.put("a_content", fromObject);

					}
				}
			}

			if (ar.getA_id() > 0) {
				cmsService.updateArticleCount(ar);
			}
			return new IOTResult2(true, "查看成功", listArticle, 0, totalpage, listArticleCount);
		}
		return new IOTResult2(false, "暂无相关信息", null, 0);
	}

}
