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
    class Solution1 {
        List<String> res = new ArrayList<>();

        public List<String> generateParenthesis(int n) {
            dfs(n, n, "");
            return res;
        }

        private void dfs(int left, int right, String curStr) {
            if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
                res.add(curStr);
                return;
            }
            if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
                dfs(left - 1, right, curStr + "(");
            }
            if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
                dfs(left, right - 1, curStr + ")");
            }
        }
    }
}
