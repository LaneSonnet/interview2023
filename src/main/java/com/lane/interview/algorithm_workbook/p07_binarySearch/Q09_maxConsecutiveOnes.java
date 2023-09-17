package com.lane.interview.algorithm_workbook.p07_binarySearch;

/**
 * @ Author:  duenpu
 * @ Date  :  13:45 2023/9/17
 */
public class Q09_maxConsecutiveOnes {
    /**
     * 给定一个二进制数组 nums ， 计算其中最大连续 1 的个数。
     *
     * https://leetcode.cn/problems/max-consecutive-ones
     */
    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxCount = 0, count = 0;
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        return Math.max(maxCount, count);
    }

    /**
     * 给定一个二进制数组 nums ，如果最多可以翻转一个 0 ，则返回数组中连续 1 的最大个数。
     *
     * 滑动窗口思想
     *
     * https://leetcode.cn/problems/max-consecutive-ones-ii
     */
    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0, count = 0;
        for(int l = 0, r = 0; r < nums.length; r++) {
            if(nums[r] == 0) {
                count++;
                while(count > 1) {
                    count -= nums[l++] == 0 ? 1 : 0;
                }
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    /**
     * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
     *
     * https://leetcode.cn/problems/max-consecutive-ones-iii
     *
     * 解题思路：
     * https://leetcode.cn/problems/max-consecutive-ones-iii/solutions/608931/zui-da-lian-xu-1de-ge-shu-iii-by-leetcod-hw12/
     */
    class Solution {
        public int longestOnes(int[] nums, int k) {
            int n = nums.length;
            int[] P = new int[n + 1];
            for (int i = 1; i <= n; ++i) {
                P[i] = P[i - 1] + (1 - nums[i - 1]);
            }

            int ans = 0;
            for (int right = 0; right < n; ++right) {
                int left = binarySearch(P, P[right + 1] - k);
                ans = Math.max(ans, right - left + 1);
            }
            return ans;
        }

        public int binarySearch(int[] P, int target) {
            int low = 0, high = P.length - 1;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (P[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }
    }
}
