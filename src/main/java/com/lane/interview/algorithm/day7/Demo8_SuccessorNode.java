package com.lane.interview.algorithm.day7;

/**
 * 二叉树的后继节点
 * 后继结点：在二叉树的中序遍历的序列中，node的下一个节点叫做node的后继节点
 */
public class Demo8_SuccessorNode {

	public static class Node {
		public int value;
		public Node left;
		public Node right;
		// 指向父亲节点的指针
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * 分情况讨论：
	 * 1. 如果node有右子树，那么node的后继节点就是右子树上最左的节点
	 * 2. 如果node没有右子树，那么node的后继节点就是往上找，直到找到一个节点是其父亲节点的左孩子，那么这个父亲节点就是node的后继节点(node到底是哪一个节点的左子树的最右节点，这个节点就是node的后继结点)
	 * 3. 如果node是整棵树最右的节点，那么node的后继节点就是null
	 */
	public static Node getSuccessorNode(Node node) {
		if (node == null) {
			return node;
		}
		if (node.right != null) {
			return getLeftMost(node.right);
		} else { // 无右子树
			Node parent = node.parent;
			// 什么时候跳出循环
			// 1. 我来到一个节点，我是我父亲的做孩子，我就跳出循环
			// 2. 我来到一个节点，我的父亲是空，说明我是整棵树最右的节点，我就跳出循环
			while (parent != null && parent.right == node) { // 当前节点是其父亲节点右孩子
				// 我和我父亲 一起往上串
				node = parent;
				parent = node.parent;
			}
			return parent;
		}
	}

	public static Node getLeftMost(Node node) {
		if (node == null) {
			return node;
		}
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	public static void main(String[] args) {
		Node head = new Node(6);
		head.parent = null;
		head.left = new Node(3);
		head.left.parent = head;
		head.left.left = new Node(1);
		head.left.left.parent = head.left;
		head.left.left.right = new Node(2);
		head.left.left.right.parent = head.left.left;
		head.left.right = new Node(4);
		head.left.right.parent = head.left;
		head.left.right.right = new Node(5);
		head.left.right.right.parent = head.left.right;
		head.right = new Node(9);
		head.right.parent = head;
		head.right.left = new Node(8);
		head.right.left.parent = head.right;
		head.right.left.left = new Node(7);
		head.right.left.left.parent = head.right.left;
		head.right.right = new Node(10);
		head.right.right.parent = head.right;

		Node test = head.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.left.right.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.left;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right;
		System.out.println(test.value + " next: " + getSuccessorNode(test).value);
		test = head.right.right; // 10's next is null
		System.out.println(test.value + " next: " + getSuccessorNode(test));
	}

}
