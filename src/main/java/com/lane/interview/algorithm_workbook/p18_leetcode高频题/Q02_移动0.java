package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

/**
 * 移动0
 * 将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
 * https://leetcode.cn/problems/move-zeroes/
 *
 * @author duenpu
 * @date 2024/1/30 20:22
 */
public class Q02_移动0 {
    class Solution {
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length <= 1) {
                return;
            }
            int index = 0;
            for (int num : nums) {
                if (num != 0) {
                    nums[index] = num;
                    index++;
                }
            }
            while (index < nums.length) {
                nums[index++] = 0;
            }
        }
    }
}
