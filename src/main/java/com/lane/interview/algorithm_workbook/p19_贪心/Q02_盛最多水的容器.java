package com.lane.interview.algorithm_workbook.p19_贪心;

/**
 * @ Author:  duenpu
 * @ Date  :  00:06 2024/2/25
 */
public class Q02_盛最多水的容器 {
    // https://leetcode.cn/problems/container-with-most-water/description
    class Solution {
        public int maxArea(int[] height) {
            int i = 0, j = height.length - 1, res = 0;
            while(i < j) {
                res = height[i] < height[j] ?
                        Math.max(res, (j - i) * height[i++]):
                        Math.max(res, (j - i) * height[j--]);
            }
            return res;
        }
    }
}
