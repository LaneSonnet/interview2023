package com.lane.interview.interview.juc.aqs.someutils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author duenpu
 * @date 2023/8/15 15:07
 */
public class MutiThreadPrintABC {
    private static final Lock lock = new ReentrantLock();
    // 三个条件变量
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static final Condition conditionC = lock.newCondition();
    // 当前应当打印的字母
    private static char currentLetter = 'A';

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> print('A', conditionA, conditionB));
        Thread threadB = new Thread(() -> print('B', conditionB, conditionC));
        Thread threadC = new Thread(() -> print('C', conditionC, conditionA));

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static void print(char letter, Condition currentCondition, Condition nextCondition) {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (currentLetter != letter) {
                    currentCondition.await();
                }
                System.out.print(letter);
                currentLetter = nextLetter(letter); // 计算下一个应当打印的字母
                nextCondition.signal(); // 唤醒下一个条件的线程
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    private static char nextLetter(char letter) {
        switch (letter) {
            case 'A':
                return 'B';
            case 'B':
                return 'C';
            default:
                return 'A';
        }
    }

}
