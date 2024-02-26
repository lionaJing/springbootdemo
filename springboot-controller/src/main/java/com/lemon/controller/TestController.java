package com.lemon.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lemon.model.ParamBean;
import com.lemon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shuai.Jing
 * @date 2020/11/14
 */
@RestController
@CrossOrigin
@RequestMapping("test")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping(value = "/m1")
    public String m1(@RequestBody ParamBean paramBean) {
        return paramBean.toString();
    }

    /**
     * map 结构 {apiUrl: {}, params: {},headers: {}}
     *
     * @param map
     * @return
     */
    @PostMapping(value = "/m2")
    @ResponseBody
    public String m2(@RequestBody Map<String, Object> map) {
        Gson gson = new Gson();
        map.put("type", "postJson1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        String paramStr = gson.toJson(map.get("params"));
        Map<String, String> paramMap = gson.fromJson(paramStr, Map.class);
        String headerStr = gson.toJson(map.get("headers"));
        Map<String, String> headerMap = gson.fromJson(headerStr, Map.class);

        System.out.println(gson.toJson(headerMap));
        System.out.println(gson.toJson(paramMap));
        System.out.println(map.get("apiUrl").toString());
        String json = gson.toJson(map);
        return json;
    }

    @GetMapping(value = "/m3")
    public String m3(@RequestParam int number, HttpServletRequest request) {
        System.out.println(request.getSession().getId());

        String id = request.getSession().getId() + "_code";
        String code = (String) request.getSession().getAttribute(id);
        System.out.println("id:   " + id);
        System.out.println("code: " + code);
        if (code != null) {
            long firstCodeTime = Long.valueOf(code);
            long currentTime = new Date().getTime();
            if (currentTime - firstCodeTime < 6000) {
                return "稍等";
            }
        }
        request.getSession().setAttribute(id, String.valueOf(new Date().getTime()));

        StringBuffer buffer = new StringBuffer();
        try {
            int num = 12 / number;
            System.out.println("num => " + num);
            buffer.append("计算成功 => " + num);
        } catch (Exception e) {
            buffer.append(e.getMessage());
            e.printStackTrace();
        }

        File mFile = new File("springboot-controller/src/main/resources/static", "report");
        System.out.println("exists: " + mFile.exists());
        if(mFile.exists()) {
            System.out.println("getName: " + mFile.getName());
            System.out.println("getAbsolutePath: " + mFile.getAbsolutePath());
        }
        try {
            System.out.println();
            String path = ResourceUtils.getURL("classpath").getPath();
            File nFile = new File(path, "static/report");
            System.out.println(nFile.exists());
            System.out.println(nFile.getAbsolutePath());
            if(nFile.exists()) {
                System.out.println(nFile.getName());
                System.out.println(nFile.getAbsolutePath());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @PostMapping(value = "/m4")
    @ResponseBody
    public String m4(@RequestParam Map<String, Object> map) {
        map.put("type", "post");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        String json = new Gson().toJson(map);
        return json;
    }

    @GetMapping(value = "/m5")
    @ResponseBody
    public String m5(@RequestParam Map<String, Object> map, HttpServletResponse response) {
        response.setHeader("Access-Allow-Control-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Expose-Headers", "*");// *,Authorization
        response.setHeader("Access-Control-Allow-Credentials", "true");
        map.put("type", "get");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        String json = new Gson().toJson(map);
        return json;
    }

    @PutMapping(value = "/m6")
    @ResponseBody
    public String m6(@RequestBody Map<String, Object> map) {
        return "返回的数据是：" + map.get("key");
    }

    @GetMapping(value = "/m7")
    @ResponseBody
    public String m7(@RequestParam String msg) {
        // 测试 arthas (阿里热修复)
        int a = 0;
        int b = 100 % a;
        return "ok => ".concat(msg).concat(" => ").concat(String.valueOf(b));
    }

    @PostMapping(value = "/m8")
    @ResponseBody
    public String m8(
            @RequestBody Map<String, Object> map,
            @RequestHeader(value = "my-id") String myId) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("myId", myId);
        map.put("headers", headers);
        return new Gson().toJson(map);
    }

    @PostMapping(value = "/m9")
    @ResponseBody
    public String m9(@RequestParam(value = "msg") String msg, @RequestParam(value = "mapJson") String mapJson) {
        TypeToken<HashMap<String, String>> type = new TypeToken<HashMap<String, String>>() {
        };
        HashMap<String, String> map = new Gson().fromJson(mapJson, type.getType());
        Map<String, Object> result = new HashMap<>();
        result.put("msg", msg);
        result.putAll(map);
        return new Gson().toJson(result);
    }

    @PostMapping(value = "/m10")
    @ResponseBody
    public String m10(@RequestParam("file") MultipartFile[] files,
                      @RequestParam("mapJson") String mapJson) {
        return testService.m10(files, mapJson);
    }
}
