package com.lane.interview.algorithm_workbook.p17_系统设计题;

import java.util.ArrayDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author:  duenpu
 * @ Date  :  11:46 2024/3/8
 */
public class Q03_手动实现BlockingQueue {
    public class MyBlockingQueue<T> {
        private int capacity;
        private ArrayDeque<T> queue;
        private ReentrantLock lock;
        private Condition full;
        private Condition empty;
        public MyBlockingQueue(int capacity, boolean fair) {
            this.capacity = capacity;
            queue = new ArrayDeque<>();
            lock = new ReentrantLock();
            full = lock.newCondition();
            empty = lock.newCondition();
        }
        /**
         * 阻塞放入
         */
        public void put(T t) {
            if (t == null) {
                throw new NullPointerException();
            }
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    full.await();
                }
                queue.add(t);
                empty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        /**
         * 阻塞式获取
         */
        public T poll() {
            T t = null;
            lock.lock();
            try {
                while (queue.size() == 0){
                    empty.await();
                }
                t = queue.poll();
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                return t;
            }
        }

    }

}
