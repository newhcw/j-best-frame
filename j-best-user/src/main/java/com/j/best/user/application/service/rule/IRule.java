package com.j.best.user.application.service.rule;

import com.j.best.user.domain.vo.ActivityApplyContext;

public interface IRule {

   Boolean checkRule(ActivityApplyContext activityApplyContext);

    void next(IRule rule);
}
