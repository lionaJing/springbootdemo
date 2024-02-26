package com.lemon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * @author Shuai.Jing
 * @date 2023/7/20
 */
@Configuration
public class MultipartConfig {
    @Value("${location.tempDir:/opt/tempDir}")
    private String tempDir;

    @Bean
    MultipartConfigElement multipartConfigElement() {
        System.out.println(tempDir);
        System.out.println("======");
        MultipartConfigFactory factory = new MultipartConfigFactory();
        File tmpDirFile = new File(tempDir);
        // 判断文件夹是否存在
        if (!tmpDirFile.exists()) {
            tmpDirFile.mkdirs();
        }
        factory.setLocation(tempDir);
        return factory.createMultipartConfig();
    }
}
