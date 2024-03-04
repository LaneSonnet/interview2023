package com.lane.interview.algorithm_workbook.p05_binarySearch;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * https://leetcode.cn/problems/minimum-size-subarray-sum
 *
 * @ Author:  duenpu
 * @ Date  :  23:35 2023/9/16
 */
public class Q07_总和大于等于target的最短子数组 {

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int left = 0;
            int length = nums.length;
            int result = Integer.MAX_VALUE;
            int num = 0;
            for (int i = 0; i < length; i++) {
                num += nums[i];
                while (num >= target) {
                    result = Math.min(result, i - left + 1);
                    num -= nums[left++];
                }
            }
            return result == Integer.MAX_VALUE ? 0 : result;

        }
    }
}
