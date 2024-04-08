package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  23:45 2024/3/2
 */
public class Q07_全排列_数组_字符串 {
    // 数组全排列
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
    // 数组全排列(去重)
    class Solution1 {
        //存放结果
        List<List<Integer>> result = new ArrayList<>();
        //暂存结果
        List<Integer> path = new ArrayList<>();

        public List<List<Integer>> permuteUnique(int[] nums) {
            boolean[] used = new boolean[nums.length];
            Arrays.fill(used, false);
            Arrays.sort(nums);
            backTrack(nums, used);
            return result;
        }

        private void backTrack(int[] nums, boolean[] used) {
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
                // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
                // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
                if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                    continue;
                }
                //如果同⼀树⽀nums[i]没使⽤过开始处理
                if (used[i] == false) {
                    used[i] = true;//标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                    path.add(nums[i]);
                    backTrack(nums, used);
                    path.remove(path.size() - 1);//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                    used[i] = false;//回溯
                }
            }
        }
    }

    // 打印字符串所有全排列
    // https://leetcode.cn/problems/permutations/description
    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }

    /**
     * str: 固定参数
     * index: 来到了str[index]字符，index是位置
     * ans: 收集答案
     */
    public static void g1(char[] str, int index, List<String> ans) {
        // base case
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            // 从index位置开始，后面的每一个字符(包括index自己)都可以来到index位置
            for (int i = index; i < str.length; i++) {
                // 交换
                swap(str, index, i);
                // 递归, index + 1的位置继续
                g1(str, index + 1, ans);
                // 恢复现场
                swap(str, index, i);
            }
        }
    }

    // 打印字符串所有全排列(去重)
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    public static void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            // 这个数组记录之前出现过的字符，256的长度是因为字符的范围是0~255
            // 剪枝思想
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

}
