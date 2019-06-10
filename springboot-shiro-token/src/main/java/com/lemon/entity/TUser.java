package com.lemon.entity;

import java.io.Serializable;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
public class TUser implements Serializable {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //密码盐,增强密码破解难度
    public String pswSalt() {
        return "apple123";
    }
}
