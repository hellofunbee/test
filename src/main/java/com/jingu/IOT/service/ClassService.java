package com.jingu.IOT.service;

import com.jingu.IOT.dao.ClassDao;
import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.requset.ClassRequest;
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

    /**
     * 一级分类
     *
     * @param c
     * @return
     */
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

    public Map<String, Object> findById(int c_id) {

        if(c_id <= 0)
            return null;
        return dao.findById(c_id);
    }

    public List<Map<String, Object>> listTreeClass(ClassRequest classEntity) {
        ;
        List<Map<String, Object>> cs = dao.listClass(classEntity);

        if (classEntity.getPoint() != null && classEntity.getPoint().getTp_id() > 0) {
            List<Map<String, Object>> inClass2 = dao.listInputClass(classEntity.getPoint());


            if (inClass2 == null || inClass2.size() == 0)
                return new ArrayList<>();


            //过滤
            for (int i = 0; i < cs.size(); i++) {
                Map cc = cs.get(i);
                if (!cc.get("c_lev").equals(1)) {

                    boolean has = false;
                    for (Map c2 : inClass2) {
                        if (c2.get("c_id").equals(cc.get("c_id"))) {
                            has = true;
                            break;
                        }
                    }

                    if (!has) {
                        cs.remove(cc);
                        i--;
                    }
                }
            }
        }

//        Comparator<Map<String, Object>> byIndex = (m1, m2) -> Integer.compare(Integer.parseInt(m2.get("tp_index").toString()),Integer.parseInt(m1.get("tp_index").toString()));
//        Collections.sort(cs, byIndex);

        return effact(cs);
    }

    private List<Map<String, Object>> effact(List<Map<String, Object>> cs) {

        if (cs == null || cs.size() == 0) return new ArrayList<>();
        List<Map<String, Object>> list = new ArrayList<>();

        for (Map m : cs) {
            if ((Integer) m.get("c_rid") == 0) {
                List<Map<String, Object>> l = getChildrens(m, cs);
                if (l.size() > 0) {
                    m.put("rank", l);
                    list.add(m);
                }
            }

        }
        return list;
    }

    private List<Map<String, Object>> getChildrens(Map m, List<Map<String, Object>> cs) {
        List<Map<String, Object>> list = new ArrayList<>();

        for (Map c : cs) {
            if (c.get("c_rid").equals(m.get("c_id"))) {
                if ((Integer) c.get("c_rid") == 0) {
                    c.put("rank", getChildrens(c, cs));
                }
                list.add(c);
            }
        }
        return list;

    }


}
