package com.lane.interview.juc.aqs.someutils;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;

/**
 * @ Author:  duenpu
 * @ Date  :  23:31 2023/8/14
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 1. 创建 semaphore 对象
        Semaphore semaphore = new Semaphore(2);
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
}
