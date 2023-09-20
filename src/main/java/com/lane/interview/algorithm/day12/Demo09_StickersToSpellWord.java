package com.lane.interview.algorithm.day12;

import java.util.HashMap;

// 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word

/**
 * 贴纸拼单词
 * 题目描述：
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr中的字符串没有重复，且都是不同的。再给定一个字符串类型的aim，请问
 * 使用arr中的字符串最少可以生成多少个aim，arr中的每个字符串都可以使用无限次
 * （注意：使用arr中的某个字符串时，可以把其中的某些字符去掉，但必须保证每种字符
 * 都剩下至少一次。）
 * 举例：
 * str = "babac"，arr = {"ba","c","abcd"}
 * 要想组成str，arr至少要使用一次"ba"，一次"abcd"。所以返回2(或者两次"abcd"，答案也是2)
 */
public class Demo09_StickersToSpellWord {

	public static int minStickers1(String[] stickers, String target) {
		int ans = process1(stickers, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// 所有贴纸stickers，每一种贴纸都有无穷张
	// target是最少张数
	/**
	 * 暴力递归
	 */
	public static int process1(String[] stickers, String target) {
		// base case
		if (target.length() == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		// 枚举每一张贴纸
		for (String first : stickers) {
			// 剪枝
			// rest是剩余的目标字符串
			String rest = minus(target, first);
			// 如果没有变小，说明first贴纸不能贴出target
			// 如果变小了，说明first贴纸贴出了rest，后续的贴纸都可以用
			if (rest.length() != target.length()) {
				// 继续看看rest需要几张贴纸
				min = Math.min(min, process1(stickers, rest));
			}
		}
		// min + 1 是当前层的贴纸数
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	/**
	 * 从s1中减去s2中的字符
	 */
	public static String minus(String s1, String s2) {
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] count = new int[26];
		for (char cha : str1) {
			count[cha - 'a']++;
		}
		for (char cha : str2) {
			count[cha - 'a']--;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				for (int j = 0; j < count[i]; j++) {
					builder.append((char) (i + 'a'));
				}
			}
		}
		return builder.toString();
	}

	public static int minStickers2(String[] stickers, String target) {
		int N = stickers.length;
		// 关键优化(用词频表替代贴纸数组)
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		int ans = process2(counts, target);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	// stickers[i] 数组，当初i号贴纸的字符统计 int[][] stickers -> 所有的贴纸
	// 每一种贴纸都有无穷张
	// 返回搞定target的最少张数
	// 最少张数
	public static int process2(int[][] stickers, String t) {
		if (t.length() == 0) {
			return 0;
		}
		// target做出词频统计
		// target  aabbc  2 2 1..
		//                0 1 2..
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			// 尝试第一张贴纸是谁
			int[] sticker = stickers[i];
			// 最关键的优化(重要的剪枝!这一步也是贪心!)
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process2(stickers, rest));
			}
		}
		return min + (min == Integer.MAX_VALUE ? 0 : 1);
	}

	public static int minStickers3(String[] stickers, String target) {
		int N = stickers.length;
		int[][] counts = new int[N][26];
		for (int i = 0; i < N; i++) {
			char[] str = stickers[i].toCharArray();
			for (char cha : str) {
				counts[i][cha - 'a']++;
			}
		}
		HashMap<String, Integer> dp = new HashMap<>();
		dp.put("", 0);
		int ans = process3(counts, target, dp);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
		if (dp.containsKey(t)) {
			return dp.get(t);
		}
		char[] target = t.toCharArray();
		int[] tcounts = new int[26];
		for (char cha : target) {
			tcounts[cha - 'a']++;
		}
		int N = stickers.length;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int[] sticker = stickers[i];
			if (sticker[target[0] - 'a'] > 0) {
				StringBuilder builder = new StringBuilder();
				for (int j = 0; j < 26; j++) {
					if (tcounts[j] > 0) {
						int nums = tcounts[j] - sticker[j];
						for (int k = 0; k < nums; k++) {
							builder.append((char) (j + 'a'));
						}
					}
				}
				String rest = builder.toString();
				min = Math.min(min, process3(stickers, rest, dp));
			}
		}
		int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
		dp.put(t, ans);
		return ans;
	}

}
