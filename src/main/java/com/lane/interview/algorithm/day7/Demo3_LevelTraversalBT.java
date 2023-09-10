package com.lane.interview.algorithm.day7;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 */
public class Demo3_LevelTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 层序遍历
	 * 队列实现
	 * ①头结点入队
	 * ----循环开始----
	 * ②弹出队头元素，打印
	 * ③如果队头元素有左孩子，将左孩子入队
	 * ④如果队头元素有右孩子，将右孩子入队
	 * ----循环结束----
	 */
	public static void level(Node head) {
		if (head == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		level(head);
		System.out.println("========");
	}

}
