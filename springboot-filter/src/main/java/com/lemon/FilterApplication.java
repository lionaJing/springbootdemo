package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 拦截器与过滤器区别
 * 1、Filter是依赖于Servlet容器，属于Servlet规范的一部分，而拦截器则是独立存在的，可以在任何情况下使用。
 * 2、Filter的执行由Servlet容器回调完成，而拦截器通常通过动态代理的方式来执行。
 * 3、Filter的生命周期由Servlet容器管理，而拦截器则可以通过IoC容器来管理，因此可以通过注入等方式来获取其他Bean的实例，
 * 因此使用会更方便
 * <p>
 * 过滤器它使用户可以改变一个 request和修改一个response.
 * <p>
 * Created by lemon
 */
@SpringBootApplication
@ServletComponentScan
public class FilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
    }
}
