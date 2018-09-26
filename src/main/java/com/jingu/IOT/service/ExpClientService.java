package com.jingu.IOT.service;

import com.jingu.IOT.dao.ExpClientDao;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by weifengxu on 2018/9/26.
 */
@Component
public class ExpClientService {

    @Autowired
    ExpClientDao expClientDao;

    public IOTResult save(PageData pd) {
        int back = expClientDao.save(pd);

        if (back > 0)
            return new IOTResult(true, "保存成功", back, 0);
        return new IOTResult(false, "保存失败", null, 0);

    }

    public IOTResult del(PageData pd) {
        int back = expClientDao.del(pd);
        if (back > 0)
            return new IOTResult(true, "删除成功", back, 0);
        return new IOTResult(false, "删除失败", null, 0);
    }

    public IOTResult update(PageData pd) {
        int back = expClientDao.update(pd);
        if (back > 0)
            return new IOTResult(true, "修改成功", back, 0);
        return new IOTResult(false, "修改失败", null, 0);
    }

    public IOTResult list(PageData pd) {
        List<Map<String, Object>> back = expClientDao.list(pd);

        if (back != null && back.size() > 0)
            return new IOTResult(true, "查看成功", back, 0);
        return new IOTResult(false, "暂无数据", null, 0);

    }
}