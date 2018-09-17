/**
 * 项目名称：IOT
 * 类名称：AlarmRuleController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2018年4月13日 下午3:27:11
 * 修改人：jianghu
 * 修改时间：2018年4月13日 下午3:27:11
 * 修改备注： 下午3:27:11
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.AlarmRuleEntity;
import com.jingu.IOT.requset.AlarmRuleRequest;
import com.jingu.IOT.requset.IOTRequest;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.service.AlarmService;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**

 * @ClassName: AlarmRuleController
 * @Description: TODO
 * @author jianghu
 * @date 2018年4月13日 下午3:27:11
 * 规则
 */
@RestController
public class AlarmRuleController {

    private AlarmService alarmService;
    private ToolUtil toolUtil;

    @Autowired
    public AlarmRuleController(AlarmService alarmService, ToolUtil toolUtil) {
        this.alarmService = alarmService;
        this.toolUtil = toolUtil;
    }

    // 添加规则
    @Transactional
    @CrossOrigin
    @RequestMapping(value = "/addAlarmRuleList", method = RequestMethod.POST)
    public IOTResult addAlarmRuleList(@RequestBody AlarmRuleRequest aqr) {
        if (aqr.getCksid() == null || aqr.getCksid().trim().length() < 1 || aqr.getCkuid() == null || aqr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "请先登录", null, 1);
        }
        String check1 = toolUtil.getCheck(ToolUtil.IOT + aqr.getCkuid());
        if (check1 == null ) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        // 检查权限

        int deleteAlarmByDeviceId = alarmService.deleteAlarmByDeviceId(aqr.getDeviceid());

        int addAlarmRuleList = alarmService.addAlarmRuleList(aqr.getaList());
        if (addAlarmRuleList > 0) {
            return new IOTResult(true, "新增成功", null, 0);
        }
        return new IOTResult(false, "新增失败", null, 10);


		/*// 修改
		if(aqr.getO_type() ==2){
			String deviceid = aqr.getDeviceid();
			int deleteAlarmByDeviceId = alarmService.deleteAlarmByDeviceId(deviceid);
			int addAlarmRuleList = alarmService.addAlarmRuleList(aqr.getaList());
			if(addAlarmRuleList >0){
				return new IOTResult(true,"修改成功",null,0);
			}
			return new IOTResult(false,"修改失败",null,10);
		}

		// 删除
		if(aqr.getO_type() == 3){
			int deleteAlarmByDeviceId = alarmService.deleteAlarmByIdString(aqr.getIdString());
			if(deleteAlarmByDeviceId >0){
				return new IOTResult(true,"删除成功",null,0);
			}
			return new IOTResult(false,"删除失败",null,10);
		}
		return null;
		*/

    }

    // 查看规则
    @CrossOrigin
    @RequestMapping(value = "/listAlarmRule", method = RequestMethod.POST)
    public IOTResult listAlarmRule(@RequestBody IOTRequest<AlarmRuleEntity> aqr) {
        if (aqr.getCksid() == null || aqr.getCksid().trim().length() < 1 || aqr.getCkuid() == null || aqr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "请先登录", null, 1);
        }
        String check1 = toolUtil.getCheck(ToolUtil.IOT + aqr.getCkuid());
        if (check1 == null ) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        // 权限检查
        AlarmRuleEntity param = aqr.getParam();

        if(param == null )param = new AlarmRuleEntity();

        List<Map<String, Object>> listAlarmRule = alarmService.listAlarmRule(param, aqr.getPageNum(), aqr.getPageSize());
        if (listAlarmRule == null || listAlarmRule.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 10);
        }
        return new IOTResult(true, "查看成功", listAlarmRule, 0);
    }


}
