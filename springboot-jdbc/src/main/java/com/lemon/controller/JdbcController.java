package com.lemon.controller;

import com.lemon.bean.UserBean;
import com.lemon.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lemon
 */
@RestController
@RequestMapping(value = "/mySQL")
public class JdbcController {
    @Autowired
    private JdbcService jdbcService;

    @PostMapping(value = "/post")
    public String postUser(@RequestBody UserBean userBean) {
        return jdbcService.postUser(userBean);
    }

    @PutMapping("/put")
    public String putUser(@RequestBody UserBean userBean) {
        return jdbcService.putUser(userBean);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String name) {
        return jdbcService.deleteUser(name);
    }

    @GetMapping("/get")
    public String getUser(@RequestParam String name) {
        return jdbcService.getUser(name);
    }
}
