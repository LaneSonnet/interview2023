package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  20:14 2024/2/17
 */
public class Q10_最长回文子序列_子串 {
    /**
     * 最长回文子序列
     * 问题描述：
     * 给定一个字符串str，返回这个字符串的最长回文子序列长度。
     * 比如：str = “a12b3c43def2ghi1kpm”
     * 最长回文子序列是“1234321”或者“123c321”，返回长度7。
     */

    // https://leetcode.cn/problems/longest-palindromic-subsequence/description/

    class Solution {
        // 暴力递归
        public int longestPalindromeSubseq1(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] arr = s.toCharArray();
            return process(arr, 0, arr.length - 1);
        }

        private int process(char[] arr, int left, int right) {
            if (left == right) {
                return 1;
            }
            if (left + 1 == right) {
                return arr[left] == arr[right] ? 2 : 1;
            }
            int p1 = process(arr, left + 1, right);
            int p2 = process(arr, left, right - 1);
            int p3 = process(arr, left + 1, right - 1);
            int p4 = arr[left] == arr[right] ? (2 + process(arr, left + 1, right - 1)) : 0;
            return Math.max(Math.max(p1, p2), Math.max(p3, p4));
        }

        // 动态规划
        // dp[left][right]
        // left:0~arr.length - 1
        // right:0~arr.length - 1
        public int longestPalindromeSubseq(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] arr = s.toCharArray();
            int size = arr.length;
            int[][] dp = new int[size][size];
            // 初始化,注意row+1会越界
            dp[size - 1][size - 1] = 1;
            for (int row = 0; row < size - 1; row++) {
                dp[row][row] = 1;
                dp[row][row + 1] = arr[row] == arr[row + 1] ? 2 : 1;
            }
            for (int row = size - 3; row >= 0; row--) {
                for (int col = row + 2; col <= size - 1; col++) {
                    dp[row][col] = Math.max(dp[row][col - 1], dp[row + 1][col]);
                    if (arr[row] == arr[col]) {
                        dp[row][col] = Math.max(dp[row][col], 2 + dp[row + 1][col - 1]);
                    }
                }
            }
            return dp[0][size - 1];
        }
    }

    /**
     * 最长回文子串
     */

    // https://leetcode.cn/problems/longest-palindromic-substring
    // 看这个题解 https://leetcode.cn/problems/longest-palindromic-substring/solutions/7792/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/?company_slug=xiaohongshu
    class Solution1 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() == 0) {
                return s;
            }
            if (s.length() < 2) {
                return s;
            }
            char[] arr = s.toCharArray();
            int size = arr.length;
            Boolean[][] dp = new Boolean[size][size];
            // 两个游标来记录，开始位置和回文子串长度
            int begin = 0;
            int maxLen = 1;
            // 初始化dp表
            for (int i = 0; i < size; i++) {
                dp[i][i] = Boolean.TRUE;
            }
            for (int col = 1; col < size; col++) {
                for (int row = 0; row < col; row++) {
                    // 如果row位置和col位置字符不一样，直接false
                    if (arr[row] != arr[col]) {
                        dp[row][col] = Boolean.FALSE;
                    } else {
                        // row位置和col位置字符一样
                        // 如果一共就仨字母，一定回文
                        if (col - row + 1 < 4) {
                            dp[row][col] = Boolean.TRUE;
                        } else {
                            dp[row][col] = dp[row + 1][col - 1];
                        }
                    }
                    // 记录游标
                    if (dp[row][col] && col - row + 1 > maxLen) {
                        begin = row;
                        maxLen = col - row + 1;
                    }

                }
            }

            return s.substring(begin, begin + maxLen);
        }
    }
}
