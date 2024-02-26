package com.lemon.controller;

import com.google.gson.Gson;
import com.lemon.bean.ResultBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuai.Jing
 * @date 2023/12/27
 */
@RestController
@RequestMapping("app")
public class HiController {
    private Gson gson = new Gson();

    /**
     * 约定指定格式返回
     */
    @GetMapping("mm/get")
    @ResponseBody
    private ResultBean get(@RequestParam(value = "id") String id) {
        System.out.println(id);
        Map<String, Object> map = new HashMap<>();
        if (!"404".equals(id)) {
            map.put("name", "demo");
            map.put("nationality", "中华人民共和国");
            map.put("phone", "16798475678");
        } else {
            return new ResultBean().error();
        }
        return new ResultBean().ok(map);
    }
}
