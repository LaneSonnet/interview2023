package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @ Author:  duenpu
 * @ Date  :  00:18 2024/3/5
 */
public class Q13_和为K的子数组 {
    // https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution {
        public int subarraySum(int[] nums, int k) {
            // key：前缀和，value：key 对应的前缀和的个数
            Map<Integer, Integer> preSumFreq = new HashMap<>();
            // 对于下标为 0 的元素，前缀和为 0，个数为 1
            preSumFreq.put(0, 1);

            int preSum = 0;
            int count = 0;
            for (int num : nums) {
                preSum += num;
                // 先获得前缀和为 preSum - k 的个数，加到计数变量里
                if (preSumFreq.containsKey(preSum - k)) {
                    count += preSumFreq.get(preSum - k);
                }
                // 然后维护 preSumFreq 的定义
                preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
            }
            return count;
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

    // 组合总和
    // 每个元素只能使用一次
    // **不使用标记数组**
    class Solution2 {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        int sum = 0;

        public List<List<Integer>> combinationSum2( int[] candidates, int target ) {
            //为了将重复的数字都放到一起，所以先进行排序
            Arrays.sort( candidates );
            backTracking( candidates, target, 0 );
            return res;
        }
        private void backTracking( int[] candidates, int target, int start ) {
            if ( sum == target ) {
                res.add( new ArrayList<>( path ) );
                return;
            }
            for ( int i = start; i < candidates.length && sum + candidates[i] <= target; i++ ) {
                //正确剔除重复解的办法
                //跳过同一树层使用过的元素
                if ( i > start && candidates[i] == candidates[i - 1] ) {
                    continue;
                }

                sum += candidates[i];
                path.add( candidates[i] );
                // i+1 代表当前组内元素只选取一次
                backTracking( candidates, target, i + 1 );

                int temp = path.getLast();
                sum -= temp;
                path.removeLast();
            }
        }
    }
    /*找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
    只使用数字1到9
    每个数字 最多使用一次*/
    class Solution3 {
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        public List<List<Integer>> combinationSum3(int k, int n) {
            build(k, n, 1, 0);
            return ans;
        }
        private void build(int k, int n, int startIndex, int sum) {
            if (sum > n) {
                return;
            }
            if (path.size() > k) {
                return;
            }
            if (sum == n && path.size() == k) {
                ans.add(new ArrayList<>(path));
                return;
            }
            for(int i = startIndex; i <= 9; i++) {
                path.add(i);
                sum += i;
                build(k, n, i + 1, sum);
                sum -= i;
                path.removeLast();
            }
        }
    }
}
