package com.lemon.service;

import com.lemon.bean.User;
import com.lemon.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lemon
 */
@Service
public class UserService {
    // 在这里会提示 idea 错误(不是项目错误),扫描不到这个 bean,这是因为
    @Autowired
    private UserMapper userMapper;

    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    public Integer update(User user) {
        return userMapper.update(user);
    }

    public User getOne(int phone) {
        return userMapper.getOne(phone);
    }

    public Integer delete(int phone) {
        return userMapper.delete(phone);
    }

    public List<User> getAll() {
        return userMapper.getAll();
    }
}
