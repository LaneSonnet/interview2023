package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  22:54 2024/2/16
 */
public class Q09_最长公共子序列_子串 {

    // 最长公共子序列
    // https://leetcode.cn/problems/longest-common-subsequence/description/
    // 返回长度
    class Solution {
        // 暴力递归
        public int longestCommonSubsequence1(String text1, String text2) {
            if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
                return 0;
            }
            char[] arr1 = text1.toCharArray();
            char[] arr2 = text2.toCharArray();
            return process(arr1, arr2, arr1.length - 1, arr2.length - 1);
        }

        private int process(char[] arr1, char[] arr2, int i, int j) {
            if (i == j && i == 0) {
                return arr1[i] == arr2[j] ? 1 : 0;
            } else if (i == 0) {
                if (arr1[i] == arr2[j]) {
                    return 1;
                } else {
                    return process(arr1, arr2, i, j - 1);
                }
            } else if (j == 0) {
                if (arr1[i] == arr2[j]) {
                    return 1;
                } else {
                    return process(arr1, arr2, i - 1, j);
                }
            } else {
                int p1 = process(arr1, arr2, i, j - 1);
                int p2 = process(arr1, arr2, i - 1, j);
                int p3 = arr1[i] == arr2[j] ? (1 + process(arr1, arr2, i - 1, j - 1)) : 0;
                return Math.max(p1, Math.max(p2, p3));
            }
        }

        // 动态规划
        // dp[i][j]
        // i:0~arr1.length - 1
        // j:0~arr2.length - 1
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
                return 0;
            }
            char[] arr1 = text1.toCharArray();
            char[] arr2 = text2.toCharArray();
            int size1 = arr1.length;
            int size2 = arr2.length;
            int[][] dp = new int[size1][size2];
            // 初始化
            dp[0][0] = arr1[0] == arr2[0] ? 1 : 0;
            for (int col = 1; col < size2; col++) {
                dp[0][col] = arr1[0] == arr2[col] ? 1 : dp[0][col - 1];
            }
            for (int row = 1; row < size1; row++) {
                dp[row][0] = arr1[row] == arr2[0] ? 1 : dp[row - 1][0];
            }
            for (int row = 1;row < size1; row++ ) {
                for (int col = 1; col < size2; col++) {
                    int p1 = dp[row][col - 1];
                    int p2 = dp[row - 1][col];
                    int p3 = arr1[row] == arr2[col] ? (1 + dp[row - 1][col - 1]) : 0;
                    dp[row][col] = Math.max(p1, Math.max(p2, p3));
                }
            }
            return dp[size1 - 1][size2 - 1];
        }
    }

    // 最长公共子串

    public String longestCommonSubstring(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) {
            return "";
        }

        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        int endIndex = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i - 1;
                    }
                }
            }
        }

        if (maxLength == 0) {
            return "";
        }

        return text1.substring(endIndex - maxLength + 1, endIndex + 1);
    }

}
