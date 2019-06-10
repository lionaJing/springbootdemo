package com.lemon.controller;

import com.lemon.bean.ConfigOtherBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意如果在 bean 类前没有加 Primary 注解则会提示
 * Autowired  required a single bean 错误
 * <p>
 * Created by lemon
 */
@RestController
@EnableConfigurationProperties({ConfigOtherBean.class})
public class ConfigOtherController {
    private ConfigOtherBean bean;

    @Autowired
    public ConfigOtherController(ConfigOtherBean bean) {
        this.bean = bean;
    }

    @GetMapping(value = "/someInfo")
    public String getInfo() {
        return bean.toString();
    }
}
