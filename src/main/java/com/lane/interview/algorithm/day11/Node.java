package com.lane.interview.algorithm.day11;

import java.util.ArrayList;

// 点结构的描述
public class Node {
	public int value;
	// 多少个节点指向我
	public int in;
	// 我指向多少个节点
	public int out;
	// 从我自己出发，指向了哪些点
	public ArrayList<Node> nexts;
	// 从我自己出发，有哪些箭头
	public ArrayList<Edge> edges;

	public Node(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}
}
