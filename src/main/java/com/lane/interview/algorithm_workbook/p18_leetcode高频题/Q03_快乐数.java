package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.HashSet;
import java.util.Set;

/**
 * @ Author:  duenpu
 * @ Date  :  21:02 2024/2/24
 */
public class Q03_快乐数 {
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
