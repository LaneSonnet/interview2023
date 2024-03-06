package com.lane.interview.algorithm_workbook.p10_tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树序列化&反序列化
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/
 *
 * @ Author:  duenpu
 * @ Date  :  22:08 2024/2/6
 */
public class Q03_序列化 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Queue<String> ans = new LinkedList<>();
            pres(root, ans);
            StringBuilder sb = new StringBuilder();
            while (!ans.isEmpty()) {
                sb.append(ans.remove() + ",");
            }
            return sb.toString();
        }

        private void pres(TreeNode head, Queue<String> ans) {
            if (head == null) {
                ans.add(null);
            } else {
                ans.add(String.valueOf(head.val));
                pres(head.left, ans);
                pres(head.right, ans);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            Queue<String> prelist = new LinkedList<>();
            for (String s : split) {
                prelist.add(s);
            }
            if (prelist == null || prelist.size() == 0) {
                return null;
            }
            return preb(prelist);
        }

        private TreeNode preb(Queue<String> prelist) {
            String value = prelist.poll();
            if (value == null || value.equals("null")) {
                return null;
            }
            TreeNode head = new TreeNode(Integer.valueOf(value));
            head.left = preb(prelist);
            head.right = preb(prelist);
            return head;
        }
    }
}
