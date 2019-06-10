package com.lemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * ****
 * ****  这个类有问题 没有找出来(ShiroServiceImpl,Autowired 没有自动注入成功,应该是 过滤器的问题  **-|-|-**)
 * ****
 * Apache Shiro: https://shiro.apache.org
 * 例子 https://github.com/ityouknow/spring-boot-examples/blob/master/spring-boot-shiro
 * 文档： http://shiro.apache.org/static/1.2.2/apidocs/
 *
 * 关键概念：
 * Authentication 认证
 * Authorization 授权
 * Cryptography 加密
 * Subject 外部主体
 * SecurityManager 安全管理器
 * Realm 域
 *
 * 1. Shiro 使用到了日志框架 slf4j, 它是日志输出的统一接口,只是一种规则,而 log4j 是其的一种实现,所以项目中要添加
 * 2. Shiro 里面默认有 sql 查询语句,表名是 users,包含字段 id(主键)、username、password.如果自己不自定义 realm
 * 必须按照要求建立数据表及字段
 *
 * 此例子将 HTML 页面资源放在了 static 目录下(不正规形式)
 * Spring Boot 集成Shiro和CAS:           https://cloud.tencent.com/developer/article/1056130
 * Spring boot 中使用Shiro：             https://www.jianshu.com/p/0f2049a3983b
 * Shiro 拦截器：：                      https://www.cnblogs.com/yoohot/p/6085830.html
 * Apache Shiro的拦截器和认证:            https://blog.wuwii.com/shiro-2.html
 * SpringBoot中使用Shiro和JWT做认证和鉴权: https://www.jianshu.com/p/0b1131be7ace
 *
 * Created by lemon
 */
@SpringBootApplication
public class ShiroApplication {
    public static void main(String args[]) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
