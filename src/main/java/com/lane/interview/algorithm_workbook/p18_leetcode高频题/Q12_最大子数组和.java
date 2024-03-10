package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

/**
 * https://leetcode.cn/problems/maximum-subarray/description
 *
 * @ Author:  duenpu
 * @ Date  :  00:38 2024/2/26
 */
public class Q12_最大子数组和 {
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

    // 最大重复子串
    // https://leetcode-cn.com/problems/maximum-repeating-substring/
    public int maxRepeating(String sequence, String word) {
        int ans = 0;
        int n = sequence.length();
        int m = word.length();
        for (int i = 0; i + m <= n; i++) {
            int j = i;
            int k = 0;
            while (j < n && sequence.charAt(j) == word.charAt(k)) {
                j++;
                k++;
                if (k == m) {
                    ans++;
                    k = 0;
                }
            }
        }
        return ans;
    }

    //




































}
