package com.lemon.service.impl;

import com.alibaba.fastjson.JSON;
import com.lemon.bean.TUser;
import com.lemon.dao.ShiroUseMapper;
import com.lemon.service.MyShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@Service("myShiroService")
public class MyShiroServiceImpl implements MyShiroService {
    @Autowired
    private ShiroUseMapper shiroUseMapper;

    @Override
    public String test1() {
        return JSON.toJSONString(shiroUseMapper.login("admin", "123456"));
    }

    @Override
    public String login(String name, String pwd) {
        System.out.println("执行登录...");
        UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return "登录成功!";
    }

    @Override
    public TUser getUser(String name, String pwd) {
        return shiroUseMapper.login(name, pwd);
    }

    @Override
    public String logout(String name) {
        return null;
    }
}
