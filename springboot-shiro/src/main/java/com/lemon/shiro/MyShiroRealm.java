package com.lemon.shiro;

import com.lemon.bean.TUser;
import com.lemon.service.MyShiroService;
import com.lemon.service.impl.ShiroServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义 Realm
 *
 * @author Shuai.Jing
 * @date 2019/4/15
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private MyShiroService myShiroService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("+doGetAuthorizationInfo");
        Session session = SecurityUtils.getSubject().getSession();
        TUser user = (TUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("=doGetAuthenticationInfo");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String tName = usernamePasswordToken.getUsername();
        String tPwd = new String(usernamePasswordToken.getPassword());
        System.out.println("认证1:" + tName + "," + tPwd);

        String userName = (String) token.getPrincipal();//获取用户名
        String pwd = new String((char[]) token.getCredentials());//获取密码
        System.out.println("认证2:" + userName + "," + pwd);

        //查询sql
        TUser user = myShiroService.getUser(userName, pwd);
        System.out.println("查询用户返回:" + user.getUsername() + "," + user.getPassword());
        if (user != null) {
            //保存实体与凭证(密码使用MD5校验)
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    user.getUsername(),
                    user.getPassword(),
                    ByteSource.Util.bytes(user.pswSalt()),
                    getName());
            return authenticationInfo;
        }
        return null;
    }
}
