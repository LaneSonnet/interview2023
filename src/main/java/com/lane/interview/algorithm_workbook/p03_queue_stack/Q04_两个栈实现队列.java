package com.lane.interview.algorithm_workbook.p03_queue_stack;


import java.util.Stack;

/**
 * 栈实现队列
 * 一般会考图的宽度优先遍历，本来应该用队列解决，但是让你用栈解决
 *
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class Q04_两个栈实现队列 {
    // https://leetcode.cn/problems/implement-queue-using-stacks
    class MyQueue {
        Stack<Integer> pushStack;
        Stack<Integer> popStack;
        public MyQueue() {
            this.pushStack = new Stack<>();
            this.popStack = new Stack<>();
        }

        private void pushToPop () {
            if (popStack.isEmpty()) {
                while(!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        public void push(int x) {
            pushStack.push(x);
            pushToPop();
        }

        public int pop() {
            pushToPop();
            return popStack.pop();
        }

        public int peek() {
            pushToPop();
            return popStack.peek();
        }

        public boolean empty() {
            pushToPop();
            return popStack.isEmpty();
        }
    }
}
