package com.lane.interview.interview.algorithm;

import com.lane.interview.algorithm_workbook.p10_tree.TreeNode;

import java.util.*;

/**
 * @ Author:  duenpu
 * @ Date  :  19:48 2024/2/28
 */
public class Q03_tree {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        process(root, ans, 0);
        return ans;
    }

    private void process(TreeNode root, List<List<Integer>> ans, Integer level) {
        if (level == ans.size()) {
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);
        if (root.left != null) {
            process(root.left, ans, level + 1);
        }
        if (root.right != null) {
            process(root.right, ans, level + 1);
        }
    }

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            preS(root, queue);
            StringBuffer ans = new StringBuffer();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    ans.append(poll.val + ",");
                } else {
                    ans.append("null" + ",");
                }
            }
            return ans.toString();
        }

        private void preS(TreeNode root, Queue<TreeNode> queue) {
            if (root == null) {
                queue.add(null);
            } else {
                queue.add(root);
                preS(root.left, queue);
                preS(root.right, queue);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] arr = data.split(",");
            Queue<String> queue = new LinkedList<>();
            if (arr.length == 0) {
                return null;
            }
            for (String e : arr) {
                queue.add(e);
            }
            return preD(queue);
        }

        private TreeNode preD(Queue<String> queue) {
            String val = queue.poll();
            if (val == null || val.equals("null")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = preD(queue);
            node.right = preD(queue);
            return node;
        }

    }

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

    Map<Integer, Integer> levelMin = new HashMap<>();

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

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return Boolean.TRUE;
        }
        return process3(root.left, root.right);
    }

    private Boolean process3(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return Boolean.TRUE;
        }
        if (left == null || right == null) {
            return Boolean.FALSE;
        }
        if (left.val != right.val) {
            return Boolean.FALSE;
        }
        return process3(left.left, right.right) && process3(left.right, right.left);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return process4(root).maxDistance;
    }

    public class Info1 {
        Integer height;
        Integer maxDistance;

        public Info1(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    private Info1 process4(TreeNode root) {
        if (root == null) {
            return new Info1(0, 0);
        }
        Info1 leftInfo = process4(root.left);
        Info1 rightInfo = process4(root.right);
        Integer height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int p1 = leftInfo.height + rightInfo.height + 1;
        int p2 = Math.max(leftInfo.maxDistance, rightInfo.maxDistance);
        Integer maxDistance = Math.max(p1, p2);
        return new Info1(height, maxDistance);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process5(root, p, q).ans;
    }

    public class lInfo {
        Boolean findA;
        Boolean findB;
        TreeNode ans;

        public lInfo(Boolean findA, Boolean findB, TreeNode ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    private lInfo process5(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new lInfo(Boolean.FALSE, Boolean.FALSE, null);
        }
        lInfo leftInfo = process5(root.left, a, b);
        lInfo rightInfo = process5(root.right, a, b);
        Boolean findA = leftInfo.findA || rightInfo.findA || root == a;
        Boolean findB = leftInfo.findB || rightInfo.findB || root == b;
        TreeNode ans = null;
        if (leftInfo.ans !=null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = root;
            }
        }
        return new lInfo(findA, findB, ans);
    }
}
