package com.lemon.dao;

import com.lemon.bean.UserBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Shuai.Jing
 * @date 2019/5/27
 */
@Mapper
public interface UserDao {
    UserBean findOneByName(@Param("username") String name);
    Integer update(@Param("username") String name,@Param("state") Integer state);
}
