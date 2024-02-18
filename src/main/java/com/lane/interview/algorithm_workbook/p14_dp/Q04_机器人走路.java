package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  20:36 2024/2/15
 */
public class Q04_机器人走路 {

    // 一维数组

    /**
     * 假设有排成一行的N个位置，记为1~N，N一定大于或等于2。
     * 开始时机器人在其中的M位置上(M一定是1~N中的一个)，机器人可以往左走或者往右走，
     * 如果机器人来到1位置， 那么下一步只能往右来到2位置；
     * 如果机器人来到N位置，那么下一步只能往左来到N-1位置。
     * 规定机器人必须走K步，最终能来到P位置(P也一定是1~N中的一个)的方法有多少种。给定四个参数N、M、K、P，返回方法数。
     */
    class Solution1 {
        // 暴力递归
        public int ways1(int N, int start, int aim, int K) {
            // 边界判断
            if (N < 2 || start > N || start < 1 || aim > N || aim < 1 || K < 1) {
                return -1;
            }
            return process1(start, aim, K, N);
        }

        private int process1(int index, int aim, int rest, int N) {
            if (rest == 0) {
                return index == aim ? 1 : 0;
            }
            if (index == 1) {
                return process1(2, aim, rest - 1, N);
            }
            if (index == N) {
                return process1(N - 1, aim, rest - 1, N);
            }
            return process1(index + 1, aim, rest - 1, N) + process1(index - 1, aim, rest - 1, N);
        }

        // 动态规划
        // 二维表
        // 纵坐标index范围1~N,但是还是把第0行补齐
        // 横坐标rest范围0-K
        // dp[index][rest]
        public int ways2(int N, int start, int aim, int K) {
            // 边界判断
            if (N < 2 || start > N || start < 1 || aim > N || aim < 1 || K < 1) {
                return -1;
            }
            int[][] dp = new int[N + 1][K + 1];
            dp[aim][0] = 1;// 第一列初始化完成
            // 从第二列开始，从左往右推
            for (int i = 1; i <= K; i++) {
                dp[0][i] = dp[2][i - 1];
                // 从第二行开始，从上往下推
                for (int j = 2; j < N; j++) {
                    dp[i][j] = dp[i + 1][j - 1] + dp[i - 1][j - 1];
                }
                dp[N][i] = dp[N - 1][i - 1];
            }
            return dp[start][K];
        }
    }

    // 二维数组
    // https://leetcode.cn/problems/unique-paths/description/
    class Solution2 {
        // 暴力递归
        public int uniquePaths1(int m, int n) {
            return process1(1, 1, m, n);
        }

        private int process1(int row, int col, int m, int n) {
            if (row > m || col > n) {
                return 0;
            }
            if (row == m && col == n) {
                return 1;
            }
            if (row == m) {
                return process1(row, col + 1, m, n);
            }
            if (col == n) {
                return process1(row + 1, col, m, n);
            }
            int right = process1(row, col + 1, m, n);
            int down = process1(row + 1, col, m, n);
            return right + down;
        }

        // 动态规划
        // dp[row][col]
        // row范围0~m
        // col范围0~n
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m + 1][n + 1];
            dp[m][n] = 1;
            for (int i = 0; i < n; i++) {
                dp[m][i] = 1;
            }
            for (int i = 0; i < m; i++) {
                dp[i][n] = 1;
            }
            // 每个格子依赖 右 和 下
            for (int i = m - 1; i >= 1; i--) {
                for (int j = n - 1; j >= 1; j--) {
                    dp[i][j] = dp[i][j + 1] + dp[i + 1][j];
                }
            }
            return dp[1][1];
        }
    }

    // 最小路径累加和
    // https://leetcode.cn/problems/minimum-path-sum/description/
    class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
                return 0;
            }
            int row = grid.length;
            int col = grid[0].length;
            int[][] dp = new int[row][col];
            dp[0][0] = grid[0][0];
            // 因为只能往右或者往下走，所以第一行的值都是可以算出来的
            for (int i = 1; i < row; i++) {
                dp[i][0] = dp[i - 1][0] + grid[i][0];
            }
            // 因为只能往右或者往下走，所以第一列的值都是可以算出来的
            for (int j = 1; j < col; j++) {
                dp[0][j] = dp[0][j - 1] + grid[0][j];
            }
            // 遍历其他位置
            for (int i = 1; i < row; i++) {
                for (int j = 1; j < col; j++) {
                    // 当前(i，j)这个格子  比较它的“上格子”和“左格子”，取小的，再加上(i，j)当前格子的值，就是dp[i][j]的值
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
            return dp[row - 1][col - 1];
        }
    }
}
