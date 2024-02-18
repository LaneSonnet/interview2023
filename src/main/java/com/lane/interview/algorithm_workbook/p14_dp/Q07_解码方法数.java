package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  21:53 2024/2/16
 */
public class Q07_解码方法数 {
    /**
     * 给定一个字符串str，str全部由数字字符组成，如果str中某一个或者相邻两个字符组成的子串值在1~26之间，
     * 则这个子串可以转换为一个字母。规定‘1’转换为“A”，“2”转换为“B”...“26”转换为“Z”。
     * 举个例子: “1111”可以转换为“AAAA”，“AKA”，“KAA”，“AOK”和“KK”五种
     * 请求出str有多少种不同的转换结果，并返回种数。
     */
    //https://leetcode.cn/problems/decode-ways/description/
    class Solution {
        // 暴力递归
        public int numDecodings1(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            return process(s.toCharArray(), 0);
        }

        private int process(char[] arr, int index) {
            if (index == arr.length) {
                return 1;
            }
            if (arr[index] == '0') {
                return 0;
            }
            // 如果要当前字母单转
            int ways = process(arr, index + 1);
            // 如果当前字母和下一个字母一起转
            if (index + 1 < arr.length && ((arr[index] - '0') * 10 + arr[index + 1] - '0') < 27) {
                ways += process(arr, index + 2);
            }
            return ways;
        }

        // 动态规划
        // dp[index]
        // index：0~arr.length
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            char[] charArray = s.toCharArray();
            int size = charArray.length;
            int[] dp = new int[size + 1];
            // basecase初始化
            dp[size] = 1;
            // 从右往左
            for (int i = size - 1; i >= 0; i--) {
                if (charArray[i] != '0') {
                    int ways = dp[i + 1];
                    if (i + 1 < size && ((charArray[i] - '0') * 10 + charArray[i + 1] - '0') < 27) {
                        ways += dp[i + 2];
                    }
                    dp[i] = ways;
                }
            }
            return dp[0];
        }
    }
}
