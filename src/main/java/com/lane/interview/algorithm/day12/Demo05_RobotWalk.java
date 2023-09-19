package com.lane.interview.algorithm.day12;

/**
 * 假设有排成一行的N个位置，记为1~N，N一定大于或等于2。
 * 开始时机器人在其中的M位置上(M一定是1~N中的一个)，机器人可以往左走或者往右走，
 * 如果机器人来到1位置， 那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置。
 * 规定机器人必须走K步，最终能来到P位置(P也一定是1~N中的一个)的方法有多少种。给定四个参数N、M、K、P，返回方法数。
 */
public class Demo05_RobotWalk {

	/**
	 * 暴力递归
	 */
	public static int ways1(int N, int start, int aim, int K) {
		// 边界
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		return process1(start, K, aim, N);
	}

	// 机器人当前来到的位置是cur，
	// 机器人还有rest步需要去走，
	// 最终的目标是aim，
	// 有哪些位置？1~N
	// 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
	public static int process1(int cur, int rest, int aim, int N) {
		if (rest == 0) { // 如果已经不需要走了，走完了！
			return cur == aim ? 1 : 0;
		}
		// 机器人在1位置，下一步必须往右，1 -> 2
		if (cur == 1) { // 1 -> 2
			return process1(2, rest - 1, aim, N);
		}
		// 机器人在N位置，下一步必须往左，N-1 <- N
		if (cur == N) { // N-1 <- N
			return process1(N - 1, rest - 1, aim, N);
		}
		// 机器人在中间位置，下一步可以往左，也可以往右，cur-1 <- cur -> cur+1
		return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
	}

	/**
	 * 暴力递归中含有重复计算的问题，使用缓存表解决
	 * 缓存表就是dp表，一个二维数组
	 * dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
	 * dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
	 */
	public static int ways2(int N, int start, int aim, int K) {
		// 边界
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		// 初始化dp表，一开始都是-1
		int[][] dp = new int[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				dp[i][j] = -1;
			}
		}
		// dp就是缓存表
		// dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
		// dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
		// N+1 * K+1
		return process2(start, K, aim, N, dp);
	}

	// cur的范围: 1 ~ N
	// rest的范围：0 ~ K
	public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
		// 缓存表中有值，直接返回
		if (dp[cur][rest] != -1) {
			return dp[cur][rest];
		}
		// 之前没算过！
		int ans = 0;
		if (rest == 0) {
			ans = cur == aim ? 1 : 0;
		} else if (cur == 1) {
			ans = process2(2, rest - 1, aim, N, dp);
		} else if (cur == N) {
			ans = process2(N - 1, rest - 1, aim, N, dp);
		} else {
			ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
		}
		// 把算出来的ans放入缓存表中
		dp[cur][rest] = ans;
		// 返回ans
		return ans;

	}

	/**
	 * 动态规划
	 * 具体咋算的看图，md里面有详细说明
	 */
	public static int ways3(int N, int start, int aim, int K) {
		// 边界
		if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
			return -1;
		}
		// 初始化dp表
		int[][] dp = new int[N + 1][K + 1];
		// 这行先把第一列的那个1先填上，表示aim = cur的时候，rest = 0的时候，直接返回1
		dp[aim][0] = 1;
		// 从第二列开始，从左往右依次遍历每一列
		for (int rest = 1; rest <= K; rest++) {
			// 如果是第1行，那每格只依赖我的左下
			dp[1][rest] = dp[2][rest - 1];
			// 如果是中间行，那每格依赖我的左上和右上
			// 从第2行开始，从上至下依次遍历每一行
			for (int cur = 2; cur < N; cur++) {
				// 我的值依赖我的左上 + 我的左下
				dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
			}
			// 如果是最后一行，那每格只依赖我的左上
			dp[N][rest] = dp[N - 1][rest - 1];
		}
		// 最终返回的是dp[start][K]，cur是start，rest是K，也就是图中五角星的位置
		return dp[start][K];
	}

	public static void main(String[] args) {
		System.out.println(ways1(5, 2, 4, 6));
		System.out.println(ways2(5, 2, 4, 6));
		System.out.println(ways3(5, 2, 4, 6));
	}

}
