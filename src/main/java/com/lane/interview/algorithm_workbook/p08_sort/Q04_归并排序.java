package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * 归并排序
 * <p>
 * 时间复杂度O(N*logN)
 * 额外空间复杂度O(N)
 * 有稳定性
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q04_归并排序 {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        process(nums, 0, nums.length - 1);
        return nums;
    }

    private static void process(int[] nums, int l, int r) {
        if (l == r) {
            return;
        }
        int m = l + ((r - l) >> 1);
        process(nums, l, m);
        process(nums, m + 1, r);
        merge(nums, l, m, r);
    }

    private static void merge(int[] nums, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int index = 0;
        while (p1 <= m && p2 <= r) {
            help[index++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
        }
        while (p1 <= m) {
            help[index++] = nums[p1++];
        }
        while (p2 <= r) {
            help[index++] = nums[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            nums[l + i] = help[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, -1, -4, 19, 23, 2, 5, 74, -21};
        int[] ints = sortArray(arr);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
