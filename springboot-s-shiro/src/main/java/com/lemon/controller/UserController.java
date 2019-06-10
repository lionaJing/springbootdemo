package com.lemon.controller;

import com.alibaba.fastjson.JSON;
import com.lemon.config.ShiroRealm;
import com.lemon.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shuai.Jing
 * @date 2019/5/27
 */
@RestController
@ResponseBody
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ShiroRealm shiroRealm;
    @Autowired
    private EhCacheManager manager;

    /**
     * http://localhost:8080/login?name=admin&pwd=123456
     * pwd: 123456 -> 245ba764b37561370ef3dd2b63f82c17
     */
    @GetMapping(value = "/login")
    public String findOneByName(@RequestParam String name, @RequestParam String pwd) {
        return JSON.toJSONString(userServiceImpl.findOneByName(name, pwd));
    }

    @GetMapping(value = "/logOut")
    public String loginOut() {
        SecurityUtils.getSubject().logout();
        shiroRealm.clearCached();
        return JSON.toJSONString("退出登录");
    }

    /**
     * 测试修改权限,清除缓存功能
     * 测试方法：开启两个浏览器,一个执行权限修改操作,一个执行包含权限注解的方法，查看日志
     *
     * 链接：
     * https://blog.csdn.net/qq_37959142/article/details/80020899
     * https://blog.csdn.net/qq_34021712/article/details/80309246
     */
    @GetMapping(value = "/alertPM")
    public String testAlertPermission(@RequestParam String userName) {
        // 清除当前用户的权限缓存
        //shiroRealm.clearCached();

        // 清除指定用户的权限缓存
        // authorizationCache: 对应 ShiroConfig 中 shiroRealm() -> 'shiroRealm.setAuthorizationCacheName()'
        Cache<SimplePrincipalCollection, Object> cache = manager.getCache("authorizationCache");
        if (cache == null) {
            return JSON.toJSONString("-> null");
        }
        // SHIRO_REALM: 自定义 realm 时的 setName() 方法
        // userName： 要清除权限环缓存的用户
        cache.remove(new SimplePrincipalCollection(userName, "SHIRO_REALM"));

        return JSON.toJSONString("测试修改权限,清除缓存");
    }

    @GetMapping(value = "/hi")
    public String sayHi() {
        return JSON.toJSONString("你好,我不受权限控制");
    }

    @GetMapping(value = "/unLogin")
    public String unLogin() {
        return JSON.toJSONString("未登录");
    }

    /**
     * 当用户登录失败测试过多账户被锁定后解锁当前账户
     * state: 0 正常，1 异常被锁定
     */
    @GetMapping(value = "/updateAccountState")
    public String unLockAccount(@RequestParam String name, @RequestParam int state) {
        Integer flag = userServiceImpl.unLockAccount(name, state);
        return JSON.toJSONString(flag + " 账户状态(0:正常,1:锁定) > " + state);
    }

    @GetMapping(value = "/test1")
    @RequiresPermissions("user:list")
    public String test1() {
        return JSON.toJSONString("测试1");
    }
}
