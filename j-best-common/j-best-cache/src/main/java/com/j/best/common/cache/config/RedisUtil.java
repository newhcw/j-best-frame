package com.j.best.common.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object val) {
        redisTemplate.opsForValue().set(key,val);
    }

    public void set(String key, Object val,Long timeout,TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,val,timeout, timeUnit);
    }

    public Long lLeftPush(String key,String value){
        return redisTemplate.opsForList().leftPush(key,value);
    }

    public Long lLeftPushAll(String key,String ... value) {
        return redisTemplate.opsForList().leftPushAll(key,value);
    }

    public Long lLeftPushAll(String key, Collection<String> value) {
        return redisTemplate.opsForList().leftPushAll(key,value);
    }

    public String lRightPop(String key) {
        return (String)redisTemplate.opsForList().rightPop(key);
    }

    public String lRightPop(String key, long timeout, TimeUnit timeUnit) {
        return (String) redisTemplate.opsForList().rightPop(key,timeout,timeUnit);
    }


}
