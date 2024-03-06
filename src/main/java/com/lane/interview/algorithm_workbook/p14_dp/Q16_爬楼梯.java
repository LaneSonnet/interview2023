package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  17:44 2024/2/24
 */
public class Q16_爬楼梯 {
    // https://leetcode.cn/problems/climbing-stairs/
    class Solution {
        public int climbStairs(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            dp[1] = 1;
            for(int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }
    }
    // 最小花费
    public static int minCostClimbingStairs(int[] cost) {
        //1.dp含义： dp[i]：到达i位置 最小花费为dp[i]

        int[] dp = new int[cost.length + 1]; //3.初始化

        int min = 0; //记录i位置最小花费的索引
        for (int i = 2; i < dp.length; i++) { //4.遍历顺序
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]); //2.递推公式
            min = i;
        }
        System.out.println(dp[min]);
        return dp[min];
    }
}
