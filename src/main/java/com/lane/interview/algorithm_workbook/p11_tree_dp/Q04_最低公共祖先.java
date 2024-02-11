package com.lane.interview.algorithm_workbook.p11_tree_dp;

import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;

/**
 * @ Author:  duenpu
 * @ Date  :  23:44 2024/2/10
 */
public class Q04_最低公共祖先 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ans;
    }
    // 信息结构
    // 要得到左右子树的三个信息：1.这棵树上有没有a 2.这棵树上有没有b 3.这棵树上的ab的最低公共祖先(有可能不存在)
    public class Info{
        public boolean findA;
        public boolean findB;
        public TreeNode ans;

        public Info(boolean fA, boolean fB, TreeNode an) {
            findA = fA;
            findB = fB;
            ans = an;
        }
    }
    /**
     * 分情况讨论
     * 对于节点X来讲
     * 1.X不是ab的最低公共祖先
     * ①ab的最低公共祖先在X的左子树上
     * ②ab的最低公共祖先在X的右子树上
     * ③X为头结点的树上，ab不同时存在(只有a或者只有b) ——————————>这种情况下，返回的是a或者b,继续向上找
     *
     * 2.X是ab的最低公共祖先
     * ①ab分别在X的左右子树上
     * ②X本身就是a，b在X的左子树或右子树上
     * ③X本身就是b，a在X的左子树或右子树上
     */
    public Info process(TreeNode x, TreeNode a, TreeNode b) {
        // 边界条件
        if (x == null) {
            return new Info(false, false, null);
        }
        // 我左子树的信息
        Info leftInfo = process(x.left, a, b);
        // 我右子树的信息
        Info rightInfo = process(x.right, a, b);
        // 这棵树上有没有a：1.我就是a || 2.我左子树上有a || 3.我右子树上有a
        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        // 这棵树上有没有b：1.我就是b || 2.我左子树上有b || 3.我右子树上有b
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;
        // 初始化ab的最低公共祖先
        TreeNode ans = null;
        // 如果左子树上有ab的最低公共祖先，那么ans就是左子树上的ab的最低公共祖先
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {// 如果右子树上有ab的最低公共祖先，那么ans就是右子树上的ab的最低公共祖先
            ans = rightInfo.ans;
        } else {// 左右子树上都没有ab的最低公共祖先
            // 如果这棵树上有a和b，那么我就是ab的最低公共祖先(这块有点难理解)
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }
}
