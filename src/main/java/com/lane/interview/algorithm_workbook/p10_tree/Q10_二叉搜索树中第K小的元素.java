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
    // 二叉搜索树中第K大的元素
    public int kthLargest(TreeNode root, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        dfs(root, queue, k);
        return queue.peek();
    }
    private void dfs(TreeNode root, PriorityQueue<Integer> queue, int k) {
        if (root == null) {
            return;
        }
        if (queue.size() < k) {
            queue.offer(root.val);
        } else if (queue.peek() < root.val) {
            queue.poll();
            queue.offer(root.val);
        }
        dfs(root.left, queue, k);
        dfs(root.right, queue, k);
    }
}
