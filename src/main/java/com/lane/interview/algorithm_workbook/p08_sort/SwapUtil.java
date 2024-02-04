package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * @ Author:  duenpu
 * @ Date  :  23:41 2024/2/3
 */
public class SwapUtil {
    public static void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }
}
