package com.lane.interview.algorithm_workbook.p08_sort;

/**
 *
 * 希尔排序
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q09_希尔排序 {
    public static int[] sortArray(int[] nums) {
        shellSort(nums);
        return nums;
    }

    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int[] step = { 5, 2, 1 };
        for (int s = 0; s < step.length; s++) {
            for (int i = step[s]; i < arr.length; i++) {
                for (int j = i - step[s]; j >= 0 && arr[j] > arr[j + step[s]]; j -= step[s]) {
                    SwapUtil.swap(arr, j, j + step[s]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 1, -4, 19, 23, 2, 5, 74, 21};
        int[] ints = sortArray(arr);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
