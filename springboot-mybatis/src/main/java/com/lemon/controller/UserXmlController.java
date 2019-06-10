package com.lemon.controller;

import com.lemon.bean.User;
import com.lemon.service.UserXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lemon
 */
@RestController
@RequestMapping("xml")
@ResponseBody
public class UserXmlController {

    @Autowired
    private UserXmlService userXmlService;

    @PostMapping(value = "/insert")
    private Integer insert() {
        User user = new User(12L, "xml_Test", 120, "美国");
        return userXmlService.insert(user);
    }

    @PutMapping(value = "/update")
    private Integer update() {
        User user = new User(12L, "xml_Java", 120, "加利福尼亚");
        return userXmlService.update(user);
    }

    @GetMapping(value = "/getOne")
    private String getOne() {
        User user = userXmlService.getOne(120);
        return user.toString();
    }

    @GetMapping(value = "/getAll")
    private String getAll() {
        List<User> list = userXmlService.getAll();
        return "返回 = " + list.size();
    }

    @DeleteMapping(value = "/delete")
    private Integer delete() {
        return userXmlService.delete(120);
    }
}
