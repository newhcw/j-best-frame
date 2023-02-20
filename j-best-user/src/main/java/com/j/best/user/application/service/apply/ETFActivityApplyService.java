package com.j.best.user.application.service.apply;


import com.j.best.common.SpringUtils;
import com.j.best.user.application.service.action.IUserScoreComponent;
import com.j.best.user.application.service.rule.ApplyRuleEngine;
import com.j.best.user.application.service.rule.ApplyTimeLimitRule;
import com.j.best.user.application.service.rule.IRule;
import com.j.best.user.application.service.rule.RepeatApplyRule;
import com.j.best.user.domain.vo.ActivityApplyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ETFActivityApplyService extends AbstractActivityApplyService implements IActivityApplyService {


    @Autowired
    private IUserScoreComponent userScoreComponent;
    @Autowired
    SpringUtils springUtils;

    @Override
    public String getActivityType() {
        return ActivityType.ETF.getActivityType();
    }


    @Override
    public IRule assembleRule() {
        ApplyRuleEngine.Builder builder = new ApplyRuleEngine.Builder();
        return builder.addRule(new RepeatApplyRule())
                .addRule(new ApplyTimeLimitRule())
                .build();
    }

    @Override
    public void doAction(ActivityApplyContext activityApplyContext) {
        userScoreComponent.sendUserScore();
    }
}
