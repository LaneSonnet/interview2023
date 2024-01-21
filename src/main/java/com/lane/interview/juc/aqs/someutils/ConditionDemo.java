package com.lane.interview.juc.aqs.someutils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Author:  duenpu
 * @ Date  :  16:12 2024/1/21
 */
public class ConditionDemo {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("线程一加锁成功");
                System.out.println("线程一执行await被挂起");
                condition.await();
                System.out.println("线程一被唤醒成功");
                condition.signal();
                System.out.println("线程1唤醒线程3");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程一释放锁成功");
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("线程3加锁成功");
                System.out.println("线程3执行await被挂起");
                condition.await();
                System.out.println("线程3被唤醒成功");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("线程3释放锁成功");
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("线程二加锁成功");
                condition.signal();
                System.out.println("线程二唤醒线程一");
            } finally {
                lock.unlock();
                System.out.println("线程二释放锁成功");
            }
        }).start();
    }
}

class MultipleConditionsExample {
    private final Lock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();
    private final Queue<String> buffer = new LinkedList<>();
    private static final int BUFFER_SIZE = 10;

    public void produce(String item) throws InterruptedException {
        lock.lock();
        try {
            // 检查缓冲区是否已满
            while (buffer.size() == BUFFER_SIZE) {
                System.out.println("Buffer is full. Producer is waiting...");
                producerCondition.await(); // 等待消费者唤醒
            }

            // 生产物品并加入缓冲区
            buffer.offer(item);
            System.out.println("Produced: " + item);

            // 唤醒消费者线程
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public String consume() throws InterruptedException {
        lock.lock();
        try {
            // 检查缓冲区是否为空
            while (buffer.isEmpty()) {
                System.out.println("Buffer is empty. Consumer is waiting...");
                consumerCondition.await(); // 等待生产者唤醒
            }

            // 从缓冲区取出物品
            String item = buffer.poll();
            System.out.println("Consumed: " + item);

            // 唤醒生产者线程
            producerCondition.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MultipleConditionsExample example = new MultipleConditionsExample();

        // 生产者线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    example.produce("Item " + i);
                    Thread.sleep(100); // 模拟生产时间
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 消费者线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    example.consume();
                    Thread.sleep(150); // 模拟消费时间
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
