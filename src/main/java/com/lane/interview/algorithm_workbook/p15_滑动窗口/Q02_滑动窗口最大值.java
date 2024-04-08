package com.lane.interview.algorithm_workbook.p15_滑动窗口;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 *  https://leetcode.cn/problems/sliding-window-maximum/
 *
 *
 * @author duenpu
 * @date 2024/1/30 20:04
 */
public class Q02_滑动窗口最大值 {

    class Solution {

        public int[] getMaxWindow(int[] arr, int w) {
            // 边界
            if (arr == null || w < 1 || arr.length < w) {
                return null;
            }
            // qmax 窗口最大值的更新结构
            // qmax里面放下标，为了记录每个值是否过期
            LinkedList<Integer> qmax = new LinkedList<Integer>();
            // 收集了几个数，N-w+1个，可以举个例子观察一下规律
            int[] res = new int[arr.length - w + 1];
            // 给res用，一个一个填好答案
            int index = 0;
            for (int R = 0; R < arr.length; R++) {
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                if (qmax.peekFirst() == R - w) {
                    qmax.pollFirst();
                }
                if (R >= w - 1) {
                    res[index++] = arr[qmax.peekFirst()];
                }
            }
            return res;
        }

        public int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] pair1, int[] pair2) {
                    return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
                }
            });
            for (int i = 0; i < k; ++i) {
                pq.offer(new int[]{nums[i], i});
            }
            int[] ans = new int[n - k + 1];
            ans[0] = pq.peek()[0];
            for (int i = k; i < n; ++i) {
                pq.offer(new int[]{nums[i], i});
                while (pq.peek()[1] <= i - k) {
                    pq.poll();
                }
                ans[i - k + 1] = pq.peek()[0];
            }
            return ans;
        }
    }
}
