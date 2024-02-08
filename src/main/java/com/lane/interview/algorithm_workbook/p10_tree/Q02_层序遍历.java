package com.lane.interview.algorithm_workbook.p10_tree;

import com.lane.interview.algorithm.day7.Demo3_LevelTraversalBT;

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

    // 简单版本——不需要知道每一层，放在一起输出就行
    public static void level(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.println(cur.val);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    // 进阶版本——需要放到二维数组里
    class Solution {

        // 迭代
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<List<Integer>> ans = new ArrayList<>();
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> tmpRes = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode tmp = queue.remove();
                    tmpRes.add(tmp.val);
                    if (tmp.left != null) {
                        queue.add(tmp.left);
                    }
                    if (tmp.right != null) {
                        queue.add(tmp.right);
                    }
                }
                ans.add(tmpRes);
            }
            return ans;
        }

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
}
