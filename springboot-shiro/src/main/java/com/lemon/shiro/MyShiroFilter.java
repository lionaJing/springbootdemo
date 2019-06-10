package com.lemon.shiro;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuai.Jing
 * @date 2019/5/22
 */
public class MyShiroFilter extends AccessControlFilter {


    /**
     * 返回 true,继续执行
     * 返回 false 调用 onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        System.out.println("isAccessAllowed");

        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) return true;
        boolean isLogin = getSubject(request, response).isAuthenticated(); // 是不是验证过
        HttpServletRequest tokenRequest = (HttpServletRequest) request;
        String token = tokenRequest.getHeader("token");

        return true;
    }

    /**
     * 返回 true 请求继续响应,返回 false 直接返回响应结果
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("onAccessDenied");
        return false;
    }
}
