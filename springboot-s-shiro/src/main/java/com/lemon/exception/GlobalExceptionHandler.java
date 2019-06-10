package com.lemon.exception;

import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义全局异常捕获
 * @author Shuai.Jing
 * @date 2019/5/27
 */
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e) {
        String errorPosition = "";
        //如果错误堆栈信息存在
        if (e.getStackTrace().length > 0) {
            StackTraceElement element = e.getStackTrace()[0];
            String fileName = element.getFileName() == null ? "未找到错误文件" : element.getFileName();
            int lineNumber = element.getLineNumber();
            errorPosition = fileName + ":" + lineNumber;
        }
        JSONObject jsonObject = new JSONObject();
        JSONObject errorObject = new JSONObject();
        errorObject.put("errorLocation", e.toString() + "    错误位置:" + errorPosition);
        jsonObject.put("info", errorObject);
        return jsonObject;
    }

    /**
     * 权限不足异常
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String handleAuthorizationException() {
        return "没有访问权限,请联系管理员";
    }

    /**
     * GET/POST请求方法错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String httpRequestMethodHandler() {
        return "请求方式错误,请认真查看接口文档";
    }

    /**
     * 未登录报错拦截
     * 在请求需要权限的接口,而连登录都还没登录的时候,会报此错
     */
    @ExceptionHandler(UnauthenticatedException.class)
    public String unauthenticatedException() {
        return "您还没有登录,请先登录";
    }
}
