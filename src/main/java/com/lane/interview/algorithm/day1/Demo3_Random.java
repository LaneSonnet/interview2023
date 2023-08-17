package com.lane.interview.algorithm.day1;

/**
 * @ Author:  duenpu
 * @ Date  :  00:13 2023/8/11
 */
public class Demo3_Random {

    // 生成随机样本数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i : arr) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        return arr;
    }
}
