package com.j.best.user;

import com.j.best.common.cache.config.RedisUtil;
import com.j.best.user.domain.pojo.UserInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.j.best")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
        System.out.println("j-best-user is started");
    }
}
