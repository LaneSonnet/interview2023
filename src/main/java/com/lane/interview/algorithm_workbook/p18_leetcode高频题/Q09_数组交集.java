package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 数组交集
 *
 *
 *
 * @ Author:  duenpu
 * @ Date  :  00:10 2023/9/17
 */
public class Q09_数组交集 {

    // https://leetcode.cn/problems/intersection-of-two-arrays/

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                set.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    // https://leetcode.cn/problems/intersection-of-two-arrays-ii/description/

    class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int length1 = nums1.length, length2 = nums2.length;
            int[] intersection = new int[Math.min(length1, length2)];
            int index1 = 0, index2 = 0, index = 0;
            while (index1 < length1 && index2 < length2) {
                if (nums1[index1] < nums2[index2]) {
                    index1++;
                } else if (nums1[index1] > nums2[index2]) {
                    index2++;
                } else {
                    intersection[index] = nums1[index1];
                    index1++;
                    index2++;
                    index++;
                }
            }
            return Arrays.copyOfRange(intersection, 0, index);
        }
    }
}