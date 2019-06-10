package com.lemon.dao;

import com.lemon.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by lemon
 */
@Mapper
public interface UserXmlMapper {
    Integer insert(User user);

    Integer update(User user);

    Integer delete(int phone);

    List<User> getAll();

    User getOne(int phone);
}
