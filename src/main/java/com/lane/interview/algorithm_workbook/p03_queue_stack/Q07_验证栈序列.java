package com.lane.interview.algorithm_workbook.p03_queue_stack;

import java.util.Stack;

/**
 * @author duenpu
 * @date 2024/3/6 16:25
 */
public class Q07_验证栈序列 {
    /*
    * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
    * 示例 1：
    * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
    * 输出：true
    * 解释：我们可以按以下顺序执行：
    * push(1), push(2), push(3), push(4), pop() -> 4,
    * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
    *
    * */
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
