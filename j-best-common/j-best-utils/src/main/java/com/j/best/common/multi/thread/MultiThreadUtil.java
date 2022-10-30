package com.j.best.common.multi.thread;

import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MultiThreadUtil<T> {

    private final String THREAD_NAME_PREFIX="WorkThread-";

    private ConcurrentHashMap resultMap;
    private static CountDownLatch startLatch;
    private CountDownLatch waitDoneLatch ;

    private Supplier supplier;

    private Consumer errorHandler;

    public  static  <T> Map<String, T> start(List data, int threadCount, Supplier supplier, Consumer errorHander){
        return new MultiThreadUtil().doStart(data,threadCount,supplier,errorHander);
    }

    @SneakyThrows
    public  Map<String, T> doStart(List data, int threadCount, Supplier supplier,Consumer errorHander) {
         this.resultMap = new ConcurrentHashMap();
         this.startLatch = new CountDownLatch(1);
         this.waitDoneLatch = new CountDownLatch(threadCount);
         this.supplier = supplier;
         this.errorHandler = errorHander;

        for (int i = 0; i < threadCount; i++) {
            WorkThread myThread = new WorkThread(this);
            myThread.setName(THREAD_NAME_PREFIX+i);
            myThread.start();
        }
        startLatch.countDown();// 开始工作
        waitDoneLatch.await();
        return resultMap;
    }

    static class WorkThread extends Thread{


        private MultiThreadUtil context;

        WorkThread(MultiThreadUtil context) {
            this.context = context;
        }

        @SneakyThrows
        public void run() {
            try {
                context.startLatch.await();
                context.resultMap.put(Thread.currentThread().getName(), context.supplier.get());
            }catch (Exception e) {
                context.errorHandler.accept(e);
            }finally {
                context.waitDoneLatch.countDown();
            }
        }
    }
}
