package com.lane.interview.algorithm_workbook.p03_queue_stack;


import java.util.Stack;

/**
 * 构建一个每次弹出最小值的栈
 *
 * @ Author:  duenpu
 * @ Date  :  12:46 2024/1/27
 */
public class Q03_最小值栈 {

    /**
     * 两个栈
     * 一个数据栈  一个记录最小值的栈
     * 数据栈加入数据的同时，最小值栈也加入数据
     * 当最小值栈中新加入的数据比栈顶数据大时，将栈顶数据重复入栈
     * 当最小值栈中新加入的数据比栈顶数据小时，将新数据入栈
     */

    public static class MyStack2 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public MyStack2() {
            stackData = new Stack<Integer>();
            stackMin = new Stack<Integer>();
        }

        public void push(int newNum) {
            if (stackMin.isEmpty() || newNum < getmin()) {
                stackMin.push(newNum);
            } else {
                stackMin.push(stackMin.peek());
            }
            stackData.push(newNum);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            stackMin.pop();
            return stackData.pop();
        }

        public int getmin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("Your stack is empty.");
            }
            return stackMin.peek();
        }
    }

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

        public void pop() {
            minStack.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return this.minStack.peek();
        }
    }

}
