package com.lane.interview.algorithm_workbook.p10_tree;

/**
 * @ Author:  duenpu
 * @ Date  :  22:56 2024/4/7
 */
public class Q15_验证前序_后序 {
    // 331 验证前序遍历序列
    public boolean isValidSerialization(String preorder) {
        String[] nodes = preorder.split(",");
        int diff = 1;
        for (String node : nodes) {
            diff--;
            if (diff < 0) {
                return false;
            }
            if (!node.equals("#")) {
                diff += 2;
            }
        }
        return diff == 0;
    }

    public boolean verifyPreorder(int[] preorder) {
        return verify(preorder, 0, preorder.length - 1);
    }

    private boolean verify(int[] preorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = preorder[start];
        int i = start + 1;
        while (i <= end && preorder[i] < root) {
            i++;
        }
        int j = i;
        while (j <= end) {
            if (preorder[j] < root) {
                return false;
            }
            j++;
        }
        return verify(preorder, start + 1, i - 1) && verify(preorder, i, end);
    }

    // LCR 152 验证后序遍历序列
    public boolean verifyPostorder(int[] postorder) {
        return verify1(postorder, 0, postorder.length - 1);
    }

    private boolean verify1(int[] postorder, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = postorder[end];
        int i = start;
        while (i < end && postorder[i] < root) {
            i++;
        }
        int j = i;
        while (j < end) {
            if (postorder[j] < root) {
                return false;
            }
            j++;
        }
        return verify1(postorder, start, i - 1) && verify1(postorder, i, end - 1);
    }
}
