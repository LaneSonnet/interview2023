package com.lane.interview.algorithm_workbook.p10_tree;

import com.lane.interview.algorithm_workbook.p02_list.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  00:24 2024/2/29
 */
public class Q11_转换 {

    // 有序数组转换成二叉搜索树
    // https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/description/?envType=study-plan-v2&envId=top-100-liked
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + ((right - left) >> 1);
        TreeNode head = new TreeNode(nums[mid]);
        head.left = process(nums, left, mid - 1);
        head.right = process(nums, mid + 1, right);
        return head;
    }

    // 转换成右视图
    // https://leetcode.cn/problems/binary-tree-right-side-view/description/?envType=study-plan-v2&envId=top-100-liked
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        process2(root, ans, 0);
        return ans;
    }

    private void process2(TreeNode root, List<Integer> ans, int level) {
        if (root == null) {
            return;
        }
        if (level == ans.size()) {
            ans.add(root.val);
        }
        level++;
        process2(root.right, ans, level);
        process2(root.left, ans, level);
    }

    // 转换成链表
    // https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        process3(root);
    }
    TreeNode pre = null;
    /*
    * 右→左→头  这样不会打乱指针
    * 从后往前一个节点一个节点改
    * */
    private void process3(TreeNode root) {
        if (root == null) {
            return;
        }
        process3(root.right);
        process3(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    // 二叉搜索树转双向有序链表
    // https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
    class Solution {
        TreeNode pre, head;

        public TreeNode treeToDoublyList(TreeNode root) {
            if (root == null) {
                return null;
            }
            inOrder(root);
            head.left = pre;
            pre.right = head;
            return head;
        }

        private void inOrder(TreeNode cur) {
            if (cur == null) {
                return;
            }
            inOrder(cur.left);
            if (pre == null) {
                head = cur;
            } else {
                pre.right = cur;
            }
            cur.left = pre;
            pre = cur;
            inOrder(cur.right);
        }
    }
}
