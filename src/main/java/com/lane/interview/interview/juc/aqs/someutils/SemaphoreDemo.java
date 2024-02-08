package com.lane.interview.interview.juc.aqs.someutils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @ Author:  duenpu
 * @ Date  :  23:31 2023/8/14
 */
public class SemaphoreDemo {
    public static void main1(String[] args) {
        // 1. 创建 semaphore 对象
        // 2. 10个线程同时运行
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                // 3. 获取许可
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    System.out.println(Thread.currentThread().getName() + "running...");
                    sleep(5000);
                    System.out.println(Thread.currentThread().getName() + "end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 4. 释放许可
                    semaphore.release();
                }
            }).start();
        }
    }

    private static final Semaphore semaphore = new Semaphore(3); // 初始许可数量为3

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 5; i++) {
            executorService.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 正在尝试获取许可...");
                    semaphore.acquire();



                    System.out.println(Thread.currentThread().getName() + " 已获取许可，正在执行操作...");
                    Thread.sleep(2000); // 模拟执行一些操作
                    // 获取当前时间，单位纳秒
                    long now = System.nanoTime();
                    System.out.println(Thread.currentThread().getName() + " 执行完毕，释放许可..." + now);



                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }
}
