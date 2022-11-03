package com.j.best.user.application.service;

import com.j.best.user.domain.pojo.UserInfo;
import com.j.best.user.resposity.local.UserCacheResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserCacheResposity userCacheResposity;
    public UserInfo queryUserInfoByUserId(String userId) {
        return userCacheResposity.queryCache(userId);
    }

}
