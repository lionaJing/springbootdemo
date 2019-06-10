package com.lemon.exception;

/**
 * 自定义异常基类
 * Created by lemon
 */
public class BaseException extends Exception {
    BaseException(String msg) {
        super(msg);
    }
}
