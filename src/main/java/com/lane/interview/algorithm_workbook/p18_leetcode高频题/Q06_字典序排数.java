package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 *
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 *
 * 示例 1：
 *
 * 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * 示例 2：
 *
 * 输入：n = 2
 * 输出：[1,2]
 *
 * @ Author:  duenpu
 * @ Date  :  22:35 2024/3/6
 */
public class Q06_字典序排数 {
    // https://leetcode.cn/problems/lexicographical-numbers/description/?company_slug=xiaohongshu
    class Solution {
        List<Integer> ans = new ArrayList<>();
        public List<Integer> lexicalOrder(int n) {
            for (int i = 1; i <= 9; i++) {
                dfs(i, n);
            }
            return ans;
        }
        private void dfs(int cur, int limit) {
            if (cur > limit) {
                return;
            }
            ans.add(cur);
            for (int i = 0; i <= 9; i++) {
                dfs(cur * 10 + i, limit);
            }
        }
    }
}
