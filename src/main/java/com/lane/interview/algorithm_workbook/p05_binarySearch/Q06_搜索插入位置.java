package com.lane.interview.algorithm_workbook.p05_binarySearch;

import java.util.Arrays;

/**
 * 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 请必须使用时间复杂度为 O(log n) 的算法。
 * https://leetcode.cn/problems/search-insert-position
 *
 *
 * @ Author:  duenpu
 * @ Date  :  00:19 2023/9/17
 */
public class Q06_搜索插入位置 {
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        int index = -1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] >= target ? left : index;
    }


    public static void main(String[] args) {
        int[] test = new int[4];
        test[0] = 1;
        test[1] = 3;
        test[2] = 5;
        test[3] = 6;
        searchInsert(test, 5);
    }
}



