package com.lemon.config;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuai.Jing
 * @date 2019/5/27
 */
public class ShiroFilter extends AccessControlFilter {
    /**
     * 返回 true,继续执行
     * 返回 false 调用 onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request,
                                      ServletResponse response,
                                      Object mappedValue) throws Exception {
        System.out.println("isAccessAllowed");
        // OPTIONS 由于没有头信息,所以请求不进行校验
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) return true;
        return false;
    }

    /**
     * 返回 true 请求继续响应,返回 false 直接返回响应结果
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws Exception {
        System.out.println("onAccessDenied");

//        HttpServletRequest tokenRequest = (HttpServletRequest) request;
//        String token = tokenRequest.getHeader("token");
//        if ("".equals(token) || token == null) {
//            // 直接修改 response 响应
//            //HttpServletResponse httpResponse = (HttpServletResponse) response;
//            //httpResponse.setCharacterEncoding("UTF-8");
//            //httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//            //httpResponse.setContentType("application/json;charset=UTF-8");
//            //response.getWriter().print("没有 token,请先登录");
//
//            // 重定向到未登录页面
//            RequestDispatcher requestDispatcher = tokenRequest.getRequestDispatcher("/unLogin");
//            requestDispatcher.forward(request,response);
//            return false;
//        }
        return true;
    }
}
