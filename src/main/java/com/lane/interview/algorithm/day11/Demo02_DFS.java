package com.lane.interview.algorithm.day11;

import java.util.HashSet;
import java.util.Stack;

public class Demo02_DFS {

	public static void dfs(Node node) {
		if (node == null) {
			return;
		}
		Stack<Node> stack = new Stack<>();
		HashSet<Node> set = new HashSet<>();
		stack.add(node);
		set.add(node);
		// 入栈就打印
		System.out.println(node.value);
		while (!stack.isEmpty()) {
			Node cur = stack.pop();
			// 遍历当前节点的所有邻接点
			for (Node next : cur.nexts) {
				// 如果其中一个邻接点没有被访问过
				if (!set.contains(next)) {
					// 把当前的点重新压回栈
					stack.push(cur);
					// 把当前的这个邻接点也压入栈
					stack.push(next);
					// 把这个邻接点注册到set中
					set.add(next);
					// 打印这个邻接点
					System.out.println(next.value);
					// 退出当前循环
					break;
				}
			}
		}
	}
	
	
	

}
