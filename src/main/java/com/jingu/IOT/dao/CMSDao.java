package com.jingu.IOT.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import com.jingu.IOT.entity.ArticleEntity;
import com.jingu.IOT.entity.CheckEntity;
import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.entity.ProjectEntity;
import com.jingu.IOT.entity.UserEntity2;
import com.jingu.IOT.requset.ArticleRequset;
import com.jingu.IOT.requset.UserReq;

public class CMSDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> listClass1(ClassEntity c) {
		String sql = "select * from class where c_lev =1 and c_state =1 and c_type = ?";
		return jdbcTemplate.queryForList(sql, c.getC_type());
	}

	public List<Map<String, Object>> listClass2Byrid(ClassEntity c) {
		String sql = "select * from class where c_lev =2 and c_rid =? and  c_state =1";
		return jdbcTemplate.queryForList(sql, c.getC_rid());
	}

	public int listUserCount(UserReq use) {
		String sql = "select count(*) from shopuser  where 1= 1";
		List<Object> list = new ArrayList<>();
		if (use.getClass1name() != null && use.getClass1name().trim().length() > 0) {
			sql += " and class1name = ?";
			list.add(use.getClass1name());
		}
		if (use.getClass2name() != null && use.getClass2name().trim().length() > 0) {
			sql += " and classname = ?";
			list.add(use.getClass2name());
		}
		if (use.getClass1name() != null && use.getClass1name().trim().length() > 0) {
			sql += " and class2name = ?";
			list.add(use.getClass2name());
		}
		if (use.getDetail() != null && use.getDetail().trim().length() > 0) {
			sql += " and detail = ?";
			list.add(use.getDetail());
		}
		if (use.getField() != null && use.getField().trim().length() > 0) {
			sql += " and field = ?";
			list.add(use.getField());
		}
		if (use.getSex() != null && use.getSex().trim().length() > 0) {
			sql += " and sex = ?";
			list.add(use.getSex());
		}
		if (use.getU_name() != null && use.getU_name().trim().length() > 0) {
			sql += " and u_name = ?";
			list.add(use.getU_name());
		}
		if (use.getPostion() != null && use.getPostion().trim().length() > 0) {
			sql += " and postion = ?";
			list.add(use.getPostion());
		}
		if (use.getU_phone() != null && use.getU_phone().trim().length() > 0) {
			sql += " and u_phone = ?";
			list.add(use.getU_phone());
		}
		if (use.getU_phone2() != null && use.getU_phone2().trim().length() > 0) {
			sql += " and u_phone2 = ?";
			list.add(use.getU_phone2());
		}
		if (use.getClass1id() > 0) {
			sql += " and class1id = ?";
			list.add(use.getClass1id());
		}
		if (use.getClass2id() > 0) {
			sql += " and class2id = ?";
			list.add(use.getClass2id());
		}
		if (use.getU_id() > 0) {
			sql += " and u_id =?";
			list.add(use.getU_id());
		}
		if (use.getU_type() != null && use.getU_type().trim().length() > 0) {
			sql += " and u_type =?";
			list.add(use.getU_type());
		}
		if (use.getCheck() > 0) {
			sql += " and u_check = ?";
			list.add(use.getCheck());
		}
		if (use.getU_state() > 0) {
			sql += " and u_state = ?";
			list.add(use.getU_state());
		}
		if (use.getSerproject() != null) {
			sql += " and serproject = ?";
			list.add(use.getSerproject());
		}
		if (use.getSeredproject() != null) {
			sql += " and seredproject = ?";
			list.add(use.getSeredproject());
		}
		if (use.getSeruser() != null) {
			sql += " and seruser = ?";
			list.add(use.getSeruser());
		}
		// if(use.getU_id() >0){
		// sql += " and u_id = ?";
		// list.add(use.getU_id());
		// }
		return jdbcTemplate.queryForObject(sql, Integer.class, list.toArray());
	}

	public List<Map<String, Object>> listCheck(CheckEntity cEntity) {
		String sql = "select * from a_check where a_aid=? order by a_ctime desc";
		return jdbcTemplate.queryForList(sql, cEntity.getA_aid());
	}

	public List<Map<String, Object>> listUser(UserEntity2 use) {
		String sql = "select * from shopuser  where 1= 1";
		List<Object> list = new ArrayList<>();
		if (use.getClass1name() != null && use.getClass1name().trim().length() > 0) {
			sql += " and class1name = ?";
			list.add(use.getClass1name());
		}
		if (use.getClass2name() != null && use.getClass2name().trim().length() > 0) {
			sql += " and class2name = ?";
			list.add(use.getClass2name());
		}
		if (use.getDetail() != null && use.getDetail().trim().length() > 0) {
			sql += " and detail = ?";
			list.add(use.getDetail());
		}
		if (use.getField() != null && use.getField().trim().length() > 0) {
			sql += " and field = ?";
			list.add(use.getField());
		}
		if (use.getSex() != null && use.getSex().trim().length() > 0) {
			sql += " and sex = ?";
			list.add(use.getSex());
		}
		if (use.getU_name() != null && use.getU_name().trim().length() > 0) {
			sql += " and u_name = ?";
			list.add(use.getU_name());
		}
		if (use.getU_uname() != null && use.getU_uname().trim().length() > 0) {
			sql += " and u_uname like '%" + use.getU_uname() + "%'";
		}
		if (use.getPostion() != null && use.getPostion().trim().length() > 0) {
			sql += " and postion = ?";
			list.add(use.getPostion());
		}
		if (use.getU_phone() != null && use.getU_phone().trim().length() > 0) {
			sql += " and u_phone = ?";
			list.add(use.getU_phone());
		}
		if (use.getU_phone2() != null && use.getU_phone2().trim().length() > 0) {
			sql += " and u_phone2 = ?";
			list.add(use.getU_phone2());
		}
		if (use.getClass1id() > 0) {
			sql += " and class1id = ?";
			list.add(use.getClass1id());
		}
		if (use.getClass2id() > 0) {
			sql += " and class2id = ?";
			list.add(use.getClass2id());
		}
		if (use.getU_id() > 0) {
			sql += " and u_id =?";
			list.add(use.getU_id());
		}
		if (use.getU_type() != null && use.getU_type().trim().length() > 0) {
			sql += " and u_type =?";
			list.add(use.getU_type());
		}
		if (use.getCheck() > 0) {
			sql += " and u_check = ?";
			list.add(use.getCheck());
		}
		if (use.getU_state() > 0) {
			sql += " and u_state = ?";
			list.add(use.getU_state());
		}
		// if(use.getSerproject() !=null){
		// sql += " and serproject = ?";
		// list.add(use.getSerproject());
		// }
		// if(use.getSeredproject() !=null){
		// sql += " and seredproject = ?";
		// list.add(use.getSeredproject());
		// }
		// if(use.getSeruser() !=null){
		// sql += " and seruser = ?";
		// list.add(use.getSeruser());
		// }
		if (use.getU_id() > 0) {
			sql += " and u_id = ?";
			list.add(use.getU_id());
		}
		return jdbcTemplate.queryForList(sql, list.toArray());
	}

	public int updateUser(UserEntity2 use) {
		String sql = "update shopuser set u_id =?";
		List<Object> list = new ArrayList<>();
		list.add(use.getU_id());
		if (use.getClass1name() != null && use.getClass1name().trim().length() > 0) {
			sql += " , class1name = ?";
			list.add(use.getClass1name());
		}
		if (use.getClass2name() != null && use.getClass2name().trim().length() > 0) {
			sql += " , class2name = ?";
			list.add(use.getClass2name());
		}
		// if(use.getClass1name()!=null &&
		// use.getClass1name().trim().length()>0){
		// sql += " , class2name = ?";
		// list.add(use.getClass2name());
		// }
		if (use.getDetail() != null && use.getDetail().trim().length() > 0) {
			sql += " , detail = ?";
			list.add(use.getDetail());
		}
		if (use.getField() != null && use.getField().trim().length() > 0) {
			sql += " , field = ?";
			list.add(use.getField());
		}
		if (use.getSex() != null && use.getSex().trim().length() > 0) {
			sql += " , sex = ?";
			list.add(use.getSex());
		}
		if (use.getU_name() != null && use.getU_name().trim().length() > 0) {
			sql += " , u_name = ?";
			list.add(use.getU_name());
		}
		if (use.getPostion() != null && use.getPostion().trim().length() > 0) {
			sql += " , position = ?";
			list.add(use.getPostion());
		}
		if (use.getU_phone() != null && use.getU_phone().trim().length() > 0) {
			sql += " , u_phone = ?";
			list.add(use.getU_phone());
		}
		if (use.getU_phone2() != null && use.getU_phone2().trim().length() > 0) {
			sql += " , u_phone2 = ?";
			list.add(use.getU_phone2());
		}
		if (use.getU_uname() != null && use.getU_uname().trim().length() > 0) {
			sql += " , u_uname = ?";
			list.add(use.getU_uname());
		}
		if (use.getClass1id() > 0) {
			sql += " , class1id = ?";
			list.add(use.getClass1id());
		}
		if (use.getClass2id() > 0) {
			sql += " , class2id = ?";
			list.add(use.getClass2id());
		}
		if (use.getCheck() > 0) {
			sql += " , u_check = ?";
			list.add(use.getCheck());
		}
		if (use.getU_state() > 0) {
			sql += " , u_state = ?";
			list.add(use.getU_state());
		}
		if (use.getSerproject() != null) {
			sql += " , serproject = ?";
			list.add(use.getSerproject());
		}
		if (use.getSeredproject() != null) {
			sql += " , seredproject = ?";
			list.add(use.getSeredproject());
		}
		if (use.getSeruser() != null) {
			sql += " , seruser = ?";
			list.add(use.getSeruser());
		}
		if (use.getPassword() != null && use.getPassword().trim().length() > 0) {
			sql += " , u_password = ?";
			list.add(use.getPassword());
		}
		if (use.getHeadimgurl() != null && use.getHeadimgurl().trim().length() > 0) {
			sql += " , headimgurl = ?";
			list.add(use.getHeadimgurl());
		}
		if (use.getU_email() != null && use.getU_email().trim().length() > 0) {
			sql += " , u_email = ?";
			list.add(use.getU_email());
		}
		if (use.getU_nickname() != null && use.getU_nickname().trim().length() > 0) {
			sql += " , u_nickname = ?";
			list.add(use.getU_nickname());
		}
		if (use.getU_age() > 0) {
			sql += " , u_age = ?";
			list.add(use.getU_age());
		}
		if (list.size() == 1) {
			return 0;
		}
		sql += " where u_id =?";
		list.add(use.getU_id());
		return jdbcTemplate.update(sql, list.toArray());
	}

	public List<ClassEntity> queryAllClass(ClassEntity c) {
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from class where 1=1";
		if (c != null) {
			if (c.getC_id() > 0) {
				sql = sql + " and c_id=?";
				list.add(c.getC_id());
			}
			if (c.getC_lev() > 0) {
				sql += " and c_lev =?";
				list.add(c.getC_lev());
			}
			if (c.getC_rid() > 0) {
				sql = sql + " and c_rid=?";
				list.add(c.getC_rid());
			}
			if (c.getC_state() > 0 && c.getC_state() < 3) {
				sql = sql + " and c_state =?";
				list.add(c.getC_state());
			}
			if (c.getC_name() != null && c.getC_name().trim().length() > 0) {
				sql = sql + " and c_name=?";
				list.add(c.getC_name());
			}
			if (c.getC_posi() > 0) {
				sql = sql + " and c_posi =?";
				list.add(c.getC_posi());
			}
			if (c.getC_photo() != null && c.getC_photo().trim().length() > 0) {
				sql = sql + " and c_photo=?";
				list.add(c.getC_photo());
			}
			if (c.getC_type() > 0) {
				sql = sql + " and c_type=?";
				list.add(c.getC_type());
			}
		}
		return jdbcTemplate.query(sql, new ClassEntity(), list.toArray());
	}

	public int updateUserState(UserReq u) {
		String sql = "update shopuser set u_state =? where u_id =?";
		return jdbcTemplate.update(sql, u.getU_state(), u.getU_id());
	}

	public int updateArticleCount(ArticleRequset ae) {
		String sql = "update article set a_count=a_count +1 where a_id = ? ";
		return jdbcTemplate.update(sql, ae.getA_id());
	}

	public int listArticleCount(ArticleRequset ae) {
		String sql = " select count(*) from article a LEFT JOIN class c on c.c_id = a.a_class1 left join class cl on cl.c_id =a.a_class2  where 1=1 ";
		List<Object> list = new ArrayList<>();
		if (ae.getA_check() > 0) {
			sql += " and a.a_check =? ";
			list.add(ae.getA_check());
		}
		if (ae.getA_class1() > 0) {
			sql += " and a.a_class1 =? ";
			list.add(ae.getA_class1());
		}
		if (ae.getA_class2() > 0) {
			sql += " and a.a_class2 =? ";
			list.add(ae.getA_class2());
		}
		if (ae.getA_state() > 0) {
			sql += " and a.a_state =? ";
			list.add(ae.getA_state());
		}
		if (ae.getA_type() > 0) {
			sql += " and a.a_type =? ";
			list.add(ae.getA_type());
		}
		if (ae.getA_uid() > 0) {
			sql += " and a.a_uid =? ";
			list.add(ae.getA_uid());
		}
		if (ae.getA_authorname() != null && ae.getA_authorname().length() > 0) {
			sql += " and a.a_authorname =? ";
			list.add(ae.getA_authorname());
		}
		if (ae.getA_content() != null && ae.getA_content().length() > 0) {
			sql += " and a.a_content =? ";
			list.add(ae.getA_content());
		}
		if (ae.getA_cover() != null && ae.getA_cover().length() > 0) {
			sql += " and a.a_cover =? ";
			list.add(ae.getA_cover());
		}
		if (ae.getA_name() != null && ae.getA_name().length() > 0) {
			sql += " and a.a_name =? ";
			list.add(ae.getA_name());
		}
		if (ae.getA_reading() != null && ae.getA_reading().length() > 0) {
			sql += " and a.a_reading =? ";
			list.add(ae.getA_reading());
		}
		if (ae.getA_time() != null && ae.getA_time().length() > 0) {
			sql += " and a.a_time =UNIX_TIMESTAMP() ";
		}
		if (ae.getA_source() != null && ae.getA_source().length() > 0) {
			sql += " and a_source =? ";
			list.add(ae.getA_source());
		}
		if (ae.getA_object() != null && ae.getA_object().length() > 0) {
			sql += " and a_object =? ";
			list.add(ae.getA_object());
		}
		if (ae.getA_id() > 0) {
			sql += " and a.a_id = ?";
			list.add(ae.getA_id());
		}
		sql += " order by a.a_time desc";

		return jdbcTemplate.queryForObject(sql, Integer.class, list.toArray());
	}

	public List<Map<String, Object>> listArticle(ArticleEntity ae) {
		String sql = " select a.*,coalesce(c.c_name,'') class1name,coalesce(cl.c_name,'') class2name from article a LEFT JOIN class c on c.c_id = a.a_class1 left join class cl on cl.c_id =a.a_class2  where 1=1 ";
		List<Object> list = new ArrayList<>();
		if (ae.getA_check() > 0) {
			sql += " and a.a_check =? ";
			list.add(ae.getA_check());
		}
		if (ae.getA_class1() > 0) {
			sql += " and a.a_class1 =? ";
			list.add(ae.getA_class1());
		}
		if (ae.getA_class2() > 0) {
			sql += " and a.a_class2 =? ";
			list.add(ae.getA_class2());
		}
		if (ae.getA_state() > 0) {
			sql += " and a.a_state =? ";
			list.add(ae.getA_state());
		}
		if (ae.getA_type() > 0) {
			sql += " and a.a_type =? ";
			list.add(ae.getA_type());
		}
		if (ae.getA_uid() > 0) {
			sql += " and a.a_uid =? ";
			list.add(ae.getA_uid());
		}
		if (ae.getA_authorname() != null && ae.getA_authorname().length() > 0) {
			sql += " and a.a_authorname =? ";
			list.add(ae.getA_authorname());
		}
		if (ae.getA_content() != null && ae.getA_content().length() > 0) {
			sql += " and a.a_content =? ";
			list.add(ae.getA_content());
		}
		if (ae.getA_cover() != null && ae.getA_cover().length() > 0) {
			sql += " and a.a_cover =? ";
			list.add(ae.getA_cover());
		}
		if (ae.getA_name() != null && ae.getA_name().length() > 0) {
			sql += " and a.a_name like '%" + ae.getA_name() + "%' ";
		}
		if (ae.getA_reading() != null && ae.getA_reading().length() > 0) {
			sql += " and a.a_reading =? ";
			list.add(ae.getA_reading());
		}
		if (ae.getA_time() != null && ae.getA_time().length() > 0) {
			sql += " and a.a_time =UNIX_TIMESTAMP() ";
		}
		if (ae.getA_id() > 0) {
			sql += " and a.a_id = ?";
			list.add(ae.getA_id());
		}

		sql += " order by a.a_time desc";
		if (ae.getStart() > 0) {
			sql += " limit " + (ae.getStart() - 1) * ae.getPagesize() + "," + ae.getPagesize();
		}
		return jdbcTemplate.queryForList(sql, list.toArray());
	}
	
	public List<Map<String, Object>> listProject(ProjectEntity pe){
		String sql = " select p.*,c.c_name class1name,cl.c_name class2name from  project p left join class c on p.p_class1 =c.c_id left join class cl on p.p_class2 = cl.c_id where 1=1 ";
		List<Object> list = new ArrayList<Object>();
		if(pe.getP_type()>0){
			sql += " and p.p_type =? ";
			list.add(pe.getP_id());
		}
		if(pe.getP_state()>0){
			sql += " and  p.p_state =? ";
			list.add(pe.getP_state());
		}
		if(pe.getP_check()>0){
			sql += " and  p.p_check =? ";
			list.add(pe.getP_check());
		}
		if(pe.getP_class1()>0){
			sql += " and p.p_class1 =? ";
			list.add(pe.getP_class1());
		}
		if(pe.getP_class2()>0){
			sql += " and p.p_class2 =? ";
			list.add(pe.getP_class2());
		}
		if(pe.getP_charge()!=null && pe.getP_charge().trim().length()>0){
			sql += " and p.p_charge = ? ";
			list.add(pe.getP_charge());
		}
		if(pe.getP_chargeemail()!=null && pe.getP_chargeemail().trim().length()>0){
			sql += " and p.p_chargeemail = ? ";
			list.add(pe.getP_chargeemail());
		}
		if(pe.getP_chargepic()!=null && pe.getP_chargepic().trim().length()>0){
			sql += " and p.p_chargepic = ? ";
			list.add(pe.getP_chargepic());
		}
		if(pe.getP_chargepos()!=null && pe.getP_chargepos().trim().length()>0){
			sql += " and p.p_chargepos = ? ";
			list.add(pe.getP_chargepos());
		}
		if(pe.getP_name()!=null && pe.getP_name().trim().length()>0){
			sql += " and p.p_name like '%"+pe.getP_name()+"%'";
		}
		if(pe.getP_content()!=null && pe.getP_content().trim().length()>0){
			sql += " and p.p_contet = ? ";
			list.add(pe.getP_content());
		}
		if(pe.getP_cover()!=null && pe.getP_cover().trim().length()>0){
			sql += " and p.p_cover = ? ";
			list.add(pe.getP_cover());
		}
		if(pe.getP_member()!=null && pe.getP_member().trim().length()>0){
			sql += " and p.p_member = ? ";
			list.add(pe.getP_member());
		}
		if(pe.getP_id() >0){
			sql += " and p.p_id = ? ";
			list.add(pe.getP_id());	
		}
		if(pe.getP_uid() >0){
			sql += " and p.p_uid = ? ";
			list.add(pe.getP_uid());	
		}
		sql +=" order by p_time desc";
		if(pe.getStart()>0){
			sql += " limit "+(pe.getStart()-1)*pe.getPagesize()+","+pe.getPagesize();
		}
		return jdbcTemplate.queryForList(sql,list.toArray());
	}
}
