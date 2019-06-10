package com.lemon.config;

import com.lemon.interceptor.ErrorPageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Configuration 申明这是一个配置类相当于xml配置文件
 * Created by lemon
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    private ErrorPageInterceptor errorPageInterceptor;

    @Autowired
    public void setErrorPageInterceptor(ErrorPageInterceptor errorPageInterceptor) {
        this.errorPageInterceptor = errorPageInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(errorPageInterceptor);
    }
}
