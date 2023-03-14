package com.j.best.common.log.interceptor;

import com.j.best.common.log.utils.Constants;
import com.j.best.common.log.utils.TraceLogUtils;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Resource
public class TraceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = request.getHeader(Constants.HTTP_HEADER_TRACE_ID);
        if (StringUtils.isEmpty(traceId)) {
            traceId = TraceLogUtils.getTraceId();
        }
        MDC.put(Constants.LOG_TRACE_ID, traceId);
        return true;
    }



}


