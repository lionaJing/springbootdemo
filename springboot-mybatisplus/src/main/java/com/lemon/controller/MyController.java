package com.lemon.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lemon.entity.TStudent;
import com.lemon.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.StringJoiner;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
@RestController
@ResponseBody
public class MyController {
    @Autowired
    private MyService myService;

    @GetMapping("/sayYe")
    private String syYe() {
        return JSON.toJSONString(myService.sayYe());
    }

    // 读取 BaseMapper 中的方法,通过 id 获取某一条记录
    @GetMapping("/test1")
    public String test1() {
        TStudent studentBean = myService.selectByNo(126);
        return JSON.toJSONString(studentBean);
    }

    // 读取 IService 中的方法
    @GetMapping("/test2")
    public String test2() {
        int w = myService.count(new QueryWrapper<TStudent>()
                .eq("s_sex", 0));
        int m = myService.count(new QueryWrapper<TStudent>()
                .eq("s_sex", 1));
        StringJoiner joiner = new StringJoiner("");
        joiner.add("男生共").add(String.valueOf(m)).add("人,").add("女生共").add(String.valueOf(w)).add("人");
        return JSON.toJSONString(joiner.toString());
    }

    // 读取 BaseMapper 中的方法,获取列表
    @GetMapping("/test3")
    public String test3() {
        return JSON.toJSONString(myService.selectList(0));
    }

    // 自定义 xml-mapper
    @GetMapping("/test4")
    public String test4() {
        return JSON.toJSONString(myService.getCourseAndGrade("九儿"));
    }

    /**
     * 获取所有用户的集合
     */
    @GetMapping("/test5")
    public String test5() {
        return JSON.toJSONString(myService.list());
    }

    /**
     * 分页查询
     * size： 每页显示数
     * current: 当前页
     */
    @GetMapping("/test6")
    public String test6(Page<TStudent> page) {
        // 因为使用了泛型,所以第二个参数为 null,表示查询当前表
        return JSON.toJSONString(myService.page(page,null));
    }
}
