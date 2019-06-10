package com.lemon.service.impl;

import com.lemon.dao.TUserMapper;
import com.lemon.entity.TUser;
import com.lemon.service.ShiroTokenService;
import com.lemon.shiro.JWTToken;
import com.lemon.shiro.JWTUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shuai.Jing
 * @date 2019/5/31
 */
@Service("shiroTokenService")
public class ShiroTokenServiceImpl implements ShiroTokenService {
    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public String test1() {
        return "测试";
    }

    @Override
    public TUser getUserByName(String name, String pwd) {
        return tUserMapper.login(name,pwd);
    }

    @Override
    public String login(String name,String pwd) {
        Subject subject = SecurityUtils.getSubject();

        String compact = Jwts.builder()
                .setSubject(name)
                .signWith(SignatureAlgorithm.ES256, pwd)
                .compact();

        JWTToken jwtToken = new JWTToken(compact);
//        Jwts.builder()
//                .setSubject("")
//                .signWith()
//                .compact();
//        Jwts.parser().setSigningKey().parseClaimsJws("").getBody().getSubject()

        UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
        subject.login(token);
        subject.logout();
        return "登录成功";
    }
}
