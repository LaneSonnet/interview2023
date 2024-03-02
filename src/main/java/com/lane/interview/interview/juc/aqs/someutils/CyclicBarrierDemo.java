package com.lane.interview.interview.juc.aqs.someutils;

import org.slf4j.MDC;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ Author:  duenpu
 * @ Date  :  23:04 2023/8/14
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println("主人看看哪个猪跑最快，最肥...");
        });

        // 循环跑3次
        for (int i = 0; i < 3; i++) {
            // 5条猪开始跑
            for(int j = 0; j<5; j++) {
                int finalJ = j;
                service.submit(() -> {
                    System.out.println("pig{} is run ....." + finalJ);
                    try {
                        // 随机时间，模拟跑花费的时间
                        Thread.sleep(new Random().nextInt(5000));
                        System.out.println("pig{} reach barrier ....." + finalJ);
                        barrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        service.shutdown();
    }
}
