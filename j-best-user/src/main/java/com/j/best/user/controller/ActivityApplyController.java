package com.j.best.user.controller;

import com.j.best.user.application.service.apply.ActivityApplyServiceFactory;
import com.j.best.user.domain.request.ActivityApplyHttpRequest;
import com.j.best.user.domain.response.ResultHttpResponse;
import com.j.best.user.domain.vo.ActivityApplyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/market/activity")
public class ActivityApplyController {


    @Autowired
    private ActivityApplyServiceFactory activityApplyServiceFactory;

    @RequestMapping(value = "/apply")
    public ResultHttpResponse apply(@RequestBody ActivityApplyHttpRequest applyHttpRequest) {
        ActivityApplyContext activityApplyContext = new ActivityApplyContext(applyHttpRequest);
        Object result = activityApplyServiceFactory.getService(applyHttpRequest.getActivityType()).apply(activityApplyContext);
        return ResultHttpResponse.setData(result);
    }

}
