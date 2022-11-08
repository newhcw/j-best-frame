package com.j.best.user.application.service;

import com.j.best.user.domain.pojo.UserInfo;

public interface IUserService {
    UserInfo queryUserInfoByUserId(String userId);

    Void register(String phone, String nickName);
}
