package com.lane.interview.algorithm_workbook.p19_贪心;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @ Author:  duenpu
 * @ Date  :  10:57 2024/3/8
 */
public class Q04_无重叠区间 {
    /*
    * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
     * */
    // https://leetcode.cn/problems/non-overlapping-intervals
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);
        int n = intervals.length;
        int right = intervals[0][1];
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] >= right) {
                ans++;
                right = intervals[i][1];
            }
        }
        return n - ans;
    }
    // 返回线段数最多的重叠区间，重叠了多少个线段
    public static int maxCover(int[][] m) {
        Arrays.sort(m, (a, b) -> (a[0] - b[0]));
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
}
