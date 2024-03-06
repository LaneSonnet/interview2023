package com.lane.interview.interview.juc.threadpool;

import java.util.concurrent.*;

/**
 * @ Author:  duenpu
 * @ Date  :  13:31 2024/3/2
 */
public class CustomThreadPoolExample {
    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);

        // 提交任务到线程池
        for (int i = 0; i < 10; i++) {
            int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Executing Task " + taskNumber
                        + " in Thread " + Thread.currentThread().getName());
                try {
                    // 模拟任务执行时间
                    Thread.sleep(2000);
                    throw new RuntimeException("我异常了");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown(); // 关闭线程池
    }
}
