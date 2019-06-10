package com.lemon.service.impl;

import com.lemon.bean.TUser;
import com.lemon.dao.ShiroUseMapper;
import com.lemon.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shuai.Jing
 * @date 2019/5/30
 */
@Service(value = "testService")
public class TestServiceImpl implements TestService {
    @Autowired
    private ShiroUseMapper shiroUserDao;

    @Override
    public String sayYe() {
        return "ye,i am here";
    }

    /**
     * 获取登录信息
     */
    @Override
    public TUser getUser(String name, String pwd) {
        return shiroUserDao.login(name, pwd);
    }
}
