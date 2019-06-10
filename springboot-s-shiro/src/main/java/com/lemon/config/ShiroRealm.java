package com.lemon.config;

import com.alibaba.fastjson.JSON;
import com.lemon.bean.UserBean;
import com.lemon.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Shuai.Jing
 * @date 2019/5/27
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;

    public ShiroRealm() {
        setName("SHIRO_REALM");

        // 以下方法是当前类中的方法,同样可以实现权限缓存的清除,不过此方式是清除全部的缓存,不是指定的缓存
        // 清除所有 授权缓存
        // getAuthorizationCache().clear();
        // 清除所有 认证缓存
        // getAuthenticationCache().clear();
    }

    /**
     * 验证当前登录的用户(认证登录),调用时机: 调用 subject.login(token) 方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String userName = userToken.getUsername();
        String pwd = new String(userToken.getPassword());

        System.out.println("name,pwd = " + userName + "," + pwd);

        // 从数据库查询用户信息
        UserBean userBean = userDao.findOneByName(userName);
        if (userBean == null)
            throw new UnknownAccountException("账户名或密码不正确");
        if (userBean.getState() == 1)
            throw new LockedAccountException("账号被锁定");
        if (userBean != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    userBean.getUsername(),
                    userBean.getPassword(),
                    ByteSource.Util.bytes(userBean.pswSalt()),
                    getName()
            );
            return authenticationInfo;
        }
        return null;
    }


    /**
     * 为当限前登录的用户授予角色和权限
     * 调用时机:
     * 1.确定当前操作的用户是否是XX角色是否有xx权限
     * 2.页面上有角色权限注解(hasRole,hasPermission)
     * 注意：每次都会调用此方法(如果这个方法里面涉及到数据库查询操作,最好放到缓存中),由于使用注解
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("检查权限角色");
        System.out.println(JSON.toJSONString(principals));

        // 注意此处的强制类型转换,AuthenticationInfo 构造函数第一个参数类型是什么就强制转换为什么
        String userName = (String) principals.getPrimaryPrincipal();
        System.out.println("userName: " + userName);

        // 执行数据库权限角色查询操作(此处为模拟的)
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>();
        permissions.add("user:add");
        permissions.add("user:delete");
        permissions.add("user:update");
        permissions.add("user:list");
        info.setStringPermissions(permissions);
        return info;
    }

    // 清除与指定帐户标识/标识关联的任何缓存数据
    public void clearCached() {
        super.clearCache(SecurityUtils.getSubject().getPrincipals());
    }
}
