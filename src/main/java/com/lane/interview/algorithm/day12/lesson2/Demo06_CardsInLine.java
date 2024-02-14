package com.lane.interview.algorithm.day12.lesson2;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 */
public class Demo06_CardsInLine {

	// 根据规则，返回获胜者的分数
	/**
	 * 暴力递归
	 */
	public static int win1(int[] arr) {
		// 边界
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// 先手的最好成绩
		int first = f1(arr, 0, arr.length - 1);
		// 后手的最好成绩
		int second = g1(arr, 0, arr.length - 1);
		// 取两者最大值
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f1(int[] arr, int L, int R) {
		// 只剩一张牌了，先手拿走
		if (L == R) {
			return arr[L];
		}
		// 先手拿走了L位置的数，那我在L+1..R范围上就是后手
		int p1 = arr[L] + g1(arr, L + 1, R);
		// 先手拿走了R位置的数，那我在L..R-1范围上就是后手
		int p2 = arr[R] + g1(arr, L, R - 1);
		// 先手拿走两种情况中的最大值
		return Math.max(p1, p2);
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g1(int[] arr, int L, int R) {
		// 只剩一张牌了，后手没得拿
		if (L == R) {
			return 0;
		}
		// 对手拿走了L位置的数，我在L+1..R范围上就是先手
		int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
		// 对手拿走了R位置的数，我在L..R-1范围上就是先手
		int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
		// 对手拿走两种情况中的最小值
		// 为啥是最小值？？？ 因为对手也是绝顶聪明的，他会让你拿最小的，我只能在对手拿完之后，我成为先手之后，尽全力拿最大的
		return Math.min(p1, p2);
	}

	/**
	 * 暴力递归中含有重复计算的问题，使用缓存表解决
	 * 因为存在先手后手，所以我们用两张缓存表
	 */
	public static int win2(int[] arr) {
		// 边界
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// 初始化两张缓存表，一开始都是-1
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				fmap[i][j] = -1;
				gmap[i][j] = -1;
			}
		}
		// 先手的最好成绩
		int first = f2(arr, 0, arr.length - 1, fmap, gmap);
		// 后手的最好成绩
		int second = g2(arr, 0, arr.length - 1, fmap, gmap);
		// 取两者最大值
		return Math.max(first, second);
	}

	// arr[L..R]，先手获得的最好分数返回
	public static int f2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		// 先手缓存表中有值，直接返回
		if (fmap[L][R] != -1) {
			return fmap[L][R];
		}
		int ans = 0;
		// 只剩一张牌了，先手拿走
		if (L == R) {
			ans = arr[L];
		} else {
			// 先手拿走了L位置的数，那我在L+1..R范围上就是后手
			int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
			// 先手拿走了R位置的数，那我在L..R-1范围上就是后手
			int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
			// 先手拿走两种情况中的最大值
			ans = Math.max(p1, p2);
		}
		// 把ans放入缓存表
		fmap[L][R] = ans;
		// 返回ans
		return ans;
	}

	// // arr[L..R]，后手获得的最好分数返回
	public static int g2(int[] arr, int L, int R, int[][] fmap, int[][] gmap) {
		// 后手缓存表中有值，直接返回
		if (gmap[L][R] != -1) {
			return gmap[L][R];
		}
		int ans = 0;
		// 只剩一张牌了，后手没得拿，这里就没写
		// 不只剩一张牌
		if (L != R) {
			// 对手拿走了L位置的数，我在L+1..R范围上就是先手
			int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
			// 对手拿走了R位置的数，我在L..R-1范围上就是先手
			int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
			// 对手拿走两种情况中的最小值
			ans = Math.min(p1, p2);
		}
		// 把ans放入缓存表
		gmap[L][R] = ans;
		// 返回ans
		return ans;
	}


	/**
	 * 动态规划
	 * 具体咋算的看图，md里面有详细说明
	 */
	public static int win3(int[] arr) {
		// 边界
		if (arr == null || arr.length == 0) {
			return 0;
		}
		// 初始化两张缓存表
		int N = arr.length;
		int[][] fmap = new int[N][N];
		int[][] gmap = new int[N][N];
		// 把先手表的对角线填上，后手表的对角线都是0，不用填
		for (int i = 0; i < N; i++) {
			fmap[i][i] = arr[i];
		}
		// 我们现在要遍历对角线，咋遍历对角线呢
		// 首先从第1列开始，从左往右遍历
		for (int startCol = 1; startCol < N; startCol++) {
			// 从第0行开始
			int L = 0;
			// 从第startCol列开始，也就是第1列
			int R = startCol;
			// R不能越界
			while (R < N) {
				/**
				 * 对照着win2中这段代码看
				 *          // 先手拿走了L位置的数，那我在L+1..R范围上就是后手
				 * 			int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
				 * 			// 先手拿走了R位置的数，那我在L..R-1范围上就是后手
				 * 			int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
				 * 			// 先手拿走两种情况中的最大值
				 * 			ans = Math.max(p1, p2);
				 */
				fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
				/**
				 * 对照着win2中这段代码看
				 *          // 对手拿走了L位置的数，我在L+1..R范围上就是先手
				 * 			int p1 = f2(arr, L + 1, R, fmap, gmap); // 对手拿走了L位置的数
				 * 			// 对手拿走了R位置的数，我在L..R-1范围上就是先手
				 * 			int p2 = f2(arr, L, R - 1, fmap, gmap); // 对手拿走了R位置的数
				 * 			// 对手拿走两种情况中的最小值
				 * 			ans = Math.min(p1, p2);
				 */
				gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
				// 整体从左上往右下走
				L++;
				R++;
			}
		}
		// 返回先手表的右上角，和后手表的右上角的值，大的那一个，就是俩五角星的值，看谁大
		return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
	}

	public static void main(String[] args) {
		int[] arr = { 5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));
		System.out.println(win3(arr));

	}

}
