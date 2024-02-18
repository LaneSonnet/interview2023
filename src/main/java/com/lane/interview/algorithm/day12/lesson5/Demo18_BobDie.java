package com.lane.interview.algorithm.day12.lesson5;


/*
* 给定5个参数，N，M，row，col，k
  表示在N*M的区域上，醉汉Bob初始在(row,col)位置
  Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
  任何时候Bob只要离开N*M的区域，就直接死亡
  返回k步之后，Bob还在N*M的区域的概率
*
*
* 类似题目：
* https://leetcode.cn/problems/out-of-boundary-paths/
* https://leetcode.cn/problems/knight-probability-in-chessboard/description/
* */
public class Demo18_BobDie {

	public static double livePosibility1(int row, int col, int k, int N, int M) {
		// 总情况数：Math.pow(4, k) 每一步都有四个方向，一共k步，所以是4的k次方
		// 总生存点数除以总情况数，就是最后的概率
		return (double) process(row, col, k, N, M) / Math.pow(4, k);
	}

	// 目前在row，col位置，还有rest步要走，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
	public static long process(int row, int col, int rest, int N, int M) {
		if (row < 0 || row == N || col < 0 || col == M) {
			return 0;
		}
		// 还在棋盘中！
		if (rest == 0) {
			return 1;
		}
		// 还在棋盘中！还有步数要走
		long up = process(row - 1, col, rest - 1, N, M);
		long down = process(row + 1, col, rest - 1, N, M);
		long left = process(row, col - 1, rest - 1, N, M);
		long right = process(row, col + 1, rest - 1, N, M);
		return up + down + left + right;
	}

	public static double livePosibility2(int row, int col, int k, int N, int M) {
		long[][][] dp = new long[N][M][k + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dp[i][j][0] = 1;
			}
		}
		for (int rest = 1; rest <= k; rest++) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
					dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
					dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
					dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
				}
			}
		}
		dp[row][col][k]
		return (double) dp[row][col][k] / Math.pow(4, k);
	}

	public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
		if (r < 0 || r == N || c < 0 || c == M) {
			return 0;
		}
		return dp[r][c][rest];
	}

	public static void main(String[] args) {
		System.out.println(livePosibility1(0, 0, 2, 2, 2));
		System.out.println(livePosibility2(0, 0, 2, 2, 2));
	}

}
