package com.lane.interview.algorithm.day12;

/**
 * 给定一个字符串str，str全部由数字字符组成，如果str中某一个或者相邻两个字符组成的子串值在1~26之间，
 * 则这个子串可以转换为一个字母。规定‘1’转换为“A”，“2”转换为“B”...“26”转换为“Z”。
 * 请求出str有多少种不同的转换结果，并返回种数。
 */
public class Demo08_ConvertToLetterString {

	// str只含有数字字符0~9
	// 返回多少种转化方案
	public static int number(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		return process(str.toCharArray(), 0);
	}

	// str[0..i-1]转化无需过问
	// str[i.....]去转化，返回有多少种转化方法

	/**
	 * 暴力递归
	 */
	public static int process(char[] str, int i) {
		// base case
		if (i == str.length) {
			return 1;
		}
		// i没到最后，说明有字符
		if (str[i] == '0') { // 之前的决定有问题
			// 为啥有问题
			// 1）'0'字符不能单转
			// 2）如果前面的决定导致了目前i位置的数是0(也就是不能转化)，说明前面的决定有问题，那么后续也不用搞了，直接返回0
			// 如果i位置的字符是'0'，那么i-1的数必须和i位置的这个0拼起来作为一个整体，才能继续转化
			return 0;
		}
		// str[i] != '0'
		// 情况1：i位置的字符单转
		int ways = process(str, i + 1);
		// 情况2：i位置的字符和i+1位置的字符一起转
		// 这个判断的意思是边界判断
		// ① i+1位置的字符存在
		// ② i位置的字符和i+1位置的字符一起转化的结果在1~26之间
		if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			// i位置的字符和i+1位置的字符一起转，所以下一步是i+2位置的字符去转化
			// 要把情况1和情况2的结果都加起来
			ways += process(str, i + 2);
		}
		// 返回总的转化方案数
		return ways;
	}

	// 从右往左的动态规划
	// 就是上面方法的动态规划版本
	// dp[i]表示：str[i...]有多少种转化方式
	/**
	 * 动态规划
	 */
	public static int dp1(String s) {
		// 边界
		if (s == null || s.length() == 0) {
			return 0;
		}
		// 把字符串转换成字符数组
		char[] str = s.toCharArray();
		int N = str.length;
		// 初始化动态规划表
		int[] dp = new int[N + 1];
		// 对应了暴力递归的base case
		dp[N] = 1;
		// 分析暴力递归中的依赖关系，可以看出来，情况1和情况2都依赖了i+1位置的结果
		// 所以从右往左的动态规划，从右往左填表
		for (int i = N - 1; i >= 0; i--) {
			// 抄代码
			/**
			 *      int ways = process(str, i + 1);
			 *
			 * 		if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
			 * 			ways += process(str, i + 2);
			 *      }
			 */
			if (str[i] != '0') {
				int ways = dp[i + 1];
				if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
					ways += dp[i + 2];
				}
				dp[i] = ways;
			}
		}
		// 返回结果
		return dp[0];
	}

	// 从左往右的动态规划
	// dp[i]表示：str[0...i]有多少种转化方式
	/**
	 * 另一个方向(从左往右)的动态规划
	 */
	public static int dp2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		char[] str = s.toCharArray();
		int N = str.length;
		if (str[0] == '0') {
			return 0;
		}
		int[] dp = new int[N];
		dp[0] = 1;
		for (int i = 1; i < N; i++) {
			if (str[i] == '0') {
				// 如果此时str[i]=='0'，那么他是一定要拉前一个字符(i-1的字符)一起拼的，
				// 那么就要求前一个字符，不能也是‘0’，否则拼不了。
				// 前一个字符不是‘0’就够了嘛？不够，还得要求拼完了要么是10，要么是20，如果更大的话，拼不了。
				// 这就够了嘛？还不够，你们拼完了，还得要求str[0...i-2]真的可以被分解！(并且i-2不能越界)
				// 如果str[0...i-2]都不存在分解方案，那i和i-1拼成了也不行，因为之前的搞定不了。
				if (str[i - 1] == '0' || str[i - 1] > '2' || (i - 2 >= 0 && dp[i - 2] == 0)) {
					return 0;
				} else {
					// 如果i-2没越界，而且str[0...i-2]有分解方案，那么i和i-1拼成10或者20，就是一种方案，返回str[0...i-2]的分解方案数
					// 如果i-2越界了，那么i和i-1拼成10或者20，也是一种方案，返回1
					dp[i] = i - 2 >= 0 ? dp[i - 2] : 1;
				}
			} else {
				// str[i] != '0'
				// 情况1：i位置的字符单转
				dp[i] = dp[i - 1];
				// 情况2：i位置的字符和i-1位置的字符一起转
				// 这个判断的意思是边界判断
				// ① i-1位置的数不能是0
				// ② i位置的字符和i-1位置的字符一起转化的结果在1~26之间
				if (str[i - 1] != '0' && (str[i - 1] - '0') * 10 + str[i] - '0' <= 26) {
					// i位置的字符和i-1位置的字符一起转，所以下一步是i-2位置的字符去转化
					dp[i] += i - 2 >= 0 ? dp[i - 2] : 1;
				}
			}
		}
		return dp[N - 1];
	}

	// 为了测试
	public static String randomString(int len) {
		char[] str = new char[len];
		for (int i = 0; i < len; i++) {
			str[i] = (char) ((int) (Math.random() * 10) + '0');
		}
		return String.valueOf(str);
	}

	// 为了测试
	public static void main(String[] args) {
		int N = 30;
		int testTime = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTime; i++) {
			int len = (int) (Math.random() * N);
			String s = randomString(len);
			int ans0 = number(s);
			int ans1 = dp1(s);
			int ans2 = dp2(s);
			if (ans0 != ans1 || ans0 != ans2) {
				System.out.println(s);
				System.out.println(ans0);
				System.out.println(ans1);
				System.out.println(ans2);
				System.out.println("Oops!");
				break;
			}
		}
		System.out.println("测试结束");
	}

}
