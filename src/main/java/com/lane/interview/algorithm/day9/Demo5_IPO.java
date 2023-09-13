package com.lane.interview.algorithm.day9;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 现在要做项目，有很多项目，但是只有一个人，每个项目都有花费和利润，求最多能做多少个项目
 * 限制：最多只能做K个项目，初始资金为W
 *
 * 贪心策略：每次都做利润最大的项目
 * 实现：
 * 1.把所有项目按照花费放入小根堆
 * 2.把小根堆中所有花费小于等于W的项目依次，放入大根堆
 * 3.从大根堆中弹出一个项目，做了这个项目，W增加了这个项目的利润
 * 4.重复2，3步骤，直到大根堆为空或者做了K个项目
 */
public class Demo5_IPO {

	// 最多K个项目
	// W是初始资金
	// Profits[] Capital[] 一定等长
	// 返回最终最大的资金
	public static int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
		PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < Profits.length; i++) {
			minCostQ.add(new Program(Profits[i], Capital[i]));
		}
		// 做K个项目
		for (int i = 0; i < K; i++) {
			// 把小根堆中所有花费小于等于W的项目依次，放入大根堆
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			// 如果大根堆为空，说明从一开始，一个项目都没法做，启动资金太少，直接返回
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			// 从大根堆中弹出一个项目，做了这个项目，W增加了这个项目的利润
			W += maxProfitQ.poll().p;
		}
		return W;
	}

	public static class Program {
		public int p;
		public int c;

		public Program(int p, int c) {
			this.p = p;
			this.c = c;
		}
	}

	// 按照花费从小到大排序
	public static class MinCostComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.c - o2.c;
		}

	}

	// 按照利润从大到小排序
	public static class MaxProfitComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o2.p - o1.p;
		}

	}

}
