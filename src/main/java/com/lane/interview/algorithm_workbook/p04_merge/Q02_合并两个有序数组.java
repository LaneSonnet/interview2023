package com.lane.interview.algorithm_workbook.p04_merge;

/**
 * 合并两个有序数组
 * * https://leetcode.cn/problems/merge-sorted-array/description/
 *
 * @author duenpu
 * @date 2024/1/30 20:04
 */
public class Q02_合并两个有序数组 {

    class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1;
            int p2 = n - 1;
            int tail = m + n - 1;
            while (p1 >= 0 || p2 >= 0) {
                int cur = 0;
                if (p1 == -1) {
                    cur = nums2[p2--];
                } else if (p2 == -1) {
                    cur = nums1[p1--];
                } else if (nums1[p1] < nums2[p2]) {
                    cur = nums1[p1--];
                } else {
                    cur = nums2[p2--];
                }
                nums1[tail--] = cur;
            }
        }
    }
}
