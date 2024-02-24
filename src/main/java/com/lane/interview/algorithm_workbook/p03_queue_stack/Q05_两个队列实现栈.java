package com.lane.interview.algorithm_workbook.p03_queue_stack;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列实现栈
 * 一般会考图的深度优先遍历，本来应该用栈解决，但是让你用队列解决
 *
 * https://leetcode.cn/problems/implement-stack-using-queues/description/
 */
public class Q05_两个队列实现栈 {

    /**
     * 两个队列导数据,一个数据队列，一个辅助队列
     * 数据队列加入数据,辅助队列为空
     * 数据队列弹出数据,将数据队列中的数据导入辅助队列,直到数据队列中只剩一个数据
     * 将数据队列中的数据弹出,并将数据队列和辅助队列交换
     */
    public static class TwoQueueStack<T> {
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueueStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(T value) {
            queue.offer(value);
        }

        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            help.offer(ans);
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }


    class MyStack {
        Queue<Integer> queue;
        Queue<Integer> help;
        public MyStack() {
            this.queue = new LinkedList<>();
            this.help = new LinkedList<>();
        }

        public void push(int x) {
            queue.add(x);
        }

        public int pop() {
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int ans = queue.poll();
            Queue<Integer> tmp = new LinkedList<>();
            tmp = help;
            help = queue;
            queue = tmp;
            return ans;
        }

        public int top() {
            while (queue.size() > 1) {
                help.add(queue.poll());
            }
            int ans = queue.poll();
            help.add(ans);
            Queue<Integer> tmp = new LinkedList<>();
            tmp = help;
            help = queue;
            queue = tmp;
            return ans;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }
}
