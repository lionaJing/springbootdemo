package com.lemon.springtask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 配置 SpringTask 线程池
 * <p>
 * Created by lemon
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler pool = new ThreadPoolTaskScheduler();
        pool.setPoolSize(7);
        pool.setThreadNamePrefix("spring-task-thread");
        return pool;
    }
}
