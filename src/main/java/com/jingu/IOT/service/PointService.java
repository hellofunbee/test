/**  
*   
* 项目名称：IOT  
* 类名称：PointService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年9月5日 下午4:35:23  
* 修改人：jianghu  
* 修改时间：2017年9月5日 下午4:35:23  
* 修改备注： 下午4:35:23
* @version   
*   
*/ 
package com.jingu.IOT.service;

import com.jingu.IOT.dao.PointDao;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.requset.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**

* @ClassName: PointService
* @Description: TODO
* @author jianghu
* @date 2017年9月5日 下午4:35:23

*/
@Component
public class PointService {

	private PointDao pointDao;

	@Autowired
	public PointService(PointDao pointDao) {
		this.pointDao = pointDao;
	}
	
	public int addPoint(PointEntity pointEntity){
		if(pointEntity.getRole()==null||pointEntity.getRole().trim().length()<1){
			pointEntity.setRole("");
		}
		return pointDao.addPoint(pointEntity);
	}
	public PointEntity getPoint(PointEntity  pointEntity){
		List<PointEntity> point = pointDao.getPoint(pointEntity);
		if(point ==null || point.isEmpty()){
			return null;
		}
		return point.get(0);
	}
	public List<Map<String, Object>> listPoint(PointEntity  pointEntity){
//		pointEntity.setTp_pid(0);
		// 获得角色
		String role = pointEntity.getRole();
		if(pointEntity.getTp_pid()!=0){
			pointEntity.setRole(role);
		}
//		System.out.println(pointEntity);
		// 递归调用
		List<Map<String,Object>> listPoint = pointDao.listPoint(pointEntity);
		System.out.println(listPoint);
		// 比较器，比较顺序
		Comparator<Map<String, Object>> byIndex = (m1, m2) -> Integer.compare(Integer.parseInt(m2.get("tp_index").toString()),Integer.parseInt(m1.get("tp_index").toString()));
		// 排序
		Collections.sort(listPoint, byIndex);
		// 便利所有找到的节点
		for (Map<String, Object> map : listPoint) {
			// 找到所有一级点
			boolean equals = map.get("tp_type").toString().equals("2");
			if(equals){
				pointEntity.setTp_type(3);
			}else{
				pointEntity.setTp_type(0);
			}
			// 把当前节点id赋值给子节点父id
			pointEntity.setTp_pid(Integer.parseInt(map.get("tp_id").toString()));
//			pointEntity.setTp_state(2);
			List<Map<String,Object>> listPoint2 = listPoint(pointEntity);
			if(listPoint2==null || listPoint.isEmpty()){
				return null;
			}
			// 过滤
			List<Map<String,Object>> collect = listPoint2.stream().filter(x -> x.get("t_role").toString().contains(","+pointEntity.getRole()+":") && x.get("tp_type").toString().equals("3")).collect(Collectors.toList());
			// 过滤出来相应的子节点集合挂载到到父节点下
			if(collect!=null && !collect.isEmpty()){
				map.put("rank", collect);
			}else{
				map.put("rank", listPoint2);
			}
//			if(listPoint2)
//			System.out.println(map);
		}
		return listPoint;
	}
	public int deletePoint(PointEntity pointEntity){
		return pointDao.deletePoint(pointEntity);
	}
	public int updatePoint(PointEntity  pointEntity){
		return pointDao.updatePoint(pointEntity);
	}
	public List<Map<String, Object>> listPoint2(PointEntity  pointEntity){
		List<Map<String,Object>> listPoint = pointDao.listPoint(pointEntity);
		return listPoint;
	}
	// 设置主设备时 索要的站点分组信息
	public Map<String, Object> listPoint3(PointEntity  pointEntity){
		pointEntity.setTp_type(1);
		List<Map<String,Object>> site = pointDao.listPoint(pointEntity);
		pointEntity.setTp_type(2);
		List<Map<String,Object>> group = pointDao.listPoint(pointEntity);
		pointEntity.setTp_type(3);
		List<Map<String,Object>> device = pointDao.listPoint(pointEntity);
		pointEntity.setTp_type(4);
		List<Map<String,Object>> IPC = pointDao.listPoint(pointEntity);
		Map<String,Object> map = new HashMap<>();
		map.put("site", site);
		map.put("group", group);
		map.put("device", device);
		map.put("IPC", IPC);
		return map;
	}

	/**
	 * 2017年11月18日
	 * jianghu
	 * @param pr
	 * TODO
	 */
	// 找到站点合分组的信息
	public List<Map<String, Object>> listPointGroup(PointRequest pr) {
		pr.setTp_id(0);
		pr.setTp_type(1);
		List<Map<String,Object>> listPoint = pointDao.listPoint(pr);
		for (Map<String, Object> map : listPoint) {
			pr.setTp_pid(Integer.parseInt(map.get("tp_id").toString()));
			pr.setTp_type(2);
			List<Map<String,Object>> listPoint2 = pointDao.listPoint(pr);
			map.put("list", listPoint2);
		}
		return listPoint;
		
	}

	/**
	 * 2017年11月30日
	 * jianghu
	 * @param string
	 * TODO
	 */
	public List<Map<String, Object>> listPointByDeviceId(String string) {
		return pointDao.listPointByDeviceId(string);
		// TODO Auto-generated method stub
		
	}

	/**
	 * 2017年12月2日
	 * jianghu
	 * TODO
	 */
	public int listMaxId() {
		// TODO Auto-generated method stub
		return pointDao.listMaxId();
		
	}
	
}
