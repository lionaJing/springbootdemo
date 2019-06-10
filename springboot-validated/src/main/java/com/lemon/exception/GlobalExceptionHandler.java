package com.lemon.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理有三种方式
 * 1、使用 ExceptionHandler 注解
 * 缺点：进行异常处理的方法必须与出错的方法在同一个Controller里面
 * 2、实现 HandlerExceptionResolver 接口
 * 可以实现全局的异常控制,因为其返回了 ModelAndView,所以可以引导到指定错误页面,
 * 但是不满足 RESTFUL 模式(当然下面第三种方式同样支持返回指定页面)
 * 3、使用 @ControllerAdvice+ @ ExceptionHandler 注解
 * ControllerAdvice 注解定义全局异常处理类
 * ExceptionHandler 指定自定义错误处理方法拦截的异常类型
 * ResponseEntityExceptionHandler 里面定义了常用的一些异常,需要哪些异常只需重写哪些方法(handleXXX)
 *
 *
 * Created by lemon
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = MyException1.class)
    public String handlerMyException(HttpServletRequest req, Exception e) {
        return "抛出自定义异常:" + req.getRequestURI() + "," +
                req.getRequestURI() + "," + e.getMessage();
    }

    //参数有误异常
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        return ResponseEntity.status(status).body("{\"message\":\"参数有误\",\"code\":" + status + "}");
    }
}
