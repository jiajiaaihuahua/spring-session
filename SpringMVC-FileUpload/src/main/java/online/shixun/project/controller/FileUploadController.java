/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package online.shixun.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件控制器类
 */
@Controller
public class FileUploadController {

    /**
     * 进入上传文件页面
     * @return
     */
    @RequestMapping("/upload/page")
    public String uploadPage() {
        return "upload";

    }

    /**
     * 执行文件上传
     * @param file	上传获取到的文件
     * @return	返回成功页面
     */
    @RequestMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        InputStream in = null;
        FileOutputStream fileOutputStream = null;
        try {
            //判断文件是否为null
            if (!file.isEmpty()) {
                //获取文件输入流
                in = file.getInputStream();
                File newFile = new File(file.getOriginalFilename());
                newFile.createNewFile();
                //创建文件输出流
                fileOutputStream = new FileOutputStream(newFile);
                //创建文件流缓冲区
                byte[] b = new byte[1024];
                int length = 0;
                while ((length = in.read(b)) != -1) {
                    //输出文件流
                    fileOutputStream.write(b, 0, length);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

}
