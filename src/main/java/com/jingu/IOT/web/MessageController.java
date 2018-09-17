/**
 * 项目名称：IOT
 * 类名称：MessageController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月10日 上午11:17:12
 * 修改人：jianghu
 * 修改时间：2017年10月10日 上午11:17:12
 * 修改备注： 上午11:17:12
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.requset.MessageRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.MessageService;
import com.jingu.IOT.service.PointService;
import com.jingu.IOT.service.UserService;
import com.jingu.IOT.util.ToolUtil;
import com.jingu.IOT.util.Types;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: MessageController
 * @Description: TODO
 * @date 2017年10月10日 上午11:17:12
 */
@RestController
public class MessageController {

    private MessageService messageService;
    private ToolUtil toolUtil;
    private PointService pointService;
    private UserService userService;

    @Autowired
    public MessageController(MessageService messageService, ToolUtil toolUtil, PointService pointService,
                             UserService userService) {
        this.messageService = messageService;
        this.toolUtil = toolUtil;
        this.pointService = pointService;
        this.userService = userService;

    }

    // 发布消息
    @CrossOrigin
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public IOTResult addMessage(@RequestBody MessageRequset mr) {
        if (mr.getCksid() == null || mr.getCksid().trim().length() < 1 || mr.getCkuid() == null
                || mr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mr.getCkuid());
        if (check == null || !mr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mr.getCkuid(), mr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        // PointEntity pointEntity = mr.getPointEntity();
        // pointEntity.setUid(uid);
        // pointEntity.setRole(String.valueOf(uid));
        // PointEntity point = pointService.getPoint(pointEntity);
        // if(point ==null){
        // return new IOTResult(false,"节点不存在",null,3);
        // }
        if (mr.getM_type() == 2) {
            String intVal = toolUtil.getIntVal(ToolUtil.MESSAGE);
            if (intVal == null) {
                toolUtil.setIntVal(ToolUtil.MESSAGE, 0);
            }
            toolUtil.incIntVal(ToolUtil.MESSAGE);
        }
        int addMessage = messageService.addMessage(mr);
        if (addMessage > 0) {
            return new IOTResult(true, "消息发布成功", mr, 0);
        }
        return new IOTResult(false, "消息发布失败", null, 0);

    }


    // 修改消息
    @CrossOrigin
    @RequestMapping(value = "/updateMessage", method = RequestMethod.POST)
    public IOTResult updateMessage(@RequestBody MessageRequset mr) {
        if (mr.getCksid() == null || mr.getCksid().trim().length() < 1 || mr.getCkuid() == null
                || mr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mr.getCkuid());
        if (check == null || !mr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mr.getCkuid(), mr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        mr.setM_time(String.valueOf("1"));
        int addMessage = messageService.updateMessage(mr);
        if (addMessage > 0) {
            return new IOTResult(true, "消息发布成功", null, 0);
        }
        return new IOTResult(true, "消息发布失败", null, 0);

    }

    // 删除消息
    @CrossOrigin
    @RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
    public IOTResult deleteMessage(@RequestBody MessageRequset mr) {
        if (mr.getCksid() == null || mr.getCksid().trim().length() < 1 || mr.getCkuid() == null
                || mr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mr.getCkuid());
        if (check == null || !mr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mr.getCkuid(), mr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        // PointEntity pointEntity = mr.getPointEntity();
        // pointEntity.setUid(uid);
        // pointEntity.setRole(String.valueOf(uid));
        // PointEntity point = pointService.getPoint(pointEntity);
        // if(point ==null){
        // return new IOTResult(false,"节点不存在",null,3);
        // }
        // 设置状态
        int addMessage = messageService.deleteMessage(mr);
        if (addMessage > 0) {
            return new IOTResult(true, "消息删除成功", null, 0);
        }
        return new IOTResult(false, "消息删除失败", null, 0);

    }

    // 列表查看消息
    @CrossOrigin
    @RequestMapping(value = "/listMessage", method = RequestMethod.POST)
    public IOTResult listMessage(@RequestBody MessageRequset mr) {


        if (mr.getCksid() == null || mr.getCksid().trim().length() < 1 || mr.getCkuid() == null
                || mr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mr.getCkuid());
        if (check == null || !mr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mr.getCkuid(), mr.getCksid());
        String intVal = toolUtil.getIntVal(ToolUtil.MESSAGE);
        if (intVal == null) {
            intVal = "0";
        }
        toolUtil.setIntVal(ToolUtil.MESSAGEREADING + uid + ToolUtil.READ, Integer.parseInt(intVal));


        /*预警*/
        if (mr.getM_type() == Types.MT_YUJING) {
            List<Map<String, Object>> listMessage = messageService.listMessage3(mr);
            if (listMessage == null || listMessage.isEmpty()) {
                return new IOTResult(false, "暂无相关信息", null, 10);
            }
            return new IOTResult(true, "消息查看成功", listMessage, 0);
        }
        /*首页*/
        if (mr.getM_type() == Types.MT_SHOUYE) {
            List<Map<String, Object>> listMessage = messageService.listMessage1Bygroup(mr);
            if (listMessage == null || listMessage.isEmpty()) {
                return new IOTResult(false, "暂无相关信息", null, 10);
            }
            return new IOTResult(true, "消息查看成功", listMessage, 0);
        }

        List<Map<String, Object>> listMessage = messageService.listMessage(mr);
        if (listMessage == null || listMessage.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 10);
        }


        return new IOTResult(true, "消息查看成功", listMessage, 0);

    }

    // 政策信息-分类
    @CrossOrigin
    @RequestMapping(value = "/listMessage1Bygroup", method = RequestMethod.POST)
    public IOTResult listMessage1Bygroup(@RequestBody MessageRequset mr) {
        if (mr.getCksid() == null || mr.getCksid().trim().length() < 1 || mr.getCkuid() == null
                || mr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mr.getCkuid());
        if (check == null || !mr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mr.getCkuid(), mr.getCksid());
        // PointEntity pointEntity = mr.getPointEntity();
        // pointEntity.setUid(uid);
        // pointEntity.setRole(String.valueOf(uid));
        // PointEntity point = pointService.getPoint(pointEntity);
        // if(point ==null){
        // return new IOTResult(false,"节点不存在",null,3);
        // }

        List<Map<String, Object>> listMessage = messageService.listMessage1Bygroup(mr);
        if (listMessage == null || listMessage.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "消息查看成功", listMessage, 0);

    }

    // 查看首页消息
    @CrossOrigin
    @RequestMapping(value = "/listHomePageMessage", method = RequestMethod.POST)
    public IOTResult listHomePageMessage(@RequestBody MessageRequset mr) {
        // if(mr.getCksid()==null||mr.getCksid().trim().length()<1||mr.getCkuid()==null||mr.getCkuid().trim().length()<1){
        // return new IOTResult(false, "信息不规范", null, 1);
        // }
        // String check = toolUtil.getCheck(ToolUtil.IOT+mr.getCkuid());
        // if (check ==null||!check.equals(mr.getCksid())) {
        // return new IOTResult(false, "登录失效", null, 2);
        // }
        List<Map<String, Object>> listHomePageMessage = messageService.listHomePageMessage(mr);
        if (listHomePageMessage.size() > 0) {
            return new IOTResult(true, "查询成功", listHomePageMessage, 0);
        }
        return new IOTResult(false, "没有相关信息", null, 0);
    }

    // 查看唯独消息
    @CrossOrigin
    @RequestMapping(value = "/getUnReadMessage", method = RequestMethod.POST)
    public IOTResult getUnReadMessage(@RequestBody MessageRequset mr) {
        if (mr.getCksid() == null || mr.getCksid().trim().length() < 1 || mr.getCkuid() == null
                || mr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + mr.getCkuid());
        if (check == null || !mr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(mr.getCkuid(), mr.getCksid());
        String intVal = toolUtil.getIntVal(ToolUtil.MESSAGE);
        // String intVal = toolUtil.getIntVal(ToolUtil.MESSAGE);
        if (intVal == null) {
            toolUtil.setIntVal(ToolUtil.MESSAGE, 0);
        }
        String intVal2 = toolUtil.getIntVal(ToolUtil.MESSAGEREADING + uid + ToolUtil.READ);
        if (intVal2 == null) {
            toolUtil.setIntVal(ToolUtil.MESSAGEREADING + uid + ToolUtil.READ, 0);
        }
        int unread = Integer.parseInt(intVal) - Integer.parseInt(intVal2);
        if (unread > 0) {
            return new IOTResult(true, "您有" + unread + "未读消息", unread, 0);
        } else {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        // String check2 = toolUtil.getCheck(ToolUtil.MESSAGEREADING);
        // if(check2==null){
        //
        // int msgCount = userService.getMsgCount(uid);
        // }
        // String check3 =
        // toolUtil.getCheck(ToolUtil.MESSAGEREADING+ToolUtil.READ);
        // if(check3 ==null ){
        //// int msgCount = messageService.(uid);
        // }
        // List<Map<String,Object>> instanceMessage =
        // messageService.getInstanceMessage();
        // if(instanceMessage ==null || instanceMessage.isEmpty()){
        // return new IOTResult(false,"暂无相关信息",null,0);
        // }
        // return new IOTResult(true,"消息查看成功",instanceMessage,0);

    }

    // 查看即使消息
    @CrossOrigin
    @RequestMapping(value = "/getInstanceMessage", method = RequestMethod.POST)
    public IOTResult getInstanceMessage() {
        List<Map<String, Object>> instanceMessage = messageService.getInstanceMessage();
        if (instanceMessage == null || instanceMessage.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        return new IOTResult(true, "消息查看成功", instanceMessage, 0);

    }

}
