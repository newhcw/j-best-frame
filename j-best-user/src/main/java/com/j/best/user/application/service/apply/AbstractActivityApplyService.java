package com.j.best.user.application.service.apply;

import com.j.best.user.application.service.rule.ApplyRuleEngine;
import com.j.best.user.application.service.rule.ApplyTimeLimitRule;
import com.j.best.user.application.service.rule.IRule;
import com.j.best.user.application.service.rule.RepeatApplyRule;
import com.j.best.user.domain.vo.ActivityApplyContext;
import com.j.best.user.resposity.local.MarketActivityResposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractActivityApplyService {


    @Autowired
    private MarketActivityResposity marketActivityResposity;


    public Object apply(ActivityApplyContext activityApplyContext) {

        // 规则校验
        assembleRule().checkRule(activityApplyContext);

        // 保存报名信息
        Long applyId = marketActivityResposity.saveMarketActivityApplyIfo();

        // 报名成功后，各种业务行为交给子类执行
        doAction(activityApplyContext);

        return applyId;
    }

    public abstract IRule assembleRule();

    public abstract void doAction(ActivityApplyContext activityApplyContext);


}
