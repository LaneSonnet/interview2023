package com.lane.interview.interview.juc.thread;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ Author:  duenpu
 * @ Date  :  16:21 2024/2/25
 */
public class InterruptExample {
    public static void main(String[] args) {
        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("线程进入睡眠状态");
                Thread.sleep(5000); // 休眠5秒
            } catch (InterruptedException e) {
                System.out.println("线程被中断");
            }
        });

        sleepingThread.start();

        // 主线程休眠2秒后中断正在休眠的线程
        try {
            Thread.sleep(2000);
            sleepingThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

