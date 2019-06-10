package com.lemon.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
public class JWTToken implements AuthenticationToken {
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
