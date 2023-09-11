package com.lane.interview.algorithm.day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 一棵二叉树每两个节点之间都存在距离，求整棵树的最大距离
 */
public class Demo6_MaxDistance {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int maxDistance1(Node head) {
		if (head == null) {
			return 0;
		}
		ArrayList<Node> arr = getPrelist(head);
		HashMap<Node, Node> parentMap = getParentMap(head);
		int max = 0;
		for (int i = 0; i < arr.size(); i++) {
			for (int j = i; j < arr.size(); j++) {
				max = Math.max(max, distance(parentMap, arr.get(i), arr.get(j)));
			}
		}
		return max;
	}

	public static ArrayList<Node> getPrelist(Node head) {
		ArrayList<Node> arr = new ArrayList<>();
		fillPrelist(head, arr);
		return arr;
	}

	public static void fillPrelist(Node head, ArrayList<Node> arr) {
		if (head == null) {
			return;
		}
		arr.add(head);
		fillPrelist(head.left, arr);
		fillPrelist(head.right, arr);
	}

	public static HashMap<Node, Node> getParentMap(Node head) {
		HashMap<Node, Node> map = new HashMap<>();
		map.put(head, null);
		fillParentMap(head, map);
		return map;
	}

	public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
		if (head.left != null) {
			parentMap.put(head.left, head);
			fillParentMap(head.left, parentMap);
		}
		if (head.right != null) {
			parentMap.put(head.right, head);
			fillParentMap(head.right, parentMap);
		}
	}

	public static int distance(HashMap<Node, Node> parentMap, Node o1, Node o2) {
		HashSet<Node> o1Set = new HashSet<>();
		Node cur = o1;
		o1Set.add(cur);
		while (parentMap.get(cur) != null) {
			cur = parentMap.get(cur);
			o1Set.add(cur);
		}
		cur = o2;
		while (!o1Set.contains(cur)) {
			cur = parentMap.get(cur);
		}
		Node lowestAncestor = cur;
		cur = o1;
		int distance1 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance1++;
		}
		cur = o2;
		int distance2 = 1;
		while (cur != lowestAncestor) {
			cur = parentMap.get(cur);
			distance2++;
		}
		return distance1 + distance2 - 1;
	}

//	public static int maxDistance2(Node head) {
//		return process(head).maxDistance;
//	}
//
//	public static class Info {
//		public int maxDistance;
//		public int height;
//
//		public Info(int dis, int h) {
//			maxDistance = dis;
//			height = h;
//		}
//	}
//
//	public static Info process(Node X) {
//		if (X == null) {
//			return new Info(0, 0);
//		}
//		Info leftInfo = process(X.left);
//		Info rightInfo = process(X.right);
//		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
//		int maxDistance = Math.max(
//				Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
//				leftInfo.height + rightInfo.height + 1);
//		return new Info(maxDistance, height);
//	}

	/**
	 * 分情况讨论
	 * 对于节点X来讲
	 * 1.如果最大距离这条路不经过X
	 * ①最大距离就是X左子树上的最大距离
	 * ②最大距离就是X右子树上的最大距离
	 *
	 * 2.如果最大距离这条路经过X
	 * ①X左子树最远的点到X的距离(左子树高度) + X右子树最远的点到X的距离(右子树高度) + 1
	 */
	public static int maxDistance2(Node head) {
		return process(head).maxDistance;
	}

	// 信息结构
	// 要得到左右子树的两个信息：1.最大距离 2.树的高度
	public static class Info {
		public int maxDistance;
		public int height;

		public Info(int m, int h) {
			maxDistance = m;
			height = h;
		}

	}

	public static Info process(Node x) {
		// 边界条件
		if (x == null) {
			return new Info(0, 0);
		}
		// 我左子树的信息
		Info leftInfo = process(x.left);
		// 我右子树的信息
		Info rightInfo = process(x.right);
		// 我自己的高度
		int height = Math.max(leftInfo.height, rightInfo.height) + 1;
		// 我左子树的最大距离
		int p1 = leftInfo.maxDistance;
		// 我右子树的最大距离
		int p2 = rightInfo.maxDistance;
		// 我左子树的高度 + 我右子树的高度 + 1
		int p3 = leftInfo.height + rightInfo.height + 1;
		// 最大距离就是这三种情况里，最大值
		int maxDistance = Math.max(Math.max(p1, p2), p3);
		return new Info(maxDistance, height);
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
			if (maxDistance1(head) != maxDistance2(head)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("finish!");
	}

}
