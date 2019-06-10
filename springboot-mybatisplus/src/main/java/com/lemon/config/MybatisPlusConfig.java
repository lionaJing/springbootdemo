package com.lemon.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shuai.Jing
 * @date 2019/5/29
 */
@Configuration
@MapperScan("com.lemon.dao") //习惯性在 mapper 类上加了 @Mapper 注解,所以此处的扫描可以注释
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
