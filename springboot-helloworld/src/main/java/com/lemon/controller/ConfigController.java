package com.lemon.controller;

import com.lemon.bean.ConfigBean;
import com.lemon.bean.ConfigOtherBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加载默认配置文件 application.yml
 * 1.配置文件映射为 bean,
 * ConfigurationProperties(prefix = "my") 指定前缀
 * Configuration 可有可无
 *
 * 2. Controller 中使用构造器注入方式(idea 推荐),增加 @EnableConfigurationProperties({ConfigBean.class})
 * 指定 bean
 *
 * 3.pom.xml 增加依赖
 * <dependency>
 *  <groupId>org.springframework.boot</groupId>
 *  <artifactId>spring-boot-configuration-processor</artifactId>
 *  <optional>true</optional>
 * </dependency>
 *
 * 加载其他配置文件
 * 1.使用 PropertySource、ConfigurationProperties、ConfigurationProperties 三个注解
 * 2.使用 Value 注解 Bean 的属性
 *
 * Created by lemon
 */
@RestController
@EnableConfigurationProperties({ConfigBean.class, ConfigOtherBean.class})
public class ConfigController {
    private ConfigBean configBean;
    private ConfigOtherBean configOtherBean;

    @Autowired
    public ConfigController(ConfigBean configBean,ConfigOtherBean configOtherBean) {
        this.configBean = configBean;
        this.configOtherBean = configOtherBean;
    }

    @GetMapping(value = "/mainConfig")
    public String getMainConfig() {
        return configBean.toString();
    }

    @GetMapping(value = "/otherConfig")
    public String getOtherConfig() {
        return configOtherBean.toString();
    }
}
