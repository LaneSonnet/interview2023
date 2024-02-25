package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  00:38 2024/2/26
 */
public class Q17_最大子数组和 {
    // https://leetcode.cn/problems/maximum-subarray/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution {
        public int maxSubArray(int[] nums) {
            int ans = Integer.MIN_VALUE;
            int minPreSum = 0;
            int preSum = 0;
            for (int x : nums) {
                preSum += x; // 当前的前缀和
                ans = Math.max(ans, preSum - minPreSum); // 减去前缀和的最小值
                minPreSum = Math.min(minPreSum, preSum); // 维护前缀和的最小值
            }
            return ans;
        }
    }
}
