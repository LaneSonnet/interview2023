package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.Arrays;

/**
 * @ Author:  duenpu
 * @ Date  :  22:55 2024/4/3
 */
public class Q22_三角形周长 {
    public int largestPerimeter(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        Arrays.sort(A);
        for (int i = n - 1; i >= 2; i--) {
            if (A[i - 2] + A[i - 1] > A[i]) {
                return A[i - 2] + A[i - 1] + A[i];
            }
        }
        return 0;
    }
}
