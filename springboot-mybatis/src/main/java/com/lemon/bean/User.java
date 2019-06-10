package com.lemon.bean;

/**
 * 实体类
 * Created by lemon
 */
public class User {
    private Long id;
    private String userName;
    private int phone;
    private String address;

    public User() {
        super();
    }

    public User(Long id, String userName, int phone, String address) {
        this.id = id;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }
}
