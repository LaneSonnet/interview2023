package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.Arrays;

/**
 * @ Author:  duenpu
 * @ Date  :  20:04 2024/3/28
 */
public class Q17_编辑距离 {
    // https://leetcode.cn/problems/edit-distance/description/?company_slug=kuaishou

    // 暴力递归
    private char[] s, t;
    private int[][] memo;

    public int minDistance(String text1, String text2) {
        s = text1.toCharArray();
        t = text2.toCharArray();
        int n = s.length, m = t.length;
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1); // -1 表示还没有计算过
        }
        return dfs(n - 1, m - 1);
    }

    private int dfs(int i, int j) {
        if (i < 0) {
            return j + 1;
        }
        if (j < 0) {
            return i + 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s[i] == t[j]) {
            return memo[i][j] = dfs(i - 1, j - 1);
        }
        return memo[i][j] = Math.min(Math.min(dfs(i - 1, j), dfs(i, j - 1)), dfs(i - 1, j - 1)) + 1;
    }


    // 动态规划
    public int minDistance1(String text1, String text2) {
        char[] s = text1.toCharArray(), t = text2.toCharArray();
        int n = s.length, m = t.length;
        int[][] f = new int[n + 1][m + 1];
        for (int j = 1; j <= m; ++j) {
            f[0][j] = j;
        }
        for (int i = 0; i < n; ++i) {
            f[i + 1][0] = i + 1;
            for (int j = 0; j < m; ++j) {
                f[i + 1][j + 1] = s[i] == t[j] ? f[i][j] :
                        Math.min(Math.min(f[i][j + 1], f[i + 1][j]), f[i][j]) + 1;
            }
        }
        return f[n][m];
    }
}
