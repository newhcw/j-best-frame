package com.j.best.user.controller;

import com.j.best.common.cache.config.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/test/redis")
    public String testRedis() {
       return (String) redisUtil.get("test");
    }
}
