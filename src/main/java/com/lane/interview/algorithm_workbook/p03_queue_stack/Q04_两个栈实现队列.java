package com.lane.interview.algorithm_workbook.p03_queue_stack;


import java.util.Stack;

/**
 * 栈实现队列
 * 一般会考图的宽度优先遍历，本来应该用队列解决，但是让你用栈解决
 *
 * https://leetcode.cn/problems/implement-queue-using-stacks/
 */
public class Q04_两个栈实现队列 {

    /**
     * 两个栈用来导数据，一个进栈一个出栈
     * 注意：
     * push栈必须一次性导完数据，导完后再倒入pop栈
     * pop栈不为空时，不能导数据; pop栈为空时，将push栈的数据倒入pop栈
     */
    public static class TwoStacksQueue {
        public Stack<Integer> stackPush;
        public Stack<Integer> stackPop;

        public TwoStacksQueue() {
            this.stackPush = new Stack<Integer>();
            this.stackPop = new Stack<Integer>();
        }

        // push栈向pop栈倒入数据
        private void pushToPop() {
            // pop栈为空，把push栈全倒过来
            if (stackPop.empty()) {
                while (!stackPush.empty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }

        public void add(int pushInt) {
            stackPush.push(pushInt);
            pushToPop();
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            pushToPop();
            return stackPop.peek();
        }
    }
}
