package com.lane.interview.algorithm.primary.day1;

import java.util.Arrays;

/**
 * 二分法
 *
 * @ Author:  duenpu
 * @ Date  :  23:42 2023/8/12
 */
public class Demo4_Dichotomy {
    // 有序数组中是否存在num
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return Boolean.FALSE;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] == num) {
                return Boolean.TRUE;
            } else if (sortedArr[mid] > num ) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return sortedArr[left] == num;
    }


    public static void main(String[] args) {
        //检测exist方法是否正确
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = Boolean.TRUE;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (exist(arr, value) != exist(arr, value)) {
                System.out.println("出错了！");
                succeed = Boolean.FALSE;
                break;
            }
        }
    }

    // 生成随机样本数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i : arr) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue));
        }
        return arr;
    }
}
