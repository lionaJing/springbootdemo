package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 验证 + 全局异常捕获
 * Created by lemon
 */
@SpringBootApplication
public class ValidatedApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValidatedApplication.class, args);
    }
}
