package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MyBatis: http://www.mybatis.org/mybatis-3/
 * MyBatis 不能自动创建表
 *
 * 集成 MyBatis
 * 添加依赖 mybatis-spring-boot-starter
 * 配置 .properties
 *
 * 使用 MyBatis 有两种方式
 * 1、注解(使用注解需要注意配置 application.properties )
 * 2、xml
 *
 * 如何选择注解还是 xml?
 * 注解适用于快速简单的模式(在处理多对一、多对多时可能会麻烦)
 * xml 书写 sql 比较灵活
 *
 * 传多个参数：
 * http://www.cnblogs.com/mingyue1818/p/3714162.html
 * https://www.jianshu.com/p/12581c13b37a
 *
 * 报这个错：org.apache.ibatis.binding.BindingException
 * 一般的原因是配置 xml 映射地址出现问题
 *
 * Created by lemon
 */
@SpringBootApplication
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }
}
