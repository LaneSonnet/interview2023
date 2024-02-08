package com.lane.interview.interview.juc.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author duenpu
 * @date 2023/8/10 15:50
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {

        }
    }
}
