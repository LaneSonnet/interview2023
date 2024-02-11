package com.lane.interview.algorithm_workbook.p10_tree;

/**
 * @ Author:  duenpu
 * @ Date  :  21:11 2024/2/6
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
