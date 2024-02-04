package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * 冒泡排序
 * <p>
 * 时间复杂度O(N^2)
 * 额外空间复杂度O(1)
 * 有稳定性
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q02_冒泡排序 {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j + 1] < nums[j]) {
                    SwapUtil.swap(nums, j, j + 1);
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, -1, -4, 19, 23, 2, 5, 74, -21};
        int[] ints = sortArray(arr);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
