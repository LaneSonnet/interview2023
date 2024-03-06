package com.lane.interview.algorithm_workbook.p10_tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树层序遍历
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/description/
 *
 * @ Author:  duenpu
 * @ Date  :  22:08 2024/2/6
 */
public class Q02_层序遍历 {
    class Solution {
        // 递归
        public List<List<Integer>> levelOrder1(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            dfs(0, root, res);
            return res;
        }

        private void dfs(int index, TreeNode root, List<List<Integer>> res) {
            if (index == res.size()) {
                res.add(new ArrayList<>());
            }
            res.get(index).add(root.val);
            if (root.left != null) {
                dfs(index + 1, root.left, res);
            }
            if (root.right != null) {
                dfs(index + 1, root.right, res);
            }
        }
    }

    /**
     * 锯齿形层序遍历
     */
    class Solution1 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            helper(res, root, 0);
            return res;

        }

        private void helper(List<List<Integer>> res, TreeNode root, int depth) {
            if (root == null) {
                return;
            }
            if (res.size() == depth) {
                res.add(new LinkedList<>());
            }
            if (depth % 2 == 0) {
                res.get(depth).add(root.val);
            } else {
                res.get(depth).add(0, root.val);
            }
            helper(res, root.left, depth + 1);
            helper(res, root.right, depth + 1);
        }
    }

}
