package com.lemon.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义错误页面拦截器
 * @Component 把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
 * Created by lemon
 */
@Component
public class ErrorPageInterceptor extends HandlerInterceptorAdapter {
    private List<Integer> errorCodeList = Arrays.asList(404, 403, 500, 501);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //可以进行编码、安全控制等处理
        if (errorCodeList.contains(response.getStatus())){
            //response.sendRedirect("" + response.getStatus());
            response.sendRedirect("error");
            return false;
        }
        //在这里依然可以执行其他拦截操作
        //request.getAttribute("");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        //可以修改ModelAndView
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        //可以根据ex是否为null判断是否发生了异常，进行日志记录。
        super.afterCompletion(request, response, handler, ex);
    }
}
