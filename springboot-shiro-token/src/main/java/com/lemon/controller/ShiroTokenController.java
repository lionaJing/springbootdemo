package com.lemon.controller;

import com.alibaba.fastjson.JSON;
import com.lemon.service.ShiroTokenService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@RestController
@ResponseBody
public class ShiroTokenController {
    @Autowired
    private ShiroTokenService shiroTokenService;

    @GetMapping("/t1")
    private String test() {
        return JSON.toJSONString(shiroTokenService.test1());
    }

    @GetMapping("/unLogin")
    private String unLogin() {
        return JSON.toJSONString("您还没有登录,请先登录");
    }

    @GetMapping("/t2")
    private String test2() {
        return JSON.toJSONString(shiroTokenService.getUserByName("admin", "123456"));
    }

    @GetMapping("/t3")
    @RequiresPermissions({"sys:list", "sys:delete"})
    private String t3() {
        return JSON.toJSONString("en 访问我需要权限,你做到了");
    }

    @GetMapping("/t/login")
    private String t4() {
        return JSON.toJSONString(shiroTokenService.login("admin", "123456"));
    }
}
