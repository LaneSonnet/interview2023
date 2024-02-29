package com.lane.interview.algorithm_workbook.p11_tree_dp;

import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;

/**
 * @ Author:  duenpu
 * @ Date  :  21:36 2024/2/10
 */
public class Q02_最大搜索二叉子树 {

    // 返回子树的节点数量
    // https://leetcode.cn/problems/largest-bst-subtree/description/

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public int largestBSTSubtree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return process(root).maxBSTSize;
        }

        /*
         * 收集4个信息
         * 最大搜索子树大小
         * 整棵树最大值
         * 整棵树最小值
         * 整棵树大小
         * */
        public class Info {
            int maxBSTSize;
            int max;
            int min;
            int allSize;

            public Info(int a, int b, int c, int d) {
                this.maxBSTSize = a;
                this.max = b;
                this.min = c;
                this.allSize = d;
            }
        }

        /*
         * 1.当X不为头结点时
         * ① X左子树的最大搜索子树的大小是答案
         * ② X右子树的最大搜索子树的大小是答案
         *
         * 2.当X为头结点时
         * ①X左子树是BST
         * ②X右子树是BST
         * ③X左子树最大值小于X
         * ④X右子树最小值大于X
         * ⑤X的最大搜索子树=X整棵树的大小，也就是答案
         *
         * 三个答案取最大值
         *
         * */
        private Info process(TreeNode root) {
            if (root == null) {
                return null;
            }
            Info left = process(root.left);
            Info right = process(root.right);
            int max = root.val;
            int min = root.val;
            int allSize = 1;
            // 先搞定max，min，allSize
            if (left != null) {
                max = Math.max(left.max, max);
                min = Math.min(left.min, min);
                allSize += left.allSize;
            }
            if (right != null) {
                max = Math.max(right.max, max);
                min = Math.min(right.min, min);
                allSize += right.allSize;
            }
            // 再来搞定maxBSTSize
            // 左子树的maxBSTSize，右子树的maxBSTSize，root整棵树是BST时整棵树大小，三者取最大值
            int p1 = -1;
            if (left != null) {
                p1 = left.maxBSTSize;
            }
            int p2 = -1;
            if (right != null) {
                p2 = right.maxBSTSize;
            }
            int p3 = -1;
            boolean isleftBST = left == null ? Boolean.TRUE : (left.maxBSTSize == left.allSize);
            boolean isrightBST = right == null ? Boolean.TRUE : (right.maxBSTSize == right.allSize);
            if (isleftBST && isrightBST) {
                boolean isleftless = left == null ? Boolean.TRUE : (left.max < root.val);
                boolean isrightmore = right == null ? Boolean.TRUE : (right.min > root.val);
                if (isleftless && isrightmore) {
                    int leftAllSize = left == null ? 0 : left.allSize;
                    int rightAllSize = right == null ? 0 : right.allSize;
                    p3 = leftAllSize + rightAllSize + 1;
                }
            }
            int maxBSTSize = Math.max(p1, Math.max(p2, p3));
            return new Info(maxBSTSize, max, min, allSize);
        }

    }

    // 返回子树的头结点
    // 思路和上面一样，收集信息里面多一个头结点，不用看高度
    public TreeNode largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return root;
        }
        return process(root).maxBSTHead;
    }

    /*
     * 收集4个信息
     * 最大搜索子树头结点
     * 整棵树最大值
     * 整棵树最小值
     * 最大搜索子树大小
     * */
    public class Info {
        TreeNode maxBSTHead;
        int max;
        int min;
        int maxBSTSize;

        public Info(TreeNode a, int b, int c, int d) {
            this.maxBSTHead = a;
            this.max = b;
            this.min = c;
            this.maxBSTSize = d;
        }
    }

    /*
     * 1.当X不为头结点时
     * ① X左子树的最大搜索子树的头结点是答案
     * ② 或者 X右子树的最大搜索子树的头结点是答案
     * 看左子树的最大搜索子树 和 右子树的最大搜索子树哪个大
     *
     * 2.当X为头结点时
     * ①X左子树是BST
     * ②X右子树是BST
     * ③X左子树最大值小于X
     * ④X右子树最小值大于X
     * ⑤X这个头结点就是答案
     *
     *
     * */
    private Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int min = root.val;
        int max = root.val;
        TreeNode maxBSTHead = null;
        int maxBSTSize = 0;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxBSTHead = leftInfo.maxBSTHead;
            maxBSTSize = leftInfo.maxBSTSize;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if (rightInfo.maxBSTSize > maxBSTSize) {
                maxBSTHead = rightInfo.maxBSTHead;
                maxBSTSize = rightInfo.maxBSTSize;
            }
        }
        if ((leftInfo == null ? true : (leftInfo.maxBSTHead == root.left && leftInfo.max < root.val))
                && (rightInfo == null ? true : (rightInfo.maxBSTHead == root.right && rightInfo.min > root.val))) {
            maxBSTHead = root;
            maxBSTSize = (leftInfo == null ? 0 : leftInfo.maxBSTSize)
                    + (rightInfo == null ? 0 : rightInfo.maxBSTSize) + 1;
        }
        return new Info(maxBSTHead, maxBSTSize, min, max);
    }

}
