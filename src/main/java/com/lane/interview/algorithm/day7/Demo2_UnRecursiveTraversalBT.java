package com.lane.interview.algorithm.day7;

import java.util.Stack;

/**
 * 二叉树的非递归遍历
 */
public class Demo2_UnRecursiveTraversalBT {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int v) {
			value = v;
		}
	}

	/**
	 * 先序遍历
	 * ①头结点入栈
	 * ----循环开始----
	 * ②弹出栈顶元素，打印
	 * ③如果栈顶元素有右孩子，将右孩子入栈
	 * ④如果栈顶元素有左孩子，将左孩子入栈
	 * ----循环结束----
	 */
	public static void pre(Node head) {
		System.out.print("pre-order: ");
		if (head != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(head);
			while (!stack.isEmpty()) {
				head = stack.pop();
				System.out.print(head.value + " ");
				if (head.right != null) {
					stack.push(head.right);
				}
				if (head.left != null) {
					stack.push(head.left);
				}
			}
		}
		System.out.println();
	}

	/**
	 * 中序遍历
	 * ①先从头结点开始，将整棵树的左边界依次入栈
	 * ②左边界走到底了，弹出栈顶元素，打印
	 * ③如果栈顶元素有右孩子，将右孩子作为头结点，重复①
	 * 本质：二叉树可以看成是一个左斜树，从左边界开始，每个节点先搞左子树，然后自己，然后右子树
	 */
	public static void in(Node cur) {
		System.out.print("in-order: ");
		if (cur != null) {
			Stack<Node> stack = new Stack<Node>();
			while (!stack.isEmpty() || cur != null) {
				if (cur != null) {
					stack.push(cur);
					cur = cur.left;
				} else {
					cur = stack.pop();
					System.out.print(cur.value + " ");
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}

	/**
	 * 后序遍历(两个栈实现)
	 * 本质：先序遍历的变形，头 右 左 (逆序)-> 左 右 头
	 * ①头结点入栈1
	 * ----循环开始----
	 * ②栈1顶元素弹出，入栈2
	 * ③如果栈1顶元素有右孩子，将右孩子入栈1
	 * ④如果栈1顶元素有左孩子，将左孩子入栈1
	 * ----循环结束----
	 * ⑤栈2元素依次弹出，打印
	 */
	public static void pos1(Node head) {
		System.out.print("pos-order: ");
		if (head != null) {
			Stack<Node> s1 = new Stack<Node>();
			Stack<Node> s2 = new Stack<Node>();
			s1.push(head);
			while (!s1.isEmpty()) {
				head = s1.pop(); // 头 右 左
				s2.push(head);
				if (head.left != null) {
					s1.push(head.left);
				}
				if (head.right != null) {
					s1.push(head.right);
				}
			}
			// 左 右 头
			while (!s2.isEmpty()) {
				System.out.print(s2.pop().value + " ");
			}
		}
		System.out.println();
	}

	/**
	 * 一个栈实现后序遍历
	 * 可以忽略，太难了
	 */
	public static void pos2(Node h) {
		System.out.print("pos-order: ");
		if (h != null) {
			Stack<Node> stack = new Stack<Node>();
			stack.push(h);
			Node c = null;
			while (!stack.isEmpty()) {
				c = stack.peek();
				if (c.left != null && h != c.left && h != c.right) {
					stack.push(c.left);
				} else if (c.right != null && h != c.right) {
					stack.push(c.right);
				} else {
					System.out.print(stack.pop().value + " ");
					h = c;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);

		pre(head);
		System.out.println("========");
		in(head);
		System.out.println("========");
		pos1(head);
		System.out.println("========");
		pos2(head);
		System.out.println("========");
	}

}
