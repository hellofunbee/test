package com.jingu.IOT.service;

import com.jingu.IOT.dao.ClassDao;
import com.jingu.IOT.entity.ClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ClassService {
    private ClassDao dao;

    @Autowired
    public ClassService(ClassDao dao) {
        this.dao = dao;
    }

    public int addClass(ClassEntity c) {
        return dao.addClass(c);
    }

    public List<ClassEntity> queryAllClass(ClassEntity c) {
        return dao.queryAllClass(c);
    }

    public int updateClass(ClassEntity c) {
        return dao.updateClass(c);
    }

    public int deleteClass(ClassEntity c) {
        return dao.deleteClass(c);
    }

    public int ckClassByName(ClassEntity c) {
        return dao.ckClassByName(c);
    }

    public int testSetClassShow(List<ClassEntity> list) {
        for (ClassEntity c : list) {
            if (c.getC_id() < 1) {
                return 0;
            }
            List<ClassEntity> l = dao.queryAllClass(new ClassEntity(c.getC_id(), 0, 0, 0, null, 0, null, 0));
            if (l.size() < 1 || l.get(0).getC_lev() != 1 || l.get(0).getC_state() != 1) {
                return 0;
            }
        }
        return 1;
    }

    public List<Map<String, Object>> listClass1(ClassEntity c) {
//		Map<Object,Object> map = new HashMap<>();
        c.setC_lev(1);
        c.setC_state(1);
        List<Map<String, Object>> listAllClass = dao.listAllClass(c);
        if (c.getC_type() == 2 || c.getC_type() == 1 || c.getC_type() == 7) {
            for (Map<String, Object> map : listAllClass) {
                c.setC_rid(Integer.parseInt(map.get("c_id").toString()));
                c.setC_lev(2);
                List<Map<String, Object>> listAllClass2 = dao.listAllClass(c);
                if (listAllClass2 != null && !listAllClass2.isEmpty()) {
                    map.put("list", listAllClass2);
                } else {
                    map.put("list", new ArrayList<>());
                }
            }
        } else {
            for (Map<String, Object> map : listAllClass) {
                map.put("list", null);
            }
        }
        return listAllClass;
    }

    public List<Map<String, Object>> listClass2Byrid(ClassEntity c) {
        return dao.listClass2Byrid(c);
    }
}
