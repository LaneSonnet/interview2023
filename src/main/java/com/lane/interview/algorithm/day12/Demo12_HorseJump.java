package com.lane.interview.algorithm.day12;

/**
 * 马走日
 * 问题描述：
 * 中国象棋中的马走日，给定一个棋盘，棋盘的大小为10*9，棋盘的左下角为(0,0)位置，棋盘的右上角为(9,8)位置，
 * 给定三个参数x，y，k，返回马从(0,0)位置出发，必须走k步，最后落在(x,y)上的方法数有多少种？
 *
 */
public class Demo12_HorseJump {

	// 当前来到的位置是（x,y）
	// 还剩下rest步需要跳
	// 跳完rest步，正好跳到a，b的方法数是多少？
	// 10 * 9

	/**
	 * 暴力递归
	 */
	public static int jump(int a, int b, int k) {
		return process(0, 0, k, a, b);
	}

	public static int process(int x, int y, int rest, int a, int b) {
		// 边界
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		// base case
		if (rest == 0) {
			return (x == a && y == b) ? 1 : 0;
		}
		// 分别尝试8种可能性
		// 因为马可以往8个不同方向走
		int ways = process(x + 2, y + 1, rest - 1, a, b);
		ways += process(x + 1, y + 2, rest - 1, a, b);
		ways += process(x - 1, y + 2, rest - 1, a, b);
		ways += process(x - 2, y + 1, rest - 1, a, b);
		ways += process(x - 2, y - 1, rest - 1, a, b);
		ways += process(x - 1, y - 2, rest - 1, a, b);
		ways += process(x + 1, y - 2, rest - 1, a, b);
		ways += process(x + 2, y - 1, rest - 1, a, b);
		return ways;
	}

	/**
	 * 动态规划
	 * 三维的dp表
	 *
	 */
	public static int dp(int a, int b, int k) {
		// x范围0~9
		// y范围0~8
		// k范围0~k
		// 初始化dp表
		int[][][] dp = new int[10][9][k + 1];
		// base case
		dp[a][b][0] = 1;// 第0层中，只有这个位置是1，其余位置都是0
		/*
		* 看成是三维的空间
		* 三条坐标轴是x,y,rest
		* 可以从递归方法里看到，rest=0这一层是basecase，而每一层的ways都依赖于rest-1(什么意思？就是每一层的数据都依赖前一层的数据)
		* 那显而易见，从第0层往上面依次计算每一层
		*
		* */
		// 从第一层开始，一直计算到第k层
		for (int rest = 1; rest <= k; rest++) {
			// 从第0行开始，一直计算到第9行
			for (int x = 0; x < 10; x++) {
				// 从第0列开始，一直计算到第8列
				for (int y = 0; y < 9; y++) {
					// 优化点：pick函数
					int ways = pick(dp, x + 2, y + 1, rest - 1);
					ways += pick(dp, x + 1, y + 2, rest - 1);
					ways += pick(dp, x - 1, y + 2, rest - 1);
					ways += pick(dp, x - 2, y + 1, rest - 1);
					ways += pick(dp, x - 2, y - 1, rest - 1);
					ways += pick(dp, x - 1, y - 2, rest - 1);
					ways += pick(dp, x + 1, y - 2, rest - 1);
					ways += pick(dp, x + 2, y - 1, rest - 1);
					dp[x][y][rest] = ways;
				}
			}
		}
		return dp[0][0][k];
	}

	/**
	 * 在dp表中，得到dp[x][y][rest]的值，但如果(x，y)位置越界的话，返回0；
	 */
	public static int pick(int[][][] dp, int x, int y, int rest) {
		if (x < 0 || x > 9 || y < 0 || y > 8) {
			return 0;
		}
		return dp[x][y][rest];
	}














	// 以下是暴力方法，为了验证，不用看

	public static int ways(int a, int b, int step) {
		return f(0, 0, step, a, b);
	}

	public static int f(int i, int j, int step, int a, int b) {
		if (i < 0 || i > 9 || j < 0 || j > 8) {
			return 0;
		}
		if (step == 0) {
			return (i == a && j == b) ? 1 : 0;
		}
		return f(i - 2, j + 1, step - 1, a, b) + f(i - 1, j + 2, step - 1, a, b) + f(i + 1, j + 2, step - 1, a, b)
				+ f(i + 2, j + 1, step - 1, a, b) + f(i + 2, j - 1, step - 1, a, b) + f(i + 1, j - 2, step - 1, a, b)
				+ f(i - 1, j - 2, step - 1, a, b) + f(i - 2, j - 1, step - 1, a, b);

	}

	public static int waysdp(int a, int b, int s) {
		int[][][] dp = new int[10][9][s + 1];
		dp[a][b][0] = 1;
		for (int step = 1; step <= s; step++) { // 按层来
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 9; j++) {
					dp[i][j][step] = getValue(dp, i - 2, j + 1, step - 1) + getValue(dp, i - 1, j + 2, step - 1)
							+ getValue(dp, i + 1, j + 2, step - 1) + getValue(dp, i + 2, j + 1, step - 1)
							+ getValue(dp, i + 2, j - 1, step - 1) + getValue(dp, i + 1, j - 2, step - 1)
							+ getValue(dp, i - 1, j - 2, step - 1) + getValue(dp, i - 2, j - 1, step - 1);
				}
			}
		}
		return dp[0][0][s];
	}

	// 在dp表中，得到dp[i][j][step]的值，但如果(i，j)位置越界的话，返回0；
	public static int getValue(int[][][] dp, int i, int j, int step) {
		if (i < 0 || i > 9 || j < 0 || j > 8) {
			return 0;
		}
		return dp[i][j][step];
	}

	public static void main(String[] args) {
		int x = 7;
		int y = 7;
		int step = 10;
		System.out.println(ways(x, y, step));
		System.out.println(dp(x, y, step));

		System.out.println(jump(x, y, step));
	}
}
