package com.jingu.IOT.service;

import com.jingu.IOT.entity.InputRequset;
import com.jingu.IOT.entity.PointEntity;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.requset.ProduceRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weifengxu on 2018/9/25.
 * 处理逻辑 ：发送生成excel指令 生成成功后返回文件地址 进行下载
 */
@Component
public class ExcelService {
    @Autowired
    InputService inputService;
    @Autowired
    ProduceService produceService;
    @Autowired
    private ToolUtil toolUtil;
    @Autowired
    GatherService gatherService;


    public void export() throws Exception {

        String sheetName = "用车统计表单";
        String titleName = "用车申请数据统计表";
        String fileName = "用车申请统计表单";
        int columnNumber = 3;
        int[] columnWidth = {10, 20, 30};
        String[][] dataList = {{"001", "2015-01-01", "IT"},
                {"002", "2015-01-02", "市场部"}, {"003", "2015-01-03", "测试"}};
        String[] columnName = {"单号", "申请时间", "申请部门"};
        new ExcelExporter().ExportNoResponse(sheetName, titleName, fileName,
                columnNumber, columnWidth, columnName, null);
    }

    /**
     * 投入品excel导出
     *
     * @param ir
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public IOTResult2 inputExcelOut(InputRequset ir, HttpServletRequest request, HttpServletResponse response) throws Exception {


        ir.setStart(0);//不需要分页
        String in_mattername = ir.getIn_mattername();
        String s = ir.getP_begintime();
        String e = ir.getP_endtime();
        String namePre = gennerNamePre(new String[]{in_mattername, s, e});

        int tp_id = 0;
        PointEntity pointEntity = ir.getPointEntity();
        if (pointEntity != null) {
            tp_id = pointEntity.getTp_id();
        }
        ir.setTp_id(tp_id);

        List<Map<String, Object>> listInput = inputService.listInput(ir);
        if (listInput == null || listInput.isEmpty()) {
            return new IOTResult2(false, "暂无相关信息", null, 0, 0, 0);
        }
        //生成excel
        String sheetName = "生产管理->投入品";
        String titleName = "投入品";

        String fileName = "投入品--" + CommonUtils.forFormatDate() + ".xls";
        String path = ToolUtil.FILEPATH + fileName;
//        String url = ToolUtil.IOTURL + "/getActImage?file_name=" + fileName;


        int columnNumber = 7;
        int[] columnWidth = {30, 30, 20, 20, 20, 20, 20};
        String[] columnName = {"生产者姓名", "投入物料", "投入总量", "排产作物", "排产标准", "排产面积", "投入时间"};

        List<List<String>> dataList = getDataList(listInput);
        int status = new ExcelExporter().ExportNoResponse(sheetName, titleName, path,
                columnNumber, columnWidth, columnName, dataList);
        if (status > 0) {
            return new IOTResult2(true, "生成成功", fileName, 0);
        } else {
            return new IOTResult2(false, "生成失败", null, -1);

        }

    }

    /**
     * 生产计划 excel导出
     *
     * @param pr
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public IOTResult produceExcelOut(ProduceRequset pr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        pr.setStart(0);//不需要分页
        PointEntity pointEntity = pr.getPointEntity();
        if (pointEntity == null) {
            pointEntity = new PointEntity();
        }
        pr.setTp_id(pointEntity.getTp_id());

        List<Map<String, Object>> listProducePlan = produceService.listProducePlan(pr);
        if (listProducePlan == null || listProducePlan.isEmpty()) {
            return new IOTResult2(false, "暂无相关信息", null, 0, 0, 0);
        }

        //生成excel
        String sheetName = "生产管理->生产计划";
        String titleName = "生产计划";

        String fileName = "生产计划--" + CommonUtils.forFormatDate() + ".xls";
        String path = ToolUtil.FILEPATH + fileName;
//        String url = ToolUtil.IOTURL + "/getActImage?file_name=" + fileName;

        int columnNumber = 7;
        int[] columnWidth = {30, 30, 20, 20, 20, 20, 20};


        String[] columnName = {"所有者姓名", "排产作物", "排产品种", "排产标准", "排产面积", "开始时间", "结束时间"};

        List<List<String>> dataList = getDataList_produce(listProducePlan);
        int status = new ExcelExporter().ExportNoResponse(sheetName, titleName, path,
                columnNumber, columnWidth, columnName, dataList);
        if (status > 0) {
            return new IOTResult2(true, "生成成功", fileName, 0);
        } else {
            return new IOTResult2(false, "生成失败", null, -1);

        }

    }


    /**
     * 设备传感信息导出
     *
     * @param p
     * @param request
     * @param response
     * @return
     */

    public IOTResult sensorInfoExcelOut(PointRequest pr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        pr.setStart(1);
        pr.setPagesize(1000);

        if (pr.getCksid() == null || pr.getCksid().trim().length() < 1 || pr.getCkuid() == null
                || pr.getCkuid() == null) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + pr.getCkuid());
        if (check == null || !pr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(pr.getCkuid(), pr.getCksid());
        pr.setUid(uid);

        List<Map<String, Object>> listSensorInfo = gatherService.listSensorInfo(pr);
        List<Map<String, Object>> listSensorInfoUnit = gatherService.listSensorInfoListUnit(pr);
        if (listSensorInfo == null || listSensorInfo.size() < 1) {
            return new IOTResult(false, "暂无相关信息", null, 4);
        }

        //生成excel
        String sheetName = "综合信息->传感信息";
        String titleName = "传感信息";

        String fileName = pr.getTp_name() + "--传感信息--" + CommonUtils.forFormatDate() + ".xls";
        String path = ToolUtil.FILEPATH + fileName;
//        String url = ToolUtil.IOTURL + "/getActImage?file_name=" + fileName;

        int columnNumber = 7;
        int[] columnWidth = new int[0];
        String[] columnName = new String[0];

        Map m = getDataList_sensor(listSensorInfo, listSensorInfoUnit, columnName);

        List<List<String>> dataList = (List<List<String>>) m.get("arr");
        columnName = (String[]) m.get("names");
        columnWidth = new int[columnName.length];
        for (int i = 0; i < columnName.length; i++) {
            columnWidth[i] = 20;
        }


        int status = new ExcelExporter().ExportNoResponse(sheetName, titleName, path,
                columnNumber, columnWidth, columnName, dataList);
        if (status > 0) {
            return new IOTResult2(true, "生成成功", fileName, 0);
        } else {
            return new IOTResult2(false, "生成失败", null, -1);

        }

    }

    /**
     * 传感器的数据
     *
     * @param listSensorInfo
     * @param listSensorInfoUnit
     * @param columnName
     * @return
     */
    private Map getDataList_sensor(List<Map<String, Object>> info, List<Map<String, Object>> unit, String[] names) {
        List<String> colum = new ArrayList<>();
        List<String> field = new ArrayList<>();
        colum.add("时间");
        field.add("infoDataTime");

        for (Map m : unit) {
            PageData pd = new PageData();
            pd.putAll(m);
            if (pd.get("listDisplay").equals(1)) {
                colum.add(pd.getString("name") + "(" + pd.getString("unit") + ")");
                field.add(pd.getString("fieldName"));
            }
        }
        names = colum.toArray(names);

        List<List<String>> arr = new ArrayList<>();
        for (Map m : info) {
            PageData pd = new PageData();
            pd.putAll(m);
            List<String> a = new ArrayList<>();
            for (String f : field) {
                if (f.equals("infoDataTime")) {
                    a.add(CommonUtils.timestampToString((Timestamp) pd.get(f)));
                    continue;
                }
                a.add(pd.getString(f));
            }
            arr.add(a);
        }
        Map result = new HashMap();
        result.put("arr", arr);
        result.put("names", names);
        return result;
    }
    //拼接字符串

    private String gennerNamePre(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        StringBuffer sb = new StringBuffer();
        for (String s : strs) {
            if (s != null && s.trim().length() > 0) {
                sb.append(s);
                sb.append("-");
            }
        }
        return sb.toString();
    }

    /**
     * 投入品数据
     *
     * @param list
     * @return
     */
    private static List<List<String>> getDataList(List<Map<String, Object>> list) {
        List<List<String>> arr = new ArrayList<>();
        for (Map m : list) {
            PageData pd = new PageData();
            pd.putAll(m);

            List<String> a = new ArrayList<>();
            a.add(pd.getString("in_ownername"));
            a.add(pd.getString("in_mattername"));
            a.add(pd.getString("in_total") + "亩");
            a.add(pd.getString("class2name"));
            a.add(standard(pd.get("in_pstandrad")));//标准
            a.add(pd.getString("in_parea") + "亩");
            a.add(pd.getString("in_time"));
            arr.add(a);

        }
        return arr;

    }

    /**
     * 生产计划数据
     *
     * @param list
     * @return
     */
    private static List<List<String>> getDataList_produce(List<Map<String, Object>> list) {
        List<List<String>> arr = new ArrayList<>();
        for (Map m : list) {
            PageData pd = new PageData();
            pd.putAll(m);
            List<String> a = new ArrayList<>();
            a.add(pd.getString("p_ownername"));
            a.add(pd.getString("c1_name"));
            a.add(pd.getString("c2_name"));
            a.add(standard(pd.get("p_standrad")));//标准
            a.add(pd.getString("p_harvestarea") + "亩");
            a.add(pd.getString("p_begintime"));
            a.add(pd.getString("p_endtime"));

            arr.add(a);

        }
        return arr;

    }

    /**
     * 投放标准
     *
     * @param s
     * @return
     */
    private static String standard(Object s) {
        if (s == null) return "";
        if (CommonUtils.isNumber(s))
            return Types.getStandard(Integer.parseInt(s.toString()));
        return s.toString();
    }


}
