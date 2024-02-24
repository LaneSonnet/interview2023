package com.lane.interview.algorithm_workbook.p03_queue_stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

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
}
