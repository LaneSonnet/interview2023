package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author:  duenpu
 * @ Date  :  01:16 2024/2/22
 */
public class Q10_最长递增子序列_子串 {
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

    // 最长递增子串
    // 返回子串
    public String longestIncreasingSubstring(String S) {
        int len = S.length();
        int left = 1, right = len;
        // 哈希表用于存储子串及其出现次数
        Map<String, Integer> map = new HashMap<>();
        // 循环遍历子串长度
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean found = false;
            // 遍历字符串 S，找出长度为 mid 的子串
            for (int i = 0; i + mid <= len; i++) {
                String sub = S.substring(i, i + mid);
                // 如果哈希表中已经存在该子串，则找到了重复子串
                if (map.containsKey(sub)) {
                    found = true;
                    break;
                } else {
                    // 否则将子串放入哈希表中
                    map.put(sub, 1);
                }
            }
            // 根据当前子串长度是否找到重复子串，调整二分查找的范围
            if (found) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return "";
    }
}
