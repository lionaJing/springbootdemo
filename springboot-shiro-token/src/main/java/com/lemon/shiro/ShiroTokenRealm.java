package com.lemon.shiro;

import com.lemon.entity.TUser;
import com.lemon.service.ShiroTokenService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
public class ShiroTokenRealm extends AuthorizingRealm {
    @Autowired
    private ShiroTokenService shiroTokenService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("===权限给予===");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("===登录认证===");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String userName = userToken.getUsername();
        String pwd = new String(userToken.getPassword());

        System.out.println("name,pwd = " + userName + "," + pwd);
        TUser tUser = shiroTokenService.getUserByName(userName, pwd);

        if (tUser == null)
            throw new UnknownAccountException("账户名或密码不正确");
        if (token != null) {
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    tUser.getUsername(),
                    tUser.getPassword(),
                    ByteSource.Util.bytes(tUser.pswSalt()),
                    getName()
            );
            return authenticationInfo;
        }
        return null;
    }
}
