package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 * @ Author:  duenpu
 * @ Date  :  00:30 2024/3/1
 */
public class Q12_搜索二维矩阵 {
    // https://leetcode.cn/problems/search-a-2d-matrix/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution {
        public boolean searchMatrix(int[][] mat, int t) {
            int m = mat.length, n = mat[0].length;
            // 第一次二分：定位到所在行（从上往下，找到最后一个满足 mat[x]][0] <= t 的行号）
            int l = 0, r = m - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (mat[mid][0] <= t) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int row = r;
            if (mat[row][0] == t) {
                return true;
            }
            if (mat[row][0] > t) {
                return false;
            }
            // 第二次二分：从所在行中定位到列（从左到右，找到最后一个满足 mat[row][x] <= t 的列号）
            l = 0; r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (mat[row][mid] <= t) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            int col = r;

            return mat[row][col] == t;
        }
    }
}
