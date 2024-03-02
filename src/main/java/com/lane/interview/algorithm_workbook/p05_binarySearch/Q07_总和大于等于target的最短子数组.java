package com.lane.interview.algorithm_workbook.p05_binarySearch;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * https://leetcode.cn/problems/minimum-size-subarray-sum
 *
 * @ Author:  duenpu
 * @ Date  :  23:35 2023/9/16
 */
public class Q07_总和大于等于target的最短子数组 {
    /**
     * 我们申请一个临时数组 sums，其中 sums[i] 表示的是原数组 nums 前 i 个元素的和，
     * 题中说了 “给定一个含有 n 个 正整数 的数组”，既然是正整数，那么相加的和会越来越大，也就是sums数组中的元素是递增的。
     * 我们只需要找到 sums[k]-sums[j]>=s，那么 k-j 就是满足的连续子数组，但不一定是最小的，所以我们要继续找，直到找到最小的为止。
     * 怎么找呢，我们可以使用两个 for 循环来枚举，但这又和第一种暴力求解一样了，
     * 所以我们可以换种思路，求 sums[k]-sums[j]>=s 我们可以求 sums[j]+s<=sums[k]，
     * 那这样就好办了，因为数组sums中的元素是递增的，也就是排序的，我们只需要求出 sum[j]+s 的值，然后使用二分法查找即可找到这个 k。
     * <p>
     * 注意这里的函数 int index = Arrays.binarySearch(sums, target);如果找到就会返回值的下标，如果没找到就会返回一个负数，
     * 这个负数取反之后就是查找的值应该在数组中的位置
     * 举个例子，比如排序数组 [2，5，7，10，15，18，20] 如果我们查找 18，
     * 因为有这个数会返回 18 的下标 5，
     * 如果我们查找 9，因为没这个数会返回 -4（至于这个是怎么得到的，大家可以看下源码，这里不再过多展开讨论），
     * 我们对他取反之后就是3，也就是说如果我们在数组中添加一个 9，他在数组的下标是 3，
     * 也就是第 4 个位置（也可以这么理解，只要取反之后不是数组的长度，那么他就是原数组中第一个比他大的值的下标）
     */
    public int minSubArrayLen(int target, int[] nums) {
        int length = nums.length;
        // 前缀和数组
        int[] sums = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int s = target + sums[i];
            int index = Arrays.binarySearch(sums, s);
            if (index < 0) {
                index = ~index;
            }
            if (index <= length) {
                min = Math.min(min, index - i);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int left = 0;
            int length = nums.length;
            int result = Integer.MAX_VALUE;
            int num = 0;
            for (int i = 0; i < length; i++) {
                num += nums[i];
                while (num >= target) {
                    result = Math.min(result, i - left + 1);
                    num -= nums[left++];
                }
            }
            return result == Integer.MAX_VALUE ? 0 : result;

        }
    }
}
