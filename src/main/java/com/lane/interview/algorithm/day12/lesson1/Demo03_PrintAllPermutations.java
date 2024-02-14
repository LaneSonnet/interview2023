package com.lane.interview.algorithm.day12.lesson1;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列 (进阶：打印去重)
 */
public class Demo03_PrintAllPermutations {

	/**
	 * 普通的暴力递归
	 */
	public static List<String> permutation1(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		ArrayList<Character> rest = new ArrayList<Character>();
		for (char cha : str) {
			rest.add(cha);
		}
		String path = "";
		f(rest, path, ans);
		return ans;
	}

	/**
	 * rest: 剩余的字符
	 * path: 之前做的选择
	 * ans: 收集答案
	 */
	public static void f(ArrayList<Character> rest, String path, List<String> ans) {
		// base case
		if (rest.isEmpty()) {
			ans.add(path);
		} else {
			int N = rest.size();
			// 遍历剩余的每一个字符
			for (int i = 0; i < N; i++) {
				// 取出来一个字符
				char cur = rest.get(i);
				// 在剩余的字符中删除这个字符
				rest.remove(i);
				// 递归
				f(rest, path + cur, ans);
				// 恢复现场
				rest.add(i, cur);
			}
		}
	}

	/**
	 * 进阶的递归
	 */
	public static List<String> permutation2(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g1(str, 0, ans);
		return ans;
	}

	/**
	 * str: 固定参数
	 * index: 来到了str[index]字符，index是位置
	 * ans: 收集答案
	 */
	public static void g1(char[] str, int index, List<String> ans) {
		// base case
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
			// 从index位置开始，后面的每一个字符(包括index自己)都可以来到index位置
			for (int i = index; i < str.length; i++) {
				// 交换
				swap(str, index, i);
				// 递归, index + 1的位置继续
				g1(str, index + 1, ans);
				// 恢复现场
				swap(str, index, i);
			}
		}
	}

	/**
	 * 进阶的递归，去重
	 */
	public static List<String> permutation3(String s) {
		List<String> ans = new ArrayList<>();
		if (s == null || s.length() == 0) {
			return ans;
		}
		char[] str = s.toCharArray();
		g2(str, 0, ans);
		return ans;
	}

	public static void g2(char[] str, int index, List<String> ans) {
		if (index == str.length) {
			ans.add(String.valueOf(str));
		} else {
			// 这个数组记录之前出现过的字符，256的长度是因为字符的范围是0~255
			// 剪枝思想
			boolean[] visited = new boolean[256];
			for (int i = index; i < str.length; i++) {
				if (!visited[str[i]]) {
					visited[str[i]] = true;
					swap(str, index, i);
					g2(str, index + 1, ans);
					swap(str, index, i);
				}
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void main(String[] args) {
		String s = "acc";
		List<String> ans1 = permutation1(s);
		for (String str : ans1) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans2 = permutation2(s);
		for (String str : ans2) {
			System.out.println(str);
		}
		System.out.println("=======");
		List<String> ans3 = permutation3(s);
		for (String str : ans3) {
			System.out.println(str);
		}

	}

}
