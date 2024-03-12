package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  21:16 2024/2/16
 */
public class Q06_背包问题 {
    /**
     * 背包问题
     * 问题描述：
     * 有一个背包，容量为bag，现在有n个物品，第i个物品的重量为w[i]，价值为v[i]，
     * 求将哪些物品装入背包可使价值总和最大。
     */
    // 暴力递归
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process(w, v, bag, 0);
    }

    private static int process(int[] w, int[] v, int rest, int index) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        // 不要当前货
        int p1 = process(w, v, rest, index + 1);
        // 要当前货
        int p2 = 0;
        int next = process(w, v, rest - w[index], index + 1);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    // 动态规划
    // dp[rest][index]
    // rest:0~bag
    // index:0~w.length
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int[][] dp = new int[bag + 1][w.length + 1];
        for (int row = 0; row <= bag; row++) {
            for (int col = w.length - 1; col >= 0; col--) {
                int p1 = dp[row][col + 1];
                int p2 = 0;
                // 注意这行，其实就是basecase移动到这里了
                int next = row - w[col] < 0 ? -1 : dp[row - w[col]][col + 1];
                if (next != -1) {
                    p2 = v[col] + next;
                }
                dp[row][col] = Math.max(p1, p2);
            }
        }
        return dp[bag][0];
    }


    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
