package com.j.best.common.alarm.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class AlarmFilter extends Filter<ILoggingEvent> {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${alarm.keywords.name}")
    private String alarmKeywords;
    @Value("${alarm.robot.url}")
    private String wxChatRobotUrl;


    private static String ip;

    private static String localApplicationName;

    private static String localWxChatUrl;

    private static String[] keysWords;

    private static ExecutorService executorService;

    @PostConstruct
    private void init(){
        localApplicationName = this.applicationName;
        localWxChatUrl =  this.wxChatRobotUrl;

        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        }catch (Exception e){

        }
        keysWords = alarmKeywords.split(",");
    }



    @Override
    public FilterReply decide(ILoggingEvent event) {

        if (isAlarm(event)) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    StringBuffer content = new StringBuffer();
                    content.append("\n\n")
                            .append("********异常告警信息*********")
                            .append("\n\n")
                            .append("服务器IP：").append(AlarmFilter.ip)
                            .append("\n\n")
                            .append("告警项目:").append(AlarmFilter.localApplicationName)
                            .append("\n\n")
                            .append("告警方法:").append(event.getLoggerName())
                            .append("\n\n")
                            .append("告警信息:").append(event.getFormattedMessage());
                    log.info(content.toString());
                    Map<String, Object> markDownMap = new HashMap<>();
                    markDownMap.put("content",content.toString());
                    Map<String, Object> map = new HashMap<>();
                    map.put("markdown",markDownMap);
                    map.put("msgtype","markdown");
                    String robotUrl = AlarmFilter.localWxChatUrl;
                    if (!StringUtils.isEmpty(robotUrl) && !robotUrl.equals("null")){

                    }
                }
            });
        }
        return FilterReply.ACCEPT;
    }


    private boolean isAlarm(ILoggingEvent event) {

        for (String keysWord : keysWords) {
            if (event.getFormattedMessage().startsWith(keysWord)){
                return true;
            }
        }
        return false;
    }


    static {
        executorService = new ThreadPoolExecutor(2,2,30L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10),new ThreadPoolExecutor.DiscardOldestPolicy());
    }
}
