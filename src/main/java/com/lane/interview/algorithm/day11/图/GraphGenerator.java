package com.lane.interview.algorithm.day11.图;

public class GraphGenerator {

	// matrix 所有的边
	// N*3 的矩阵
	// [weight, from节点上面的值，to节点上面的值]
	// 
	// [ 5 , 0 , 7]
	// [ 3 , 0,  1]
	// 
	public static Graph createGraph(int[][] matrix) {
		Graph graph = new Graph();
		for (int i = 0; i < matrix.length; i++) {
			 // 拿到每一条边， matrix[i] 
			int weight = matrix[i][0];
			int from = matrix[i][1];
			int to = matrix[i][2];
			//建from点
			if (!graph.nodes.containsKey(from)) {
				graph.nodes.put(from, new Node(from));
			}
			//建to点
			if (!graph.nodes.containsKey(to)) {
				graph.nodes.put(to, new Node(to));
			}
			//拿到from点
			Node fromNode = graph.nodes.get(from);
			//拿到to点
			Node toNode = graph.nodes.get(to);
			//建边
			Edge newEdge = new Edge(weight, fromNode, toNode);
			//from点的nexts里面加上to点
			fromNode.nexts.add(toNode);
			//from点的out加1
			fromNode.out++;
			//to点的in加1
			toNode.in++;
			//from点的edges里面加上这条边
			fromNode.edges.add(newEdge);
			//图里面加上这条边
			graph.edges.add(newEdge);
		}
		return graph;
	}

}
