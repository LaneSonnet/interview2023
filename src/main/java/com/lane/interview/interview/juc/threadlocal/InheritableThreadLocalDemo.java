package com.lane.interview.interview.juc.threadlocal;

/**
 * @ Author:  duenpu
 * @ Date  :  16:57 2023/8/12
 */
public class InheritableThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        threadLocal.set("父类数据：I'm your father-ThreadLocal");
        inheritableThreadLocal.set("父类数据：I'm your father-inheritableThreadLocal");

        new Thread(() -> {
            System.out.println("子线程获取父线程ThreadLocal数据：" + threadLocal.get());
            System.out.println("子线程获取父线程inheritableThreadLocal数据：" + inheritableThreadLocal.get());
        }).start();
    }
}
