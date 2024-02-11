package com.lane.interview.algorithm_workbook.p10_tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
            return process(root);
        }

        private int process(TreeNode root){
            if (root == null) {
                return 0;
            }
            int maxDepth1 = process(root.left);
            int maxDepth2 = process(root.right);
            return Math.max(maxDepth1, maxDepth2) + 1;
        }
    }

    /*
     * 最大宽度
     *
     * */

    // null值不算数
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        int max = 0;
        int curNodeCount = 0;
        while(!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left!=null) {
                nextEnd = cur.left;
                queue.add(cur.left);
            }
            if (cur.right!=null) {
                nextEnd = cur.right;
                queue.add(cur.right);
            }
            curNodeCount++;
            if(cur == curEnd) {
                max = Math.max(max, curNodeCount);
                curNodeCount = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    // null值算数
    // https://leetcode.cn/problems/maximum-width-of-binary-tree/description/

    class Solution1 {
        Map<Integer, Integer> levelMin = new HashMap<Integer, Integer>();

        public int widthOfBinaryTree(TreeNode root) {
            return dfs(root, 1, 1);
        }

        public int dfs(TreeNode node, int depth, int index) {
            if (node == null) {
                return 0;
            }
            levelMin.putIfAbsent(depth, index); // 每一层最先访问到的节点会是最左边的节点，即每一层编号的最小值
            return Math.max(index - levelMin.get(depth) + 1, Math.max(dfs(node.left, depth + 1, index * 2), dfs(node.right, depth + 1, index * 2 + 1)));
        }
    }
}
