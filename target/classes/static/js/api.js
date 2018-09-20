if (top.layer !== window.layer) {
    window.layer = top.layer
}
API.dict = {
    /*1 超级管理员  2 管理员 3 普通用户 4监管者用户 5生产者用户 6专家用户*/
    userType: [
        {value: 1, label: "超级管理员"},
        {value: 2, label: "管理员"},
        {value: 3, label: "普通用户"},
        {value: 4, label: "监管者用户"},
        {value: 5, label: "生产者用户"},
        {value: 6, label: "专家用户"},
    ],
    user_type: {
        1: "超级管理员",
        2: "管理员",
        3: "普通用户",
        4: "监管者用户",
        5: "生产者用户",
        6: "专家用户",
    },
    user_sex: {0: "保密", 1: "男",2:"女"},
    user_edu: {1: "博士", 2: "硕士",3:"本科",4:"专科",0:"无"},
    user_state: {1: "正常", 2: "不正常"},
    yes_no: {1: "是", 2: "不是"},

    ipc_stream: {0: "主码流", 1: "子码流"},
    ipc_online: {0: "不在线", 1: "在线"},
    deviceState: {0: "-", 1: "运行中", 2: "停止"},
    ipc_status: {0: "未同步", 1: "已同步"},
    controlType: {1: "电机", 2: "电磁阀"},
    powerType: {0: "市电", 1: "太阳能电"},
    power_ctrl: {0: "不控制", 1: "控制"},
    ruleEnable: {1: "启用", 0: "停用"},
    statusPictureName: {1: "控光", 2: "控温", 3: "控水"},
    encoderType: {0: "私有264", 1: "标准h264", 2: "标准mpeg4", 7: "M-JPEG", 8: "MPEG2", 10: "H.265"},
    chartType: {
        0: "无数据",
        1: "空气温度",
        2: "空气湿度",
        3: "雨量",
        4: "风向",
        5: "风速",
        6: "光照度",
        7: "土壤水分",
        8: "土壤温度",
        9: "氮肥含量",
        10: "钾肥含量",
        11: "二氧化碳含量",
        12: "氧气含量",
        13: "PH值"
    },
    inputPlanStandard: {1: "有机", 2: "绿色", 3: "无公害"}
};
API.listHomePage = API.bind("/listHomePage");
API.listHomePage_edit = API.bind("/listHomePage_edit");




API.login = function (user, pwd, successFunc, errorFunc) {
    API.service("/Login", {tu_username: user, tu_pwd: pwd}, successFunc, errorFunc)
};
API.addRule = function (name, deviceId, cycleDay, beginTime, endTime, ctrl_id, execTime, duration, ruleEnable, successFunc, errorFunc) {
    API.service("/addRule", {
        r_name: name,
        r_deviceId: deviceId,
        cycleDay: cycleDay,
        beginTime: beginTime,
        endTime: endTime,
        ctrl_id: ctrl_id,
        execTime: endTime,
        duration: duration,
        ruleEnable: ruleEnable
    }, successFunc, errorFunc)
};
API.setRuleList = function (ctrlId, successFunc, errorFunc) {
    API.service("/setRuleList", {ctrl_id: ctrlId}, successFunc, errorFunc)
};
API.listRelationShip = API.bind("/listRelationShip");
API.autoSyn = function (mapingdeviceId, deviceId, pointEntityIp, pointEntityPort, successFunc, errorFunc) {
    API.service("/autoSyn", {
        mapingdeviceId: mapingdeviceId,
        deviceId: deviceId,
        pointEntity: {ip: pointEntityIp, port: pointEntityPort}
    }, successFunc, errorFunc)
};
API.system = {};
API.system.user = {
    addUser: function (username, pwd, type, state, successFunc, errorFunc) {
        API.service("/addUser", {
            tu_username: username,
            tu_pwd: pwd,
            tu_type: state,
            tu_state: state
        }, successFunc, errorFunc)
    }, updateUser: function (username, pwd, type, state, successFunc, errorFunc) {
        API.service("/updateUser", {
            tu_username: username,
            tu_pwd: pwd,
            tu_type: state,
            tu_state: state
        }, successFunc, errorFunc)
    }, listUser: function (start, type, search, successFunc, errorFunc) {
        API.service("/listUser", {tu_type: type, start: start || 0, u_search: search || ""}, successFunc, errorFunc)
    }
};
API.listPoint = API.bind("/listPoint");
API.listProvince = API.bind("/listProvice");
API.listCity = function (provinceId, successFunc, errorFunc) {
    API.bind("/listCity")({ar_id: provinceId}, successFunc, errorFunc)
};
API.listDistrict = function (cityId, successFunc, errorFunc) {
    API.bind("/listDistrict")({ar_id: cityId}, successFunc, errorFunc)
};
API.addDistribution = function (province, city, district, state, type, contentArray, successFunc, errorFunc) {
    var contentArr = [];
    $.each(contentArray, function (idx) {
        var item = this;
        if (item.content && item.value) {
            contentArr.push({d_content: items.content, d_value: item.value})
        }
    });
    API.service("/addDistribution", {
        d_province: province,
        d_city: city,
        d_district: district,
        d_state: state,
        d_type: type,
        d_content: contentArr
    }, successFunc, errorFunc)
};
API.listClassType = function (type, successFunc, errorFunc) {
    API.service("/listClass1", {c_type: type}, successFunc, errorFunc)
};
API.listSensorChartInfo = function (deviceId, tpId, successFunc, errorFunc) {
    API.service("/listSensorChartInfo", {deviceId: deviceId, tp_id: tpId}, successFunc, errorFunc)
};
API.listSensorChartInfo = function (deviceId, tpId, successFunc, errorFunc) {
    API.service("/listSensorChartInfo", {deviceId: deviceId, tp_id: tpId}, successFunc, errorFunc)
};
API.listSensorInfo = function (deviceId, tpId, start, successFunc, errorFunc) {
    API.service("/listSensorInfo", {deviceId: deviceId, tp_id: tpId, start: start}, successFunc, errorFunc)
};
API.listMessage = function (type, id, start, successFunc, errorFunc) {
    API.service("/listMessage", {m_type: type, m_id: id, start: start}, successFunc, errorFunc)
};
API.getMainDeviceInfo = function (deviceId, tpId, successFunc, errorFunc) {
    API.service("/getMainDeviceInfo", {deviceId: deviceId, tp_id: tpId}, successFunc, errorFunc)
};
API.getUnReadMessage = API.bind("/getUnReadMessage");
API.getShopCamera = function (deviceId, successFunc, errorFunc) {
    API.service("/getShopCamera", {deviceId: deviceId}, successFunc, errorFunc)
};
