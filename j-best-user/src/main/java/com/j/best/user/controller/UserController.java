package com.j.best.user.controller;

import com.j.best.common.cache.config.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/test/redis")
    public String testRedis() {
       return (String) redisUtil.get("test");
    }

    @RequestMapping(value = "/test/log")
    public String testLog(String message) {
        log.error(String.format("异常：%s ",message));
        return "hi,log";
    }
}
