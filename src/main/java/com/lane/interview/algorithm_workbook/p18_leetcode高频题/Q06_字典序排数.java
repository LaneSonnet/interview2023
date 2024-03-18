package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 13 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9] 示例 2：
 * <p>
 * 输入：n = 2 输出：[1,2]
 *
 * @ Author:  duenpu
 * @ Date  :  22:35 2024/3/6
 */
public class Q06_字典序排数 {
    // https://leetcode.cn/problems/lexicographical-numbers/description/?company_slug=xiaohongshu
    private static List<Integer> ans = new ArrayList<>();
    public static List<Integer> lexicalOrder(int n) {
        for (int i = 1; i <= 9; i++) {
            dfs(i, n);
        }
        return ans;
    }

    private static void dfs(int cur, int limit) {
        if (cur > limit) {
            return;
        }
        ans.add(cur);
        for (int i = 0; i <= 9; i++) {
            dfs(cur * 10 + i, limit);
        }
    }

    // public List<Integer> lexicalOrder(int n) {
    //     List<Integer> list = new ArrayList<>();
    //     int curr = 1;
    //     //10叉树的先序遍历
    //     for(int i=0;i<n;i++){
    //         list.add(curr);
    //         if(curr*10<=n){
    //             curr*=10;//进入下一层
    //         }else{
    //             if(curr>=n)   curr/=10;//如果这一层结束了
    //             curr+=1;
    //             while(curr%10==0) curr/=10;//如果>10就要返回上一层
    //         }
    //     }
    //     return list;
    // }

    public static void main(String[] args) {
        System.out.println(lexicalOrder(178));
    }

}
