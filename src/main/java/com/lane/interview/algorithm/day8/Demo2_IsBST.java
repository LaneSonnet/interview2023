package com.lane.interview.algorithm.day8;

import java.util.ArrayList;

/**
 * 判断一棵树是否是搜索二叉树
 */
public class Demo2_IsBST {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBST1(Node head) {
		if (head == null) {
			return true;
		}
		ArrayList<Node> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).value <= arr.get(i - 1).value) {
				return false;
			}
		}
		return true;
	}

	public static void in(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	/**
	 * 对于节点X
	 *
	 * ①X的左子树是搜索二叉树
	 *
	 * ②X的右子树是搜索二叉树
	 *
	 * ③X的左子树最大值<X
	 *
	 * ④X的右子树最小值>X
	 */
	public static boolean isBST2(Node head) {
		// 空树也是搜索二叉树
		if (head == null) {
			return true;
		}
		return process(head).isBST;
	}

	// 信息结构
	// 要得到左右子树的三个信息：1.是否是搜索二叉树 2.树的最大值 3.树的最小值
	public static class Info {
		public boolean isBST;
		public int max;
		public int min;

		public Info(boolean i, int ma, int mi) {
			isBST = i;
			max = ma;
			min = mi;
		}

	}

	public static Info process(Node x) {
		// 边界条件
		if (x == null) {
			return null;
		}
		// 我左子树的信息
		Info leftInfo = process(x.left);
		// 我右子树的信息
		Info rightInfo = process(x.right);
		// 最大值一开始是我自己
		int max = x.value;
		if (leftInfo != null) {
			max = Math.max(max, leftInfo.max);
		}
		if (rightInfo != null) {
			max = Math.max(max, rightInfo.max);
		}
		// 最小值一开始是我自己
		int min = x.value;
		if (leftInfo != null) {
			min = Math.min(min, leftInfo.min);
		}
		if (rightInfo != null) {
			min = Math.min(min, rightInfo.min);
		}
		// 我是否是搜索二叉树
		boolean isBST = true;
		// 我的左子树不是搜索二叉树，那我也不是
		if (leftInfo != null && !leftInfo.isBST) {
			isBST = false;
		}
		// 我的右子树不是搜索二叉树，那我也不是
		if (rightInfo != null && !rightInfo.isBST) {
			isBST = false;
		}
		// 我的左子树最大值大于我，那我不是搜索二叉树
		if (leftInfo != null && leftInfo.max >= x.value) {
			isBST = false;
		}
		// 我的右子树最小值小于我，那我不是搜索二叉树
		if (rightInfo != null && rightInfo.min <= x.value) {
			isBST = false;
		}
		return new Info(isBST, max, min);
	}

	// for test
	public static Node generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// for test
	public static Node generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		Node head = new Node((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isBST1(head) != isBST2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
