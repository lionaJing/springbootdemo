package com.lemon.controller;

import com.lemon.model.UserBean;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * SpringBoot 官方不推荐使用 jsp,使用 jsp 需要额外进行配置
 * 1. 添加依赖
 * <dependency>
 *  <groupId>org.apache.tomcat.embed</groupId>
 *  <artifactId>tomcat-embed-jasper</artifactId>
 *  <scope>provided</scope>
 * </dependency>
 *  <dependency>
 *  <groupId>javax.servlet</groupId>
 *  <artifactId>jstl</artifactId>
 * </dependency>
 * 2. 修改配置文件 application.properties
 * 页面默认前缀目录 spring.mvc.view.prefix=/WEB-INF/jsp/
 * 响应页面默认后缀 spring.mvc.view.suffix=.jsp
 * 3. 在 src/main 下面创建 webapp/WEB-INF/jsp 目录用来存放我们的jsp页面
 * 4. 要想让spring-boot支持JSP，需要将项目打成war包
 * pom.xml: <packaging>war</packaging>
 *
 * 整合Thymeleaf模板,SpringBoot 官方推荐
 * 1.添加依赖
 * <dependency>
 *  <groupId>org.springframework.boot</groupId>
 *  <artifactId>spring-boot-starter-thymeleaf</artifactId>
 * </dependency>
 * 2. 在 src/main/resources 下面创建 static、templates 目录
 *
 * 关于错误页面
 * a.禁用错误页面(注意 yml 文件格式)
 * 方法1. 修改配置文件属性：server.error.whitelabel.enabled=false 将显示源自底层应用程序容器的简明页面，例如Tomcat
 * 方法2. 通过排除ErrorMvcAutoConfiguration bean
 * spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration
 * #for Spring Boot 2.0
 * #spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
 * 方法3. 在应用主类增加 @EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
 *
 * b. 自定义 error page
 * 情况1：框架中的默认错误页面
 * 新建 resources/templates/error.html 框架会自动寻找 error.html
 * 情况2：使用拦截器处理内嵌与外部 tomcat
 * https://blog.csdn.net/IT_faquir/article/details/79521417
 * 情况3: 内嵌 tomcat 错误页面监听
 * https://blog.csdn.net/csdn_0911/article/details/82794426
 *
 *
 * 供内部 tomcat 使用
 * 1. EmbeddedServletContainerCustomizer
 * 供外部 tomcat 使用
 * 自定义 error 页面
 *
 * Created by lemon
 */
@RestController
public class PageController {

    @GetMapping(value = "/index")
    public ModelAndView indexPage() {
        ModelAndView view = new ModelAndView();
        // 设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
        view.setViewName("index");
        // 设置属性
        view.addObject("title", "欢迎页面");
        view.addObject("desc", "用户信息：");
        UserBean bean = new UserBean();
        bean.name = "Jack";
        bean.age = 777;
        bean.phone = "1234567";
        view.addObject("user", bean);
        return view;
    }

//    @GetMapping(value = "/error")
//    public ModelAndView errorPage() {
//        ErrorPage errorPage = new ErrorPage();
//        return view;
//    }
}
