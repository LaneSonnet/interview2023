package com.lane.interview.juc.单例;

/**
 * 单例——双重检查锁
 * @ Author:  duenpu
 * @ Date  :  20:58 2024/1/27
 */
public class Singleton {
    private static volatile Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
