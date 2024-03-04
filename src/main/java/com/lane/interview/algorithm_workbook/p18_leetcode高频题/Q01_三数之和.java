package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  00:15 2024/2/19
 */
public class Q01_三数之和 {
    // https://leetcode.cn/problems/3sum
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length <= 2) {
                return ans;
            }
            // 先从小到大排序
            Arrays.sort(nums);
            // 遍历nums
            for (int i = 0; i < nums.length; i++) {
                // 如果当前数直接大于0，那就break
                if (nums[i] > 0) {
                    break;
                }
                // 如果当前数与上一位数一样(已经算过了)，那就指针直接右移
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 初始化左右指针，针对i右边的数，从两边向中间遍历
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        // 不要每次都建一个list，会超出内存限制
                        ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                        // 如果left与left的下一位数一样，那就left指针一直右移
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 如果right与right的上一位数一样，那就right指针一直左移
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return ans;
        }
    }
}
