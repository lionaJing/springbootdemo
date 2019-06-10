package com.lemon.service;

import com.lemon.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注解 @Transactional 事务回滚
 * 注意，默认只会对运行时异常进行事务回滚，非运行时异常不会回滚事务
 * 注解 @Transactional(rollbackFor=Exception.class) 所有异常都会回滚(运行时、非运行时)
 * <p>
 * Created by lemon
 */
@Service
public class JdbcService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public String postUser(UserBean userBean) {
        jdbcTemplate.update("INSERT INTO user(name,age,sex) VALUES(?,?,?)",
                userBean.name, userBean.age, userBean.sex);

        //???
//        jdbcTemplate.execute("INSERT INTO user(name,age,sex) VALUES(?,?,?)", new PreparedStatementCallback<UserBean>() {
//            @Override
//            public UserBean doInPreparedStatement(PreparedStatement ps)
//                    throws SQLException, DataAccessException {
//                ps.setString(1,userBean.name);
//                ps.setInt(2,23);
//                ps.setString(3,userBean.sex);
//                return userBean;
//            }
//        });
        return "Success insert...";
    }

    public String putUser(UserBean userBean) {
        jdbcTemplate.update("UPDATE user SET age = ?,sex= ? WHERE name = ?",
                userBean.age,userBean.sex,userBean.name);
        return "update ok...";
    }

    public String deleteUser(String name){
        return "delete ok...";
    }

    public String getUser(String name) {
        return "";
    }
}
