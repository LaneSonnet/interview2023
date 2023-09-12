package com.lane.interview.algorithm.day8;

import java.util.LinkedList;

/**
 * 判断一棵树是否是完全二叉树
 */
public class Demo1_IsCBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isCBT1(Node head) {
		// 边界条件
		if (head == null) {
			return true;
		}
		// 层序遍历
		LinkedList<Node> queue = new LinkedList<>();
		// 是否遇到过左右两个孩子不双全的节点
		boolean leaf = false;
		Node l = null;// 左孩子
		Node r = null;// 右孩子
		queue.add(head);
		while (!queue.isEmpty()) {
			head = queue.poll();
			l = head.left;
			r = head.right;
			if (
			    (leaf && (l != null || r != null)) // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点(有左孩子或者右孩子)，直接返回false
			    || 
			    (l == null && r != null)// 有右无左，直接返回false

			) {
				return false;
			}
			if (l != null) {
				queue.add(l);
			}
			if (r != null) {
				queue.add(r);
			}
			// 只要遇到了不双全的节点，进入第二阶段(遇到了不双全的节点)
			if (l == null || r == null) {
				leaf = true;
			}
		}
		return true;
	}

	/**
	 * 分情况讨论
	 *
	 * 对于节点X来讲
	 * ①X的左子树和右子树都是满的，那么以X为头结点的整棵树就是完全二叉树(不仅是完全二叉树，还是满二叉树)
	 *
	 * ②X的左子树是完全二叉树(但是不满)，右子树是满二叉树，右子树的高度 + 1 = 左子树的高度(左树比右树高一层)
	 *
	 * ③X的左子树是满二叉树，右子树也是满二叉树，右子树的高度 + 1 = 左子树的高度(左树比右树高一层)
	 *
	 * ④X的左子树是满二叉树，右子树是完全二叉树(但是不满)，右子树的高度 = 左子树的高度(左树右树高度一致)
	 *
	 */
	public static boolean isCBT2(Node head) {
		if (head == null) {
			return true;
		}
		return process(head).isCBT;
	}

	// 信息结构
	// 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
	public static class Info {
		public boolean isFull;
		public boolean isCBT;
		public int height;

		public Info(boolean full, boolean cbt, int h) {
			isFull = full;
			isCBT = cbt;
			height = h;
		}
	}

	public static Info process(Node X) {
		// 边界条件
		if (X == null) {
			return new Info(true, true, 0);
		}
		// 我左子树的信息
		Info leftInfo = process(X.left);
		// 我右子树的信息
		Info rightInfo = process(X.right);
		// 高度初始化
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		// 是否满二叉树 初始化(左树满&右树满&左右树高度一致)
		boolean isFull = leftInfo.isFull 
				&& 
				rightInfo.isFull 
				&& leftInfo.height == rightInfo.height;
		// 是否完全二叉树 初始化
		boolean isCBT = false;
		// 如果满二叉树，那肯定是完全二叉树
		if (isFull) {
			isCBT = true;
		} else { // 以x为头整棵树，不满
			// 我的左子树和右子树都得是完全二叉树(情况②③④)
			if (leftInfo.isCBT && rightInfo.isCBT) {
				// 情况②
				if (leftInfo.isCBT 
						&& rightInfo.isFull 
						&& leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				// 情况③
				if (leftInfo.isFull 
						&& 
						rightInfo.isFull 
						&& leftInfo.height == rightInfo.height + 1) {
					isCBT = true;
				}
				// 情况④
				if (leftInfo.isFull 
						&& rightInfo.isCBT && leftInfo.height == rightInfo.height) {
					isCBT = true;
				}
			}
		}
		return new Info(isFull, isCBT, height);
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
		int maxLevel = 5;
		int maxValue = 100;
		int testTimes = 1000000;
		for (int i = 0; i < testTimes; i++) {
			Node head = generateRandomBST(maxLevel, maxValue);
			if (isCBT1(head) != isCBT2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
