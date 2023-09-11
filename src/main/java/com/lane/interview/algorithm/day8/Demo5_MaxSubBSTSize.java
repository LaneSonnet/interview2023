package com.lane.interview.algorithm.day8;

import java.util.ArrayList;

// 在线测试链接 : https://leetcode.com/problems/largest-bst-subtree

/**
 * 给定一个二叉树，找到其中最大的二叉搜索树（BST）子树，并返回该子树的大小。其中，最大指的是子树节点数最多的。
 *
 * 二叉搜索树（BST）中的所有节点都具备以下属性：
 *
 * 左子树的值小于其父（根）节点的值。
 *
 * 右子树的值大于其父（根）节点的值。
 *
 * 注意：子树必须包含其所有后代。
 */
public class Demo5_MaxSubBSTSize {

	// 提交时不要提交这个类
	public static class TreeNode {
		public int val;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int value) {
			val = value;
		}
	}

	// 提交如下的largestBSTSubtree方法，可以直接通过

	/**
	 * 分情况讨论
	 * 对于节点X来讲
	 * 1.X不做为最大搜索二叉树的头结点
	 * ①X的左子树的最大搜索二叉树的size是这道题的结果
	 * ②X的右子树的最大搜索二叉树的size是这道题的结果
	 *
	 * 2.X做为最大搜索二叉树的头结点
	 * ①X的左子树是搜索二叉树
	 * ②X的右子树是搜索二叉树
	 * ③X的左子树最大值<X
	 * ④X的右子树最小值>X
	 * ⑤整棵树的size是这道题的结果(X的左子树的size+X的右子树的size+1)
	 */
	public static int largestBSTSubtree(TreeNode head) {
		if (head == null) {
			return 0;
		}
		return process(head).maxBSTSubtreeSize;
	}

	// 信息结构
	// 需要获取5个信息：①子树中最大搜索二叉树的size，②是不是搜索二叉树，③树的最大值，④树的最小值，⑤树的节点数
	// 其中当①等于⑤的时候，②自然为true，所以②可以干掉，不用收集
	public static class Info {
		public int maxBSTSubtreeSize;
		public int allSize;
		public int max;
		public int min;

		public Info(int m, int a, int ma, int mi) {
			maxBSTSubtreeSize = m;
			allSize = a;
			max = ma;
			min = mi;
		}
	}

	public static Info process(TreeNode x) {
		// 边界条件
		if (x == null) {
			return null;
		}
		// 我左子树的信息
		Info leftInfo = process(x.left);
		// 我右子树的信息
		Info rightInfo = process(x.right);
		// 初始化
		int max = x.val;
		int min = x.val;
		int allSize = 1;
		// 收集我左子树的信息
		if (leftInfo != null) {
			max = Math.max(leftInfo.max, max);
			min = Math.min(leftInfo.min, min);
			allSize += leftInfo.allSize;
		}
		// 收集我右子树的信息
		if (rightInfo != null) {
			max = Math.max(rightInfo.max, max);
			min = Math.min(rightInfo.min, min);
			allSize += rightInfo.allSize;
		}
		// 我左子树上最大的搜索二叉树的size
		int p1 = -1;
		if (leftInfo != null) {
			p1 = leftInfo.maxBSTSubtreeSize;
		}
		// 我右子树上最大的搜索二叉树的size
		int p2 = -1;
		if (rightInfo != null) {
			p2 = rightInfo.maxBSTSubtreeSize;
		}
		// 我为头结点的整棵树就是搜索二叉树，我整棵树的size就是我左子树的size+我右子树的size+1
		int p3 = -1;
		// 我的左子树是不是搜索二叉树
		boolean leftBST = leftInfo == null ? true : (leftInfo.maxBSTSubtreeSize == leftInfo.allSize);
		// 我的右子树是不是搜索二叉树
		boolean rightBST = rightInfo == null ? true : (rightInfo.maxBSTSubtreeSize == rightInfo.allSize);
		// 我的左右子树都是搜索二叉树，并且 我的左子树最大值小于我，我右子树最小值大于我，那我就是搜索二叉树
		if (leftBST && rightBST) {
			// 我的左子树最大值小于我
			boolean leftMaxLessX = leftInfo == null ? true : (leftInfo.max < x.val);
			// 我的右子树最小值大于我
			boolean rightMinMoreX = rightInfo == null ? true : (x.val < rightInfo.min);
			// 我的左子树最大值小于我 并且 我右子树最小值大于我
			if (leftMaxLessX && rightMinMoreX) {
				// 我的左子树的size
				int leftSize = leftInfo == null ? 0 : leftInfo.allSize;
				// 我的右子树的size
				int rightSize = rightInfo == null ? 0 : rightInfo.allSize;
				// 我的整棵树的size
				p3 = leftSize + rightSize + 1;
			}
		}
		return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
	}




















	// 为了验证
	// 对数器方法
	public static int right(TreeNode head) {
		if (head == null) {
			return 0;
		}
		int h = getBSTSize(head);
		if (h != 0) {
			return h;
		}
		return Math.max(right(head.left), right(head.right));
	}

	// 为了验证
	// 对数器方法
	public static int getBSTSize(TreeNode head) {
		if (head == null) {
			return 0;
		}
		ArrayList<TreeNode> arr = new ArrayList<>();
		in(head, arr);
		for (int i = 1; i < arr.size(); i++) {
			if (arr.get(i).val <= arr.get(i - 1).val) {
				return 0;
			}
		}
		return arr.size();
	}

	// 为了验证
	// 对数器方法
	public static void in(TreeNode head, ArrayList<TreeNode> arr) {
		if (head == null) {
			return;
		}
		in(head.left, arr);
		arr.add(head);
		in(head.right, arr);
	}

	// 为了验证
	// 对数器方法
	public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
		return generate(1, maxLevel, maxValue);
	}

	// 为了验证
	// 对数器方法
	public static TreeNode generate(int level, int maxLevel, int maxValue) {
		if (level > maxLevel || Math.random() < 0.5) {
			return null;
		}
		TreeNode head = new TreeNode((int) (Math.random() * maxValue));
		head.left = generate(level + 1, maxLevel, maxValue);
		head.right = generate(level + 1, maxLevel, maxValue);
		return head;
	}

	// 为了验证
	// 对数器方法
	public static void main(String[] args) {
		int maxLevel = 4;
		int maxValue = 100;
		int testTimes = 1000000;
		System.out.println("测试开始");
		for (int i = 0; i < testTimes; i++) {
			TreeNode head = generateRandomBST(maxLevel, maxValue);
			if (largestBSTSubtree(head) != right(head)) {
				System.out.println("出错了！");
			}
		}
		System.out.println("测试结束");
	}

}
