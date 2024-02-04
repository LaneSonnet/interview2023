package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * 计数排序
 *
 * 时间复杂度O(N)
 * 额外空间复杂度O(M)
 * 有稳定性
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q08_计数排序 {
    public static int[] sortArray(int[] nums) {
        countSort(nums);
        return nums;
    }

    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, 1, 4, 19, 23, 2, 5, 74, 21};
        int[] ints = sortArray(arr);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
