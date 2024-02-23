package com.lane.interview.algorithm_workbook.p14_dp;

import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;
import com.lane.interview.algorithm_workbook.p11_tree_dp.Q05_打家劫舍;

import java.util.Arrays;

/**
 * @ Author:  duenpu
 * @ Date  :  00:48 2024/2/22
 */
public class Q14_打家劫舍 {
    // https://leetcode.cn/problems/house-robber/description
    /*
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     * */
    class Solution1 {
        public int rob(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            // 0~k-1 范围能偷的最大钱数
            int[] dp = new int[nums.length + 1];
            dp[0] = 0;
            dp[1] = nums[0];
            for (int index = 2; index <= nums.length; index++) {
                dp[index] = Math.max(dp[index - 1], dp[index - 2] + nums[index - 1]);
            }
            return dp[nums.length];
        }
    }

    // https://leetcode.cn/problems/house-robber-ii/description/
    /*
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，
     * 这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
     * */
    class Solution2 {
        public int rob(int[] nums) {
            if (nums.length == 1) {
                return nums[0];
            }
            if (nums.length == 2) {
                return Math.max(nums[0],nums[1]);
            }
            //进行区间选取  是不是很简单~
            return Math.max(process(Arrays.copyOfRange(nums,0,nums.length-1)),process(Arrays.copyOfRange(nums,1,nums.length)));
        }

        public int process(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            // 0~k-1 范围能偷的最大钱数
            int[] dp = new int[nums.length + 1];
            dp[0] = 0;
            dp[1] = nums[0];
            for (int index = 2; index <= nums.length; index++) {
                dp[index] = Math.max(dp[index - 1], dp[index - 2] + nums[index - 1]);
            }
            return dp[nums.length];
        }
    }

    // https://leetcode.cn/problems/house-robber-iii/description/
    /*
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
     * 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，
     * 聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
     * 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     * 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
     *
     * */
    class Solution3 {
        public int rob(TreeNode root) {
            Info ans = process(root);
            return Math.max(ans.yes, ans.no);
        }

        public class Info {
            int yes;
            int no;

            public Info(int a, int b) {
                this.yes = a;
                this.no = b;
            }
        }

        private Info process(TreeNode root) {
            if (root == null) {
                return new Info(0, 0);
            }
            Info left = process(root.left);
            Info right = process(root.right);
            int yes = root.val;
            int no = 0;
            yes += left.no + right.no;
            no += Math.max(left.yes, left.no) + Math.max(right.yes, right.no);
            return new Info(yes, no);
        }
    }

}
