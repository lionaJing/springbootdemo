package com.lemon.shiro;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.filter.session.NoSessionCreationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
public class ShiroTokenFilter extends AccessControlFilter {

    //返回 true 继续执行,返回 false 调用 onAccessDenied
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) throws Exception {
        System.out.println("-isAccessAllowed");
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) return true;
        boolean isAuthenticated = getSubject(request, response).isAuthenticated(); // 是不是验证过

        //判断 token
        HttpServletRequest tokenRequest = (HttpServletRequest) request;
        String token = tokenRequest.getHeader("token");
        System.out.println("token = "+token);
        System.out.println("isAuthenticated = "+isAuthenticated);

        if(isAuthenticated) {
            if ("".equals(token) || token == null) {
                return false;
            }
            // 解析 token,检查格式、签名、是否过期
            return true;
        }
        return false;
    }

    // 返沪 true 请求继续响应,返回 false 直接返回响应结果
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("-onAccessDenied");
        HttpServletRequest tokenRequest = (HttpServletRequest) request;
        // 重定向到未登录页面
        RequestDispatcher requestDispatcher = tokenRequest.getRequestDispatcher("/unLogin");
        requestDispatcher.forward(request,response);
        return false;
    }
}
