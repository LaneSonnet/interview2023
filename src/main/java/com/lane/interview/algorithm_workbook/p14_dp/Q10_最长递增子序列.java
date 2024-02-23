package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.Arrays;

/**
 * @ Author:  duenpu
 * @ Date  :  01:16 2024/2/22
 */
public class Q10_最长递增子序列 {
    // https://leetcode.cn/problems/longest-increasing-subsequence
    class Solution {
        public int lengthOfLIS(int[] nums) {
            if(nums.length == 0) {
                return 0;
            }
            int[] dp = new int[nums.length];
            int res = 0;
            Arrays.fill(dp, 1);
            for(int i = 0; i < nums.length; i++) {
                for(int j = 0; j < i; j++) {
                    if(nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }
}
