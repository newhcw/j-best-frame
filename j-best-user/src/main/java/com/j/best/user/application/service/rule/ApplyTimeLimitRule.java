package com.j.best.user.application.service.rule;

import com.j.best.user.domain.vo.ActivityApplyContext;

public class ApplyTimeLimitRule implements IRule{

    @Override
    public Boolean checkRule(ActivityApplyContext activityApplyContext) {
        return null;
    }

    @Override
    public IRule next(IRule rule) {
        return null;
    }
}
