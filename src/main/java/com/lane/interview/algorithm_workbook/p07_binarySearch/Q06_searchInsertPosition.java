package com.lane.interview.algorithm_workbook.p07_binarySearch;

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
public class Q06_searchInsertPosition {
    // 解法一：
    // 直接用原生方法
    public int searchInsert1(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        if (index < 0) {
            index = ~index;
        }
        if (index <= nums.length) {
            return index;
        }
        return nums.length;
    }

    // 解法二：
    // 手动实现
    public static int searchInsert2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return  -1;
        }
        // 特殊判断
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] < target){
                // 下一轮搜索的区间是 [mid + 1..right]
                left = mid + 1;
            } else {
                // 下一轮搜索的区间是 [left..mid]
                right = mid;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        int[] test = new int[4];
        test[0] = 1;
        test[1] = 3;
        test[2] = 5;
        test[3] = 6;
        searchInsert2(test, 5);
    }
}



