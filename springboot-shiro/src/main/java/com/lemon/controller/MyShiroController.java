package com.lemon.controller;

import com.alibaba.fastjson.JSON;
import com.lemon.service.MyShiroService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@RestController
@ResponseBody
@RequestMapping("/s")
public class MyShiroController {
    @Autowired
    private MyShiroService myShiroService;

    @GetMapping("/s1")
    private String test() {
        return myShiroService.test1();
    }

    @GetMapping("/unLogin")
    private String test1() {
        return JSON.toJSONString("您还没有登录-unLogin");
    }

    @GetMapping("/s2")
    private String test2() {
        return JSON.toJSONString("你好,我不受权限控制");
    }

    @GetMapping("/login")
    private String test3() {
        System.out.println("==执行登录...");//245ba764b37561370ef3dd2b63f82c17
        return JSON.toJSONString(myShiroService.login("admin","123456"));
    }

    @GetMapping("/s4")
    @RequiresPermissions({"user:list","user:delete"})
    private String test4() {
        return JSON.toJSONString("en,你通过了我的权限验证");
    }
}
