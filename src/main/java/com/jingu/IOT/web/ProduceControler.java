/**
 * 项目名称：IOT
 * 类名称：ProduceControler
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月10日 下午4:56:22
 * 修改人：jianghu
 * 修改时间：2017年10月10日 下午4:56:22
 * 修改备注： 下午4:56:22
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.MainDeviceEntity;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.requset.ProduceRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.service.MainDeviceService;
import com.jingu.IOT.service.PointService;
import com.jingu.IOT.service.ProduceService;
import com.jingu.IOT.util.CommonUtils;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: ProduceControler
 * @Description: TODO
 * @date 2017年10月10日 下午4:56:22
 * 生产
 */

@RestController
public class ProduceControler {

    private ToolUtil toolUtil;
    private ProduceService produceService;
    private PointService pointService;
    private MainDeviceService mainDeviceService;

    @Autowired
    public ProduceControler(ToolUtil toolUtil, ProduceService produceService, PointService pointService, MainDeviceService mainDeviceService) {
        this.toolUtil = toolUtil;
        this.produceService = produceService;
        this.pointService = pointService;
        this.mainDeviceService = mainDeviceService;
    }

    // 添加生产计划
    @CrossOrigin
    @RequestMapping(value = "/addProduce", method = RequestMethod.POST)
    public IOTResult addProduce(@RequestBody ProduceRequset pr) {
        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null || pr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
        PointEntity pointEntity = pr.getPointEntity();
//		pointEntity.setUid(uid);
        List<Map<String, Object>> listPoint = pointService.listPoint(pointEntity);
        if (listPoint == null || listPoint.isEmpty()) {
            return new IOTResult(false, "节点不存在", null, 3);
        }
        if (pr.getP_id() > 0) {
            int addProducePlan = produceService.updateProducePlan(pr);
            if (addProducePlan > 0) {
                return new IOTResult(true, "生产计划修改成功", null, 0);
            }
            return new IOTResult(false, "生产计划修改失败", null, 4);
        }
        MainDeviceEntity mainDeviceEntity = new MainDeviceEntity();
        mainDeviceEntity.setId(pointEntity.getTp_id());
        MainDeviceEntity mainDeviceById = mainDeviceService.getMainDeviceById(mainDeviceEntity);
        pr.setP_ownername(mainDeviceById.getName());
        pr.setP_begintime(toolUtil.cktime(pr.getP_begintime()));
        pr.setP_endtime(toolUtil.cktime(pr.getP_endtime()));
        pr.setTp_id(pointEntity.getTp_id());
        int addProducePlan = produceService.addProducePlan(pr);
        if (addProducePlan > 0) {
            return new IOTResult(true, "生产计划创建成功", null, 0);
        }
        return new IOTResult(false, "生产计划创建失败", null, 4);
    }

    // 修改生产计划
    @CrossOrigin
    @RequestMapping(value = "/updateProduce", method = RequestMethod.POST)
    public IOTResult updateProduce(@RequestBody ProduceRequset pr) {
        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null || pr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        /*
        long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
		PointEntity pointEntity = pr.getPointEntity();
		pointEntity.setUid(uid);
		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
		if(listPoint==null || listPoint.isEmpty()){
			return  new IOTResult(false,"节点不存在",null,3);
		}*/
        int addProducePlan = produceService.updateProducePlan(pr);
        if (addProducePlan > 0) {
            return new IOTResult(true, "生产计划修改成功", null, 0);
        }
        return new IOTResult(false, "生产计划修改失败", null, 4);
    }

    // 删除生产计划
    @CrossOrigin
    @RequestMapping(value = "/deleteProduce", method = RequestMethod.POST)
    public IOTResult deleteProduce(@RequestBody ProduceRequset pr) {
        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null || pr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
    /*	PointEntity pointEntity = pr.getPointEntity();

		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
		if(listPoint==null || listPoint.isEmpty()){
			return  new IOTResult(false,"节点不存在",null,3);
		}*/
        int addProducePlan = produceService.deleteProducePlan(pr);
        if (addProducePlan > 0) {
            return new IOTResult(true, "生产计划删除成功", null, 0);
        }
        return new IOTResult(false, "生产计划删除失败", null, 4);
    }

    // 查看生产计划
    @CrossOrigin
    @RequestMapping(value = "/listProduce", method = RequestMethod.POST)
    public IOTResult listProduce(@RequestBody ProduceRequset pr) {
        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null || pr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult2(false, "登陆失效", null, 2, 0, 0);
        }
        long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
        PointEntity pointEntity = pr.getPointEntity();
//		pointEntity.setUid(uid);

//		List<Map<String,Object>> listPoint = pointService.listPoint(pointEntity);
//		if(listPoint==null || listPoint.isEmpty()){
//			return  new IOTResult2(false,"节点不存在",null,3,0,0);
//		}
        if (pointEntity == null) {
            pointEntity = new PointEntity();
        }
        int pagesize = 0;
        pr.setTp_id(pointEntity.getTp_id());
        int listProducePlanCount = produceService.listProducePlanCount(pr);
        if (pr.getApp() == 1) {
            pagesize = pr.getAppPagesize();
        } else {
            pagesize = pr.getPagesize();
        }
        int totalpage = 0;
        if (listProducePlanCount % pagesize > 0) {
            totalpage = listProducePlanCount / pagesize + 1;
        } else {
            totalpage = listProducePlanCount / pagesize;
        }

        List<Map<String, Object>> listProducePlan = produceService.listProducePlan(pr);
        if (listProducePlan == null || listProducePlan.isEmpty()) {
            return new IOTResult2(false, "暂无相关信息", null, 0, 0, 0);
        }
        return new IOTResult2(true, "生产计划查看成功", listProducePlan, 0, totalpage, listProducePlanCount);
    }

    // 根据时间产看生产计划
    @CrossOrigin
    @RequestMapping(value = "/listProduceBytime", method = RequestMethod.POST)
    public IOTResult listProduceBytime(@RequestBody ProduceRequset pr) {
        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null || pr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
        PointEntity pointEntity = pr.getPointEntity();
//		pointEntity.setUid(uid);
        List<Map<String, Object>> listPoint = pointService.listPoint(pointEntity);
        if (listPoint == null || listPoint.isEmpty()) {
            return new IOTResult(false, "节点不存在", null, 3);
        }
        List<Map<String, Object>> listProducePlan = produceService.listProducePlanBytime(pr);
        if (listProducePlan == null || listProducePlan.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "生产计划查看成功", listProducePlan, 4);
    }


    // 根据时间产看生产计划
    @CrossOrigin
    @RequestMapping(value = "/produceAnalysis", method = RequestMethod.POST)
    public IOTResult produceAnalysis(@RequestBody ProduceRequset pr) {
        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null || pr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }

        List<Integer> tp_ids = pr.getTp_ids();
        List<Map> back = new ArrayList<>();
        for (int tp_id : tp_ids) {
            pr.setTp_id(tp_id);
            Map p = pointService.findById(tp_id);
            if (p == null || !CommonUtils.has(p.get("tp_id")))
                continue;

            Map m = produceService.sumArea(pr);
            List<Map<String, Object>> standard = produceService.groupByStandrad(pr);
            List<Map<String, Object>> class1 = produceService.groupByClass1(pr);
            List<Map<String, Object>> class2 = produceService.groupByClass2(pr);

            //1: "有机", 2: "绿色", 3: "无公害"
            for (Map s : standard) {

                if (s.get("p_standrad").equals("1")) {
                    s.put("name", "有机");
                }
                if (s.get("p_standrad").equals("2")) {
                    s.put("name", "绿色");
                }
                if (s.get("p_standrad").equals("3")) {
                    s.put("name", "无公害");
                }
            }

            Map result = new HashMap();
            result.put("standard", standard);
            result.put("class1", class1);
            result.put("class2", class2);
            result.put("deviceName", p.get("tp_name"));
            result.put("deviceId", p.get("deviceId"));


            back.add(result);
        }


        return new IOTResult(true, "查询成功", back, 4);
    }


}
