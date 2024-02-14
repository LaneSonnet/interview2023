package com.lane.interview.algorithm.day12.lesson3;

/**
 * 背包问题
 * 问题描述：
 * 有一个背包，容量为bag，现在有n个物品，第i个物品的重量为w[i]，价值为v[i]，
 * 求将哪些物品装入背包可使价值总和最大。
 */
public class Demo07_Knapsack {

	// 所有的货，重量和价值，都在w和v数组里
	// 为了方便，其中没有负数
	// bag背包容量，不能超过这个载重
	// 返回：不超重的情况下，能够得到的最大价值

	//从左往右尝试模型

	/**
	 * 暴力递归
	 */
	public static int maxValue(int[] w, int[] v, int bag) {
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		// 尝试函数！
		return process(w, v, 0, bag);
	}

	// index 范围是 0~N
	// rest  范围是 负~bag
	// 当前考虑到了index位置的货物，index后续货物，都可以自由选择
	// rest是背包的剩余空间
	// 返回：不超重的情况下，能够得到的最大价值
	public static int process(int[] w, int[] v, int index, int rest) {
		// 剩余空间为负数，没法装了，返回-1
		if (rest < 0) {
			return -1;
		}
		// 当前index已经越界了，没货了，返回0
		if (index == w.length) {
			return 0;
		}
		// 情况1：不要当前index的货物，所以直接跳到下一个货物，rest不变
		int p1 = process(w, v, index + 1, rest);
		// 情况2：要当前index的货物，所以直接跳到下一个货物，rest减去当前index的重量
		int p2 = 0;
		// 先判断一下，如果rest减去当前index的重量后，是不是负数，如果是负数，说明装不下了，p2就不要算了
		int next = process(w, v, index + 1, rest - w[index]);
		// 如果不是负数，说明可以装下，那么p2就是 当前index的价值 + 后续货物的最大价值
		if (next != -1) {
			p2 = v[index] + next;
		}
		// 情况1和情况2，哪个价值大，就返回哪个
		return Math.max(p1, p2);
	}

	/**
	 * 动态规划
	 */
	public static int dp(int[] w, int[] v, int bag) {
		// 边界条件
		if (w == null || v == null || w.length != v.length || w.length == 0) {
			return 0;
		}
		int N = w.length;
		// dp[i][j]的含义是：对于前i个货物，当前背包的容量是j，这种情况下能够得到的最大价值是多少？
		// 纵坐标是index，横坐标是rest
		// index范围：0~N，rest范围：负数~bag(负数不考虑)
		// 所以dp的大小是(N+1)*(bag+1)
		// 根据下面代码，可以得出dp表最后一行都是0
		/*if (index == w.length) {
			return 0;
		}*/
		// 根据暴力递归中的依赖关系分析，p1和p2都是依赖下一行的值，所以从下往上计算
		// 最后一行都是0，所以从下往上计算，最后一行不用计算
		int[][] dp = new int[N + 1][bag + 1];
		// 纵坐标从下往上遍历
		for (int index = N - 1; index >= 0; index--) {
			// 横坐标从左往右遍历
			for (int rest = 0; rest <= bag; rest++) {
				/**
				 * 改写代码
				 *      // 情况1：不要当前index的货物，所以直接跳到下一个货物，rest不变
				 * 		int p1 = process(w, v, index + 1, rest);
				 * 		// 情况2：要当前index的货物，所以直接跳到下一个货物，rest减去当前index的重量
				 * 		int p2 = 0;
				 * 		// 先判断一下，如果rest减去当前index的重量后，是不是负数，如果是负数，说明装不下了，p2就不要算了
				 * 		int next = process(w, v, index + 1, rest - w[index]);
				 * 		// 如果不是负数，说明可以装下，那么p2就是 当前index的价值 + 后续货物的最大价值
				 * 		if (next != -1) {
				 * 	    p2 = v[index] + next;
				 *      }
				 */
				int p1 = dp[index + 1][rest];
				int p2 = 0;
				// 先判断一下，如果rest减去当前index的重量后，是不是负数，如果是负数，说明装不下了，p2就不要算了
				int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
				if (next != -1) {
					p2 = v[index] + next;
				}
				dp[index][rest] = Math.max(p1, p2);
			}
		}
		// 最后返回的是dp[0][bag]，index是0(一个货没拿)，背包容量是bag的情况下，能够得到的最大价值
		return dp[0][bag];
	}

	public static void main(String[] args) {
		int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
		int[] values = { 5, 6, 3, 19, 12, 4, 2 };
		int bag = 15;
		System.out.println(maxValue(weights, values, bag));
		System.out.println(dp(weights, values, bag));
	}

}
