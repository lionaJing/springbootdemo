package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * webService 构建方式这里将两种：
 * 1、Axis(要使用 wsdl4j.jar)
 * SpringBoot 官方 Demo 好像就是使用这种方式,个人感觉没有 cxf 使用简单
 * https://spring.io/guides/gs/producing-web-service/
 * http://www.springboottutorial.com/creating-soap-web-service-with-spring-boot-web-services-starter
 *
 * 2、CXF(最常用,本 Demo 使用此方式构建)
 * 客户端的使用要拷贝服务端的.wsdl 文件,然后自动生成代码
 * https://blog.csdn.net/c99463904/article/details/76140591
 *
 * https://www.imooc.com/article/261006
 * https://github.com/xie19900123/spring-boot-learning/blob/master/chapter-34
 *
 * https://www.jianshu.com/p/dd1bf8fa6904
 *
 * 其他：
 * JDOM：https://github.com/jaxen-xpath/jaxen/blob/master/src/java/samples/JDOMDemo.java
 * jaxen： http://www.cafeconleche.org/jaxen/apidocs/index.html
 *
 *
 * Created by lemon
 */
@SpringBootApplication
public class WebServicesApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServicesApplication.class, args);
    }
}
