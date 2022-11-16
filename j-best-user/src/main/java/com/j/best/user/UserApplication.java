package com.j.best.user;


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
