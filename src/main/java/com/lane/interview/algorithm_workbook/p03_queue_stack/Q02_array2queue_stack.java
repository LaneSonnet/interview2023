package com.lane.interview.algorithm_workbook.p03_queue_stack;


/**
 * 数组实现队列和栈
 * 队列：FIFO  push pop
 * 栈：FILO   push pop
 *
 * @ Author:  duenpu
 * @ Date  :  12:46 2024/1/27
 */
public class Q02_array2queue_stack {

    // 数组实现栈，简单
    public class MyStack {
        private int[] array;
        private int size;
        private int limit;

        public MyStack(int limit) {
            this.limit = limit;
            this.array = new int[limit];
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (isFull()) {
                System.out.println("Stack is full. Cannot push " + value);
                return;
            }
            array[size++] = value;
        }

        public int pop() {
            if (isEmpty()) {
                System.out.println("Stack is empty. Cannot pop.");
                return -1; // or throw an exception
            }
            return array[--size];
        }

        public int peek() {
            if (isEmpty()) {
                System.out.println("Stack is empty. Cannot peek.");
                return -1; // or throw an exception
            }
            return array[size - 1];
        }

        public int size() {
            return size;
        }
    }

    // 数组实现队列，需要构建一个环形数组
    public static class MyQueue {
        private int[] arr;
        private int end;
        private int begin;
        private int size;
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            begin = 0;
            end = 0;
            size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列满了，不能再加了");
            }
            size++;
            arr[begin] = value;
            begin = nextIndex(begin);
        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("队列空了，不能再拿了");
            }
            size--;
            int ans = arr[end];
            end = nextIndex(end);
            return ans;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 如果现在的下标是i，返回下一个位置
        private int nextIndex(int i) {
            return i < limit - 1 ? i + 1 : 0;
        }

    }
}
