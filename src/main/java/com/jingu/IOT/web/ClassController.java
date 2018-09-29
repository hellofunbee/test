package com.jingu.IOT.web;

import com.jingu.IOT.entity.ClassEntity;
import com.jingu.IOT.requset.ClassRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.*;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 类目
 *
 * @author xsmal
 */
@RestController
public class ClassController {
    private ClassService service;
    private ToolUtil tool;
    private UserService userService;
    private ProduceService produceService;
    private HomePageService homePageService;
    private MessageService messageService;
    private PointService pointService;

    @Autowired
    public ClassController(ClassService service, ToolUtil tool, UserService userService, ProduceService produceService,
                           HomePageService homePageService, MessageService messageService, PointService pointService) {
        this.service = service;
        this.tool = tool;
        this.userService = userService;
        this.produceService = produceService;
        this.homePageService = homePageService;
        this.messageService = messageService;
        this.pointService = pointService;
    }

    // 增加类目
    @CrossOrigin
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public IOTResult addClass(@RequestBody ClassRequest c) {
        if (c.getCksid() == null || c.getCksid().trim().length() < 1 || c.getCkuid() == null
                || c.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        String check = tool.getCheck(ToolUtil.IOT + c.getCkuid());
        if (check == null || !check.equals(c.getCksid())) {
            return new IOTResult(false, "登录失效", null, 2);
        }
        long uid = tool.getbase_uidSid(c.getCkuid(), c.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        // 等级 // 名称重复
        if (c.getC_lev() == 1 && c.getC_rid() != 0) {
            return new IOTResult(false, "一级类目rid为0", null, 3);
        }
        if (c.getC_lev() == 2 && c.getC_rid() == 0) {
            return new IOTResult(false, "二级类目rid部位0", null, 3);
        }
        int ckClassByName = service.ckClassByName(c);
        if (ckClassByName > 0) {
            return new IOTResult(false, "该名称类目已存在", null, 3);
        }
        c.setC_state(1);
        int addClass = service.addClass(c);
        if (addClass > 0) {
            return new IOTResult(true, "添加成功", null, 0);
        }
        return new IOTResult(false, "添加失败", null, 0);
    }


    //
    // // 修改类目
    @CrossOrigin
    @RequestMapping(value = "/updateClass", method = RequestMethod.POST)
    public IOTResult updateClass(@RequestBody ClassRequest c) {
        if (c.getCksid() == null || c.getCksid().trim().length() < 1 || c.getCkuid() == null
                || c.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        String check = tool.getCheck(ToolUtil.IOT + c.getCkuid());
        if (check == null || !check.equals(c.getCksid())) {
            return new IOTResult(false, "登录失效", null, 2);
        }
        long uid = tool.getbase_uidSid(c.getCkuid(), c.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        // 等级 // 名称重复
        if (c.getC_lev() == 1 && c.getC_rid() != 0) {
            return new IOTResult(false, "一级类目rid为0", null, 3);
        }
        if (c.getC_lev() == 2 && c.getC_rid() == 0) {
            return new IOTResult(false, "二级类目rid部位0", null, 4);
        }
        int ckClassByName = service.ckClassByName(c);
        if (ckClassByName > 0) {
            return new IOTResult(false, "该名称类目已存在", null, 5);
        }
        int updateClass = service.updateClass(c);
        if (updateClass > 0) {
            return new IOTResult(true, "修改成功", null, 0);
        }
        return new IOTResult(true, "修改失败", null, 0);

    }

    // //删除类目
    @CrossOrigin
    @RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
    public IOTResult deleteClass(@RequestBody ClassRequest c) {
        if (c.getCksid() == null || c.getCksid().trim().length() < 1 || c.getCkuid() == null
                || c.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        String check = tool.getCheck(ToolUtil.IOT + c.getCkuid());
        if (check == null || !check.equals(c.getCksid())) {
            return new IOTResult(false, "登录失效", null, 2);
        }
        long uid = tool.getbase_uidSid(c.getCkuid(), c.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        // 检测这个分类是否配被引用
        ClassEntity classEntity = new ClassEntity();
        classEntity.setC_id(c.getC_id());
        List<ClassEntity> queryAllClass2 = service.queryAllClass(classEntity);
        if (queryAllClass2 == null || queryAllClass2.isEmpty()) {
            return new IOTResult(false, "分类不存在", null, 3);
        }
        ClassEntity classEntity2 = queryAllClass2.get(0);
        // 1 作物 (计划) 2 农资 3 预警 4 政策 5 即时信息 7 首页
        if (classEntity2.getC_type() == 1) {
            int ckClass = produceService.ckClass(classEntity2.getC_id());
            if (ckClass > 0) {
                return new IOTResult(false, "分类存在引用不能删除", null, 3);
            }
        }
        if (classEntity2.getC_type() == 3 || classEntity2.getC_type() == 4 || classEntity2.getC_type() == 5
                || classEntity2.getC_type() == 6 || classEntity2.getC_type() == 7) {
            int ckClass = messageService.ckClass(classEntity2.getC_id());
            if (ckClass > 0) {
                return new IOTResult(false, "分类存在引用不能删除", null, 3);
            }
        }
        // 检查是否子类 //
        classEntity.setC_id(0);
        classEntity.setC_rid(c.getC_id());
        classEntity.setC_state(1);
        List<ClassEntity> queryAllClass = service.queryAllClass(classEntity);

        if (queryAllClass == null || queryAllClass.isEmpty()) {
            classEntity.setC_rid(0);
            classEntity.setC_id(c.getC_id());
            classEntity.setC_state(2);
            // 检查是否被引用
            int deleteClass = service.updateClass(classEntity);
            if (deleteClass > 0) {
                return new IOTResult(true, "删除成功", null, 0);
            }
            return new IOTResult(false, "删除失败", null, 3);
        }
        return new IOTResult(false, "含有子类不能删除", null, 4);
    }

    //
    // //后台查询首页显示的类目
    @CrossOrigin
    @RequestMapping(value = "/listClass1", method = RequestMethod.POST)
    public IOTResult listClass1(@RequestBody ClassRequest c) {
        if (c.getCksid() == null || c.getCksid().trim().length() < 1 || c.getCkuid() == null
                || c.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        String check = tool.getCheck(ToolUtil.IOT + c.getCkuid());
        if (check == null || !check.equals(c.getCksid())) {
            return new IOTResult(false, "登录失效", null, 2);
        }
        List<Map<String, Object>> list = service.listClass1(c);
        if (list.size() > 0) {
            return new IOTResult(true, "查询成功", list, 0);
        }
        return new IOTResult(false, "没有相关信息", null, 0);
    }


    // 同过一级分类查看二级分类
    @CrossOrigin
    @RequestMapping(value = "/listClass2Byrid", method = RequestMethod.POST)
    public IOTResult listClass2Byrid(@RequestBody ClassRequest c) {
        if (c.getCksid() == null || c.getCksid().trim().length() < 1 || c.getCkuid() == null
                || c.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        String check = tool.getCheck(ToolUtil.IOT + c.getCkuid());
        if (check == null || !check.equals(c.getCksid())) {
            return new IOTResult(false, "登录失效", null, 2);
        }
        List<Map<String, Object>> list = service.listClass2Byrid(c);
        if (list.size() > 0) {
            return new IOTResult(true, "查询成功", list, 0);
        }
        return new IOTResult(false, "没有相关信息", null, 0);
    }



    @CrossOrigin
    @RequestMapping(value = "/listClass", method = RequestMethod.POST)
    public IOTResult listClass(@RequestBody ClassRequest c) {
        if (c.getCksid() == null || c.getCksid().trim().length() < 1 || c.getCkuid() == null
                || c.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        String check = tool.getCheck(ToolUtil.IOT + c.getCkuid());
        if (check == null || !check.equals(c.getCksid())) {
            return new IOTResult(false, "登录失效", null, 2);
        }
        List<Map<String, Object>> list = service.listTreeClass(c);
        if (list.size() > 0) {
            return new IOTResult(true, "查询成功", list, 0);
        }
        return new IOTResult(false, "没有相关信息", null, 0);
    }


    // //后台设置主页类目显示
    // @CrossOrigin
    // @RequestMapping(value="/setClassShow",method=RequestMethod.POST)
    // public IOTResult setClassShow(@RequestBody ClassShowRequest c){
    // if(c.getUid()<1||c.getSid()==null||c.getSid().trim().length()<1){
    // return new IOTResult(false, "信息不规范", null, 0);
    // }
    // if(!tool.getSid(c.getUid()).equals(c.getSid())){
    // return new IOTResult(false, "登录失效", null, 1);
    // }
    // if(service.setClassShow(c.getList())>0){
    // return new IOTResult(true, "修改成功", null, 0);
    // }
    // return new IOTResult(false, "修改失败", null, 0);
    // }
    //
    // //wx查询首页显示的类目
    // @CrossOrigin
    // @RequestMapping(value="/wxQueryShowClass",method=RequestMethod.POST)
    // public IOTResult wxQueryShowClass(@RequestBody Map<String, String> map)
    // throws UnsupportedEncodingException{
    // /*if(map.get("code") ==null ||map.get("code").trim().length()<1){
    // return new IOTResult(false, "信息不规范", null, 1);
    // }
    // String code = map.get("code");
    // //验证code
    // UserEntity user = tool.getWxUser(code);
    // if(user ==null){
    // user = wxService.verifyCode(code,null);
    // if(user==null){
    // return new IOTResult(false, "微信登录失效", null, 1);
    // }
    // tool.setWxUser(code, user);
    // }*/
    //
    // List<Map<String, Object>> list = service.queryShowClass();
    // if(list.size()>0){
    // return new IOTResult(true, "查询成功", list, 0);
    // }
    // return new IOTResult(false, "没有相关信息", null, 0);
    // }
    // //wx查询所有类目
    // @CrossOrigin
    // @RequestMapping(value="/wxQueryAllClass",method=RequestMethod.POST)
    // public IOTResult wxQueryAllClass(@RequestBody Map<String, String> map)
    // throws UnsupportedEncodingException{
    /// * if(map.get("code") ==null ||map.get("code").trim().length()<1){
    // return new IOTResult(false, "信息不规范", null, 1);
    // }
    // String code = map.get("code");
    // //验证code
    // UserEntity user = tool.getWxUser(code);
    // if(user ==null){
    // user = wxService.verifyCode(code,null);
    // if(user==null){
    // return new IOTResult(false, "微信登录失效", null, 1);
    // }
    // tool.setWxUser(code, user);
    // }*/
    // List<ClassEntity> list = service.queryAllClass(new ClassEntity(0, 1, 0,
    // 1, null, 0, null));
    // if(list.size()>0){
    // return new IOTResult(true, "查询成功", list, 0);
    // }
    // return new IOTResult(false, "没有相关信息", null, 0);
    // }
    //
    // @CrossOrigin
    // @RequestMapping(value="/wxSecondClass",method=RequestMethod.POST)
    // public IOTResult wxSecendClass(@RequestBody Map<String, String> map)
    // throws UnsupportedEncodingException{
    /// * if(map.get("code") ==null ||map.get("code").trim().length()<1){
    // return new IOTResult(false, "信息不规范", null, 1);
    // }
    // String code = map.get("code");
    // //验证code
    // UserEntity user = tool.getWxUser(code);
    // if(user ==null){
    // user = wxService.verifyCode(code,null);
    // if(user==null){
    // return new IOTResult(false, "微信登录失效", null, 1);
    // }
    // tool.setWxUser(code, user);
    // }*/
    // String string = map.get("c_id");
    // int rid = Integer.parseInt(string);
    // List<ClassEntity> list = service.queryAllClass(new ClassEntity(0, 2, rid,
    // 1, null, 0, null));
    // Collections.sort(list);
    // Map<Character,List<ClassEntity>> map2 = new
    // HashMap<Character,List<ClassEntity>>();
    // if(list.size()>0){
    // for (ClassEntity classEntity : list) {
    // String c_name = classEntity.getC_name();
    // String firstSpell = PingYinUtil.getFirstSpell(c_name);
    // //System.out.println(firstSpell);
    // if(map2.containsKey(firstSpell.charAt(0))){
    // List<ClassEntity> list2 = map2.get(firstSpell.charAt(0));
    // list2.add(classEntity);
    // map2.put(firstSpell.charAt(0),list2);
    // }else{
    // ArrayList<ClassEntity> list2 = new ArrayList<ClassEntity>();
    // list2.add(classEntity);
    // map2.put(firstSpell.charAt(0),list2);
    // }
    // }
    // return new IOTResult(true, "查询成功", map2, 0);
    // }
    // return new IOTResult(false, "没有相关信息", null, 0);
    // }
    // //查看类目
    // @CrossOrigin
    // @RequestMapping(value="/queryForClass",method=RequestMethod.POST)
    // public IOTResult queryForClass(@RequestBody ClassRequest c){
    // if(c.getC_lev()<1||c.getUid()<1||c.getSid()==null||c.getSid().trim().length()<1){
    // return new IOTResult(false, "请输入需要查看的类目", null, 0);
    // }
    // int checking =0;
    // String check = tool.getCheck(ToolUtil.ADMIN+c.getUid());
    // if (check !=null && check.equals(c.getSid())) {
    // checking = 1;
    // }
    // String check2 = tool.getCheck(ToolUtil.SELLER+c.getUid());
    // if (check2!=null && check2.equals(c.getSid())) {
    // checking = 2;
    // }
    // if(checking == 0){
    // return new IOTResult(false, "登陆失效", null, 0);
    // }
    //
    // List<ClassEntity> list = service.queryClass(c);
    // if(list.size()<1){
    // return new IOTResult(false, "没有相关信息", null, 0);
    // }
    // return new IOTResult(true, "查询成功", list, 0);
    // }
    //
}
