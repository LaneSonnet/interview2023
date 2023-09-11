package com.lane.interview.algorithm.day8;

public class Demo3_IsBalanced {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalanced1(Node head) {
		boolean[] ans = new boolean[1];
		ans[0] = true;
		process1(head, ans);
		return ans[0];
	}

	public static int process1(Node head, boolean[] ans) {
		if (!ans[0] || head == null) {
			return -1;
		}
		int leftHeight = process1(head.left, ans);
		int rightHeight = process1(head.right, ans);
		if (Math.abs(leftHeight - rightHeight) > 1) {
			ans[0] = false;
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}

	/**
	 * 对于节点X
	 *
	 * ①X的左子树是平衡二叉树
	 *
	 * ②X的右子树是平衡二叉树
	 *
	 * ③X的左子树深度与右子树深度差值的绝对值<2
	 */
	public static boolean isBalanced2(Node head) {
		return process(head).isBalanced;
	}

	// 信息结构
	// 要得到左右子树的两个信息：1.是否是平衡二叉树 2.树的高度
	public static class Info{
		public boolean isBalanced;
		public int height;
		
		public Info(boolean i, int h) {
			isBalanced = i;
			height = h;
		}
	}
	
	public static Info process(Node x) {
		// 边界条件
		if(x == null) {
			return new Info(true, 0);
		}
		// 我左子树的信息
		Info leftInfo = process(x.left);
		// 我右子树的信息
		Info rightInfo = process(x.right);
		// 我自己的高度
		int height = Math.max(leftInfo.height, rightInfo.height)  + 1;
		// 我是否是平衡二叉树
		boolean isBalanced = true;
		// 我的左子树不平衡，那我也不平衡
		if(!leftInfo.isBalanced) {
			isBalanced = false;
		}
		// 我的右子树不平衡，那我也不平衡
		if(!rightInfo.isBalanced) {
			isBalanced = false;
		}
		// 我的左右子树高度差大于1，那我也不平衡
		if(Math.abs(leftInfo.height - rightInfo.height) > 1) {
			isBalanced = false;
		}
		return new Info(isBalanced, height);
	}
	
	
	// ---------------------------------------------------------------------------------------------------------------------
	
	

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
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isBalanced1(head) != isBalanced2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
