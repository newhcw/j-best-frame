package com.j.best.user.application.service.action;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IUserScoreComponent implements IAction{


    public void sendUserScore() {
        log.info("发送积分成功");
    }

}
