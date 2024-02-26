package com.lemon.bean;

import java.io.Serializable;

/**
 * @author Shuai.Jing
 * @date 2023/12/27
 */
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code = 0;
    private String msg = "success";
    private T data;

    public ResultBean<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public boolean success() {
        return code == 0;
    }

    public ResultBean<T> error() {
        this.code = 500;
        this.msg = "";
        return this;
    }

    public ResultBean<T> error(int code) {
        this.code = code;
        this.msg = "";
        return this;
    }

    public ResultBean<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public ResultBean<T> error(String msg) {
        this.code = 500;
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
