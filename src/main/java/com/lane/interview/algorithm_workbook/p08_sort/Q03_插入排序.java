package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * 插入排序
 * <p>
 * 时间复杂度O(N^2)
 * 额外空间复杂度O(1)
 * 有稳定性
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q03_插入排序 {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        for (int i = 1; i <= nums.length - 1; i++) {
            for (int pre = i - 1; pre >= 0 && nums[pre + 1] < nums[pre]; pre--) {
                SwapUtil.swap(nums, pre, pre + 1);
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
