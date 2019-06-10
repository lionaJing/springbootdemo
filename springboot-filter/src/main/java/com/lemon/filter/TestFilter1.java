package com.lemon.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器1
 * Created by lemon
 */
@WebFilter(filterName="testFilter1",urlPatterns="/*")
@Order(1)
public class TestFilter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器1初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("测试过滤器1---请求开始");
        chain.doFilter(request, response);
        System.out.println("测试过滤器1---请求结束");
    }

    @Override
    public void destroy() {

    }
}
