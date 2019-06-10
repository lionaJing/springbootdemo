package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * jpa 数据库连接池
 * Lombok使用 https://segmentfault.com/a/1190000012449170
 *
 * <p>
 * Created by lemon
 */
@SpringBootApplication
public class JpaApplication {
    public static void main(String args[]) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
