package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  23:19 2024/3/2
 */
public class Q10_回文子串数量 {
    // https://leetcode.cn/problems/palindromic-substrings/

    class Solution {
        public int countSubstrings(String s) {
            char[] chars = s.toCharArray();
            int len = chars.length;
            boolean[][] dp = new boolean[len][len];
            int result = 0;
            for (int i = len - 1; i >= 0; i--) {
                for (int j = i; j < len; j++) {
                    if (chars[i] == chars[j]) {
                        if (j - i <= 1) { // 情况一 和 情况二
                            result++;
                            dp[i][j] = true;
                        } else if (dp[i + 1][j - 1]) { //情况三
                            result++;
                            dp[i][j] = true;
                        }
                    }
                }
            }
            return result;
        }
    }
}
