package com.lemon.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lemon.utils.PDFUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

/**
 * @author Shuai.Jing
 * @date 2023/7/20
 */
@Service(value = "TestService")
public class TestService {
    public String m10(MultipartFile[] files, String mapJson) {
        System.out.println("上传交付报告");
        TypeToken<HashMap<String, String>> type = new TypeToken<HashMap<String, String>>() {
        };
        HashMap<String, String> param = new Gson().fromJson(mapJson, type.getType());
        System.out.println("参数: " + mapJson);
        System.out.println("files length: " + files.length);

        try {
            //得到截图文件,用于向PDF文件插入
            File parentFile = PDFUtils.getReportFile(param.get("userId"), param.get("account"));
            System.out.println("====0=====");
            System.out.println("parentFile: " + parentFile.getAbsolutePath());
            File screenShotFile = new File(parentFile, param.get("orderId") + "-screenshot.jpg");
            files[0].transferTo(screenShotFile); //写入 screenShotFile

            System.out.println("====1=====");
            System.out.println("screenShotFile exists:  " + screenShotFile.exists());
            System.out.println("screenShotFile Path:  " + screenShotFile.getAbsolutePath());
            System.out.println("screenShotFile size:  " + screenShotFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }
}
