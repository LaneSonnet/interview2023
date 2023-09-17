package com.lane.interview.algorithm.day11.图;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

// OJ链接：https://www.lintcode.com/problem/topological-sorting
public class Demo03_TopologicalOrderDFS2 {

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
	// 记录了一个点，和 从这个点出发往后走，能走到多少个点(包含这个点自己，也就是点次，每个点可以重复算，有点像递归序)
	public static class Record {
		public DirectedGraphNode node;
		public long nodes;

		public Record(DirectedGraphNode n, long o) {
			node = n;
			nodes = o;
		}
	}

	// 比较器，按照点次从大到小排序
	public static class MyComparator implements Comparator<Record> {

		@Override
		public int compare(Record o1, Record o2) {
			return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
		}
	}

	/**
	 * X和Y都是节点
	 * 如果从X出发，一直走，能走到80个节点
	 * 如果从Y出发，一直走，能走到100个节点
	 * 那么X的拓扑序一定小于等于Y的拓扑序
	 */
	public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
		HashMap<DirectedGraphNode, Record> order = new HashMap<>();
		// 从每一个点出发，都算一遍点次，写入order缓存
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

	// 当前来到cur点，请返回cur点所到之处，所有的点次！
	// 返回（cur，点次）
	// 缓存！！！！！order   
	//  key : 某一个点的点次，之前算过了！
	//  value : 点次是多少
	public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
		if (order.containsKey(cur)) {
			return order.get(cur);
		}
		// cur的点次之前没算过！
		long nodes = 0;
		for (DirectedGraphNode next : cur.neighbors) {
			// 将cur的所有邻居，都走一遍，把所有点次都累加
			nodes += f(next, order).nodes;
		}
		// cur的所有邻居都走完了，cur的点次就是nodes+1（算上cur自己）
		Record ans = new Record(cur, nodes + 1);
		// 将cur的点次，缓存到order里
		order.put(cur, ans);
		return ans;
	}

}
