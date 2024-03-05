package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import com.lane.interview.algorithm_workbook.p02_list.ListNode;

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

    // https://leetcode.cn/problems/merge-two-sorted-lists/description/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
    // https://leetcode.cn/problems/merge-k-sorted-lists/description/
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) >> 1;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }
}
