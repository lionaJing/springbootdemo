package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**1. log4j 配置
 * 注意依赖和配置文件log4j.properties
 *
 * 2. 定时任务
 * a Spring Task
 * b Quartz
 *
 * 两者区别：
 * Quartz功能完善，它支持集群环境下的任务调度，需要将任务调度状态序列化到数据库
 * Spring Task Spring3.0以后自带的task，轻量级的Quartz，使用简单，
 * 支持固定时间 (支持cron表达式)和固定时间间隔调度任务，支持线程池管理。
 *
 * Created by lemon
 */
@SpringBootApplication
public class Log4jApplication {
    public static void main(String[] args) {
        SpringApplication.run(Log4jApplication.class, args);
    }
}
