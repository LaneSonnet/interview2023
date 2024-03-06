package com.lane.interview.algorithm_workbook.p10_tree;

import java.util.*;

/**
 * @ Author:  duenpu
 * @ Date  :  22:54 2024/2/8
 */
public class Q05_最大深度and宽度 {
    /*
     * 最大深度
     * https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/
     *
     * */

    class Solution {
        public int maxDepth(TreeNode root) {
            return process1(root);
        }

        private int process1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = process1(root.left);
            int right = process1(root.right);
            if (root.left == null || root.right == null) {
                return left + right + 1;
            }
            return Math.max(left, right) + 1;
        }
    }

    /*
     * 最小深度
     * https://leetcode.cn/problems/minimum-depth-of-binary-tree
     *
     * */

    class Solution1 {
        public int minDepth(TreeNode root) {
            return process1(root);
        }

        private int process1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = process1(root.left);
            int right = process1(root.right);
            if (root.left == null || root.right == null) {
                return left + right + 1;
            }
            return Math.min(left, right) + 1;
        }
    }

    // N叉树最大深度

    public int maxDepth(Q04_N叉树转二叉树.Node root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        for (Q04_N叉树转二叉树.Node node : root.children) {
            int depth = maxDepth(node);
            max = Math.max(max, depth);
        }
        return max + 1;
    }


    /*
     * 最大宽度
     *
     * */

    // null值不算数

    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<List<Integer>> nodeArr = new ArrayList<>();
        process2(root, 0, nodeArr);
        return nodeArr.stream().mapToInt(List::size).max().orElse(0);
    }

    private void process2(TreeNode root, int index, List<List<Integer>> nodeArr) {
        if (index == nodeArr.size()) {
            nodeArr.add(new ArrayList<>());
        }
        nodeArr.get(index).add(root.val);
        if (root.left != null) {
            process2(root.left, index + 1, nodeArr);
        }
        if (root.right != null) {
            process2(root.right, index + 1, nodeArr);
        }
    }

    // null值算数
    // https://leetcode.cn/problems/maximum-width-of-binary-tree/description/

    class Solution2 {
        Map<Integer, Integer> levelMin = new HashMap<Integer, Integer>();

        public int widthOfBinaryTree2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return dfs(root, 0, 1);
        }

        private int dfs(TreeNode root, int level, int index) {
            if (root == null) {
                return 0;
            }
            levelMin.putIfAbsent(level, index);
            int p = index - levelMin.get(level) + 1;
            int pl = dfs(root.left, level + 1, index * 2 + 1);
            int pr = dfs(root.right, level + 1, index * 2 + 2);
            return Math.max(p, Math.max(pl, pr));
        }
    }
}
