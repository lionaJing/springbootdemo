package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 关于 shiro 缓存两种方案：shiro-ehcache(或其他的内存缓存框架或自己实现的)、Redis
 * 1.ehcache,直接在jvm虚拟机中缓存，速度快，效率高；但是缓存共享麻烦，集群分布式应用不方便
 * 2.redis,通过socket访问到缓存服务，效率比 ehcache 低，比数据库要快很多，处理集群和分布式缓存方便，有成熟的方案
 * 总结：
 * 如果是单个应用或者对缓存访问要求很高的应用，用ehcache；
 * 如果是大型系统，存在缓存共享、分布式部署、缓存内容很大的，建议用redis
 *
 * 学习连接： https://www.jianshu.com/p/1f57e495c4f6
 * @author Shuai.Jing
 * @date 2019/5/27
 */
@SpringBootApplication
public class ApplicationShiro {
    public static void main(String args[]) {
        SpringApplication.run(ApplicationShiro.class,args);
    }
}
