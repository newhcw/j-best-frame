package com.j.best.user.resposity.local;

import com.j.best.user.infrastructure.mapper.MarketActivityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MarketActivityResposity {

    @Autowired
    private MarketActivityMapper marketActivityMapper;

    public Long saveMarketActivityApplyIfo() {
       return marketActivityMapper.save();
    }

}
