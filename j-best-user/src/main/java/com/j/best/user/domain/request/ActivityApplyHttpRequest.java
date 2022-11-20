package com.j.best.user.domain.request;

import lombok.Data;

@Data
public class ActivityApplyHttpRequest {

    private String activityId;
    private String activityType;
    private String applyUserId;
    private String applyUserType;

    private ActivityApplyApplyExtra activityApplyApplyExtra;


}
