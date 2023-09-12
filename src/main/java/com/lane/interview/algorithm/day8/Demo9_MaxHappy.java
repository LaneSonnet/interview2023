package com.lane.interview.algorithm.day8;

import java.util.ArrayList;
import java.util.List;

// 类似的题目 https://leetcode.cn/problems/house-robber-iii/
// 但是leetcode这道题是二叉树，本题是多叉树，更牛逼

/**
 * 多叉树表示公司的层级结构
 * 每个节点都有一个happy值，表示这个员工的快乐值(就是节点的val)
 * 公司的每个员工都有唯一的直接上级
 *
 * 现在发请柬，开party
 * 规则：
 * 1.选整棵树里的一些节点
 * 2.如果某个节点来了，那么这个节点的所有下级和上级都不能来
 * 3.求party的最大快乐值
 */
public class Demo9_MaxHappy {

	public static class Employee {
		public int happy;
		public List<Employee> nexts;

		public Employee(int h) {
			happy = h;
			nexts = new ArrayList<>();
		}

	}

	public static int maxHappy1(Employee boss) {
		if (boss == null) {
			return 0;
		}
		return process1(boss, false);
	}

	// 当前来到的节点叫cur，
	// up表示cur的上级是否来，
	// 该函数含义：
	// 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	// 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
	public static int process1(Employee cur, boolean up) {
		if (up) { // 如果cur的上级来的话，cur没得选，只能不来
			int ans = 0;
			for (Employee next : cur.nexts) {
				ans += process1(next, false);
			}
			return ans;
		} else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
			int p1 = cur.happy;
			int p2 = 0;
			for (Employee next : cur.nexts) {
				p1 += process1(next, true);
				p2 += process1(next, false);
			}
			return Math.max(p1, p2);
		}
	}

	/**
	 * 分情况讨论
	 * 对于节点X来讲
	 * 1.如果x来
	 * 对于X为头结点的这棵树的范围上来说，答案就是 X自己的快乐值 + X的孩子1不来的最大快乐值 + X的孩子2不来的最大快乐值 + ...
	 *
	 * 2.如果x不来
	 * 对于X为头结点的这棵树的范围上来说，答案就是 X的孩子1来或者不来的最大快乐值(来或不来，哪个大取哪个) + X的孩子2来或者不来的最大快乐值(来或不来，哪个大取哪个) + ...
	 *
	 * 最后！！！情况1和情况2取大的那个，就是答案
	 */
	public static int maxHappy2(Employee head) {
		Info allInfo = process(head);
		return Math.max(allInfo.no, allInfo.yes);
	}

	// 信息结构
	// no:我不来的最大快乐值
	// yes:我来的最大快乐值
	public static class Info {
		public int no;
		public int yes;

		public Info(int n, int y) {
			no = n;
			yes = y;
		}
	}

	public static Info process(Employee x) {
		// 边界条件
		if (x == null) {
			return new Info(0, 0);
		}
		// 我不来的最大快乐值 初始化为0
		int no = 0;
		// 我来的最大快乐值 初始化为我自己的快乐值
		int yes = x.happy;
		// 遍历我的所有下级
		for (Employee next : x.nexts) {
			// 下级的信息
			Info nextInfo = process(next);
			// 我不来的最大快乐值 = 我不来的最大快乐值(就是0) + 下级来或不来的最大快乐值(下级来或不来，哪个大取哪个)
			no += Math.max(nextInfo.no, nextInfo.yes);
			// 我来的最大快乐值 = 我来的最大快乐值 + 下级不来的最大快乐值
			yes += nextInfo.no;

		}
		return new Info(no, yes);
	}

	// for test
	public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
		if (Math.random() < 0.02) {
			return null;
		}
		Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
		genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
		return boss;
	}

	// for test
	public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
		if (level > maxLevel) {
			return;
		}
		int nextsSize = (int) (Math.random() * (maxNexts + 1));
		for (int i = 0; i < nextsSize; i++) {
			Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
			e.nexts.add(next);
			genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
		}
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxNexts = 7;
		int maxHappy = 100;
		int testTimes = 100000;
		for (int i = 0; i < testTimes; i++) {
			Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
			if (maxHappy1(boss) != maxHappy2(boss)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
