package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author:  duenpu
 * @ Date  :  20:14 2024/2/17
 */
public class Q11_跳马问题 {

    /**
     * 马走日
     * 问题描述：
     * 中国象棋中的马走日，给定一个棋盘，棋盘的大小为10*9，棋盘的左下角为(0,0)位置，棋盘的右上角为(9,8)位置，
     * 给定三个参数x，y，k，返回马从(0,0)位置出发，必须走k步，最后落在(x,y)上的方法数有多少种？
     * <p>
     * 返回方法数
     */

    class Solution1 {
        // 暴力递归
        public int jump1(int a, int b, int k) {
            return process(0, 0, k, a, b);
        }

        private int process(int row, int col, int rest, int targetRow, int targetCol) {
            if (row < 0 || row > 9 || col < 0 || col > 8) {
                return 0;
            }
            if (rest == 0) {
                return (row == targetRow && col == targetCol) ? 1 : 0;
            }
            int ways = process(row - 2, col + 1, rest - 1, targetRow, targetCol);
            ways += process(row - 1, col + 2, rest - 1, targetRow, targetCol);
            ways += process(row + 1, col + 2, rest - 1, targetRow, targetCol);
            ways += process(row + 2, col + 1, rest - 1, targetRow, targetCol);
            ways += process(row - 2, col - 1, rest - 1, targetRow, targetCol);
            ways += process(row - 1, col - 2, rest - 1, targetRow, targetCol);
            ways += process(row + 1, col - 2, rest - 1, targetRow, targetCol);
            ways += process(row + 2, col - 1, rest - 1, targetRow, targetCol);
            return ways;
        }

        // 动态规划
        // dp[row][col][rest]
        // row：0~9
        // col：0~8
        // rest：0~k
        public int jump(int a, int b, int k) {
            int[][][] dp = new int[10][9][k + 1];
            dp[a][b][0] = 1;
            for (int rest = 1; rest <= k; rest++) {
                for (int row = 0; row <= 9; row++) {
                    for (int col = 0; col <= 8; col++) {
                        dp[row][col][rest] = pick(dp, row - 2, col + 1, rest - 1)
                                + pick(dp, row - 1, col + 2, rest - 1)
                                + pick(dp, row + 1, col + 2, rest - 1)
                                + pick(dp, row + 2, col + 1, rest - 1)
                                + pick(dp, row - 2, col - 1, rest - 1)
                                + pick(dp, row - 1, col - 2, rest - 1)
                                + pick(dp, row + 1, col - 2, rest - 1)
                                + pick(dp, row + 2, col - 1, rest - 1);
                    }
                }
            }

            return dp[a][b][k];
        }

        public int pick(int[][][] dp, int x, int y, int rest) {
            if (x < 0 || x > 9 || y < 0 || y > 8) {
                return 0;
            }
            return dp[x][y][rest];
        }
    }

    /*
     * 马走日
     *
     * -300 <= x, y <= 300
     *
     * 返回最小方法数
     * https://leetcode.cn/problems/minimum-knight-moves/description/
     *
     * https://leetcode.cn/problems/minimum-knight-moves/solutions/2428102/jin-ji-de-qi-shi-by-leetcode-solution-r1pn/
     * */

    class Solution2 {
        // 记忆化搜索，就是傻缓存法
        private Map<String, Integer> memo = new HashMap<>();

        // 绝对值是为了控制在第一象限，我们只讨论第一象限内的情况
        public int minKnightMoves(int x, int y) {
            return dfs(Math.abs(x), Math.abs(y));
        }

        private int dfs(int x, int y) {
            String key = x + "," + y;
            if (memo.containsKey(key)) {
                return memo.get(key);
            }
            // 我们将题目转化为，从(x,y)走到(0,0)需要的最少步数
            // 如果x + y == 0，说明(x,y)就是(0,0)
            if (x + y == 0) {
                return 0;
                // 如果x + y == 2，说明需要走一个之字形。比如从(1,1)走到(0,0)
            } else if (x + y == 2) {
                return 2;
            } else {
                // 我们不用讨论八个方向
                // 从(x,y)到(0,0)，只有(x - 1)，(y - 2)和(x - 2)，(y - 1)这两个方向在向原点靠近
                int ret = Math.min(dfs(Math.abs(x - 1), Math.abs(y - 2)),
                        dfs(Math.abs(x - 2), Math.abs(y - 1))) + 1;
                memo.put(key, ret);
                return ret;
            }
        }
    }

    /*
     * 出界问题——返回概率
     *
     * 给定5个参数，N，M，row，col，k
     * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
     * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
     * 任何时候Bob只要离开N*M的区域，就直接死亡
     * 返回k步之后，Bob还在N*M的区域的概率
     *
     * */

    class Solution3 {
        // 暴力递归
        public double livePosibility1(int row, int col, int k, int N, int M) {
            return (double) process(row, col, k, N, M) / Math.pow(4, k);
        }

        private long process(int row, int col, int rest, int N, int M) {
            if (row < 0 || row == N || col < 0 || col == M) {
                return 0;
            }
            if (rest == 0) {
                return 1;
            }
            long ways = process(row - 1, col, rest - 1, N, M);
            ways += process(row, col + 1, rest - 1, N, M);
            ways += process(row, col - 1, rest - 1, N, M);
            ways += process(row + 1, col, rest - 1, N, M);
            return ways;
        }

        // 动态规划
        // dp[row][col][rest]
        // row:0~N-1
        // col:0~M-1
        // rest:0~k
        public double livePosibility(int row, int col, int k, int N, int M) {
            long[][][] dp = new long[N][M][k + 1];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dp[i][j][0] = 1;
                }
            }
            for (int q = 1; q <= k; q++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        dp[i][j][q] = pick(dp, i - 1, j, N, M, q - 1)
                                + pick(dp, i, j - 1, N, M, q - 1)
                                + pick(dp, i, j + 1, N, M, q - 1)
                                + pick(dp, i + 1, j, N, M, q - 1);
                    }
                }
            }

            return (double) dp[row][col][k] / Math.pow(4, k);
        }

        private long pick(long[][][] dp, int row, int col, int N, int M, int rest) {
            if (row < 0 || row == N || col < 0 || col == M) {
                return 0;
            }
            return dp[row][col][rest];
        }
    }

    /*
     * 出界问题——返回路径数
     *
     * https://leetcode.cn/problems/out-of-boundary-paths/
     *
     * */
    class Solution4 {
        // 暴力递归
        public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
            long paths = process(m, n, maxMove, startRow, startColumn);
            return (int) (paths % (Math.pow(10, 9) + 7));
        }

        private long process(int m, int n, int rest, int row, int col) {
            if (row < 0 || row >= m || col < 0 || col >= n) {
                return 0;
            }
            if (rest == 0) {
                return 1;
            }
            long ways = process(m, n, rest - 1, row, col + 1);
            ways += process(m, n, rest - 1, row, col - 1);
            ways += process(m, n, rest - 1, row + 1, col);
            ways += process(m, n, rest - 1, row - 1, col);
            return ways;
        }

        // 动态规划
        public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            int mod = 1000000007;
            long[][][] dp = new long[m][n][maxMove + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][0] = 1;
                }
            }
            for (int move = 1; move <= maxMove; move++) {
                for (int row = 0; row < m; row++) {
                    for (int col = 0; col < n; col++) {
                        long left = col == 0 ? 1 : dp[row][col - 1][move - 1];
                        long right = col == n - 1 ? 1 : dp[row][col + 1][move - 1];
                        long up = row == 0 ? 1 : dp[row - 1][col][move - 1];
                        long down = row == m - 1 ? 1 : dp[row + 1][col][move - 1];
                        dp[row][col][move] = (left + right + up + down) % mod;
                    }
                }
            }
            return (int) dp[startRow][startColumn][maxMove];
        }
    }

    /*
     * 马出界问题
     *
     * https://leetcode.cn/problems/knight-probability-in-chessboard/description/
     *
     * */

    class Solution5 {
        public double knightProbability(int n, int k, int row, int column) {
            long[][][] dp = new long[n][n][k + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j][0] = 1;
                }
            }
            for (int q = 1; q <= k; q++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        dp[i][j][q] = pick(dp, i - 1, j - 2, n, n, q - 1)
                                + pick(dp, i + 1, j - 2, n, n, q - 1)
                                + pick(dp, i - 2, j - 1, n, n, q - 1)
                                + pick(dp, i + 2, j - 1, n, n, q - 1)
                                + pick(dp, i + 1, j - 2, n, n, q - 1)
                                + pick(dp, i + 1, j + 2, n, n, q - 1)
                                + pick(dp, i - 1, j + 2, n, n, q - 1)
                                + pick(dp, i + 1, j + 2, n, n, q - 1);
                    }
                }
            }

            return (double) dp[row][column][k] / Math.pow(8, k);
        }

        private long pick(long[][][] dp, int row, int col, int N, int M, int rest) {
            if (row < 0 || row == N || col < 0 || col == M) {
                return 0;
            }
            return dp[row][col][rest];
        }
    }
}
