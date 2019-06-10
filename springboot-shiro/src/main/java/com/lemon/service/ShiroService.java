package com.lemon.service;

import com.alibaba.fastjson.JSONObject;
import com.lemon.bean.TUser;

/**
 * @author Shuai.Jing
 * @date 2019/4/5
 */
public interface ShiroService {
    JSONObject login(String name,String pwd);
    TUser getUser(String name, String pwd);
}
