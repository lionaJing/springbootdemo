package com.lemon.expection;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 权限不足异常
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException() {
        return JSON.toJSONString("没有访问权限,请联系管理员");
    }

    @ExceptionHandler(AuthenticationException.class)
    public String authenticationException() {
        return JSON.toJSONString("验证未通过");
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public String unauthenticatedException() {
        return JSON.toJSONString("您还没有登录,请先登录");
    }

    @ExceptionHandler(UnknownAccountException.class)
    public String unknownAccountException() {
        return JSON.toJSONString("用户名或密码驶入错误,请重新验证");
    }
}
