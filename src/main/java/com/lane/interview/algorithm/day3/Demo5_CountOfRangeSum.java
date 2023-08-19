package com.lane.interview.algorithm.day3;

// 这道题直接在leetcode测评：
// https://leetcode.com/problems/count-of-range-sum/
//给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
//区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
public class Demo5_CountOfRangeSum {
	/**
	 * 第一步思路
	 * 假设0-i的和为sum[i]，那么sum[i]-sum[j]就是j+1到i的和，如果sum[i]-sum[j]在lower和upper之间，那么j+1到i的和就是符合要求的
	 *
	 * 第二步思路
	 * 假设0-i整体累加和(0-0,0-1,0-2...0-i)是x，求必须以i位置结尾的子数组(0-i,1-i,2-i...（i-1)-i)，累加和在[low,up]区间内
	 * 这就等同于求i之前的所有子数组前缀和中，有多少个前缀和在[x-up,x-low]区间内
	 *
	 * 第三步思路
	 * 一开始的整数数组完全可以转化为sum[]前缀和数组，然后求前缀和数组中，有多少个前缀和在[x-up,x-low]区间内
	 */
	public static int countRangeSum(int[] nums, int lower, int upper) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		long[] sum = new long[nums.length];
		sum[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			sum[i] = sum[i - 1] + nums[i];
		}
		return process(sum, 0, sum.length - 1, lower, upper);
	}

	public static int process(long[] sum, int L, int R, int lower, int upper) {
		if (L == R) {
			return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
		}
		int M = L + ((R - L) >> 1);
		return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper)
				+ merge(sum, L, M, R, lower, upper);
	}

	public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
		int ans = 0;
		int windowL = L;
		int windowR = L;
		// [windowL, windowR)
		for (int i = M + 1; i <= R; i++) {
			long min = arr[i] - upper;
			long max = arr[i] - lower;
			while (windowR <= M && arr[windowR] <= max) {
				windowR++;
			}
			while (windowL <= M && arr[windowL] < min) {
				windowL++;
			}
			ans += windowR - windowL;
		}
		long[] help = new long[R - L + 1];
		int i = 0;
		int p1 = L;
		int p2 = M + 1;
		while (p1 <= M && p2 <= R) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[L + i] = help[i];
		}
		return ans;
	}

}
