package com.lemon.service.impl;

import com.lemon.dao.UserDao;
import com.lemon.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shuai.Jing
 * @date 2019/5/27
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public String findOneByName(String name, String pwd) {
        Subject subject = SecurityUtils.getSubject();
        // 如果使用 token 要使用 token 头进行判断是否登录过
//        if (subject.isAuthenticated())
//            return "已经登录过";
        UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
        System.out.println("token = " + token.toString());

        try {
            subject.login(token);
            return "登录成功 -> "+token;
        } catch (UnknownAccountException e) {
            return "用户名不存在:"+e.getMessage();
        } catch (IncorrectCredentialsException e) {
            return "用户名或密码错误";
        } catch (ExcessiveAttemptsException e) {
//            userDao.update(name,1);
            return "登录重试次数,超限";
        }catch (LockedAccountException e) {
            //通过缓存实现
            return "账户被锁定";
        } catch (DisabledAccountException e) {
            return "验证未通过,帐号已经禁止登录";
        } catch (AuthenticationException e) {
            return "验证未通过";
        }
    }

    /**
     * 解锁账户
     */
    public Integer unLockAccount(String name,int state) {
        // 先查账户是否存在再去改账户状态
        return userDao.update(name,state);
    }
}
