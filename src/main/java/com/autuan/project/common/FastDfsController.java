package com.autuan.project.common;

import com.autuan.common.utils.file.FileUploadUtils;
import com.autuan.framework.config.RuoYiConfig;
import com.autuan.framework.web.domain.AjaxResult;
import com.autuan.project.front.entity.ReturnResult;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
public class FastDfsController {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Value("${fdfs.down}")
    private String downPath;

    /**
     * fastdfs 图片上传
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/fdfs/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            String originalFilename = file.getOriginalFilename();

            FastFile fastFile = new FastFile.Builder()
                    .withFile(file.getInputStream(),file.getSize(),originalFilename)
                    .build();

            StorePath storePath = fastFileStorageClient.uploadFile(fastFile);
            String url = downPath + storePath.getFullPath();
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }
}
