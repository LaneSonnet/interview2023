package com.lane.interview.interview.juc.threadlocal;

/**
 * @author duenpu
 * @date 2023/8/10 20:40
 */
public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0;i < 10;i++) {
                    threadLocal.set(i);
                    threadLocal1.set(i + 100);
                    System.out.println(Thread.currentThread().getName() + " threadLocal.get() = " + threadLocal.get());
                    System.out.println(Thread.currentThread().getName() + " threadLocal111.get() = " + threadLocal1.get());
                    Thread.sleep(200);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                threadLocal.remove();
            }
        }, "Thread-A").start();

//        new Thread(() -> {
//            try {
//                for (int i = 0;i < 10;i++) {
//                    //threadLocal.set(i);
//                    System.out.println(Thread.currentThread().getName() + " threadLocal.get() = " + threadLocal.get());
//                    Thread.sleep(200);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                threadLocal.remove();
//            }
//        }, "Thread-B").start();
    }
}
