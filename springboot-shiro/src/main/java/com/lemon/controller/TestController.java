package com.lemon.controller;

import com.alibaba.fastjson.JSON;
import com.lemon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
@RestController
@ResponseBody
@RequestMapping("/t")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/t1")
    private String test1() {
        return "test1";
    }

    @GetMapping("/t2")
    private String sayYe() {
        return testService.sayYe();
    }

    @GetMapping("/t3")
    private String getUser() {
        return JSON.toJSONString(testService.getUser("admin", "123456"));
    }
}
