package com.j.best.user.application.service.rule;

import com.j.best.user.domain.vo.ActivityApplyContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplyTimeLimitRule implements IRule{

    private IRule nextRule;

    @Override
    public Boolean checkRule(ActivityApplyContext activityApplyContext) {
       log.info("apply time rule");
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
