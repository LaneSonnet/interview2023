package com.lane.interview.algorithm.day11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

// no negative weight
public class Demo06_Dijkstra {

	/**
	 * 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
	 *
	 * 未优化版本
	 */
	public static HashMap<Node, Integer> dijkstra1(Node from) {
		HashMap<Node, Integer> distanceMap = new HashMap<>();
		distanceMap.put(from, 0);
		// 打过对号的点
		HashSet<Node> selectedNodes = new HashSet<>();
		Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
		while (minNode != null) {
			//  原始点  ->  minNode(跳转点)   最小距离distance
			int distance = distanceMap.get(minNode);
			for (Edge edge : minNode.edges) {
				Node toNode = edge.to;
				if (!distanceMap.containsKey(toNode)) {
					distanceMap.put(toNode, distance + edge.weight);
				} else { // toNode已经在了
					distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
				}
			}
			selectedNodes.add(minNode); // 锁住
			// 下一个要解锁的点
			minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
		}
		return distanceMap;
	}

	/**
	 * 从distanceMap中找到一个未被打过对号的节点，且离源节点最近
	 */
	public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
		Node minNode = null;
		int minDistance = Integer.MAX_VALUE;
		for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
			Node node = entry.getKey();
			int distance = entry.getValue();
			if (!touchedNodes.contains(node) && distance < minDistance) {
				minNode = node;
				minDistance = distance;
			}
		}
		return minNode;
	}

	public static class NodeRecord {
		public Node node;
		public int distance;

		public NodeRecord(Node node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}

	public static class NodeHeap {
		private Node[] nodes; // 实际的堆结构
		// key 某一个node， value 上面堆中的位置
		private HashMap<Node, Integer> heapIndexMap;
		// key 某一个节点， value 从源节点出发到该节点的目前最小距离
		private HashMap<Node, Integer> distanceMap;
		private int size; // 堆上有多少个点

		public NodeHeap(int size) {
			nodes = new Node[size];
			heapIndexMap = new HashMap<>();
			distanceMap = new HashMap<>();
			size = 0;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
		// 判断要不要更新，如果需要的话，就更新
		public void addOrUpdateOrIgnore(Node node, int distance) {
			// 如果node就在堆上，只是距离变了，更新距离
			if (inHeap(node)) {
				distanceMap.put(node, Math.min(distanceMap.get(node), distance));
				insertHeapify(heapIndexMap.get(node));
			}
			// 如果node从来就没进过堆，直接加入堆
			if (!isEntered(node)) {
				nodes[size] = node;
				heapIndexMap.put(node, size);
				distanceMap.put(node, distance);
				insertHeapify(size++);
			}
			// 如果node进过堆，直接忽视
		}

		public NodeRecord pop() {
			NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
			// 把堆上最后一个点，放到堆顶上，然后堆的大小减1
			swap(0, size - 1);
			// heapIndexMap中保留的是node在堆上的位置，即使node从堆上被删除了，位置信息也不会消失，只是value会变成-1
			heapIndexMap.put(nodes[size - 1], -1);
			// distanceMap中保留的是node到源节点的距离, 直接删除
			distanceMap.remove(nodes[size - 1]);
			// 删除堆上的最后一个点
			nodes[size - 1] = null;
			// 堆的大小减1， 堆顶下沉操作
			heapify(0, --size);
			return nodeRecord;
		}

		private void insertHeapify(int index) {
			while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
				swap(index, (index - 1) / 2);
				index = (index - 1) / 2;
			}
		}

		private void heapify(int index, int size) {
			int left = index * 2 + 1;
			while (left < size) {
				int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
						? left + 1
						: left;
				smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
				if (smallest == index) {
					break;
				}
				swap(smallest, index);
				index = smallest;
				left = index * 2 + 1;
			}
		}

		// heapIndexMap中保留的是node在堆上的位置，即使node从堆上被删除了，位置信息也不会消失，只是value会变成-1
		private boolean isEntered(Node node) {
			return heapIndexMap.containsKey(node);
		}

		private boolean inHeap(Node node) {
			return isEntered(node) && heapIndexMap.get(node) != -1;
		}

		private void swap(int index1, int index2) {
			heapIndexMap.put(nodes[index1], index2);
			heapIndexMap.put(nodes[index2], index1);
			Node tmp = nodes[index1];
			nodes[index1] = nodes[index2];
			nodes[index2] = tmp;
		}
	}

	// 改进后的dijkstra算法
	// 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
	public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
		NodeHeap nodeHeap = new NodeHeap(size);
		// 难点就是这个方法
		nodeHeap.addOrUpdateOrIgnore(head, 0);
		HashMap<Node, Integer> result = new HashMap<>();
		while (!nodeHeap.isEmpty()) {
			NodeRecord record = nodeHeap.pop();
			Node cur = record.node;
			int distance = record.distance;
			for (Edge edge : cur.edges) {
				nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
			}
			result.put(cur, distance);
		}
		return result;
	}

}
