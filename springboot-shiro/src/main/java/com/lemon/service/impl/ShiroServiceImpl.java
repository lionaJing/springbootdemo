package com.lemon.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lemon.bean.TUser;
import com.lemon.dao.ShiroUseMapper;
import com.lemon.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shuai.Jing
 * @date 2019/4/5
 */
@Service(value = "shiroService")
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private ShiroUseMapper shiroUserDao;

    @Override
    public JSONObject login(String name, String pwd) {
        System.out.println("service 登录参数:" + name + "," + pwd);
        JSONObject jsonObject = new JSONObject();
        JSONObject resultObj = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        //用户名/密码身份验证令牌
        UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
        try {
            subject.login(token);
            Session session = subject.getSession();
            TUser u = (TUser) session.getAttribute("LOGIN");
            System.out.println("登录成功：" + JSON.toJSONString(u));

            resultObj.put("code", 0);
            resultObj.put("desc", "ok");
        } catch (UnknownAccountException e) {
            resultObj.put("code", 1);
            resultObj.put("desc", "用户未注册");
        } catch (IncorrectCredentialsException e) {
            resultObj.put("code", 2);
            resultObj.put("desc", "用户验证失败");
        } catch (LockedAccountException e) {
            resultObj.put("code", 3);
            resultObj.put("desc", "该账号被锁定");
        } catch (ExcessiveAttemptsException e) {
            resultObj.put("code", 4);
            resultObj.put("desc", "验证未通过,错误次数过多");
        } catch (AuthenticationException e) {
            resultObj.put("code", 5);
            resultObj.put("desc", "验证未通过" + e.getMessage());
        }
        jsonObject.put("result", resultObj);
        if (subject.isAuthenticated()) {
            //验证通过
            jsonObject.put("data", name);
        } else {
            //验证未通过
            jsonObject.put("data", null);
        }
        return jsonObject;
    }

    /**
     * 获取登录信息
     */
    @Override
    public TUser getUser(String name, String pwd) {
        return shiroUserDao.login(name, pwd);
    }
}
