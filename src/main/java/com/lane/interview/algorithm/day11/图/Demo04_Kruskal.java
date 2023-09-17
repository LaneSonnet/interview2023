package com.lane.interview.algorithm.day11.图;

import java.util.*;

//undirected graph only
public class Demo04_Kruskal {

	// Union-Find Set
	public static class UnionFind {
		// key 某一个节点， value key节点往上的节点
		private HashMap<Node, Node> fatherMap;
		// key 某一个集合的代表节点, value key所在集合的节点个数
		private HashMap<Node, Integer> sizeMap;

		public UnionFind() {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}
		
		public void makeSets(Collection<Node> nodes) {
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		private Node findFather(Node n) {
			Stack<Node> path = new Stack<>();
			while(n != fatherMap.get(n)) {
				path.add(n);
				n = fatherMap.get(n);
			}
			while(!path.isEmpty()) {
				fatherMap.put(path.pop(), n);
			}
			return n;
		}

		public boolean isSameSet(Node a, Node b) {
			return findFather(a) == findFather(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aDai = findFather(a);
			Node bDai = findFather(b);
			if (aDai != bDai) {
				int aSetSize = sizeMap.get(aDai);
				int bSetSize = sizeMap.get(bDai);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aDai, bDai);
					sizeMap.put(bDai, aSetSize + bSetSize);
					sizeMap.remove(aDai);
				} else {
					fatherMap.put(bDai, aDai);
					sizeMap.put(aDai, aSetSize + bSetSize);
					sizeMap.remove(bDai);
				}
			}
		}
	}
	
	// 无向图的边的权重，从小到大排序
	public static class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge o1, Edge o2) {
			return o1.weight - o2.weight;
		}

	}

	public static Set<Edge> kruskalMST(Graph graph) {
		UnionFind unionFind = new UnionFind();
		// 所有节点都各自成为单独的集合，初始化并查集
		unionFind.makeSets(graph.nodes.values());
		// 从小的边到大的边，依次弹出，小根堆！
		PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
		for (Edge edge : graph.edges) { // M 条边
			priorityQueue.add(edge);  // O(logM)
		}
		Set<Edge> result = new HashSet<>();
		while (!priorityQueue.isEmpty()) { // M 条边
			// 弹出的边，一定是最小的边
			Edge edge = priorityQueue.poll(); // O(logM)
			// 如果这条边的两个点，不是同一个集合，就说明不会形成环
			if (!unionFind.isSameSet(edge.from, edge.to)) { // O(1)
				// 加入结果集
				result.add(edge);
				// 合并两个集合
				unionFind.union(edge.from, edge.to);
			}
		}
		return result;
	}
}
