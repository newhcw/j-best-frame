package com.j.best.user.application.service.apply;


import com.j.best.user.application.service.rule.ApplyRuleEngine;
import com.j.best.user.application.service.rule.ApplyTimeLimitRule;
import com.j.best.user.application.service.rule.RepeatApplyRule;
import com.j.best.user.domain.vo.ActivityApplyContext;
import org.springframework.stereotype.Service;

@Service
public class ETFActivityApplyService extends AbstractActivityApplyService implements IActivityApplyService {


    @Override
    public String getActivityType() {
        return ActivityType.ETF.getActivityType();
    }

    @Override
    public Object apply(ActivityApplyContext activityApplyContext) {

        ApplyRuleEngine.Builder builder = new ApplyRuleEngine.Builder();
        builder.addRule(new RepeatApplyRule())
                .addRule(new ApplyTimeLimitRule())
                .build().checkRule(activityApplyContext);


        return null;
    }
}
