package com.lemon.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@Configuration
public class ShiroTokenConfig {
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(2);
        return credentialsMatcher;
    }

    @Bean
    public ShiroTokenRealm shiroTokenRealm() {
        ShiroTokenRealm shiroTokenRealm = new ShiroTokenRealm();
        shiroTokenRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroTokenRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroTokenRealm());
        // 关闭自带的 session
        // http://shiro.apache.org/session-management.html#disabling-subject-state-session-storage
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);
        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }

    // https://blog.csdn.net/jadyer/article/details/12172839
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new ShiroTokenFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/unLogin");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/t1", "anon"); //可以匿名访问
        filterChainDefinitionMap.put("/t/login", "anon");
        //filterChainDefinitionMap.put("/**", "tokenFilter"); //所有的请求都走自定义的过滤器
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }
}
