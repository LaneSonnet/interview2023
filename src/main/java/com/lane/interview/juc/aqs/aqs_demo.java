package com.lane.interview.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author duenpu
 * @date 2023/8/9 14:39
 */
public class aqs_demo {

    private static void increase() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println("do something");
        } finally {
            lock.unlock();
        }
    }

//    public static void main(String[] args) {
//        ReentrantLock lock = new ReentrantLock();
//        new Thread(() -> {
//            lock.lock();
//            try {
//                System.out.println(Thread.currentThread().getId() + " 1get lock ");
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println(Thread.currentThread().getId() + " 1release lock ");
//                lock.unlock();
//            }
//        }).start();
//        new Thread(() -> {
//            lock.lock();
//            try {
//                System.out.println(Thread.currentThread().getId() + " 2get lock ");
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                System.out.println(Thread.currentThread().getId() + " 2release lock ");
//                lock.unlock();
//            }
//        }).start();
//    }

    private static Integer j = 0;

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Runnable runnable = () -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getId() + " get lock ");
                for (int i = 0; i < 10; i++) {
                    j++;
                }
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getId() + " release lock ");
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println(Thread.currentThread().getId() + " main ");
        System.out.println(j);
    }

}
/*
*  基于 AQS 来实现一个非可重入的互斥锁
* */
class Mutex  {

    private Sync sync = new Sync();

    public void lock () {
        sync.acquire(1);
    }

    public void unlock () {
        sync.release(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire (int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease (int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively () {
            return getState() == 1;
        }
    }
}
