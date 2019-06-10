package com.lemon.exception;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e) {
//        String errorPosition = "";
//        //如果错误堆栈信息存在
//        if (e.getStackTrace().length > 0) {
//            StackTraceElement element = e.getStackTrace()[0];
//            String fileName = element.getFileName() == null ? "-未找到错误文件" : element.getFileName();
//            int lineNumber = element.getLineNumber();
//            errorPosition = fileName + ":" + lineNumber;
//        }
//        JSONObject jsonObject = new JSONObject();
//        JSONObject errorObject = new JSONObject();
//        errorObject.put("errorLocation", e.toString() + " -错误位置:" + errorPosition);
//        jsonObject.put("info", errorObject);
//        return jsonObject;
//    }

    /**
     * 权限不足异常
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException() {
        return "没有访问权限,请联系管理员";
    }

    @ExceptionHandler(AuthenticationException.class)
    public String authenticationException() {
        return "验证未通过";
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public String unauthenticatedException() {
        return "您还没有登录,请先登录";
    }

    @ExceptionHandler(UnknownAccountException.class)
    public String unknownAccountException() {
        return "用户名或密码驶入错误,请重新验证";
    }
}
