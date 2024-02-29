package com.lane.interview.algorithm_workbook.p10_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  00:42 2024/2/29
 */
public class Q13_路径 {

    // 所有路径
    // https://leetcode.cn/problems/binary-tree-paths/description/
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        process(root, "", ans);
        return ans;
    }

    private void process(TreeNode root, String path, List<String> ans) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            ans.add(path + root.val);
            return;
        }
        process(root.left, path + root.val + "->", ans);
        process(root.right, path + root.val + "->", ans);
    }

    // 路径总和1
    // https://leetcode.cn/problems/path-sum/description/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return Boolean.FALSE;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 路径总和2
    // https://leetcode.cn/problems/path-sum-ii/description/
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, sum, new ArrayList<>(), result);
        return result;
    }

    public void dfs(TreeNode root, int sum, List<Integer> list,
                    List<List<Integer>> result) {
        //如果节点为空直接返回
        if (root == null) {
            return;
        }
        //因为list是引用传递，为了防止递归的时候分支污染，我们要在每个路径
        //中都要新建一个subList
        List<Integer> subList = new ArrayList<>(list);
        //把当前节点值加入到subList中
        subList.add(new Integer(root.val));
        //如果到达叶子节点，就不能往下走了，直接return
        if (root.left == null && root.right == null) {
            //如果到达叶子节点，并且sum等于叶子节点的值，说明我们找到了一组，
            //要把它放到result中
            if (sum == root.val)
                result.add(subList);
            //到叶子节点之后直接返回，因为在往下就走不动了
            return;
        }
        //如果没到达叶子节点，就继续从他的左右两个子节点往下找，注意到
        //下一步的时候，sum值要减去当前节点的值
        dfs(root.left, sum - root.val, subList, result);
        dfs(root.right, sum - root.val, subList, result);
    }

    // 路径总和3
    // https://leetcode.cn/problems/path-sum-iii/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution1 {
        public int pathSum(TreeNode root, int sum) {
            if(root == null){
                return 0;
            }
            int result = countPath(root,sum);
            int a = pathSum(root.left,sum);
            int b = pathSum(root.right,sum);
            return result+a+b;

        }
        public int countPath(TreeNode root,int sum){
            if(root == null){
                return 0;
            }
            sum = sum - root.val;
            int result = sum == 0 ? 1:0;
            return result + countPath(root.left,sum) + countPath(root.right,sum);
        }
    }

    // 最大路径和
    // https://leetcode.cn/problems/binary-tree-maximum-path-sum/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution2 {
        int pathSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return pathSum;
        }

        // dfs 返回以该节点为端点的最大路径和
        public int dfs(TreeNode node) {
            if (node == null) {
                return 0;
            }
            int left = dfs(node.left);
            int right = dfs(node.right);
            // 当前节点有四个选择：
            // 1）独立成线，直接返回自己的值
            // 2）跟左子节点合成一条路径
            // 3）跟右子节点合成一条路径
            int ret = Math.max(node.val, node.val + Math.max(left, right));
            // 4）以自己为桥梁，跟左、右子节点合并成一条路径
            pathSum = Math.max(pathSum, Math.max(ret, node.val + left + right));
            return ret;
        }
    }
}
