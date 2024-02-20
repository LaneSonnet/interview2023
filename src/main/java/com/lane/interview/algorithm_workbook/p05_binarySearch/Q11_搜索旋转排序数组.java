package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 * @ Author:  duenpu
 * @ Date  :  22:12 2024/2/19
 */
public class Q11_搜索旋转排序数组 {
    // https://leetcode.cn/problems/search-in-rotated-sorted-array
    class Solution {
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            if (nums.length == 1) {
                return target == nums[0] ? 0 : -1;
            }
            int left = 0;
            int right = nums.length - 1;
            int mid = 0;
            while (left < right) {
                mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] >= nums[0]) {
                    if (target >= nums[0] && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (target > nums[mid] && target <= nums[nums.length - 1]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return nums[left] == target ? left : -1;
        }
    }
}
