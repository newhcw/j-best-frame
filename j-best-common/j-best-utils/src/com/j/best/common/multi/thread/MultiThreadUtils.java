package com.j.best.common.multi.thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MultiThreadUtils<T> {




    public Map<String, T> doAction(List data, int threadCount, Supplier supplier)  {

        Map resultMap = new ConcurrentHashMap();

        CountDownLatch countDownLatchEnd = new CountDownLatch(1);
        CountDownLatch countDownLatchWait = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatchWait.countDown();
                        countDownLatchEnd.await();
                        resultMap.put(Thread.currentThread().getName(),supplier.get());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();

        }
        try {
            countDownLatchWait.await();
            countDownLatchEnd.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return resultMap;
    }

}
