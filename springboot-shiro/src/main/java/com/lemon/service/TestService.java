package com.lemon.service;

import com.lemon.bean.TUser;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
public interface TestService {
    String sayYe();
    TUser getUser(String name, String pwd);
}
