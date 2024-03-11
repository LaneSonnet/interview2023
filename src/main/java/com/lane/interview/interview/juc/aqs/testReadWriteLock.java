package com.lane.interview.interview.juc.aqs;

import java.util.concurrent.Semaphore;

/**
 * @ Author:  duenpu
 * @ Date  :  23:57 2024/3/11
 */
public class testReadWriteLock {
    static class ReadWriteLock {
        private final Semaphore readLock = new Semaphore(1);
        private final Semaphore writeLock = new Semaphore(1);

        public void lockRead() throws InterruptedException {
            readLock.acquire();
        }

        public void unlockRead() {
            readLock.release();
        }

        public void lockWrite() throws InterruptedException {
            writeLock.acquire();
        }

        public void unlockWrite() {
            writeLock.release();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock lock = new ReadWriteLock();

        // 读线程
        Runnable readTask = () -> {
            try {
                lock.lockRead();
                System.out.println(Thread.currentThread().getName() + " acquired read lock");
                Thread.sleep(1000); // 模拟读操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead();
                System.out.println(Thread.currentThread().getName() + " released read lock");
            }
        };

        // 写线程
        Runnable writeTask = () -> {
            try {
                lock.lockWrite();
                System.out.println(Thread.currentThread().getName() + " acquired write lock");
                Thread.sleep(1000); // 模拟写操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockWrite();
                System.out.println(Thread.currentThread().getName() + " released write lock");
            }
        };

        // 创建并启动多个读线程和写线程
        for (int i = 0; i < 3; i++) {
            new Thread(readTask, "Reader-" + i).start();
            new Thread(writeTask, "Writer-" + i).start();
        }
    }
}
