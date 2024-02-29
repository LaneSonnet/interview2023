package com.lane.interview.algorithm_workbook.p10_tree;

import java.util.PriorityQueue;

/**
 * @ Author:  duenpu
 * @ Date  :  00:24 2024/2/29
 */
public class Q10_二叉搜索树中第K小的元素 {

    // https://leetcode.cn/problems/kth-smallest-element-in-a-bst/description/?envType=study-plan-v2&envId=top-100-liked
    int num = 0;
    int res;

    public int kthSmallest(TreeNode root, int k) {
        inorderTraversal(root, k);
        return res;
    }

    private void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, k);
        num++;
        if (num == k) {
            res = node.val;
            return;
        }
        inorderTraversal(node.right, k);
    }
}
