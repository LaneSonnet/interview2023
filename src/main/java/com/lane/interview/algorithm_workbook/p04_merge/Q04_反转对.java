package com.lane.interview.algorithm_workbook.p04_merge;

/**
 *
 * 反转对(左>右的二倍)
 * https://leetcode.cn/problems/reverse-pairs/description/
 *
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q04_反转对 {
    class Solution {
        public int reversePairs(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }
            return process(nums, 0, nums.length - 1);
        }

        private int process(int[] nums, int l, int r) {
            if (l == r) {
                return 0;
            }
            int m = l + ((r - l) >> 1);
            return process(nums, l, m) + process(nums, m + 1, r) + merge(nums, l, m, r);
        }

        private int merge(int[] nums, int l, int m, int r) {
            int ans = 0;
            int right = m + 1;
            for (int i = l; i <= m; i++) {
                while (right <= r && (long)nums[i] > (long)2 * nums[right]) {
                    right++;
                }
                ans += right - (m + 1);
            }
            int[] help = new int[r - l + 1];
            int p1 = l;
            int p2 = m + 1;
            int index = 0;
            while (p1 <= m && p2 <= r) {
                help[index++] = nums[p1] <= nums[p2] ? nums[p1++] : nums[p2++];
            }
            while (p1 <= m) {
                help[index++] = nums[p1++];
            }
            while (p2 <= r) {
                help[index++] = nums[p2++];
            }
            for (int j = 0; j < help.length; j++) {
                nums[l + j] = help[j];
            }
            return ans;
        }
    }
}
