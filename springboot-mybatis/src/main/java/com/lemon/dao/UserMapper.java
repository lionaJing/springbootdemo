package com.lemon.dao;

import com.lemon.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by lemon
 */
@Mapper
public interface UserMapper {
    @Insert("insert into users(userName,u_address,phone) values(#{userName},#{address},#{phone})")
    Integer insert(User user);

    @Delete("delete from users where phone = #{phone}")
    Integer delete(int phone);

    @Update("update users set userName = #{userName},u_address = #{address} where phone = #{phone}")
    Integer update(User user);

    @Select("select userName,u_address,phone from users")
    @Results({
            @Result(property = "userName", column = "userName"),
            @Result(property = "address", column = "u_address"),
            @Result(property = "phone", column = "phone")
    })
    List<User> getAll();

    @Select("select userName,u_address,phone from users where phone = #{phone}")
    @Results({
            @Result(property = "address", column = "u_address"),
    })
    User getOne(int phone);
}
