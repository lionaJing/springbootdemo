package com.lemon.service;

import com.lemon.entity.TUser;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
public interface ShiroTokenService {
    String test1();
    TUser getUserByName(String name,String pwd);
    String login(String name,String pwd);
}
