package com.lemon.springtask;

import com.lemon.controller.HelloWorldController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * cron表达式
 * 1、 占位符介绍
 * 整个时间段触发 : *
 * 指定的时间触发: ,
 * 指定的范围触发: -
 * 触发步进:      / (前面的值代表初始值,后面的代表偏移量)
 * 不指定值:      ? (只用于天、星期 两个子表达式)
 * 最后:         L (只用于天、星期 两个子表达式,表示某月最后一天、某星期最后一天)
 * 距离给定日期最近的工作日: W (只用于天)
 *
 * 2、 一个cron表达式有至少6个,按顺序为 (秒 分 时 天[某月的某天] 月 星期 年[可选])
 * 3、 例子
 * 0 0 12 * * ?   表示: 每天中午十二点触发
 * 4、 注解
 * cron: 指定任务在特定时间执行
 * fixedDelay: 表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms
 * fixedDelayString: 与fixedDelay含义一样，只是参数类型变为String
 * fixedRate: 表示按一定的频率执行任务，参数类型为long，单位ms
 * fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String
 * initialDelay: 表示延迟多久再第一次执行任务，参数类型为long，单位ms
 * initialDelayString: 与initialDelay的含义一样，只是将参数类型变为String
 * zone: 时区，默认为当前时区
 *
 * SpringTask 默认是单线程的,在实际开发中，不希望所有的任务都运行在一个线程中，想要改成多线程，
 * 给SpringTask提供一个多线程的TaskScheduler，Spring已经有默认实现
 *
 *
 * Created by lemon
 */
@Configuration
@EnableScheduling
public class SpringTaskSample {
    private static final Logger logger = LoggerFactory.getLogger(SpringTaskSample.class);

    // 每 10 秒打印一次
    @Scheduled(cron = "0/10 * * * * *")
    public void test1() {
        logger.info(Thread.currentThread().getName()+"=> "+(new Date()).toString());
    }
}
