package com.j.best.user.application.service.apply;

import com.j.best.user.domain.vo.ActivityApplyContext;

public interface IActivityApplyService {

    String getActivityType();

    Object apply(ActivityApplyContext activityApplyContext);
}
