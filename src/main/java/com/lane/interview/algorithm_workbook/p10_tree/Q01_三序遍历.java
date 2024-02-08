package com.lane.interview.algorithm_workbook.p10_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ Author:  duenpu
 * @ Date  :  21:28 2024/2/6
 */
public class Q01_三序遍历 {

    //------------------------前序遍历------------------------
    //https://leetcode.cn/problems/binary-tree-inorder-traversal/description/

    //递归

    class Solution1 {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            process(root, ans);
            return ans;
        }

        private void process(TreeNode root, List<Integer> ans) {
            if (root == null) {
                return;
            }
            ans.add(root.val);
            process(root.left, ans);
            process(root.right, ans);
        }
    }

    //迭代

    class Solution2 {
        public List<Integer> preorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>(root.val);
            }
            List<Integer> ans = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode tmp = stack.pop();
                ans.add(tmp.val);
                if (tmp.right != null) {
                    stack.push(tmp.right);
                }
                if (tmp.left != null) {
                    stack.push(tmp.left);
                }
            }
            return ans;
        }
    }


    //------------------------中序遍历------------------------
    // https://leetcode.cn/problems/binary-tree-preorder-traversal/description/

    //递归

    class Solution3 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            process(root, ans);
            return ans;
        }

        private void process(TreeNode root, List<Integer> ans) {
            if (root == null) {
                return;
            }
            process(root.left, ans);
            ans.add(root.val);
            process(root.right, ans);
        }
    }

    //迭代

    class Solution4 {
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<Integer> ans = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                if (root != null) {
                    stack.add(root);
                    root = root.left;
                } else {
                    TreeNode tmp = stack.pop();
                    ans.add(tmp.val);
                    root = tmp.right;
                }
            }
            return ans;
        }
    }

    //------------------------后序遍历------------------------
    //https://leetcode.cn/problems/binary-tree-postorder-traversal/

    //递归

    class Solution5 {
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            process(root, ans);
            return ans;
        }

        private void process(TreeNode root, List<Integer> ans) {
            if (root == null) {
                return;
            }
            process(root.left, ans);
            process(root.right, ans);
            ans.add(root.val);
        }
    }

    //迭代
    class Solution6 {
        public List<Integer> postorderTraversal(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            List<Integer> ans = new ArrayList<>();
            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.push(root);
            while (!stack1.isEmpty()) {
                TreeNode tmp = stack1.pop();
                stack2.push(tmp);
                if (tmp.left != null) {
                    stack1.push(tmp.left);
                }
                if (tmp.right != null) {
                    stack1.push(tmp.right);
                }
            }
            while (!stack2.isEmpty()) {
                ans.add(stack2.pop().val);
            }
            return ans;
        }
    }

}
