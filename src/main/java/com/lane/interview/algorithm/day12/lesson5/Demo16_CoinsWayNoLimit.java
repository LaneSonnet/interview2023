package com.lane.interview.algorithm.day12.lesson5;


/*
*arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 每个值都认为是一种面值，且认为张数是无限的。
 返回组成aim的方法数
 例如：arr = {1,2}，aim = 4
 方法如下：1+1+1+1、1+1+2、2+2
 一共就3种方法，所以返回3
*
*
* 从左往右的尝试模型
*
*
* 本题要点：
* 动态规划的题目两种解法
* 1.傻缓存法，也就是记忆化搜索
* 2.严格的dp表结构方法
*
* 当算每一个格子的时候，只依赖有限几个其他格子的值时，方法1和2都可以
* 当算每一个格子的时候，计算过程中有for循环了，方法1就不行了，只能用方法2
*
*
* */
public class Demo16_CoinsWayNoLimit {

	public static int coinsWay(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return process(arr, 0, aim);
	}

	// arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
	public static int process(int[] arr, int index, int rest) {
		// 为啥这里不判断rest < 0 ?
		// 因为下面的for循环，zhang * arr[index] <= rest，所以不会出现rest < 0的情况
		if (index == arr.length) { // 没钱了
			return rest == 0 ? 1 : 0;
		}
		int ways = 0;
		// index位置的钱，可以用任意张，那就从0张开始，一直遍历到超过rest越界结束
		for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
			// 从左往右的尝试模型
			ways += process(arr, index + 1, rest - (zhang * arr[index]));
		}
		return ways;
	}

	/*
	* index范围是0~arr.length
	* rest范围是0~aim
	*
	* */
	public static int dp1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		// 初始化最后一行
		dp[N][0] = 1;
		// 从最后一行开始，从下往上一行一行推
		for (int index = N - 1; index >= 0; index--) {
			// 从左往右推
			for (int rest = 0; rest <= aim; rest++) {
				int ways = 0;
				for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
					ways += dp[index + 1][rest - (zhang * arr[index])];
				}
				dp[index][rest] = ways;
			}
		}
		return dp[0][aim];
	}

	public static int dp2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		int N = arr.length;
		int[][] dp = new int[N + 1][aim + 1];
		dp[N][0] = 1;
		for (int index = N - 1; index >= 0; index--) {
			for (int rest = 0; rest <= aim; rest++) {
				/*
				* 优化点来了
				* 看dp1方法的for循环可以得知
				* 如果我要算dp[index][rest]的值，我需要知道dp[index + 1][rest - (0 * arr[index])]的值，dp[index + 1][rest - (1 * arr[index])]的值，dp[index + 1][rest - (2 * arr[index])]的值，dp[index + 1][rest - (3 * arr[index])]的值……
				* 假设arr[index]面值是3
				* 也就是这个格子的“下”和“下“的左侧第3个格子，第6个格子，第9个格子……
				* 那dp[index][rest - 3]的值，也是这个格子的“下”和“下“的左侧第3个格子，第6个格子，第9个格子……
				* 有重复的地方
				* 所以dp[index][rest]的值 = dp[index][rest - 3]的值 + dp[index][rest]的”下“的值
				* */
				dp[index][rest] = dp[index + 1][rest];//我获得我下面格子的值
				if (rest - arr[index] >= 0) {// 如果我自己这一行没越界
					dp[index][rest] += dp[index][rest - arr[index]];// 我就是我的”下“加上dp[index][rest - arr[index]]
				}
			}
		}
		return dp[0][aim];
	}




















	// 为了测试
	public static int[] randomArray(int maxLen, int maxValue) {
		int N = (int) (Math.random() * maxLen);
		int[] arr = new int[N];
		boolean[] has = new boolean[maxValue + 1];
		for (int i = 0; i < N; i++) {
			do {
				arr[i] = (int) (Math.random() * maxValue) + 1;
			} while (has[arr[i]]);
			has[arr[i]] = true;
		}
		return arr;
	}

	// 为了测试
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 为了测试
	public static void main(String[] args) {
		int maxLen = 10;
		int maxValue = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int[] arr = randomArray(maxLen, maxValue);
			int aim = (int) (Math.random() * maxValue);
			int ans1 = coinsWay(arr, aim);
			int ans2 = dp1(arr, aim);
			int ans3 = dp2(arr, aim);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
				printArray(arr);
				System.out.println(aim);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println(ans3);
				break;
			}
		}
		System.out.println("测试结束");
	}

}
