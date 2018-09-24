package com.jingu.IOT.service;

import com.jingu.IOT.response.IOTResult;
import com.jingu.IOT.util.ExcelData;
import com.jingu.IOT.util.ToolUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class UploadService {
    @Autowired
    ReadExcelService readExcelService;

    public IOTResult uploadFilebyte(String type, int o_id, byte[] bytes, String filename) {
        IOTResult result = new IOTResult();
        String fileName = type + "_" + o_id + "_" + filename + ".jpg";
        try {

            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(ToolUtil.FILEPATH + fileName)));
            stream.write(bytes);
            stream.close();
            result.setSuccess(true);
            result.setMsg("上传成功！");
            result.setObject(ToolUtil.IOTURL + "/getActImage?file_name=" + fileName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg("上传失败！");
            result.setObject(null);
        }
        result.setSuccess(false);
        result.setMsg("上传失败！");
        result.setObject(null);
        return result;
    }

    public IOTResult uploadFile2(String type, String o_id, MultipartFile file) {
        IOTResult result = new IOTResult();
        String name = file.getName();
        String split = file.getOriginalFilename();
        String fileName = type + "_" + o_id + "_" + System.currentTimeMillis() + ".jpg";
        BufferedOutputStream stream = null;
        //String filename = type+"_"+o_id+"_"+split;
        try {
            byte[] bytes = file.getBytes();

            stream = new BufferedOutputStream(new FileOutputStream(new File(ToolUtil.FILEPATH + fileName)));
            stream.write(bytes);
            stream.close();
            //Thumbnails.of(ToolUtil.FILEPATH+fileName).scale(1f).toFile(ToolUtil.FILEPATH+fileName);
            //Thumbnails.of(ToolUtil.FILEPATH+filename).scale(0.25f).
            result.setSuccess(true);
            result.setMsg("上传成功！");
            result.setObject("/getActImage?file_name=" + fileName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                stream.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            result.setSuccess(false);
            result.setMsg("上传失败！");
            result.setObject(null);
        }
        result.setSuccess(false);
        result.setMsg("上传失败！");
        result.setObject(null);
        return result;
    }

    public IOTResult uploadFile3(String type, int o_id, MultipartFile file) {

        /*String originalFilename = file.getOriginalFilename();
        String encode = Base64.encode(originalFilename.getBytes());
        String fileName = type + "_" + o_id + "_" + encode;*/

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String fileName = type + "_" + o_id + "_" + uuid;
        try {
           /*File f_outpath = new File(ToolUtil.FILEPATH + fileName);
            if(!f_outpath.exists())
                f_outpath.mkdirs();

            file.transferTo(f_outpath);*/

            byte[] bytes = file.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(ToolUtil.FILEPATH + fileName)));
            stream.write(bytes);
            stream.close();

            List<String[]> jarry = null;
            try {
                jarry = ExcelData.getExcelData(file);
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (jarry != null || jarry.size() > 0) {
                JSONArray arr = new JSONArray();
                for (String[] kv : jarry) {
                    if (kv != null && kv.length == 2) {
                        JSONObject jo = new JSONObject();
                        jo.put("d_content", kv[0]);
                        jo.put("d_value", kv[1]);
                        arr.add(jo);
                    }
                }


                Map map = new HashMap();
                map.put("jarray", arr);
                map.put("file", "/getActImage?file_name=" + fileName);
                return new IOTResult(true, "解析成功", map, 1);
            } else {
                new File(ToolUtil.FILEPATH + fileName).delete();
                return new IOTResult(true, "解析失败", null, 0);

            }


        } catch (Exception e) {
            e.printStackTrace();
            return new IOTResult(true, "解析失败", null, 1);

        }


    }

    public boolean deleteFile(String filename) {
        File file = new File(filename);
        if (file.exists() && file.isFile()) {
            boolean delete = file.delete();
            return delete;
        }
        return true;
    }

}
