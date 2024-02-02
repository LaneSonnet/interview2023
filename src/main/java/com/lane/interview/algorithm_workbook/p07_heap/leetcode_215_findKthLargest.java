package com.lane.interview.algorithm_workbook.p07_heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 *
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 *
 * @author duenpu
 * @date 2024/1/30 20:04
 */
public class leetcode_215_findKthLargest {

    class Solution {
        public int findKthLargest(int[] nums, int k) {
            PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int i = 0; i < k; i++) {
                heap.add(nums[i]);
            }
            for (int j = k; j < nums.length; j++) {
                Integer top = heap.peek();
                if (nums[j] >= top) {
                    heap.poll();
                    heap.add(nums[j]);
                }
            }
            return heap.peek();
        }
    }
}
