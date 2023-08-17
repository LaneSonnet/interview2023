package com.lane.interview.algorithm.day2;

public class Demo8_GetMax {

	// 求arr中的最大值
	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	// arr[L..R]范围上求最大值  L ... R   N
	public static int process(int[] arr, int L, int R) {
		// arr[L..R]范围上只有一个数，直接返回，base case
		if (L == R) { 
			return arr[L];
		}
		// L...R 不只一个数
		// mid = (L + R) / 2
		int mid = L + ((R - L) >> 1); // 中点   	1
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax);
	}

	// 将process函数的递归行为，改成动态规划
	public static int dp1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int N = arr.length;
		int[] dp = new int[N];
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			dp[i] = arr[i];
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

}
