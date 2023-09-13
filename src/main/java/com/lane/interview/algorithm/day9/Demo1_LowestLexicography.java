package com.lane.interview.algorithm.day9;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定一个字符串类型的数组strs，找到一种拼接方式，使得把所有字符串拼起来之后形成的字符串具有最低的字典序
 *
 *  例如：strs = {"abc", "cks", "bct"}
 *  拼接成的最低字典序的字符串是abcbctcks
 *
 * 字典序：字符串比较大小的时候，是按照从左到右的顺序依次比较字符的大小
 * 1.两个字符串长度相等时，按照从左到右依次比较字符的大小
 * 2.长度不等时，短的字符串补齐到和长的字符串一样的长度(用最小的asc码补齐)，然后按照从左到右依次比较字符的大小(举例说明，abcd<bcd，因为abcd<bcd0)
 *
 * 贪心思路：
 *  两个字符串a，b
 *  a拼接b 比 b拼接a 字典序小，那么a排在b前面
 */
public class Demo1_LowestLexicography {

	public static String lowestString1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		TreeSet<String> ans = process(strs);
		return ans.size() == 0 ? "" : ans.first();
	}

	// strs中所有字符串全排列，返回所有可能的结果
	public static TreeSet<String> process(String[] strs) {
		TreeSet<String> ans = new TreeSet<>();
		if (strs.length == 0) {
			ans.add("");
			return ans;
		}
		for (int i = 0; i < strs.length; i++) {
			String first = strs[i];
			String[] nexts = removeIndexString(strs, i);
			TreeSet<String> next = process(nexts);
			for (String cur : next) {
				ans.add(first + cur);
			}
		}
		return ans;
	}

	// {"abc", "cks", "bct"}
	// 0 1 2
	// removeIndexString(arr , 1) -> {"abc", "bct"}
	public static String[] removeIndexString(String[] arr, int index) {
		int N = arr.length;
		String[] ans = new String[N - 1];
		int ansIndex = 0;
		for (int i = 0; i < N; i++) {
			if (i != index) {
				ans[ansIndex++] = arr[i];
			}
		}
		return ans;
	}

	// a拼接b，和b拼接a，哪个字典序小
	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	public static String lowestString2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	// for test
	public static String generateRandomString(int strLen) {
		char[] ans = new char[(int) (Math.random() * strLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			int value = (int) (Math.random() * 5);
			ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
		}
		return String.valueOf(ans);
	}

	// for test
	public static String[] generateRandomStringArray(int arrLen, int strLen) {
		String[] ans = new String[(int) (Math.random() * arrLen) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = generateRandomString(strLen);
		}
		return ans;
	}

	// for test
	public static String[] copyStringArray(String[] arr) {
		String[] ans = new String[arr.length];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = String.valueOf(arr[i]);
		}
		return ans;
	}

	public static void main(String[] args) {
		int arrLen = 6;
		int strLen = 5;
		int testTimes = 10000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String[] arr1 = generateRandomStringArray(arrLen, strLen);
			String[] arr2 = copyStringArray(arr1);
			if (!lowestString1(arr1).equals(lowestString2(arr2))) {
				for (String str : arr1) {
					System.out.print(str + ",");
				}
				System.out.println();
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
