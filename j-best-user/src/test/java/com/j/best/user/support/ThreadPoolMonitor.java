package com.j.best.user.support;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.cache.GuavaCacheMetrics;
import io.micrometer.core.instrument.binder.jvm.ExecutorServiceMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Service
public class ThreadPoolMonitor {

    @Autowired
    private MeterRegistry meterRegistry;

    /**
     * 线程池改造
     */
    public void execute() {
        ExecutorServiceMetrics.monitor(meterRegistry, Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("dubbo.refresh"+t.getId());
                return t;
            }
        }),"refreshCacheThread");
    }

    /**
     * Guava Cache改造
     */
    public void cache(){
        GuavaCacheMetrics.monitor(meterRegistry, CacheBuilder.newBuilder().recordStats().build(new CacheLoader<String, String>() {
            @Override
            public String load(String k1) throws Exception {
                return null;
            }
        }),"refresh_cache");
    }
}
