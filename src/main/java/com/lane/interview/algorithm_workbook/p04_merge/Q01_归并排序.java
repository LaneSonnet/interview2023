package com.lane.interview.algorithm_workbook.p04_merge;

/**
 *
 * 归并排序
 *
 * https://leetcode.cn/problems/sort-an-array/description/
 *
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q01_归并排序 {
    class Solution {
        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            process(nums, 0, nums.length - 1);
            return nums;
        }

        private void process(int[] nums, int L, int R) {
            if (L == R) {
                return;
            }
            int M = L + ((R - L) >> 1);
            process(nums, L, M);
            process(nums, M + 1, R);
            merge(nums, L, M, R);
        }

        private void merge(int[] nums, int L, int M, int R) {
            int[] help = new int[R - L + 1];
            int p1 = L;
            int p2 = M + 1;
            int index = 0;
            while (p1 <= M && p2 <= R) {
                help[index++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
            }
            while (p1 <= M) {
                help[index++] = nums[p1++];
            }
            while (p2 <= R) {
                help[index++] = nums[p2++];
            }
            for (int i = 0; i < help.length; i++) {
                nums[L + i] = help[i];
            }
        }
    }
}
