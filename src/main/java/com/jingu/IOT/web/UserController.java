/**
 * 项目名称：IOT
 * 类名称：UserController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月6日 上午10:00:39
 * 修改人：jianghu
 * 修改时间：2017年9月6日 上午10:00:39
 * 修改备注： 上午10:00:39
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.RelationShipEntity;
import com.jingu.IOT.entity.UserEntity;
import com.jingu.IOT.requset.RelationShipRequset;
import com.jingu.IOT.requset.UserReq;
import com.jingu.IOT.requset.UserRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.service.*;
import com.jingu.IOT.util.ToolUtil;
import com.jingu.IOT.util.Types;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jianghu
 * @ClassName: UserController
 * @Description: TODO
 * @date 2017年9月6日 上午10:00:39
 */
@RestController
public class UserController {

    private UserService userService;
    private ToolUtil toolUtil;
    private UploadService uploadService;
    private RelationShipService relationShipService;
    private CtrlService ctrlService;
    private CMSService cMSService;

    @Autowired
    public UserController(UserService userService, ToolUtil toolUtil, UploadService uploadService, RelationShipService relationShipService, CtrlService ctrlService, CMSService cMSService) {
        this.userService = userService;
        this.toolUtil = toolUtil;
        this.uploadService = uploadService;
        this.relationShipService = relationShipService;
        this.ctrlService = ctrlService;
        this.cMSService = cMSService;
    }


    /**
     * 2018年5月24日
     * jianghu
     *
     * @param uRequest
     * @return TODO登陆
     */
    @CrossOrigin
    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public IOTResult login(@RequestBody UserRequest uRequest) {
        IOTResult x = CheckUP(uRequest);
        if (x != null) return x;


        UserEntity userByPwd = userService.getUserByPwd(uRequest);
        if (userByPwd != null) {
            String intVal = toolUtil.getIntVal(ToolUtil.MESSAGEREADING + userByPwd.getUid() + ToolUtil.READ);
            if (intVal == null) {
                toolUtil.setIntVal(ToolUtil.MESSAGEREADING + userByPwd.getUid() + ToolUtil.READ, 0);
            }
            String sid = UUID.randomUUID().toString();
            //String string =ToolUtil.IOTUID+userByPwd.getUid();
            String string = String.valueOf(userByPwd.getUid());
            toolUtil.setCheck(ToolUtil.IOT + string, sid);
//			toolUtil.setCheckexpire(ToolUtil.IOT+userByPwd.getUid(), 86400);
            toolUtil.setHashUidSid(string, sid, userByPwd.getUid());
//			toolUtil.setHashUidSidexpire(string,86400);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("ckuid", string);
            map.put("cksid", sid);
            int tu_type = userByPwd.getTu_type();
            // 可以去找找他是否有权限管理相应的设备
            if (tu_type == 1 || tu_type == 2) {
                map.put("u_type", 1);
            } else {
                map.put("u_type", 3);
            }

            map.put("tu_type", tu_type);
            return new IOTResult(true, "登陆成功", map, 0);
        }

        JSONObject professorByPwd = userService.getProfessorByPwd(uRequest);
        if (professorByPwd != null) {

            String string = String.valueOf(professorByPwd.get("ckuid"));
            String sid = String.valueOf(professorByPwd.get("cksid"));
            toolUtil.setCheck(ToolUtil.IOT + string, String.valueOf(professorByPwd.get("cksid")));
            toolUtil.setHashUidSid(string, sid, Long.parseLong(string));
            return new IOTResult(true, "登陆成功", professorByPwd, 0);
        }
        return new IOTResult(false, "用户名或密码错误", null, 0);
    }

    // 添加用户
    @CrossOrigin
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public IOTResult addPoint(@RequestBody UserRequest ur) {
        IOTResult x = CheckUP(ur);
        if (x != null) return x;

        if (ur.getTu_type() == 1 || ur.getTu_type() == 2) {
            x = checkAdmin(ur);
            if (x != null) return x;

        }
        UserEntity userByName = userService.getUserByName(ur);
        if (userByName != null) {
            return new IOTResult(false, "用户名已存在", null, 3);
        }
        int addUser = userService.addUser(ur);
        if (addUser > 0) {
            return new IOTResult(true, "添加成功", null, 0);
        }
        return new IOTResult(false, "添加失败", null, 4);
    }


    //修改用户
    @CrossOrigin
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public IOTResult updateUser(@RequestBody UserRequest ur) {
        IOTResult x = chckSession(ur);
        if (x != null) return x;
        x = checkAdmin(ur);
        if (x != null) return x;

        if (ur.getTu_type() == 7) {
            int updateUser = cMSService.updateUserState(ur.getUid(), ur.getTu_state());
            if (updateUser > 0) {
                return new IOTResult(true, "保存成功", null, 0);
            }
            return new IOTResult(false, "保存失败", null, 0);
        }
        int addUser = userService.updateUser(ur);
        if (addUser > 0) {
            return new IOTResult(true, "修改成功", null, 0);
        }
        return new IOTResult(false, "修改失败", null, 3);
    }

    // 解绑用户
    @CrossOrigin
    @RequestMapping(value = "/unbind", method = RequestMethod.POST)
    public IOTResult unbind(@RequestBody UserRequest ur) {
        IOTResult x = chckSession(ur);
        if (x != null) return x;
        x = checkAdmin(ur);
        if (x != null) return x;

        int unbind = relationShipService.unbind(ur);
        if (unbind > 0) {
            return new IOTResult(true, "解绑成功", null, 0);
        }
        return new IOTResult(false, "解绑失败", null, 3);
    }


    //删除用户
    @CrossOrigin
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public IOTResult deleteUser(@RequestBody UserRequest ur) {
        IOTResult x = chckSession(ur);
        if (x != null) return x;
        x = checkAdmin(ur);
        if (x != null) return x;


        int addUser = userService.deleteUserByUid(ur);
        if (addUser > 0) {
            return new IOTResult(true, "删除成功", null, 0);
        }
        return new IOTResult(false, "删除失败", null, 3);
    }

    // 查看用户
    @CrossOrigin
    @RequestMapping(value = "/findUserById", method = RequestMethod.POST)
    public IOTResult findUserById(@RequestBody UserRequest ur) {
        IOTResult x = chckSession(ur);
        if (x != null) return x;
        x = checkAdmin(ur);
        if (x != null) return x;

        Map u = userService.findById(ur);

        if (u != null && !u.isEmpty()) {
            return new IOTResult2(true, "查看成功", u, 0);
        }
        return new IOTResult2(false, "暂无相关信息", null, 3);
    }


    // 查看用户
    @CrossOrigin
    @RequestMapping(value = "/listUserForMap", method = RequestMethod.POST)
    public IOTResult listUserForMap(@RequestBody UserRequest ur) {
        IOTResult x = chckSession(ur);
        if (x != null) return x;
        x = checkAdmin(ur);
        if (x != null) return x;

        List<Map<String, Object>> listUser = userService.listUserForMap(ur);
        int listUserCount = userService.listUserCount(ur);
        int totalpage = 0;
        if (listUserCount % ur.getPagesize() > 0) {
            totalpage = listUserCount / ur.getPagesize() + 1;
        } else {
            totalpage = listUserCount / ur.getPagesize();
        }
        if (listUser != null && !listUser.isEmpty()) {
            return new IOTResult2(true, "查看成功", listUser, 0, totalpage, listUserCount);
        }
        return new IOTResult2(false, "暂无相关信息", null, 3, totalpage, listUserCount);
    }

    // 查看用户
    @CrossOrigin
    @RequestMapping(value = "/listUser", method = RequestMethod.POST)
    public IOTResult listUser(@RequestBody UserRequest ur) {
        IOTResult x = chckSession(ur);
        if (x != null) return x;
        x = checkAdmin(ur);
        if (x != null) return x;

        List<UserEntity> listUser = userService.listUser(ur);
        int listUserCount = userService.listUserCount(ur);
        int totalpage = 0;
        if (listUserCount % ur.getPagesize() > 0) {
            totalpage = listUserCount / ur.getPagesize() + 1;
        } else {
            totalpage = listUserCount / ur.getPagesize();
        }
        if (listUser != null && !listUser.isEmpty()) {
            return new IOTResult2(true, "查看成功", listUser, 0, totalpage, listUserCount);
        }
        return new IOTResult2(false, "暂无相关信息", null, 3, totalpage, listUserCount);
    }


    // 管理员查看权限
    @CrossOrigin
    @RequestMapping(value = "/listPower", method = RequestMethod.POST)
    public IOTResult listPower(@RequestBody UserRequest ur) {
        IOTResult x = chckSession(ur);
        if (x != null) return x;
        x = checkAdmin(ur);
        if (x != null) return x;

        List<UserEntity> listUser = userService.listUser(ur);
        if (listUser == null) {
            listUser = new ArrayList<>();
        }

        RelationShipEntity relationShipEntity = new RelationShipEntity();
        for (UserEntity userEntity : listUser) {

            if (userEntity.getTu_type() == 3) {
                int uid2 = userEntity.getUid();
                relationShipEntity.setProducerid(uid2);
                List<Map<String, Object>> listRelationShipProduce = relationShipService.listRelationShip(relationShipEntity);
                relationShipEntity.setProducerid(0);
                relationShipEntity.setSuperviseid(uid2);
                List<Map<String, Object>> listRelationShipSupervis = relationShipService.listRelationShip(relationShipEntity);
                // tu_type 1 超级管理员   2 管理员           3 普通用户   4 生产者  5 监督者   6 生产者+监督者     7 专家
                if (listRelationShipProduce != null && listRelationShipSupervis != null && !listRelationShipProduce.isEmpty() && !listRelationShipSupervis.isEmpty()) {
                    userEntity.setTu_type(6);
                } else if (listRelationShipProduce != null && !listRelationShipProduce.isEmpty() && (listRelationShipSupervis == null || listRelationShipSupervis.isEmpty())) {
                    userEntity.setTu_type(4);
                } else if (listRelationShipSupervis != null && !listRelationShipSupervis.isEmpty() && (listRelationShipProduce == null || listRelationShipProduce.isEmpty())) {
                    userEntity.setTu_type(5);
                } else {

                }
            }
            relationShipEntity.setSuperviseid(0);
        }
        List<Map<String, Object>> listProfessor = userService.listProfessor(new UserEntity());
        for (Map<String, Object> map : listProfessor) {
            UserEntity userEntity = new UserEntity();
            userEntity.setTu_type(7);
            userEntity.setTu_name(map.get("u_uname").toString());
            userEntity.setTu_phone(map.get("u_phone").toString());
            userEntity.setUid(Integer.parseInt(map.get("u_id").toString()));
            userEntity.setTu_state(Integer.parseInt(map.get("u_state").toString()));
            listUser.add(userEntity);
        }

        if (listUser != null && !listUser.isEmpty()) {
            return new IOTResult2(true, "查看成功", listUser, 0);
        }
        return new IOTResult2(false, "暂无相关信息", null, 3);
    }


    // 上传图片
    @CrossOrigin
    @RequestMapping(value = "/addPicture", method = RequestMethod.POST)
    public
    @ResponseBody
    IOTResult addPicture(@RequestParam("picture") MultipartFile picture, @RequestParam("cksid") String cksid, @RequestParam("ckuid") String ckuid, @RequestParam("oldfile") String oldfile) {

        if (!oldfile.equals("")) {
            String[] split = oldfile.split("file_name=");
            System.out.println(split[1]);
            boolean deleteFile = uploadService.deleteFile(ToolUtil.FILEPATH + split[1]);
            System.out.println(deleteFile);
        }
        return uploadService.uploadFile2(ToolUtil.IOT, ckuid, picture);
    }

    // 上传图片
    @CrossOrigin
    @RequestMapping(value = "/addSuperPicture", method = RequestMethod.POST)
    public
    @ResponseBody
    IOTResult addSuperPicture(@RequestParam("picture") MultipartFile picture, @RequestParam("cksid") String cksid, @RequestParam("ckuid") String ckuid, @RequestParam("oldfile") String oldfile) {
        System.out.println(oldfile);
        if (!oldfile.equals("")) {
            String[] split = oldfile.split("file_name=");
            System.out.println(split[1]);
            boolean deleteFile = uploadService.deleteFile(ToolUtil.FILEPATH + split[1]);
            System.out.println(deleteFile);
        }
        return uploadService.uploadFile2("superAdmin", "ABC", picture);
    }


    // 查看专家
    @CrossOrigin
    @RequestMapping(value = "/updateLogoTitle", method = RequestMethod.POST)
    public IOTResult updateLogoTitle(@RequestBody UserReq u) {

        int ckAuthor = userService.ckAuthor(u.getU_uname(), u.getPassword());
        return null;

    }


    // 查看专家
    @CrossOrigin
    @RequestMapping(value = "/listProfessor", method = RequestMethod.POST)
    public IOTResult listProfessor(@RequestBody UserReq u) {
        u.setU_type(String.valueOf(2));
        int totalpage = 0;

        UserEntity ue = new UserEntity();
        ue.setTu_type(Types.usr_expert);

        int listUserCount = userService.listUserCount(ue);

        if (listUserCount % u.getPagesize() > 0) {
            totalpage = listUserCount / u.getPagesize() + 1;
        } else {
            totalpage = listUserCount / u.getPagesize();
        }

        List<UserEntity> listUser = userService.listUser(ue);

        if (listUser != null && !listUser.isEmpty()) {
            return new IOTResult2(true, "查看成功", listUser, 0, totalpage, listUserCount);
        }
        return new IOTResult2(false, "暂无相关信息", null, 0, totalpage, listUserCount);

    }

    // 查看关联关系
    @CrossOrigin
    @RequestMapping(value = "/listRelationShip", method = RequestMethod.POST)
    public IOTResult listRelationShip(@RequestBody RelationShipRequset re) {
        IOTResult r = chckSession(re);
        if (r != null) return r;
        r = checkAdmin(re);
        if (r != null) return r;

        List<Map<String, Object>> listRelationShip = relationShipService.listRelationShip(re);
        List<Map<String, Object>> collect = listRelationShip.stream().filter(x -> x.get("tu_name") != null).collect(Collectors.toList());
        if (collect != null && !collect.isEmpty()) {
            return new IOTResult2(true, "查看成功", collect, 0);
        }
        return new IOTResult2(false, "暂无相关信息", null, 0);

    }

    /**
     * 检查 用户名 和密码
     *
     * @param ur
     * @return
     */
    private IOTResult CheckUP(UserRequest ur) {
        if (ur.getTu_username() == null || ur.getTu_username().trim().length() < 1) {
            return new IOTResult(false, "用户名不能为空", null, 1);
        }
        if (ur.getTu_pwd() == null || ur.getTu_pwd().trim().length() < 1) {
            return new IOTResult(false, "密码不能为空", null, 2);
        }
        return null;
    }

    private IOTResult checkAdmin(RelationShipRequset re) {

        if(1 == 1)
        return null;

        long uid = toolUtil.getbase_uidSid(re.getCkuid(), re.getCksid());
        int ckAdmin = userService.ckSuperAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        return null;
    }

    private IOTResult checkAdmin(UserRequest re) {
        if(1 == 1)
            return null;

        long uid = toolUtil.getbase_uidSid(re.getCkuid(), re.getCksid());
        int ckAdmin = userService.ckSuperAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        return null;
    }


    private IOTResult chckSession(RelationShipRequset re) {
        if (re.getCksid() == null || re.getCksid().trim().length() < 1 || re.getCkuid() == null || re.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + re.getCkuid());
        if (check == null || !re.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        return null;
    }

    private IOTResult chckSession(UserRequest re) {
        if (re.getCksid() == null || re.getCksid().trim().length() < 1 || re.getCkuid() == null || re.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + re.getCkuid());
        if (check == null || !re.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        return null;
    }


}
