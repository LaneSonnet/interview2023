package com.lane.interview.algorithm_workbook.p11_tree_dp;

import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;

/**
 * @ Author:  duenpu
 * @ Date  :  20:48 2024/2/9
 */
public class Q01_判断是不是各种树 {
    // 是否是完全二叉树
    public static boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        return process1(head).isCBT;
    }

    public static class isCBTInfo {
        public Boolean isFull;
        public Boolean isCBT;
        public Integer height;

        public isCBTInfo(Boolean f, Boolean c, Integer h) {
            this.isFull = f;
            this.isCBT = c;
            this.height = h;
        }
    }

    /*
     * 四种情况
     * 1.左树满，右树满 → 完全二叉树
     * 2.左树完全但不满&右树满&左树比右树高一层 → 完全二叉树
     * 3.左树完全而且满&右树满&左树比右树高一层 → 完全二叉树
     * 4.左树完全而且满&右树完全但不满&左树右树等高 → 完全二叉树
     *
     * */
    private static isCBTInfo process1(TreeNode head) {
        if (head == null) {
            return new isCBTInfo(Boolean.TRUE, Boolean.TRUE, 0);
        }
        isCBTInfo left = process1(head.left);
        isCBTInfo right = process1(head.right);
        Boolean isFull = left.isFull && right.isFull && (left.height.equals(right.height));
        int height = Math.max(left.height, right.height);
        Boolean isCBT = Boolean.FALSE;
        if (isFull) {
            isCBT = Boolean.TRUE;
        } else {
            if (left.isCBT && right.isCBT) {
                if (left.isCBT
                        && right.isFull
                        && left.height == right.height + 1) {
                    isCBT = true;
                }
                // 情况③
                if (left.isFull
                        &&
                        right.isFull
                        && left.height == right.height + 1) {
                    isCBT = true;
                }
                if (left.isFull
                        && right.isCBT && left.height == right.height) {
                    isCBT = true;
                }
            }
        }

        return new isCBTInfo(isFull, isCBT, height);
    }


    // 是否是搜索二叉树
    public static boolean isBST(TreeNode head) {
        if (head == null) {
            return Boolean.TRUE;
        }
        return process2(head).isBST;
    }

    public static class isBSTInfo {
        public Boolean isBST;
        public Integer min;
        public Integer max;

        public isBSTInfo(Boolean b, Integer i, Integer a) {
            this.isBST = b;
            this.min = i;
            this.max = a;
        }
    }

    /*
     * 必须同时满足
     * 1.左子树是BST
     * 2.右子树是BST
     * 3.左子树最大值小于head
     * 4.右子树最小值大于head
     *
     * */
    private static isBSTInfo process2(TreeNode head) {
        if (head == null) {
            return null;
        }
        isBSTInfo left = process2(head.left);
        isBSTInfo right = process2(head.right);
        Integer min = head.val;
        if (left != null) {
            min = Math.min(min, left.min);
        }
        if (right != null) {
            min = Math.min(min, right.min);
        }
        Integer max = head.val;
        if (left != null) {
            max = Math.max(max, left.max);
        }
        if (right != null) {
            max = Math.max(max, right.max);
        }
        Boolean isBST = Boolean.TRUE;
        if (left != null && !left.isBST) {
            isBST = Boolean.FALSE;
        }
        if (right != null && !right.isBST) {
            isBST = Boolean.FALSE;
        }
        if (left != null && left.max >= head.val) {
            isBST = Boolean.FALSE;
        }
        if (right != null && right.min <= head.val) {
            isBST = Boolean.FALSE;
        }
        return new isBSTInfo(isBST, min, max);
    }

    // 是否是平衡二叉树
    public static boolean isBBT(TreeNode head) {
        return process3(head).isBBT;
    }

    public static class isBBTInfo {
        public Boolean isBBT;
        public Integer height;

        public isBBTInfo(Boolean b, Integer i) {
            this.isBBT = b;
            this.height = i;
        }
    }

    /*
     * 必须同时满足
     * 1.左子树是BBT
     * 2.右子树是BBT
     * 3.左子树与右子树的高度差的绝对值小于等于1
     *
     * */
    private static isBBTInfo process3(TreeNode head) {
        if (head == null) {
            return new isBBTInfo(Boolean.TRUE, 0);
        }
        isBBTInfo left = process3(head.left);
        isBBTInfo right = process3(head.right);
        Integer height = Math.max(left.height, right.height);
        Boolean isBBT = Boolean.TRUE;
        if (!left.isBBT) {
            isBBT = Boolean.FALSE;
        }
        if (!right.isBBT) {
            isBBT = Boolean.FALSE;
        }
        if (Math.abs(left.height - right.height) > 1) {
            isBBT = Boolean.FALSE;
        }
        return new isBBTInfo(isBBT, height);
    }

    // 是否是满二叉树
    public static boolean isFull(TreeNode head) {
        if (head == null) {
            return Boolean.TRUE;
        }
        return process4(head).isFull;
    }

    public static class isFullInfo {
        public Boolean isFull;
        public Integer height;

        public isFullInfo(Boolean b, Integer i) {
            this.isFull = b;
            this.height = i;
        }
    }

    /*
     * 必须同时满足
     * 1.左子树满
     * 2.右子树满
     * 3.左子树与右子树高度相等
     *
     * */
    private static isFullInfo process4(TreeNode head) {
        if (head == null) {
            return null;
        }
        isFullInfo left = process4(head.left);
        isFullInfo right = process4(head.right);
        Boolean isFull = left.isFull && right.isFull && left.height.equals(right.height);
        Integer height = Math.max(left.height, right.height);
        return new isFullInfo(isFull, height);
    }
}
