/**
 * 项目名称：IOT
 * 类名称：DistributionController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年10月11日 下午6:49:10
 * 修改人：jianghu
 * 修改时间：2017年10月11日 下午6:49:10
 * 修改备注： 下午6:49:10
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.entity.ContentValueEntity;
import com.jingu.IOT.entity.DistributionEntity;
import com.jingu.IOT.requset.DistributionRequset;
import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.response.IOTResult3;
import com.jingu.IOT.service.DistributionService;
import com.jingu.IOT.service.ReadExcelService;
import com.jingu.IOT.service.UploadService;
import com.jingu.IOT.service.UserService;
import com.jingu.IOT.util.Base64;
import com.jingu.IOT.util.ToolUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author jianghu
 * @ClassName: DistributionController
 * @Description: TODO
 * @date 2017年10月11日 下午6:49:10
 * 生产分布
 */
@RestController
public class DistributionController {

    private DistributionService distributionService;
    private ToolUtil toolUtil;
    private UserService userService;
    private UploadService uploadService;
    private ReadExcelService readExcelService;
    // private rea

    @Autowired
    public DistributionController(DistributionService distributionService, ToolUtil toolUtil, UserService userService,
                                  UploadService uploadService, ReadExcelService readExcelService) {
        this.distributionService = distributionService;
        this.toolUtil = toolUtil;
        this.userService = userService;
        this.uploadService = uploadService;
        this.readExcelService = readExcelService;
    }

    // 添加生产分布物种分布信息
    @CrossOrigin
    @RequestMapping(value = "/addDistribution", method = RequestMethod.POST)
    public IOTResult addDistribution(@RequestBody DistributionRequset dr) {
        if (dr.getCksid() == null || dr.getCksid().trim().length() < 1 || dr.getCkuid() == null
                || dr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + dr.getCkuid());
        if (check == null || !dr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(dr.getCkuid(), dr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        // List<DistributionEntity> distributionEntity =
        // dr.getDistributionEntity();
        List<ContentValueEntity> content = dr.getContent();
        JSONArray fromObject = JSONArray.fromObject(content);
        dr.setD_content(fromObject.toString());

        int addDistributionList = distributionService.addDistribution(dr);
        if (addDistributionList > 0) {
            return new IOTResult(true, "添加成功", null, 0);
        }
        return new IOTResult(false, "添加失败", null, 0);
    }

    // 添加物种分布excel
    @CrossOrigin
    @RequestMapping(value = "/addDistribution2", method = RequestMethod.POST)
    public IOTResult addDistribution2(@RequestParam("ckuid") String ckuid, @RequestParam("cksid") String cksid,
                                      @RequestParam("d_type") int d_type, @RequestParam("d_state") int d_state,
                                      @RequestParam("d_province") String d_province, @RequestParam("d_city") String d_city,
                                      @RequestParam("d_district") String d_district, @RequestParam("d_content") MultipartFile d_content,
                                      @RequestParam(value = "d_procedure", required = false) Integer d_procedure) {
        if (cksid == null || cksid.trim().length() < 1 || ckuid == null || ckuid.trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ckuid);
        if (check == null || !cksid.equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(ckuid, cksid);
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        IOTResult uploadFile3 = uploadService.uploadFile3("distribute", 0, d_content);
        if (!uploadFile3.isSuccess()) {
            return uploadFile3;
        }

        Map m = (Map) uploadFile3.getObject();
        JSONArray jsonArray = (JSONArray) m.get("jarray");

        String originalFilename = d_content.getOriginalFilename();
        /*String encode = Base64.encode(originalFilename.getBytes());
        String fileName = "distribute" + "_" + 0 + "_" + encode;*/

        DistributionEntity distr = new DistributionEntity();
        distr.setD_content(jsonArray.toString());
        distr.setD_type(d_type);
        distr.setD_state(d_state);
        distr.setD_province(d_province);
        distr.setD_city(d_city);
        distr.setD_district(d_district);
        distr.setOrginalName(originalFilename);

        distr.setFileName((String) m.get("file"));

        distr.setD_procedure(d_procedure == null ? 0 : d_procedure);

        int backStatus = distributionService.addDistribution2(distr);
        if (backStatus > 0)
            return new IOTResult(true, "添加成功", distr, 0);
        return new IOTResult(false, "添加失败", null, 10);
    }

    // 添加生产分布excel
    @CrossOrigin
    @RequestMapping(value = "/addProductionProcess", method = RequestMethod.POST)
    public IOTResult addProductionProcess(@RequestParam("ckuid") String ckuid, @RequestParam("cksid") String cksid,
                                          @RequestParam("d_type") int d_type, @RequestParam("d_state") int d_state,
                                          @RequestParam("d_content") MultipartFile d_content,
                                          @RequestParam(value = "d_procedure", required = false) Integer d_procedure) {
        if (cksid == null || cksid.trim().length() < 1 || ckuid == null || ckuid.trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + ckuid);
        if (check == null || !cksid.equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(ckuid, cksid);
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        IOTResult uploadFile3 = uploadService.uploadFile3("distribute", 0, d_content);
        String originalFilename = d_content.getOriginalFilename();
        String encode = Base64.encode(originalFilename.getBytes());
        String fileName = "distribute" + "_" + 0 + "_" + encode;
        // readser


        int addDistribution2 = distributionService.addDistribution2(new DistributionEntity(0, d_type, d_state, "", "",
                "", "", "", 0, "", "", "", "", null, originalFilename, fileName, d_procedure, 0));
        List<Map<String, Object>> listDistribution = distributionService.listDistribution3(new DistributionEntity(0,
                d_type, d_state, "", "", "", "", "", 0, "", "", "", "", null, "", "", d_procedure, 0));
        if (addDistribution2 > 0) {
            return new IOTResult(true, "上传成功", listDistribution, 0);
        }
        return new IOTResult(false, "添加失败", listDistribution, 10);
    }

    // 删除分布
    @CrossOrigin
    @RequestMapping(value = "/deleteDistribution", method = RequestMethod.POST)
    public IOTResult deleteDistribution(@RequestBody DistributionRequset dr) {
        if (dr.getCksid() == null || dr.getCksid().trim().length() < 1 || dr.getCkuid() == null
                || dr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + dr.getCkuid());
        if (check == null || !dr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(dr.getCkuid(), dr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        int addDistributionList = distributionService.deleteDistribution(dr);
        if (addDistributionList > 0) {
            return new IOTResult(true, "删除成功", null, 0);
        }
        return new IOTResult(false, "删除失败", null, 10);
    }

    // 修改分布情况
    @CrossOrigin
    @RequestMapping(value = "/updateDistribution", method = RequestMethod.POST)
    public IOTResult updateDistribution(@RequestBody DistributionRequset dr) {
        if (dr.getCksid() == null || dr.getCksid().trim().length() < 1 || dr.getCkuid() == null
                || dr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + dr.getCkuid());
        if (check == null || !dr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(dr.getCkuid(), dr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }
        // List<DistributionEntity> distributionEntity =
        // dr.getDistributionEntity();
        List<ContentValueEntity> content = dr.getContent();
        JSONArray fromObject = JSONArray.fromObject(content);
        dr.setD_content(fromObject.toString());
        int addDistributionList = distributionService.updateDistribution(dr);
        if (addDistributionList > 0) {
            return new IOTResult(true, "修改成功", null, 0);
        }
        return new IOTResult(false, "修改失败", null, 0);
    }

    // 查看分布情况（old）
    @CrossOrigin
    @RequestMapping(value = "/listDistribution", method = RequestMethod.POST)
    public IOTResult listDistribution(@RequestBody DistributionRequset dr) {
        if (dr.getCksid() == null ||
                dr.getCksid().trim().length() < 1 || dr.getCkuid() == null || dr.getCkuid().trim().length() < 1) {
            return new IOTResult(false, "信息不规范", null, 1);
        }
        // 注册登陆按照什么来????
        String check = toolUtil.getCheck(ToolUtil.IOT + dr.getCkuid());
        if (check == null || !dr.getCksid().equals(check)) {
            return new IOTResult(false, "登陆失效", null, 2);
        }
        long uid = toolUtil.getbase_uidSid(dr.getCkuid(), dr.getCksid());
        int ckAdmin = userService.ckAdmin(uid);
        if (ckAdmin == 0) {
            return new IOTResult(false, "权限不足", null, 111);
        }


        List<Map<String, Object>> listDistribution = distributionService.listDistribution(dr);

        if (listDistribution == null || listDistribution.isEmpty()) {

            return new IOTResult(false, "暂无相关信息", null, 0);
        }
        for (Map<String, Object> map : listDistribution) {
            Object object = map.get("d_content");
            if (object == null)
                continue;
            JSONArray fromObject = JSONArray.fromObject(object);
            map.put("d_content", fromObject);
        }
        return new IOTResult(true, "查看成功", listDistribution, 0);
    }


    // 查看分布情况
    @CrossOrigin
    @RequestMapping(value = "/listDistribute", method = RequestMethod.POST)
    public IOTResult listDistribution3(@RequestBody DistributionRequset dr) {
        if (dr.getD_type() == 1) {
            Map<String, Object> lastProcess = distributionService.getLastProcess(dr.getD_type(), dr.getD_procedure());

            if (lastProcess == null) {
                return new IOTResult3(false, "暂无相关信息", null, 10, "");
            }
            List<Object> readExcelProcess = readExcelService.readExcelProcess(lastProcess.get("d_originalname").toString());

            if (readExcelProcess == null || readExcelProcess.isEmpty()) {
                return new IOTResult3(false, "暂无相关信息", null, 10, "");
            }
            return new IOTResult3(true, "查看成功", readExcelProcess, 0, lastProcess.get("c_name").toString());
        }
        List<Map<String, Object>> listDistribution = distributionService.listDistribution3(dr);
        if (listDistribution == null || listDistribution.isEmpty()) {
            return new IOTResult(false, "暂无相关信息", null, 10);
        }
        return new IOTResult(true, "查看成功", listDistribution, 0);
    }


    // 下载模板
    @CrossOrigin
    @RequestMapping(value = "/getExcelFile", method = RequestMethod.GET)
    public void getExcelFile(Integer d_type, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = "";
        String string = "";
        if (d_type == 1) {
            fileName = "wuzhongmuban";
            string = new String("物种模板.xlsx".getBytes(), "ISO8859-1");
        }
        if (d_type == 2) {
            fileName = "shengchanmuban";
            string = new String("生产模板.xlsx".getBytes(), "ISO8859-1");
        }
        response.setContentType("application/force-download");// 设置强制下载不打开
        response.addHeader("Content-Disposition", "attachment;fileName=" + string);// 设置文件名
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        if (fileName != null && !"".equals(fileName)) {
            File file = new File(ToolUtil.FILEPATH + Base64.encode(fileName.getBytes()));
            if (file.exists()) {
                System.out.println("文件存在");
                System.out.println(ToolUtil.FILEPATH + Base64.encode(fileName.getBytes()));
            } else {
                System.out.println("文件不存在");
                System.out.println(ToolUtil.FILEPATH + Base64.encode(fileName.getBytes()));
            }
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                // System.out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
