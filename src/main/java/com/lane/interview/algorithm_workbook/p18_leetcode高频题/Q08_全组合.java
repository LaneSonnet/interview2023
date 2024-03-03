package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  00:33 2024/3/3
 */
public class Q08_全组合 {
    // 数组的所有子集
    // https://leetcode.cn/problems/subsets/description/
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            backtrack(0, nums, res, new ArrayList<Integer>());
            return res;

        }

        private void backtrack(int i, int[] nums, List<List<Integer>> res, ArrayList<Integer> tmp) {
            res.add(new ArrayList<>(tmp));
            for (int j = i; j < nums.length; j++) {
                tmp.add(nums[j]);
                backtrack(j + 1, nums, res, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    // https://leetcode.cn/problems/combination-sum/description/
    /*
    * 示例 1：
      输入：candidates = [2,3,6,7], target = 7
      输出：[[2,2,3],[7]]
      解释：
      2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
      7 也是一个候选， 7 = 7 。
      仅有这两种组合。
    *
    * */
    // 剪枝优化
    class Solution1 {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(candidates); // 先进行排序
            backtracking(res, new ArrayList<>(), candidates, target, 0, 0);
            return res;
        }

        public void backtracking(List<List<Integer>> res, List<Integer> path, int[] candidates, int target, int sum, int idx) {
            // 找到了数字和为 target 的组合
            if (sum == target) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = idx; i < candidates.length; i++) {
                // 如果 sum + candidates[i] > target 就终止遍历
                if (sum + candidates[i] > target) {
                    break;
                }
                path.add(candidates[i]);
                backtracking(res, path, candidates, target, sum + candidates[i], i);
                path.remove(path.size() - 1); // 回溯，移除路径 path 最后一个元素
            }
        }
    }
}
