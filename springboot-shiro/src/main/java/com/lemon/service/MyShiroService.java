package com.lemon.service;

import com.lemon.bean.TUser;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
public interface MyShiroService {
    String test1();
    String login(String name,String pwd);
    String logout(String name);
    TUser getUser(String name, String pwd);
}
