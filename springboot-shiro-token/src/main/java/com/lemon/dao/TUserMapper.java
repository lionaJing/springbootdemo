package com.lemon.dao;

import com.lemon.entity.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@Mapper
public interface TUserMapper {
    TUser login(@Param("username") String name, @Param("pwd") String pwd);
}
