package com.lane.interview.algorithm_workbook.p10_tree;


/**
 * @ Author:  duenpu
 * @ Date  :  23:41 2024/2/8
 */
public class Q06_中序后继 {
    // Node定义中没有parent指针
    // 且整棵树是二叉搜索树
    // https://leetcode.cn/problems/successor-lcci/description/

/*
    利用 BST 的特性，我们可以根据当前节点 root 与 p 的值大小关系来确定搜索方向：

    若有 root.val <= p.val : 根据 BST 特性可知当前节点 root 及其左子树子节点均满足「值小于等于 p.val」，
    因此不可能是 p 点的后继，我们直接到 root 的右子树搜索 p 的后继（递归处理）；
    若有 root.val > p.val : 当第一次搜索到满足此条件的节点时，在以 root 为根节点的子树中「位于最左下方」的值为 p 的后继，
    但也有可能 root 没有左子树，因此 p 的后继要么在 root 的左子树中（若有），要么是 root 本身，此时我们可以直接到 root 的左子树搜索，若搜索结果为空返回 root，否则返回搜索结果。
*/

    class Solution {
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (root == null) {
                return null;
            }
            if (root.val <= p.val) {
                return inorderSuccessor(root.right, p);
            }
            TreeNode ans = inorderSuccessor(root.left, p);
            return ans == null ? root : ans;
        }
    }
}
