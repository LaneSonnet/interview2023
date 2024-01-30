package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 * 有序数组中是否存在某个数
 *
 * https://leetcode.cn/problems/binary-search/description/
 *
 * @ Author:  duenpu
 * @ Date  :  23:31 2023/9/15
 */
public class Q01_exist {
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length -1 ;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }
}
