/**
 * 项目名称：shop
 * 类名称：ImgController
 * 类描述：
 * 创建人：jianghu
 * 创建时间：2017年9月7日 下午5:37:27
 * 修改人：jianghu
 * 修改时间：2017年9月7日 下午5:37:27
 * 修改备注： 下午5:37:27
 *
 * @version
 */
package com.jingu.IOT.web;

import com.jingu.IOT.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author jianghu
 * @ClassName: ImgController
 * @Description: TODO
 * @date 2017年9月7日 下午5:37:27
 * 图片
 */
@RestController
public class ImageController {

    private ToolUtil toolUtil;

    @Autowired
    public ImageController(ToolUtil toolUtil) {
        this.toolUtil = toolUtil;
    }



    // 下载文件
    @CrossOrigin
    @RequestMapping(value = "/getReportImage", method = RequestMethod.GET)
    public
    @ResponseBody
    FileSystemResource getReportImage(@RequestParam("file_name") String fileName
    /* @RequestParam("sid") String sid, @RequestParam("uid") String uid */) throws UnsupportedEncodingException {
        /*
         * if (null == sid || sid.length() < 1 || null == uid || uid.length() <
		 * 1) { return null; // return new StoryResult(false,"信息不规范",null,0); }
		 * if (!uid.equals(toolUtil.getWxUser(sid).getSu_id())) { return null;
		 * // return new StoryResult(false,"您已在其他地方上线",null,1); }
		 */
        if (fileName != null && !"".equals(fileName)) {
            if (ToolUtil.IMG.startsWith("/")) {
                fileName = fileName.substring(10);
                System.out.println(ToolUtil.IMG);
            } else {
                fileName = fileName.replace("/", "\\");
            }
            //\data\IOTImage/vraImage/10.00.21.27.01/10.00.21.27.01_2_20171118_193612.jpg
            File file = new File(ToolUtil.IMG + fileName);
            System.out.println(fileName);
            System.out.println(ToolUtil.IMG + fileName);
            System.out.println(file.exists());
            if (file.exists()) {
                return new FileSystemResource(file);
                /*
                 * Map<String, String> map = new HashMap<>(); map.put("picture",
				 * "http://192.168.0.124:8080/getActImage?file_name=111111111_0_image.jpg"
				 * ); return map;
				 */
                // return new StoryResult(true,"成功",new
                // FileSystemResource(file),0);
            }
        }
        System.out.println("...........");
        return null;
    }


    /**
     * 下载文件
     *  
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "downLoadFile")
    public void downLoadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = request.getParameter("file_name");
        File file = null;
        if (fileName != null && !"".equals(fileName)) {
            if (fileName.startsWith("CMS")) {
                file = new File(ToolUtil.CMSIMG + fileName);
                if (!file.exists()) {
                    file = null;
                }
            } else {
                file = new File(ToolUtil.FILEPATH + fileName);

                if (!file.exists()) {
                    file = null;
                }
            }
        }
        if (file == null) {
            System.out.println("文件不存在，下载失败！");
            response.reset();
        }

        BufferedInputStream in = null;
        ServletOutputStream out = null;
        byte[] b;

        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        // 2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        try {
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            response.setCharacterEncoding("utf-8");
            in = new BufferedInputStream(new FileInputStream(file));
            b = new byte[1024];
            out = response.getOutputStream();
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null)
                    in.close();
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // 下载文件
    @CrossOrigin
    @RequestMapping(value = "/getActImage", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getActImage(@RequestParam("file_name") String fileName
    /* @RequestParam("sid") String sid, @RequestParam("uid") String uid */) throws UnsupportedEncodingException {
        /*
         * if (null == sid || sid.length() < 1 || null == uid || uid.length() <
		 * 1) { return null; // return new StoryResult(false,"信息不规范",null,0); }
		 * if (!uid.equals(toolUtil.getWxUser(sid).getSu_id())) { return null;
		 * // return new StoryResult(false,"您已在其他地方上线",null,1); }
		 */

        if (fileName != null && !"".equals(fileName)) {
            if (fileName.startsWith("CMS")) {
                File file = new File(ToolUtil.CMSIMG + fileName);
                System.out.println(fileName);
                System.out.println(file.exists());
                if (file.exists()) {
                    return new FileSystemResource(file);
                }
            } else {
                File file1 = new File(ToolUtil.FILEPATH + fileName);
                System.out.println(fileName);
                System.out.println(file1.exists());
                if (file1.exists()) {
//					return new FileSystemResource(file1);
                    return new FileSystemResource(file1);
                }
                /*
                 * Map<String, String> map = new HashMap<>(); map.put("picture",
				 * "http://192.168.0.124:8080/getActImage?file_name=111111111_0_image.jpg"
				 * ); return map;
				 */
                // return new StoryResult(true,"成功",new
                // FileSystemResource(file),0);
            }
            System.out.println(fileName + "不存在><><><><><><><><><><");
        }
        System.out.println("...........");
        return null;
    }
}
