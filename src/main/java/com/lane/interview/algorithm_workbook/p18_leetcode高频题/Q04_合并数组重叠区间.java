package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.*;

/**
 * @ Author:  duenpu
 * @ Date  :  23:51 2024/2/25
 */
public class Q04_合并数组重叠区间 {
    // https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-100-liked

    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length < 2) {
                return intervals;
            }
            Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
            List<int[]> mergeList = new ArrayList<>();
            for (int[] interval : intervals) {
                int l = interval[0];
                int r = interval[1];
                if (mergeList.isEmpty() || mergeList.get(mergeList.size() - 1)[1] < l) {
                    mergeList.add(interval);
                } else {
                    mergeList.get(mergeList.size() - 1)[1] = Math.max(mergeList.get(mergeList.size() - 1)[1], r);
                }
            }
            return mergeList.toArray(new int[mergeList.size()][]);
        }
    }
}
