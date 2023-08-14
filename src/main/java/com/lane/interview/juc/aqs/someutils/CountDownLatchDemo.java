package com.lane.interview.juc.aqs.someutils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @ Author:  duenpu
 * @ Date  :  22:37 2023/8/14
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int nThreads = 5; // 需要等待的线程数
        CountDownLatch latch = new CountDownLatch(nThreads);
        Random random = new Random();
        for (int i = 0; i < nThreads; i++) {
            new Thread(() -> {
                // 执行任务
                System.out.println(Thread.currentThread().getName() + " is running...");
                try {
                    Thread.sleep(random.nextInt(5000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finished...");
                // 任务完成，计数器减1
                latch.countDown();
            }).start();
        }

        // 等待所有线程执行完毕
        latch.await();
        System.out.println("All threads have finished.");
    }
}
