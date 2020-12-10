package com.autuan.project.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.autuan.common.utils.file.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.autuan.common.constant.Constants;
import com.autuan.common.utils.StringUtils;
import com.autuan.common.utils.file.FileUploadUtils;
import com.autuan.common.utils.file.FileUtils;
import com.autuan.framework.config.RuoYiConfig;
import com.autuan.framework.config.ServerConfig;
import com.autuan.framework.web.domain.AjaxResult;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import java.io.File;
import java.io.InputStream;

/**
 * 通用请求处理
 *
 * @author ruoyi
 */
@Controller
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.isValidFilename(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 本地资源路径
        String localPath = RuoYiConfig.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource, Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }


    @RequestMapping(value = "/ftp/upload",method= RequestMethod.POST)
    public Object add( HttpServletRequest request) {
        MultipartFile uploadFile=null;
        //=============================检测是否为上传请求
        String contentType = request.getContentType();
        if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
            MultipartHttpServletRequest multipartRequest =
                    WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
            uploadFile= multipartRequest.getFile("file");
        }
//        log.info("新增app版本=====》请求报文={}", JSON.toJSONString(model));
        //返回的文件Path
        String pictureName="";
        String Id ="";
        //FTP中文件的目录所在
        String filePath="";
        try{
            Id= IdUtil.simpleUUID();
//            model.setId(Id);

            InputStream input=uploadFile.getInputStream();
            pictureName = uploadFile.getOriginalFilename();
            FTPUtil.uoloadFile(input,pictureName);

//            if (!"IOS".equals(model.getClientType())){
//                if (null!=uploadFile){
//                    String filename=uploadFile.getOriginalFilename();
//                    pictureName= ApkDirectoryUtil.getApkFileName()+filename;
//                    //获取文件类型
//
//                    log.info("=====》apk上传成功=pictureName={}",pictureName);
//                }else {
//                    log.info("=====》未选择apk");
//                }
//                filePath= File.separator+ApkDirectoryUtil.getApkDirectory().get(model.getAppName())+File.separator;
//                model.setAppDownloadUrl(filePath+pictureName);
//            }
//            this.iAppService.insertSelective(model);
        } catch (Exception e) {
            log.error("异常=====》", e);
        }

        return "redirect:/app/app_update/"+Id;
    }
}
