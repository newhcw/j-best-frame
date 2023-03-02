package com.j.best.web.repeat;

import com.j.best.common.cache.config.RedisUtil;
import com.j.best.web.util.WebUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 定义防重复切面
 * @author PingYa
 */
@Component
@Aspect
@Order(1)
public class RepeatAspect {

    @Resource
    private RedisUtil redisUtil;
    @Pointcut("@annotation(com.j.best.web.repeat.Repeat))")
    public void repeatRequest(){};

    @Around("repeatRequest()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = WebUtils.getRequest();
        Long userId = WebUtils.getId();
        String uri = request.getRequestURI();
        MethodSignature methodSignature = (MethodSignature)point.getSignature();
        Repeat repeat = methodSignature.getMethod().getAnnotation(Repeat.class);
        StringBuilder key = new StringBuilder(uri).append("_").append(userId);
        if(redisUtil.lock(key.toString(), repeat.value())){
            return point.proceed();
        }else{
            return "请勿重复点击";
        }
    }
}

