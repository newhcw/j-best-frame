package com.j.best.common.log.utils;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Configuration
public class RestTemplateConfiguration {

    public RestTemplate get() {
        // 以下省略其他相关配置
        RestTemplate restTemplate = new RestTemplate();
        // 使用拦截器包装http header
        restTemplate.setInterceptors(new ArrayList<ClientHttpRequestInterceptor>() {
            {
                add((request, body, execution) -> {
                    String traceId = MDC.get(Constants.LOG_TRACE_ID);
                    if (StringUtils.hasLength(traceId)) {
                        request.getHeaders().add(Constants.HTTP_HEADER_TRACE_ID, traceId);
                    }
                    return execution.execute(request, body);
                });
            }
        });

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        // 注意此处需开启缓存，否则会报getBodyInternal方法“getBody not supported”错误
        factory.setBufferRequestBody(true);
        restTemplate.setRequestFactory(factory);

        return restTemplate;


    }
}
