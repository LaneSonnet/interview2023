package com.lane.interview.interview.algorithm;

import com.lane.interview.algorithm_workbook.p08_sort.SwapUtil;

/**
 * @ Author:  duenpu
 * @ Date  :  20:34 2024/2/25
 */
public class Q01_sortArray {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        process(nums, 0, nums.length - 1);
        return nums;
    }

    private static void process(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        SwapUtil.swap(nums, (int)(l + Math.random() * (r - l + 1)), r);
        int[] equalArea = netherlandFlag(nums, l, r);
        process(nums, l, equalArea[0] - 1);
        process(nums, equalArea[1] + 1, r);
    }

    private static int[] netherlandFlag(int[] nums, int l, int r) {
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (nums[index] == nums[r]) {
                index++;
            } else if (nums[index] < nums[r]) {
                SwapUtil.swap(nums, index++, ++less);
            } else {
                SwapUtil.swap(nums, index, --more);
            }
        }
        SwapUtil.swap(nums, r, more);
        return new int[]{less+1, more};
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, -1, -4, 19, 23, 2, 5, 74, -21};
        int[] ints = sortArray(arr);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
