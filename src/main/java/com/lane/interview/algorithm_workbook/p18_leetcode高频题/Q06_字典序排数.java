package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.List;

/**
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
