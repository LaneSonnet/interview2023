package com.lane.interview.algorithm.day11.图;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Demo01_BFS {

	// 从node出发，进行宽度优先遍历

	/**
	 * 1.利用队列实现(需要额外一个set用来去重，因为图结构可能节点与节点之间有回路，入队列的时候也需要入这个set)
	 *
	 * 2.从源节点开始依次按照宽度进队列，然后弹出
	 *
	 * 3.每弹出一个点，把该节点所有的没有进过队列的邻接点放入队列
	 *
	 * 4.直到队列变空
	 */
	public static void bfs(Node start) {
		if (start == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<>();
		HashSet<Node> set = new HashSet<>();
		queue.add(start);
		set.add(start);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			for (Node next : cur.nexts) {
				if (!set.contains(next)) {
					set.add(next);
					queue.add(next);
				}
			}
		}
	}

}
