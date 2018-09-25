/**
 * 项目名称：IOT
 * 类名称：InputController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月10日 下午7:07:39
 * 修改人：jianghu
 * 修改时间：2017年10月10日 下午7:07:39
 * 修改备注： 下午7:07:39
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.InputEntity;
import com.jingu.IOT.entity.InputRequset;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.entity.ProduceEntity;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.service.InputService;
import com.jingu.IOT.service.ProduceService;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: InputController
 * @Description: TODO
 * @date 2017年10月10日 下午7:07:39
 */
@RestController
public class InputController {

    private ToolUtil toolUtil;
    private InputService inputService;
    private ProduceService produceService;

    @Autowired
    public InputController(ToolUtil toolUtil, InputService inputService, ProduceService produceService) {
        this.toolUtil = toolUtil;
        this.inputService = inputService;
        this.produceService = produceService;
    }

    @CrossOrigin
    @RequestMapping(value = "/addInput", method = RequestMethod.POST)
    public IOTResult addMessage(@RequestBody InputRequset ir) {
        if (ir.getCksid() == null || ir.getCksid().trim().length() < 1 || ir.getCkuid() == null || ir.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ir.getCkuid());
        if (check == null || !ir.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        //long uid = toolUtil.getbase_uidSid(ir.getCkuid(), ir.getCksid());

        ProduceEntity produceEntity = new ProduceEntity();

        List<InputEntity> list = new ArrayList<>();
        List<InputEntity> ips = ir.getInputEntity();
        if (ips != null && ips.size() == 1 && ips.get(0).getIn_id() > 0) {
            produceEntity.setP_id(ips.get(0).getIn_pid());
            List<Map<String, Object>> listProducePlan = produceService.listProducePlan(produceEntity);
            if (listProducePlan == null || listProducePlan.isEmpty()) {
                return new IOTResult(false, "生产计划不存在", null, 3);
            }
            InputEntity ip = ips.get(0);
            ip.setIn_class1(Integer.parseInt(listProducePlan.get(0).get("p_class1").toString()));
            ip.setIn_class1(Integer.parseInt(listProducePlan.get(0).get("p_class2").toString()));
            ip.setIn_ownername(listProducePlan.get(0).get("p_ownername").toString());
            ip.setIn_pname(listProducePlan.get(0).get("p_name").toString());
            ip.setIn_pstandrad(listProducePlan.get(0).get("p_standrad").toString());
            ip.setIn_parea(listProducePlan.get(0).get("p_harvestarea").toString());
            ip.setTp_id(Integer.parseInt(listProducePlan.get(0).get("tp_id").toString()));
            int updateInput = inputService.updateInput(ip);
            if (updateInput > 0) {
                return new IOTResult(true, "投入品记录修改成功", null, 0);
            }
            return new IOTResult(false, "投入品记录修改失败", null, 0);

        }
        for (InputEntity ip : ips) {
            produceEntity.setP_id(ip.getIn_pid());
            List<Map<String, Object>> listProducePlan = produceService.listProducePlan(produceEntity);
            if (listProducePlan == null || listProducePlan.isEmpty()) {
                return new IOTResult(false, "生产计划不存在", null, 3);
            }

            if (toolUtil.cktime(ip.getP_begintime()).compareTo(toolUtil.cktime(ip.getIn_time())) > 0) {
                return new IOTResult(false, "时间存在错误", null, 3);
            }
            if (toolUtil.cktime(ip.getP_endtime()).compareTo(toolUtil.cktime(ip.getIn_time())) < 0) {
                return new IOTResult(false, "时间存在错误", null, 3);
            }
            ip.setIn_ownername(listProducePlan.get(0).get("p_ownername").toString());
            ip.setIn_pname(listProducePlan.get(0).get("c2_name").toString());
            ip.setIn_pstandrad(listProducePlan.get(0).get("p_standrad").toString());
            ip.setIn_parea(listProducePlan.get(0).get("p_harvestarea").toString());
            ip.setIn_class1(Integer.parseInt(listProducePlan.get(0).get("p_class1").toString()));
            ip.setIn_class2(Integer.parseInt(listProducePlan.get(0).get("p_class2").toString()));
            ip.setIn_time(toolUtil.cktime(ip.getIn_time()));
            ip.setTp_id(Integer.parseInt(listProducePlan.get(0).get("tp_id").toString()));
            list.add(ip);
        }
        int addInputList = inputService.addInputList(list);

        if (addInputList > 0) {
            return new IOTResult(true, "投入品记录添加成功", null, 0);
        }
        return new IOTResult(false, "投入品记录添加失败", null, 0);

    }

    @CrossOrigin
    @RequestMapping(value = "/updateInput", method = RequestMethod.POST)
    public IOTResult updateInput(@RequestBody InputRequset ir) {
        if (ir.getCksid() == null || ir.getCksid().trim().length() < 1 || ir.getCkuid() == null || ir.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ir.getCkuid());
        if (check == null || !ir.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        int addMessage = inputService.updateInput(ir);
        if (addMessage > 0) {
            return new IOTResult(true, "投入品记录修改成功", null, 0);
        }
        return new IOTResult(false, "投入品记录修改失败", null, 0);

    }


    @CrossOrigin
    @RequestMapping(value = "/deleteInput", method = RequestMethod.POST)
    public IOTResult deleteInput(@RequestBody InputRequset ir) {
        if (ir.getCksid() == null || ir.getCksid().trim().length() < 1 || ir.getCkuid() == null || ir.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ir.getCkuid());
        if (check == null || !ir.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }


        int addMessage = inputService.deleteInput(ir);
        if (addMessage > 0) {
            return new IOTResult(true, "投入品记录删除成功", null, 0);
        }
        return new IOTResult(false, "投入品记录删除失败", null, 0);

    }


    @CrossOrigin
    @RequestMapping(value = "/listInput", method = RequestMethod.POST)
    public IOTResult listInput(@RequestBody InputRequset ir) {
        if (ir.getCksid() == null || ir.getCksid().trim().length() < 1 || ir.getCkuid() == null || ir.getCkuid() == null) {
            return new IOTResult2(false, "信息不规范", null, 1, 0, 0);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ir.getCkuid());
        if (check == null || !ir.getCksid().equals(check)) {
            return new IOTResult2(false, "登陆失效", null, 2, 0, 0);
        }

        int tp_id = 0;
        PointEntity pointEntity = ir.getPointEntity();
        if (pointEntity != null) {
            tp_id = pointEntity.getTp_id();
        }
        ir.setTp_id(tp_id);
        int pagesize = 0;
        int listInputCount = inputService.listInputCount(ir);
        int totalpage = 0;
        if (ir.getApp() == 1) {
            pagesize = ir.getAppPagesize();
        } else {
            pagesize = ir.getPagesize();
        }
        if (listInputCount % pagesize > 0) {
            totalpage = listInputCount / pagesize + 1;
        } else {
            totalpage = listInputCount / pagesize;
        }

        List<Map<String, Object>> listInput = inputService.listInput(ir);
        if (listInput == null || listInput.isEmpty()) {
            return new IOTResult2(false, "暂无相关信息", null, 0, 0, 0);
        }
        return new IOTResult2(true, "投入品查看成功", listInput, 0, totalpage, listInputCount);

    }

}
