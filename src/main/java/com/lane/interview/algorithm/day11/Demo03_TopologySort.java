package com.lane.interview.algorithm.day11;

import java.util.*;

public class Demo03_TopologySort {

	// directed graph and no loop
	public static List<Node> sortedTopology(Graph graph) {
		// key 某个节点   value 剩余的入度
		HashMap<Node, Integer> inMap = new HashMap<>();
		// 只有剩余入度为0的点，才进入这个队列
		Queue<Node> zeroInQueue = new LinkedList<>();
		// 初始化inMap和zeroInQueue
		for (Node node : graph.nodes.values()) {
			inMap.put(node, node.in);
			if (node.in == 0) {
				zeroInQueue.add(node);
			}
		}
		List<Node> result = new ArrayList<>();
		// 如果zeroInQueue不为空，就弹出
		while (!zeroInQueue.isEmpty()) {
			Node cur = zeroInQueue.poll();
			result.add(cur);
			// 遍历cur的所有邻居
			for (Node next : cur.nexts) {
				// cur的邻居的入度减1
				inMap.put(next, inMap.get(next) - 1);
				// 如果减1之后，入度为0，就进入zeroInQueue
				if (inMap.get(next) == 0) {
					zeroInQueue.add(next);
				}
			}
		}
		return result;
	}
}
