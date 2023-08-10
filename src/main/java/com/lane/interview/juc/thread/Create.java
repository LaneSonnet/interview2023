package com.lane.interview.juc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author duenpu
 * @date 2023/8/10 16:25
 */
public class Create {
    static class ThreadExample extends Thread {
        @Override
        public void run() {
            System.out.println("Thread is running");
        }
    }

    static class RunnableExample implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable is running");
        }
    }

    public static void main(String[] args) {
        // 1-使用Thread类
        ThreadExample thread1 = new ThreadExample();
        thread1.start();

        // 2-使用Runnable接口
        RunnableExample runnable = new RunnableExample();
        Thread thread2 = new Thread(runnable);
        thread2.start();

        // 3-使用匿名类
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous thread is running");
            }
        });
        thread3.start();

        // 4-使用Lambda表达式
        Thread thread4 = new Thread(() -> {
            System.out.println("Lambda thread is running");
        });
        thread4.start();

        // 5-使用线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            System.out.println("Thread from ExecutorService is running");
        });
        executor.shutdown();
    }
}
