package com.lemon.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by lemon
 */
@WebFilter(filterName = "testFilter2", urlPatterns = "/say")
@Order(2)
public class TestFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器2初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("过滤器2---拦截/say-请求开始");
        chain.doFilter(request, response);
        System.out.println("过滤器2---拦截/say—请求结束");
    }

    @Override
    public void destroy() {

    }
}
