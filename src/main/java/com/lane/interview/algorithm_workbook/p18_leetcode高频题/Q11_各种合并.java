package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  18:46 2024/3/3
 */
public class Q11_各种合并 {
    /**
     * 合并两个有序数组
     * * https://leetcode.cn/problems/merge-sorted-array/description/
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int tail = m + n - 1;
        while (p1 >= 0 || p2 >= 0) {
            int cur = 0;
            if (p1 < 0) {
                cur = nums2[p2--];
            } else if (p2 < 0) {
                cur = nums1[p1--];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums2[p2--];
            } else {
                cur = nums1[p1--];
            }
            nums1[tail--] = cur;
        }
    }

    /**
     * 合并数组重叠区间
     * * https://leetcode.cn/problems/merge-intervals/description/?envType=study-plan-v2&envId=top-100-liked
     */
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
