package com.lane.interview.algorithm_workbook.p07_heap;

import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 有序矩阵中第 K 小的元素
 *
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *
 * 你必须找到一个内存复杂度优于 O(n2) 的解决方案。
 *
 * https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/description/
 *
 * @author duenpu
 * @date 2024/1/30 20:04
 */
public class Q06_有序矩阵中第K小的元素 {
/*    思路分析：
    要找第k小的元素，一种最常规的做法就是使用优先队列。
    找第k小的元素，就保留k个最小的元素，其中最大的那个就是答案，所以可以使用最大优先队列。
    遍历矩阵中的元素，将元素添加到队列中，如果队列中元素数目MaxPQ.size() > k，就将堆顶最大的元素弹出。
    遍历结束后弹出堆顶元素，就是最小的k个元素中最大的，即第k小的元素。
    这里可以利用矩阵的有序性做一点小的优化：
    如果在遍历的过程中，队列中的元素数目已经为k了，且如果当前元素大于堆顶元素，这个元素放入队列中还会被弹出，所以就没必要放入。
    并且遍历的内循环是从某一行的从左到右遍历，当前元素的右边元素比当前元素更大，也没必要放入队列，所以当MaxPQ.size() == k && num > MaxPQ.peek()，直接打断内循环，进行下一行的遍历。*/
    class Solution {
        public int kthSmallest(int[][] matrix, int k) {
            PriorityQueue<Integer> MaxPQ = new PriorityQueue<>(Collections.reverseOrder());
            for (int[] row : matrix) {
                for (int num : row) {
                    if (MaxPQ.size() == k && num > MaxPQ.peek()) {
                        break;
                    }
                    MaxPQ.add(num);
                    if (MaxPQ.size() > k) {
                        MaxPQ.remove();
                    }
                }
            }
            return MaxPQ.remove();
        }
    }
}
