package com.belief.module.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.belief.utils.GlobalResponseData;
import com.belief.utils.GlobalResultCode;

@Controller
@RequestMapping("upload")
public class SysFileUploadController {

    @RequestMapping("/uploadIdx")
    public String uploadIdx() {
        return "fileupload";
    }

    @RequestMapping(value = "/uploadSingleFile")
    @ResponseBody
    public GlobalResponseData<Boolean> uploadSingleFile(
            @RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        GlobalResponseData<Boolean> result = new GlobalResponseData<Boolean>();
        result.setCode(GlobalResultCode.success);
        if (file != null) {
            try {
                String realPath =
                        request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
                File dir = new File(realPath);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                FileUtils.copyInputStreamToFile(
                        file.getInputStream(),
                        new File(realPath, System.currentTimeMillis() + "_"
                                + file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            result.setData(Boolean.TRUE);
        } else {
            result.setData(Boolean.FALSE);
        }

        return result;
    }
}
