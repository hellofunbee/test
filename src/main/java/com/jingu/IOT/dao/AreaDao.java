package com.jingu.IOT.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jingu.IOT.entity.AreaBean;
import com.jingu.IOT.requset.AreaReq;


@Component
public class AreaDao {
	@Resource
	@Qualifier("primaryJdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
//	public AreaDao(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}

	public List<Map<String, Object>> listProvince(){
		String sql = "select * from  s_area a where right(a.ar_id,4)='0000'";
		return jdbcTemplate.queryForList(sql);
	}
	public List<Map<String, Object>> listCity(AreaBean aEntity){
		String sql = "select * from  s_area a where left(a.ar_id,2)=left(?,2) and right(a.ar_id,2) ='00' and SUBSTR(a.ar_id FROM 3 FOR 2) !='00'";
		return jdbcTemplate.queryForList(sql,aEntity.getAr_id());
	}

	public List<Map<String, Object>> listDistrict(AreaReq a) {
		String sql = "select * from  s_area a where left(a.ar_id,4)=left(?,4) and right(a.ar_id,2) !='00' and right(a.ar_id,2) !='01'";
		return jdbcTemplate.queryForList(sql,a.getAr_id());
	}
}
