package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  20:37 2024/2/15
 */
public class Q05_纸牌输赢 {
    // 课上题目

    /**
     * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，玩家A和玩家B依次拿走每张纸牌，
     * 规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，
     * 玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
     */
    class Solution1 {
        // 暴力递归
        public int win1(int[] arr) {
            // 边界
            if (arr == null || arr.length == 0) {
                return 0;
            }
            // 先手在0~arr.length - 1范围上的最好成绩
            int f = f(arr, 0, arr.length - 1);
            // 后手在0~arr.length - 1范围上的最好成绩
            int g = g(arr, 0, arr.length - 1);
            return Math.max(f, g);
        }

        private int f(int[] arr, int left, int right) {
            if (left == right) {
                return arr[left];
            }
            // 我拿最左
            int l = arr[left] + g(arr, left + 1, right);
            // 我拿最右
            int r = arr[right] + g(arr, left, right - 1);
            return Math.max(l, r);
        }

        private int g(int[] arr, int left, int right) {
            if (left == right) {
                return 0;
            }
            // 我拿最左
            int l = f(arr, left + 1, right);
            // 我拿最右
            int r = f(arr, left, right - 1);
            return Math.min(l, r);
        }

        // 动态规划
        // dp[left][right]
        // left范围：0~size
        // right范围：0~size
        public int win2(int[] arr) {
            // 边界
            if (arr == null || arr.length == 0) {
                return 0;
            }
            int size = arr.length;
            int[][] dp1 = new int[size][size];
            int[][] dp2 = new int[size][size];
            for (int i = 0; i < size; i++) {
                dp1[i][i] = arr[i];
            }
            // 遍历对角线
            for (int col = 1; col < size; col++) {
                int L = 0;
                int R = col;
                while (R < size) {
                    dp1[L][R] = Math.max(arr[L] + dp2[L + 1][R], arr[R] + dp2[L][R - 1]);
                    dp2[L][R] = Math.min(dp1[L + 1][R], dp1[L][R - 1]);
                    L++;
                    R++;
                }
            }
            return Math.max(dp1[0][size], dp2[0][size]);
        }
    }


    // 预测赢家
    // https://leetcode.cn/problems/predict-the-winner/description/
    class Solution2 {

        // 暴力递归
        public boolean predictTheWinner1(int[] nums) {
            // 玩家1稳定先手
            int fResult = f1(nums, 0, nums.length - 1);
            int gResult = g1(nums, 0, nums.length - 1);
            return fResult >= gResult ? Boolean.TRUE : Boolean.FALSE;
        }

        private int f1(int[] nums, int left, int right) {
            if (left == right) {
                return nums[left];
            }
            int leftResult = nums[left] + g1(nums, left + 1, right);
            int rightResult = nums[right] + g1(nums, left, right - 1);
            return Math.max(leftResult, rightResult);
        }

        private int g1(int[] nums, int left, int right) {
            if (left == right) {
                return 0;
            }
            int leftResult = f1(nums, left + 1, right);
            int rightResult = f1(nums, left, right - 1);
            return Math.min(leftResult, rightResult);
        }

        // 动态规划
        // dp[left][right]
        public boolean predictTheWinner(int[] nums) {
            int size = nums.length;
            int[][] dp1 = new int[size][size];
            int[][] dp2 = new int[size][size];
            // basecase初始化
            for (int i = 0; i < size; i++) {
                dp1[i][i] = nums[i];
            }
            for (int i = 1; i < size; i++) {
                int row = 0;
                int col = i;
                while (col < size) {
                    dp1[row][col] = Math.max(nums[row] + dp2[row + 1][col], nums[col] + dp2[row][col - 1]);
                    dp2[row][col] = Math.min(dp1[row + 1][col], dp1[row][col - 1]);
                    row++;
                    col++;
                }
            }
            int fResult = dp1[0][size - 1];
            int gResult = dp2[0][size - 1];
            return fResult >= gResult ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    // 我能赢吗
    // https://leetcode.cn/problems/can-i-win/description/
    class Solution3 {

    }
}
