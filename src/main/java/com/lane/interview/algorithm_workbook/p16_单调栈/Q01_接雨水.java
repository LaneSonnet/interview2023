package com.lane.interview.algorithm_workbook.p16_单调栈;

/**
 * @ Author:  duenpu
 * @ Date  :  13:11 2024/2/24
 */
public class Q01_接雨水 {
    // https://leetcode.cn/problems/trapping-rain-water/

    // https://leetcode.cn/problems/trapping-rain-water/solutions/9112/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
    class Solution {
        public int trap(int[] height) {
            int sum = 0;
            int[] max_left = new int[height.length];
            int[] max_right = new int[height.length];

            for (int i = 1; i < height.length - 1; i++) {
                max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
            }
            for (int i = height.length - 2; i >= 0; i--) {
                max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
            }
            for (int i = 1; i < height.length - 1; i++) {
                int min = Math.min(max_left[i], max_right[i]);
                if (min > height[i]) {
                    sum = sum + (min - height[i]);
                }
            }
            return sum;
        }
    }
}
