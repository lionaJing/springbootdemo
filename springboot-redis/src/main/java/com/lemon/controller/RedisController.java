package com.lemon.controller;

import com.lemon.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lemon
 */
@RestController
@ResponseBody
public class RedisController {
    @Autowired
    private RedisDao redisDao;

    @GetMapping(value = "/put")
    private String postKeyValue(@RequestParam String key, @RequestParam String value) {
        redisDao.set(key, value);
        return "设置完毕：" + key + "," + value;
    }

    @GetMapping(value = "/get")
    private String getKeyValue(@RequestParam String key) {
        return redisDao.get(key);
    }

    @GetMapping(value = "/test")
    private String test(@RequestParam String msg) {
        return "hi..." + msg;
    }
}
