package com.lemon.controller;

import com.lemon.bean.UserBean;
import com.lemon.exception.MyException1;
import com.lemon.validated.RestfulValid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 参数验证 + 异常处理
 * <p>
 * Created by lemon
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping(value = "/do")
    public UserBean getUser(@Validated(RestfulValid.POST.class) @RequestBody UserBean user) {
        return user;
    }

    @GetMapping("/say")
    public String sayYe() {
        return "ye!..";
    }

    /**
     * 测试自定义异常
     * http://localhost:8080/user/MyException?name=go
     */
    @GetMapping("/MyException")
    public String testMyException(@RequestParam String name) throws Exception {
        if ("go".equals(name)) {
            throw new MyException1("testMyException...");
        }
        return "testMyException...";
    }
}
