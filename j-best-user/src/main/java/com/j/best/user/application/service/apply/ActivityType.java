package com.j.best.user.application.service.apply;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityType {


    ETF("1","etf");

    private String activityId;

    private String activityType;

}
