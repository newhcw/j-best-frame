package com.j.best.user.resposity.local;


import com.j.best.common.cache.config.RedisUtil;
import com.j.best.user.domain.pojo.UserInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCacheResposity implements InitializingBean {

    @Autowired
    private RedisUtil redisUtil;

    private String USER_CACHE_KEY_PREFIX = "user:info:id:";

    public UserInfo queryCache(String userId) {
        return (UserInfo) redisUtil.get(USER_CACHE_KEY_PREFIX.concat(userId));
    }

    public void writeUserInfoCache(UserInfo userInfo) {
        redisUtil.set(USER_CACHE_KEY_PREFIX.concat(userInfo.getUserId()),userInfo);
    }



    public void afterPropertiesSet() throws Exception {
        this.writeUserInfoCache(new UserInfo("1","huangchunwu"));
        this.writeUserInfoCache(new UserInfo("2","chenlihua"));
        this.writeUserInfoCache(new UserInfo("3","huangyuchen"));
    }


}
