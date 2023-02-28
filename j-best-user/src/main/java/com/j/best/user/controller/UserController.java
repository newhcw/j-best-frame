package com.j.best.user.controller;

import com.j.best.user.application.service.IUserService;
import com.j.best.user.domain.response.ResultHttpResponse;
import io.micrometer.core.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/query")
    public ResultHttpResponse queryUserInfoByUserId(@RequestParam(value = "userId")String userId) {
        return ResultHttpResponse.setData(userService.queryUserInfoByUserId(userId));
    }

    @Timed(value = "register_request_duration", description = "注册接口", histogram = true)
    @RequestMapping(value = "/register")
    public ResultHttpResponse register(@RequestParam(value = "phone")String phone,@RequestParam(value = "nickName")String nickName) {
        return ResultHttpResponse.setData(userService.register(phone,nickName));
    }

}
