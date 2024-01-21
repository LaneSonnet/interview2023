package com.lane.interview.algorithm.day12;

// 测试链接：https://leetcode.com/problems/longest-palindromic-subsequence/

/**
 * 最长回文子序列
 * 问题描述：
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度。
 * 比如：str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7。
 */
public class Demo11_PalindromeSubsequence {

	/**
	 * 暴力递归
	 */
	public static int lpsl1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		return f(str, 0, str.length - 1);
	}

	// str[L..R]最长回文子序列长度返回
	public static int f(char[] str, int L, int R) {
		// base case
		if (L == R) {
			return 1;
		}
		if (L == R - 1) {
			return str[L] == str[R] ? 2 : 1;
		}
		// 情况1：str[L..R]最长回文子序列，不以str[L]开头，也不以str[R]结尾
		int p1 = f(str, L + 1, R - 1);
		// 情况2：str[L..R]最长回文子序列，以str[L]开头，不以str[R]结尾
		int p2 = f(str, L, R - 1);
		// 情况3：str[L..R]最长回文子序列，不以str[L]开头，以str[R]结尾
		int p3 = f(str, L + 1, R);
		// 情况4：str[L..R]最长回文子序列，以str[L]开头，也以str[R]结尾，str[L] == str[R]
		int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));
		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
	}

	/**
	 * 动态规划
	 */
	public static int lpsl2(String s) {
		// 边界
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		// 初始化dp表
		// 横坐标是R范围是0~N-1，纵坐标是L范围是0~N-1
		// 左下半部分没用，所以不用填
		int[][] dp = new int[N][N];
		// 对应上面的base case
		// 先把右下角的位置填好
		dp[N - 1][N - 1] = 1;
		// 再把对角线的每个格子以及它右边的格子填好
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
		}
		// L从下往上遍历，R从左往右遍历
		for (int L = N - 3; L >= 0; L--) {
			for (int R = L + 2; R < N; R++) {
				/**
				 * 改写代码
				 *      // 情况1：依赖左下角的格子
				 * 		int p1 = f(str, L + 1, R - 1);
				 * 		// 情况2：依赖左边的格子
				 * 		int p2 = f(str, L, R - 1);
				 * 		// 情况3：依赖下边的格子
				 * 		int p3 = f(str, L + 1, R);
				 * 		// 情况4：如果str[L] == str[R]，那么依赖左下角的格子
				 * 		int p4 = str[L] != str[R] ? 0 : (2 + f(str, L + 1, R - 1));
				 * 		return Math.max(Math.max(p1, p2), Math.max(p3, p4));
				 */
				// 不用分四种情况讨论，因为每个格子依赖它的 左 & 下 & 左下 （如果str[L] ++ str[R] 依赖左下+2）
				// 可以先取左和下的最大值，再看它和左下+2的最大值
				// 左下不用看了，因为左下的值已经在上面的循环中计算过了
				// 这里其实就是一个小优化，简洁了代码逻辑
				dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
				if (str[L] == str[R]) {
					dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
				}
			}
		}
		return dp[0][N - 1];
	}







	public static int longestPalindromeSubseq1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() == 1) {
			return 1;
		}
		char[] str = s.toCharArray();
		char[] reverse = reverse(str);
		return longestCommonSubsequence(str, reverse);
	}

	public static char[] reverse(char[] str) {
		int N = str.length;
		char[] reverse = new char[str.length];
		for (int i = 0; i < str.length; i++) {
			reverse[--N] = str[i];
		}
		return reverse;
	}

	public static int longestCommonSubsequence(char[] str1, char[] str2) {
		int N = str1.length;
		int M = str2.length;
		int[][] dp = new int[N][M];
		dp[0][0] = str1[0] == str2[0] ? 1 : 0;
		for (int i = 1; i < N; i++) {
			dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
		}
		for (int j = 1; j < M; j++) {
			dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (str1[i] == str2[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
				}
			}
		}
		return dp[N - 1][M - 1];
	}

	public static int longestPalindromeSubseq2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		if (s.length() == 1) {
			return 1;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		int[][] dp = new int[N][N];
		dp[N - 1][N - 1] = 1;
		for (int i = 0; i < N - 1; i++) {
			dp[i][i] = 1;
			dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
		}
		for (int i = N - 3; i >= 0; i--) {
			for (int j = i + 2; j < N; j++) {
				dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
				if (str[i] == str[j]) {
					dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
				}
			}
		}
		return dp[0][N - 1];
	}

}
