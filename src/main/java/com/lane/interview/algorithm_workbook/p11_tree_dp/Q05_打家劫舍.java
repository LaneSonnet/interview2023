package com.lane.interview.algorithm_workbook.p11_tree_dp;

import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;
import java.util.Arrays;

/**
 * @ Author:  duenpu
 * @ Date  :  00:20 2024/2/11
 */
public class Q05_打家劫舍 {
    // https://leetcode.cn/problems/house-robber/description/
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

    // 树形dp
    // https://leetcode.cn/problems/house-robber-iii/description/
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
            return new Info(0,0);
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
