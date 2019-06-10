package com.lemon.controller;

import com.alibaba.fastjson.JSON;
import com.lemon.entity.CSBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
@Api(value = "模块1", tags = "A", description = "模块1 API", produces = "http")
@RestController
@ResponseBody
@RequestMapping("/swagger2")
public class Swagger2Controller {
    @ApiOperation(value = "test1 接口", notes = "提示：该接口是GET方式")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
    @GetMapping("/test1")
    private String test1(@RequestParam String id) {
        return JSON.toJSONString("test1");
    }

    @ApiOperation(value = "test2接口", notes = "该接口是POST方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "用户电话", required = true, dataType = "int"),
    })
    @PostMapping("/test2")
    private String test2(@RequestParam String id, @RequestParam int phone) {
        return JSON.toJSONString("test2");
    }

    @ApiOperation(value = "test3", notes = "该接口是POST方式")
    @ApiImplicitParam(name = "csBean", value = "用户", required = true, dataType = "CSBean")
    @PostMapping("/test3")
    private String test3(@RequestBody CSBean csBean) {
        return JSON.toJSONString("test3");
    }

    @ApiOperation(value = "test4", notes = "该接口是Put方式")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
    @PutMapping("/test4/{id}")
    private String test4(@PathVariable("id") Long id) {
        return JSON.toJSONString("test4");
    }

    @ApiOperation(value = "test3", notes = "该接口是Delete方式")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @DeleteMapping("/test5")
    private String test5(@RequestParam int id) {
        return JSON.toJSONString("test5");
    }
}
