package com.jingu.IOT.web;

import com.jingu.IOT.entity.InputRequset;
import com.jingu.IOT.requset.PointRequest;
import com.jingu.IOT.requset.ProduceRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult2;
import com.jingu.IOT.service.ExcelService;
import com.jingu.IOT.service.InputService;
import com.jingu.IOT.service.ProduceService;
import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by weifengxu on 2018/9/25.
 */
@RestController
public class ExcelController {
    private ToolUtil toolUtil;
    private InputService inputService;
    private ProduceService produceService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    public ExcelController(ToolUtil toolUtil, InputService inputService, ProduceService produceService) {
        this.toolUtil = toolUtil;
        this.inputService = inputService;
        this.produceService = produceService;
    }


    /**
     * 投入品excel 导出
     *
     * @param ir
     * @param request
     * @param response
     * @return
     */

    @CrossOrigin
    @RequestMapping(value = "/inputExcelOut", method = RequestMethod.POST)
    public IOTResult inputExcelOut(@RequestBody InputRequset ir, HttpServletRequest request, HttpServletResponse response) {
        try {
            return excelService.inputExcelOut(ir, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return new IOTResult2(false, "生成失败", e.getMessage(), -1);
        }


    }
    /**
     * 生产计划excel 导出
     * @param ir
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/produceExcelOut", method = RequestMethod.POST)
    public IOTResult produceExcelOut(@RequestBody ProduceRequset ir, HttpServletRequest request, HttpServletResponse response) {
        try {
            return excelService.produceExcelOut(ir, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return new IOTResult2(false, "生成失败", e.getMessage(), -1);
        }


    }


    /**
     * 设备传感器数据excel 导出
     * @param p
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/sensorInfoExcelOut", method = RequestMethod.POST)
    public IOTResult sensorInfoExcelOut(@RequestBody PointRequest p, HttpServletRequest request, HttpServletResponse response) {
        try {
            return excelService.sensorInfoExcelOut(p, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return new IOTResult2(false, "生成失败", e.getMessage(), -1);
        }


    }
}
