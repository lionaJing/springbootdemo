package com.lemon.bean;

import java.io.Serializable;

/**
 * @author Shuai.Jing
 * @date 2019/4/6
 */
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String password; //密码存储的是 MD5 编码后的值
    private int state; //账号状态,0：正常,1：异常

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    //密码盐,增强密码破解难度
    public String pswSalt() {
        return "apple123";
    }
}
