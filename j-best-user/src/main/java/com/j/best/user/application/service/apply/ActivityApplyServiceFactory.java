package com.j.best.user.application.service.apply;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityApplyServiceFactory implements InitializingBean {

    @Autowired
    private List<IActivityApplyService> activityApplyServiceList;

    private Map<String,IActivityApplyService> serviceMap;

    public IActivityApplyService getService(String activityType){
        return serviceMap.get(activityType);
    }


    public void afterPropertiesSet() throws Exception {
        serviceMap = new HashMap<String, IActivityApplyService>();
        activityApplyServiceList.forEach(activityApplyService->{
            serviceMap.put(activityApplyService.getActivityType(),activityApplyService);
        });
    }
}
