package com.lane.interview.algorithm_workbook.p03_queue_stack;


import java.util.Stack;

/**
 * 构建一个每次弹出最小值的栈
 *
 * @ Author:  duenpu
 * @ Date  :  12:46 2024/1/27
 */
public class Q03_最小值栈 {
    // https://leetcode.cn/problems/min-stack/description/
    class MinStack {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack() {
            this.stack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int val) {
            if (minStack.isEmpty() || val < minStack.peek()) {
                minStack.push(val);
            } else {
                minStack.push(minStack.peek());
            }
            stack.push(val);
        }

        public int pop() {
            minStack.pop();
            return stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return this.minStack.peek();
        }
    }

}
