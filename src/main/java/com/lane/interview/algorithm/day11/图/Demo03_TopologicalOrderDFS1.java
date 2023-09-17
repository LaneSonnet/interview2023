package com.lane.interview.algorithm.day11.图;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Demo03_TopologicalOrderDFS1 {

	// 不要提交这个类
	public static class DirectedGraphNode {
		public int label;
		public ArrayList<DirectedGraphNode> neighbors;

		public DirectedGraphNode(int x) {
			label = x;
			neighbors = new ArrayList<DirectedGraphNode>();
		}
	}

	// 提交下面的
	// 记录了一个点，和 从这个点出发往后走，能走的深度
	public static class Record {
		public DirectedGraphNode node;
		public int deep;

		public Record(DirectedGraphNode n, int o) {
			node = n;
			deep = o;
		}
	}

	// 比较器，按照深度从大到小排序
	public static class MyComparator implements Comparator<Record> {

		@Override
		public int compare(Record o1, Record o2) {
			return o2.deep - o1.deep;
		}
	}


	/**
	 * X和Y都是节点
	 * 如果从X出发，深度是1000
	 * 如果从Y出发，深度是800
	 * 那么X的拓扑序一定小于等于Y的拓扑序
	 */
	public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Record> order = new HashMap<>();
		for (DirectedGraphNode cur : graph) {
			f(cur, order);
		}
		ArrayList<Record> recordArr = new ArrayList<>();
		for (Record r : order.values()) {
			recordArr.add(r);
		}
		recordArr.sort(new MyComparator());
		ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
		for (Record r : recordArr) {
			ans.add(r.node);
		}
		return ans;
	}

	public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		int follow = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			// 计算最大深度
			follow = Math.max(follow, f(next, order).deep);
		}
		Record ans = new Record(cur, follow + 1);
		order.put(cur, ans);
		return ans;
	}

}
