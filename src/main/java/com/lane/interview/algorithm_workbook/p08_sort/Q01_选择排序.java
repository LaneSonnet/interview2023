package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * 选择排序
 * <p>
 * 时间复杂度O(N^2)
 * 额外空间复杂度O(1)
 * 无稳定性
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q01_选择排序 {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                minIndex = nums[j] < nums[minIndex] ? j : minIndex;
            }
            SwapUtil.swap(nums, minIndex, i);
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
