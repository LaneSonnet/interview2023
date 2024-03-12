package com.lane.interview.algorithm_workbook.p03_queue_stack;

import java.util.Stack;

/**
 * @author duenpu
 * @date 2024/3/6 16:25
 */
public class Q07_验证栈序列 {
    // https://leetcode.cn/problems/validate-stack-sequences
    class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> stack = new Stack<>();
            int i = 0;
            for (int num : pushed) {
                stack.push(num); // num 入栈
                while (!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                    stack.pop();
                    i++;
                }
            }
            return stack.isEmpty();
        }
    }
}
