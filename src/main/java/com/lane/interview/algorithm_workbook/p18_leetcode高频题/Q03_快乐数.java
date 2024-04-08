package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.HashSet;
import java.util.Set;

/**
 * @ Author:  duenpu
 * @ Date  :  21:02 2024/2/24
 */
public class Q03_快乐数 {
    /*「快乐数」 定义为：

    对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
    然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
    如果这个过程 结果为 1，那么这个数就是快乐数。*/
    // https://leetcode.cn/problems/happy-number/description/
    class Solution {
        public boolean isHappy(int n) {
            Set<Integer> set = new HashSet<>();
            while (n != 1 && !set.contains(n)) {
               set.add(n);
               n = process(n);
            }
            return n == 1;
        }

        private int process(int n) {
            int res = 0;
            while (n > 0) {
                int tmp = n % 10;
                res += tmp * tmp;
                n = n / 10;
            }
            return res;
        }
    }
}
