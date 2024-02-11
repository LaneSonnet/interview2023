package com.lane.interview.algorithm_workbook.p11_tree_dp;

import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;

/**
 * @ Author:  duenpu
 * @ Date  :  00:20 2024/2/11
 */
public class Q05_打家劫舍 {
    // https://leetcode.cn/problems/house-robber/description/


    // https://leetcode.cn/problems/house-robber-ii/description/

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
