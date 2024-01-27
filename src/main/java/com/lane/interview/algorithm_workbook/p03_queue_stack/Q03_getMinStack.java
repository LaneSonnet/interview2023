package com.lane.interview.algorithm_workbook.p03_queue_stack;


import java.util.Stack;

/**
 * 构建一个每次弹出最小值的栈
 *
 *
 * @ Author:  duenpu
 * @ Date  :  12:46 2024/1/27
 */
public class Q03_getMinStack {

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

}
