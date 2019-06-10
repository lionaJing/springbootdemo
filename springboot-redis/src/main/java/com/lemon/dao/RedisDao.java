package com.lemon.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by lemon
 */
@Repository
public class RedisDao {
    @Autowired
    private StringRedisTemplate template;

    //设置键对值
    public void set(String key, String value) {
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key, value, 5, TimeUnit.SECONDS);//5秒后过期
    }

    //得到值
    public String get(String key) {
        ValueOperations<String, String> ops = this.template.opsForValue();
        String value = ops.get(key);
        return value == null ? "已经过期" : value;
    }
}
