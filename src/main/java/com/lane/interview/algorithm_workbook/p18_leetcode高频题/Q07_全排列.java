package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  23:45 2024/3/2
 */
public class Q07_全排列 {
    // https://leetcode.cn/problems/permutations/
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return ans;
            }
            process(ans, nums, 0);
            return ans;
        }

        private void process(List<List<Integer>> ans, int[] nums, int index) {
            if (index == nums.length) {
                List<Integer> tmp = new ArrayList<>();
                for (int num : nums) {
                    tmp.add(num);
                }
                ans.add(tmp);
            }
            for (int i = index; i < nums.length; i++) {
                swap(nums, index, i);
                process(ans, nums, index + 1);
                swap(nums, index, i); // 恢复原始状态
            }
        }

        public void swap(int[] nums, int a, int b) {
            if (a == b) {
                return;
            }
            nums[a] = nums[a] ^ nums[b];
            nums[b] = nums[a] ^ nums[b];
            nums[a] = nums[a] ^ nums[b];
        }
    }

}
