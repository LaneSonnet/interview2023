package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.Arrays;

/**
 * @ Author:  duenpu
 * @ Date  :  23:19 2024/2/19
 */
public class Q12_俄罗斯套娃 {
    // https://leetcode.cn/problems/russian-doll-envelopes
    class Solution {
        public int maxEnvelopes1(int[][] envelopes) {
            if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
                return 0;
            }
            // 对信封按宽度升序排序，如果宽度相同则按高度降序排序
            // 为什么？因为a和a+1宽度相同,a想放进a+1里面，a+1的高度应该比a尽量大(宽度已经排好序了)
            Arrays.sort(envelopes, (a, b) ->
                    a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]
            );
            // 从0号信封开始递归
            // 每一个信封看前一个是否能装进去
            return process(envelopes, 0, -1);
        }

        // 暴力递归
        private int process(int[][] envelopes, int index, int preIndex) {
            if (index == envelopes.length) {
                return 0;
            }
            // 不要index的信封
            int p1 = process(envelopes, index + 1, index);
            // 要index的信封
            int p2 = 0;
            // 第一个信封 或者 前一个的宽度严格小于自己，才+1
            if (preIndex == -1 || envelopes[preIndex][1] < envelopes[index][1]) {
                p2 += (1 + process(envelopes, index + 1, index));
            }
            return Math.max(p1, p2);
        }

        // 动态规划
        // dp[index]
        // index：0~envelopes.length
        public int maxEnvelopes(int[][] envelopes) {
            if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
                return 0;
            }
            // 对信封按宽度升序排序，如果宽度相同则按高度降序排序
            // 为什么？因为a和a+1宽度相同,a想放进a+1里面，a+1的高度应该比a尽量大(宽度已经排好序了)
            Arrays.sort(envelopes, (a, b) ->
                    a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]
            );
            int size = envelopes.length;
            int[] dp = new int[size + 1];
            for (int i = size - 1; i >= 0; i--) {
                int p1 = dp[i + 1];
                int p2 = 0;
                if (i == 0 || envelopes[i - 1][1] < envelopes[i][1]) {
                    p2 += (1 + dp[i + 1]);
                }
                dp[i] = Math.max(p1,p2);
            }
            return dp[0];
        }
    }
}
