package com.lane.interview.interview.juc.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @ Author:  duenpu
 * @ Date  :  17:23 2023/8/12
 */
public class MemoryLeakTest {
    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<User> threadLocal = new ThreadLocal<>();
        try {
            threadLocal.set(new User());
            threadLocal.get();
        } finally {
//            threadLocal.remove();
        }
        TimeUnit.MINUTES.sleep(5);
    }

    static class User {
        private byte[] datas = new byte[1024*1024*100];
    }

}
