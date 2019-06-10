package com.lemon.service;

import com.lemon.bean.User;
import com.lemon.dao.UserXmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lemon
 */
@Service
public class UserXmlService {
    @Autowired
    private UserXmlMapper userXmlMapper;

    public Integer insert(User user) {
        return userXmlMapper.insert(user);
    }

    public Integer update(User user) {
        return userXmlMapper.update(user);
    }

    public User getOne(int phone) {
        return userXmlMapper.getOne(phone);
    }

    public Integer delete(int phone) {
        return userXmlMapper.delete(phone);
    }

    public List<User> getAll() {
        return userXmlMapper.getAll();
    }
}
