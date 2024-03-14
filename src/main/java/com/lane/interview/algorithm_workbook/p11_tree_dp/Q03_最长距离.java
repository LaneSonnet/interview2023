package com.lane.interview.algorithm_workbook.p11_tree_dp;

import com.lane.interview.algorithm.day8.Demo6_MaxDistance;
import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ Author:  duenpu
 * @ Date  :  23:05 2024/2/10
 */
public class Q03_最长距离 {
    public int maxDistance(TreeNode head) {
        return process(head).maxDistance;
    }
    /*
     * 分情况讨论
     * ①最大距离经过root
     * 最大距离就是 左子树高度 + 右子树高度 + 1
     *
     * ②最大距离不经过root
     * 最大距离 = 左子树最大距离&右子树最大距离 二者取最大
     * */
    /*
     * 收集信息：最大距离，高度
     * */
    public class Info {
        int maxDistance;
        int height;

        public Info(int a, int b) {
            this.maxDistance = a;
            this.height = b;
        }
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);

        int height = Math.max(left.height, right.height) + 1;

        int p1 = left.maxDistance;
        int p2 = right.maxDistance;
        int p3 = left.height + right.height + 1;
        int maxDistance = Math.max(p1, Math.max(p2, p3));
        return new Info(maxDistance, height);
    }
}
