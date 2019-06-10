package com.lemon.controller;

import com.lemon.bean.User;
import com.lemon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lemon
 */
@RestController
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/insert")
    private Integer insert() {
        User user = new User(10L, "Jack", 110, "中国");
        return userService.insert(user);
    }

    @PutMapping(value = "/update")
    private Integer update() {
        User user = new User(10L, "LiLI", 110, "日本");
        return userService.update(user);
    }

    @DeleteMapping(value = "/delete")
    private Integer delete() {
        return userService.delete(110);
    }

    @GetMapping(value = "/getOne")
    private String getOne() {
        return userService.getOne(110).toString();
    }

    @GetMapping(value = "/getAll")
    private String getAll() {
        int size = userService.getAll().size();
        return String.valueOf(size);
    }
}
