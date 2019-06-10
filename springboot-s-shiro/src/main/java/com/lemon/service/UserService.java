package com.lemon.service;

import com.lemon.bean.UserBean;

/**
 * @author Shuai.Jing
 * @date 2019/5/27
 */
public interface UserService {
    String findOneByName(String name,String pwd);
}
