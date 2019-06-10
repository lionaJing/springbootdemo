package com.lemon.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加载默认配置文件 application.yml
 * 使用 @Value("${spring.name}") 直接取值
 *
 * Created by lemon
 */
@RestController("/hello")
public class HelloController {
    //http://localhost:8080/helloWorld
    @GetMapping(value = "/helloWorld")
    public String helloWorld() {
        return "Hello world!";
    }

    @Value("${my.name}")
    private String name; //读取配置文件
    @Value("${my.age}")
    private String age;

    @GetMapping(value = "/myInfo")
    public String printMyInfo() {
        return name + "," +age;
    }
}
