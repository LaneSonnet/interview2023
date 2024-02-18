package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.List;

/**
 * https://leetcode.cn/problems/hanota-lcci/description/
 *
 * @ Author:  duenpu
 * @ Date  :  21:00 2024/2/14
 */
public class Q01_汉诺塔 {
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        move(A.size(), A, B, C);
    }

    private void move(Integer size, List<Integer> start, List<Integer> other, List<Integer> end) {
        if (size == 1) {
            end.add(start.remove(start.size() - 1));
            return;
        }
        move(size - 1, start, end, other);
        end.add(start.remove(start.size() - 1));
        move(size - 1, other, start, end);
    }
}
