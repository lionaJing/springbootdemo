package com.lemon.dao;

import com.lemon.bean.TUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Shuai.Jing
 * @date 2019/4/6
 */
@Mapper
public interface ShiroUseMapper {
    TUser login(@Param("username") String name, @Param("pwd") String pwd);
}
