package com.lane.interview.algorithm_workbook.p07_binarySearch;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 数组交集
 *
 * https://leetcode.cn/problems/intersection-of-two-arrays/
 *
 * @ Author:  duenpu
 * @ Date  :  00:10 2023/9/17
 */
public class Q05_intersectionOfTwoArrays {

    /**
     * 二分查找不是最优解
     * <p>
     * 先排序数组2
     * 再遍历数组1
     * 看数组2里含不含数组1的这个元素
     * 如果含，那就放set里去重
     * <p>
     * 最后set转数组
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (int target : nums1) {
            if (binarySearch(nums2, target) && !set.contains(target)) {
                set.add(target);
            }
        }
        int index = 0;
        int[] res = new int[set.size()];
        for (int num : set) {
            res[index++] = num;
        }
        return res;
    }

    public boolean binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return false;
    }
}