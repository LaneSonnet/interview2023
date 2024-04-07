package com.lane.interview.algorithm_workbook.p17_系统设计题;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author duenpu
 * @date 2024/4/7 14:26
 */
public class Q05_滑动窗口限流 {
    // https://zhuanlan.zhihu.com/p/689922080
    /**
     * 时间滑动窗口算法
     * 限流算法
     */

    /**
     * 限流上线次数
     */
    private Integer limitMaxNum;

    /**
     * 滑动窗口格子数 根据时间来，60秒分成60格
     */
    private Integer windowNum;

    /**
     * 上次统计时间，单位秒
     */
    private ConcurrentHashMap<String, AtomicLong> lastTimeMap;

    /**
     * 记录key值与时间窗口映射
     */
    private ConcurrentHashMap<String, AtomicIntegerArray> timeWindowsMap;

    /**
     * 记录key值与窗口内请求总数映射
     */
    private ConcurrentHashMap<String, AtomicInteger> timeCountMap;

    public Q05_滑动窗口限流(int limitMaxNum, int windowNum) {
        this.timeWindowsMap = new ConcurrentHashMap<>();
        this.timeCountMap = new ConcurrentHashMap<>();
        this.windowNum = windowNum;
        this.limitMaxNum = limitMaxNum;
        this.lastTimeMap = new ConcurrentHashMap<>();
    }

    /**
     * 限流方法入口
     *
     * @param key
     * @return
     */
    public Boolean limit(String key) {
        // 获取当前窗口
        AtomicIntegerArray windows = this.timeWindowsMap.computeIfAbsent(key,
                k -> new AtomicIntegerArray(this.windowNum));
        // 获取当前窗口请求总和
        AtomicInteger count = timeCountMap.computeIfAbsent(key, k -> new AtomicInteger(0));
        AtomicLong lastTime = lastTimeMap.computeIfAbsent(key, k -> new AtomicLong(System.currentTimeMillis() / 1000));

        // 计算当前时间所处格子
        Long now = System.currentTimeMillis() / 1000;
        int temp = (int) (now % this.windowNum);
        // 计算当前时间与上次请求时间差，用于刷新窗口
        Long diffTime = now - lastTime.get();
        // 将锁的粒度缩小单个value节点
        synchronized (windows) {
            if (diffTime >= 0 && now.equals(lastTime.get())) {
            /*
            （1）当前时间所属格子与上次请求记录在同一个格子中
            该情况，只需要继续沿用上次请求创建的格子，格子中请求数++
             */
                windows.getAndAdd(temp, 1);
                count.addAndGet(1);
            } else if (diffTime >= 0 && diffTime < windowNum) {
            /*
            （2）当前时间和上次请求记录时间在同一个周期中，环形数组的同一个周期中，没有超过一个周期。
            该情况意味着，从当前时间now计算时间窗口内请求数，只需要保留并计算 (now -> last) 之间的格子；
            而从(last -> now) 之间的格子已经淘汰，需要将其重置为0；
            并且将now 当前的格子数置为1，重新开始计数当前格子。
             */
                count = clearExpireWindows(windows, (int) (lastTime.get() % this.windowNum), temp, count);
                windows.set(temp, 1);
                count.addAndGet(1);
            } else if (diffTime >= 0 && diffTime >= this.windowNum) {
            /*
            （3）当前时间和上次请求记录时间不在同一个周期中，已经超过了环形数组中的一圈。
            意味着，之前统计的
             */
                windows = new AtomicIntegerArray(this.windowNum);
                windows.set(temp, 1);
                count.set(1);
            } else {
            /*
            （4）异常情况，时钟回拨，可能是服务器时间被人修改，往前修改了，导致时间出错，需要重新统计
             */
                System.out.println("时钟回拨，时间异常，重新开启限流统计");
                this.timeWindowsMap = new ConcurrentHashMap<>();
                this.timeCountMap = new ConcurrentHashMap<>();
                return true;
            }

            lastTime.set(now);

            // 如果限流了，这次计数需要回退
            if (count.get() > this.limitMaxNum) {
                windows.getAndAdd(temp, -1);
                count.addAndGet(-1);
                return false;
            }
        }

        return true;
    }

    /**
     * 清除过期数据
     *
     * @param windows 需要清除的窗口
     * @param from    开始位置
     * @param to      结束位置
     * @param count   当前周期计算总和
     * @return
     */
    private AtomicInteger clearExpireWindows(AtomicIntegerArray windows, int from, int to, AtomicInteger count) {
        if (to == from) {
            count.addAndGet(1);
            return count;
        }
        // 调整下标值，若结束位置小于开始位置，则说明当前格子位于下一个周期中。
        if (to < from) {
            to = this.windowNum + to;
        }
        while (++from <= to) {
            int window = windows.get(from % this.windowNum);
            count.addAndGet(-window);
            windows.set(from % this.windowNum, 0);
        }
        return count;
    }

    public static void main(String[] args) {
        Q05_滑动窗口限流 timeSlidingWindow = new Q05_滑动窗口限流(5, 10);

        new Thread(() -> {
            int i = 0;
            while (i < 600) {
                Boolean limit = timeSlidingWindow.limit("/hello");
                System.out.println("/hello" + i + ":" + limit + ", 时间:" + (i * 300.0) / 1000.0);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }).start();

        new Thread(() -> {
            int i = 0;
            while (i < 600) {
                Boolean limit1 = timeSlidingWindow.limit("/world");
                System.out.println("/world" + i + ":" + limit1 + ", 时间:" + (i * 500.0) / 1000.0);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }).start();
    }

}
