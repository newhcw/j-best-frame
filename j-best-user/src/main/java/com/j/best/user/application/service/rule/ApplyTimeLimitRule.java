package com.j.best.user.application.service.rule;

import com.j.best.user.domain.vo.ActivityApplyContext;

public class ApplyTimeLimitRule implements IRule{

    private IRule nextRule;

    @Override
    public Boolean checkRule(ActivityApplyContext activityApplyContext) {
        System.out.println("apply time rule");
        if (nextRule != null) {
            nextRule.checkRule(activityApplyContext);
        }
        return true;
    }

    @Override
    public void next(IRule rule) {
        nextRule = rule;
    }
}
