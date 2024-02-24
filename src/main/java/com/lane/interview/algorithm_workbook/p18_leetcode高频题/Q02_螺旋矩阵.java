package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  12:58 2024/2/24
 */
public class Q02_螺旋矩阵 {
    // https://leetcode.cn/problems/spiral-matrix/description/
    class Solution1 {
        public List<Integer> spiralOrder(int[][] mat) {
            List<Integer> ans = new ArrayList<>();
            int m = mat.length, n = mat[0].length;
            circle(mat, 0, 0, m - 1, n - 1, ans);
            return ans;
        }
        // 遍历 以 (x1, y1) 作为左上角，(x2, y2) 作为右下角形成的「圈」
        void circle(int[][] mat, int x1, int y1, int x2, int y2, List<Integer> ans) {
            if (x2 < x1 || y2 < y1) return;
            // 只有一行时，按「行」遍历
            if (x1 == x2) {
                for (int i = y1; i <= y2; i++) {
                    ans.add(mat[x1][i]);
                }
                return;
            }
            // 只有一列时，按「列」遍历
            if (y1 == y2) {
                for (int i = x1; i <= x2; i++) {
                    ans.add(mat[i][y2]);
                }
                return;
            }
            // 遍历当前「圈」
            for (int i = y1; i < y2; i++) {
                ans.add(mat[x1][i]);
            }
            for (int i = x1; i < x2; i++) {
                ans.add(mat[i][y2]);
            }
            for (int i = y2; i > y1; i--) {
                ans.add(mat[x2][i]);
            }
            for (int i = x2; i > x1; i--) {
                ans.add(mat[i][y1]);
            }
            // 往里收一圈，继续遍历
            circle(mat, x1 + 1, y1 + 1, x2 - 1, y2 - 1, ans);
        }
    }

    // https://leetcode.cn/problems/spiral-matrix-ii/description/

    class Solution2 {
        public int[][] generateMatrix(int n) {
            int[][] ans = new int[n][n];
            circle(0, 0, n - 1, n - 1, 1, ans);
            return ans;
        }
        void circle(int x1, int y1, int x2, int y2, int start, int[][] ans) {
            if (x2 < x1 || y2 < y1) return ;
            if (x1 == x2) {
                ans[x1][y1] = start;
                return;
            }
            int val = start;
            for (int i = y1; i < y2; i++) {
                ans[x1][i] = val++;
            }
            for (int i = x1; i < x2; i++) {
                ans[i][y2] = val++;
            }
            for (int i = y2; i > y1; i--) {
                ans[x2][i] = val++;
            }
            for (int i = x2; i > x1; i--) {
                ans[i][y1] = val++;
            }
            circle(x1 + 1, y1 + 1, x2 - 1, y2 - 1, val, ans);
        }
    }
}
