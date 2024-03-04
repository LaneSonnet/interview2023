package com.lane.interview.algorithm_workbook.p03_queue_stack;

import java.util.*;

/**
 * @ Author:  duenpu
 * @ Date  :  00:47 2024/2/22
 */
public class Q06_有效括号 {
    // https://leetcode.cn/problems/valid-parentheses
    class Solution {
        public boolean isValid(String s) {
            if (s == null || s.length() == 0) {
                return Boolean.TRUE;
            }
            int size = s.length();
            if (size % 2 == 1) {
                return Boolean.FALSE;
            }
            Map<Character, Character> map = new HashMap<>();
            map.put(')', '(');
            map.put(']', '[');
            map.put('}', '{');
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char index = s.charAt(i);
                if (!map.containsKey(index)) {
                    stack.push(index);
                } else {
                    if (stack.isEmpty() || stack.peek() != map.get(index)) {
                        return Boolean.FALSE;
                    } else {
                        stack.pop();
                    }
                }
            }
            return stack.isEmpty();
        }
    }

    // https://leetcode.cn/problems/generate-parentheses/description/
    public class Solution1 {

        // 做加法
        public List<String> generateParenthesis(int n) {
            List<String> res = new ArrayList<>();
            // 特判
            if (n == 0) {
                return res;
            }

            dfs("", 0, 0, n, res);
            return res;
        }

        /**
         * @param curStr 当前递归得到的结果
         * @param left   左括号已经用了几个
         * @param right  右括号已经用了几个
         * @param n      左括号、右括号一共得用几个
         * @param res    结果集
         */
        private void dfs(String curStr, int left, int right, int n, List<String> res) {
            if (left == n && right == n) {
                res.add(curStr);
                return;
            }

            // 剪枝
            if (left < right) {
                return;
            }

            if (left < n) {
                dfs(curStr + "(", left + 1, right, n, res);
            }
            if (right < n) {
                dfs(curStr + ")", left, right + 1, n, res);
            }
        }
    }
}
