package com.lane.interview.algorithm.day11.图;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	// 所有点，key是点的编号(其实就可以用点的value来理解成是编号)，value是点这个结构
	public HashMap<Integer, Node> nodes;
	// 所有的边
	public HashSet<Edge> edges;
	
	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
