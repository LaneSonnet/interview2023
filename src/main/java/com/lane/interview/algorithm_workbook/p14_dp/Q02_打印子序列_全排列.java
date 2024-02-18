package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ Author:  duenpu
 * @ Date  :  21:17 2024/2/14
 */
public class Q02_打印子序列_全排列 {
    // 打印字符串所有子序列
    public List<String> subs(String s) {
       char[] charArray = s.toCharArray();
       String path = "";
       List<String> ans = new ArrayList<>();
       process(path, charArray, ans, 0);
       return ans;

    }
    private void process(String path, char[] charArray, List<String> ans, int index) {
        if (index == charArray.length) {
            ans.add(path);
            return;
        }
        process(path, charArray, ans, index + 1);
        process(path + String.valueOf(charArray[index]), charArray, ans, index + 1);
    }

    // 打印字符串所有子序列(去重)
    public List<String> subs1(String s) {
        char[] charArray = s.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        process1(path, charArray, set, 0);
        for (String el : set) {
            ans.add(el);
        }
        return ans;

    }
    private void process1(String path, char[] charArray, Set<String> set, int index) {
        if (index == charArray.length) {
            set.add(path);
            return;
        }
        process1(path, charArray, set, index + 1);
        process1(path + String.valueOf(charArray[index]), charArray, set, index + 1);
    }

    // 打印字符串所有全排列
    // https://leetcode.cn/problems/permutations/description
    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g1(str, 0, ans);
        return ans;
    }

    /**
     * str: 固定参数
     * index: 来到了str[index]字符，index是位置
     * ans: 收集答案
     */
    public static void g1(char[] str, int index, List<String> ans) {
        // base case
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            // 从index位置开始，后面的每一个字符(包括index自己)都可以来到index位置
            for (int i = index; i < str.length; i++) {
                // 交换
                swap(str, index, i);
                // 递归, index + 1的位置继续
                g1(str, index + 1, ans);
                // 恢复现场
                swap(str, index, i);
            }
        }
    }

    // 打印字符串所有全排列(去重)
    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    public static void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            // 这个数组记录之前出现过的字符，256的长度是因为字符的范围是0~255
            // 剪枝思想
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, index, i);
                    g2(str, index + 1, ans);
                    swap(str, index, i);
                }
            }
        }
    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
}
