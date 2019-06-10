package com.lemon.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Shuai.Jing
 * @date 2019/5/27
 */
@Configuration
public class ShiroConfig {
    /**
     * Shiro生命周期处理器
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 密码校验规则 HashedCredentialsMatcher,
     * 防止密码在数据库里明码保存,如果自定义需要实现继承HashedCredentialsMatcher
     * 在这里可以实现 HashedCredentialsMatcher 子类做缓存失败的次数,最终做到"账户被锁定"功能
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        // 普通密码校验
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        // true 16进制,false base64(默认是16进制)
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        credentialsMatcher.setHashIterations(2);//加密次数(相当于 md5(md5(""))
        return credentialsMatcher;
    }

    /**
     * 配置自定义的权限登录器
     * 设置缓存
     */
    @Bean
    public MyShiroRealm shiroRealm() {
        MyShiroRealm shiroRealm = new MyShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }

    /**
     * 定义安全管理器securityManager,注入自定义的realm]
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
//        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 获取filters
        Map<String, Filter> filtersMap = new LinkedHashMap<>();
        filtersMap.put("authc",new MyShiroFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        /*
         * 定义shiro过滤链  Map结构
		 * Map中key(xml中是指value值)的第一个 '/' 代表的路径是相对于 HttpServletRequest.getContextPath()
		 * anon： 所有url都都可以匿名访问,它对应的过滤器里面是空的
		 * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器
		 * user:配置记住我或认证通过可以访问；
		 */
        Map<String, String> filterMap = new LinkedHashMap<>();

        filterMap.put("/statics/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/hi","anon");
        filterMap.put("/**", "authc");

        //针对前后端分离,这里返回地址,最周返回给前端的是 json
        shiroFilterFactoryBean.setLoginUrl("/unLogin");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 开启Shiro的注解(如 @RequiresRoles,@RequiresPermissions),需借助 SpringAOP 扫描使用 Shiro 注解的类,
     * 并在必要时进行安全逻辑验证,配置以下两个 bean( DefaultAdvisorAutoProxyCreator(可选) 和
     * AuthorizationAttributeSourceAdvisor)即可实现此功能
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    // 配置shiro跟spring的关联
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
