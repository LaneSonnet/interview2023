package com.lane.interview.algorithm_workbook.p07_heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题
 * 给定很多线段，每个线段都有两个数[start,end]
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class CoverMax {

	/**
	 * 暴力解法
	 * 1. 先找到所有线段的最小值和最大值
	 * 2. 从最小值到最大值，每次加0.5，然后遍历所有线段，看这个数是否在线段的范围内
	 * 3. 如果在，那么计数器+1，最后返回计数器的最大值
	 * 时间复杂度：O(N * M)，N是线段的数量，M是线段的最大值和最小值的差值
	 */
	public static int maxCover1(int[][] lines) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < lines.length; i++) {
			min = Math.min(min, lines[i][0]);
			max = Math.max(max, lines[i][1]);
		}
		int cover = 0;
		for (double p = min + 0.5; p < max; p += 1) {
			int cur = 0;
			for (int i = 0; i < lines.length; i++) {
				if (lines[i][0] < p && lines[i][1] > p) {
					cur++;
				}
			}
			cover = Math.max(cover, cur);
		}
		return cover;
	}

	/**
	 * 一个重合区域的左边界必是某个线段的左边界
	 * 假设以每个线段的左边界都作为重合区域的左边界，那么贯穿这个左边界的线段数最多的，就是题目的答案
	 *
	 * 1. 把所有线段按照左边界从小到大排序
	 * 2. 准备一个空的小根堆
	 * 3. 遍历每一条线段，先判断小根堆是否为空，如果不为空，看堆顶元素是否<=当前线段的左边界，如果是，弹出堆顶元素 （看之前的线段有没有贯穿这个左边界，没贯穿就滚蛋）
	 * 4. 把当前线段的右边界加入小根堆(记录当前线段的右边界)
	 * 5. 每次都更新一下小根堆的大小，最后返回最大的大小(也就是贯穿数，也就相当于重合部分是几条线段)
	 *
	 * 其实就是一个过滤器，所有线段从左到右筛一遍
	 * 时间复杂度：O(N * logN)
	 */
	public static int maxCover2(int[][] m) {
		Line[] lines = new Line[m.length];
		for (int i = 0; i < m.length; i++) {
			lines[i] = new Line(m[i][0], m[i][1]);
		}
		Arrays.sort(lines, new StartComparator());
		// 小根堆，每一条线段的结尾数值，使用默认的
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max = 0;
		for (int i = 0; i < lines.length; i++) {
			// lines[i] -> cur 在黑盒中，把<=cur.start 东西都弹出
			while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
				heap.poll();
			}
			heap.add(lines[i].end);
			max = Math.max(max, heap.size());
		}
		return max;
	}

	public static class Line {
		public int start;
		public int end;

		public Line(int s, int e) {
			start = s;
			end = e;
		}
	}

	public static class EndComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.end - o2.end;
		}

	}

	// 和maxCover2过程是一样的
	// 只是代码更短
	// 不使用类定义的写法
	public static int maxCover3(int[][] m) {
		// m是二维数组，可以认为m内部是一个一个的一维数组
		// 每一个一维数组就是一个对象，也就是线段
		// 如下的code，就是根据每一个线段的开始位置排序
		// 比如, m = { {5,7}, {1,4}, {2,6} } 跑完如下的code之后变成：{ {1,4}, {2,6}, {5,7} }
		Arrays.sort(m, (a, b) -> (a[0] - b[0]));
		// 准备好小根堆，和课堂的说法一样
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max = 0;
		for (int[] line : m) {
			while (!heap.isEmpty() && heap.peek() <= line[0]) {
				heap.poll();
			}
			heap.add(line[1]);
			max = Math.max(max, heap.size());
		}
		return max;
	}

	// for test
	public static int[][] generateLines(int N, int L, int R) {
		int size = (int) (Math.random() * N) + 1;
		int[][] ans = new int[size][2];
		for (int i = 0; i < size; i++) {
			int a = L + (int) (Math.random() * (R - L + 1));
			int b = L + (int) (Math.random() * (R - L + 1));
			if (a == b) {
				b = a + 1;
			}
			ans[i][0] = Math.min(a, b);
			ans[i][1] = Math.max(a, b);
		}
		return ans;
	}

	public static class StartComparator implements Comparator<Line> {

		@Override
		public int compare(Line o1, Line o2) {
			return o1.start - o2.start;
		}

	}

	public static void main(String[] args) {

		Line l1 = new Line(4, 9);
		Line l2 = new Line(1, 4);
		Line l3 = new Line(7, 15);
		Line l4 = new Line(2, 4);
		Line l5 = new Line(4, 6);
		Line l6 = new Line(3, 7);

		// 底层堆结构，heap
		PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
		heap.add(l1);
		heap.add(l2);
		heap.add(l3);
		heap.add(l4);
		heap.add(l5);
		heap.add(l6);

		while (!heap.isEmpty()) {
			Line cur = heap.poll();
			System.out.println(cur.start + "," + cur.end);
		}

		System.out.println("test begin");
		int N = 100;
		int L = 0;
		int R = 200;
		int testTimes = 200000;
		for (int i = 0; i < testTimes; i++) {
			int[][] lines = generateLines(N, L, R);
			int ans1 = maxCover1(lines);
			int ans2 = maxCover2(lines);
			int ans3 = maxCover3(lines);
			if (ans1 != ans2 || ans1 != ans3) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test end");
	}

}
