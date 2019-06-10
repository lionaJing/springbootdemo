package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MySQL + JDBC连接池
 *
 * spring-boot-starter家族成员简介 https://www.cnblogs.com/chenkeyu/p/8483950.html
 * springBoot注解大全 https://www.cnblogs.com/tanwei81/p/6814022.html
 *
 * Created by lemon
 */
@SpringBootApplication
public class JdbcApplication {
    public static void main(String args[]) {
        SpringApplication.run(JdbcApplication.class, args);
    }
}
