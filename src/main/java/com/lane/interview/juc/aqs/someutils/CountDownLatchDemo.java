package com.lane.interview.juc.aqs.someutils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

class StudentRunRace {

    CountDownLatch stopLatch = new CountDownLatch(1);
    CountDownLatch runLatch = new CountDownLatch(10);

    public void waitSignal() throws Exception{
        System.out.println("选手" + Thread.currentThread().getName() + "正在等待裁判发布口令");
        stopLatch.await();
        System.out.println("选手" + Thread.currentThread().getName() + "已接受裁判口令");
        Thread.sleep((long) (Math.random() * 10000));
        System.out.println("选手" + Thread.currentThread().getName() + "到达终点");
        runLatch.countDown();
    }

    public void waitStop() throws Exception{
        Thread.sleep((long) (Math.random() * 10000));
        System.out.println("裁判"+Thread.currentThread().getName()+"即将发布口令");
        stopLatch.countDown();
        System.out.println("裁判"+Thread.currentThread().getName()+"已发送口令，正在等待所有选手到达终点");
        runLatch.await();
        System.out.println("所有选手都到达终点");
        System.out.println("裁判"+Thread.currentThread().getName()+"汇总成绩排名");
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        StudentRunRace studentRunRace = new StudentRunRace();
        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                try {
                    studentRunRace.waitSignal();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            service.execute(runnable);
        }
        try {
            studentRunRace.waitStop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
