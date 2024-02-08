package com.lane.interview.interview.juc.threadlocal;

/**
 * @author duenpu
 * @date 2023/8/10 20:40
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0;i < 100;i++) {
                    threadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + " threadLocal.get() = " + threadLocal.get());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                threadLocal.remove();
            }
        }, "Thread-A").start();

        new Thread(() -> {
            try {
                for (int i = 0;i < 100;i++) {
                    //threadLocal.set(i);
                    System.out.println(Thread.currentThread().getName() + " threadLocal.get() = " + threadLocal.get());
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                threadLocal.remove();
            }
        }, "Thread-B").start();
    }
}
