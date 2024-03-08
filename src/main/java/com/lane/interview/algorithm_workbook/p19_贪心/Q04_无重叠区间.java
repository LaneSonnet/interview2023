package com.lane.interview.algorithm_workbook.p19_贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @ Author:  duenpu
 * @ Date  :  10:57 2024/3/8
 */
public class Q04_无重叠区间 {
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
}
