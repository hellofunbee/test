package com.jingu.IOT.service;

import com.jingu.IOT.dao.CtrlLogDao;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhiqiu
 * @email fei6751803@163.com
 * @date 2018-10-04 11:48:42
 */
@Component
public class CtrlLogService {
    @Autowired
    CtrlLogDao ctrlLogDao;

    public IOTResult save(PageData pd) {
        int back = ctrlLogDao.save(pd);

        if (back > 0)
            return new IOTResult(true, "保存成功", back, 0);
        return new IOTResult(false, "保存失败", null, 0);

    }

    public IOTResult del(PageData pd) {
        int back = ctrlLogDao.del(pd);
        if (back > 0)
            return new IOTResult(true, "删除成功", back, 0);
        return new IOTResult(false, "删除失败", null, 0);
    }

    public IOTResult update(PageData pd) {
        int back = ctrlLogDao.update(pd);
        if (back > 0)
            return new IOTResult(true, "修改成功", back, 0);
        return new IOTResult(false, "修改失败", null, 0);
    }

    public IOTResult list(PageData pd) {
        List<Map<String, Object>> back = ctrlLogDao.list(pd);

        if (back != null && back.size() > 0)
            return new IOTResult(true, "查看成功", back, 0);
        return new IOTResult(false, "暂无数据", null, 0);

    }

    public IOTResult findById(PageData params) {
        List<Map<String, Object>> back = ctrlLogDao.findById(params);

        if (back != null && back.size() > 0)
            return new IOTResult(true, "查看成功", back.get(0), 0);
        return new IOTResult(false, "暂无数据", null, 0);
    }
}
