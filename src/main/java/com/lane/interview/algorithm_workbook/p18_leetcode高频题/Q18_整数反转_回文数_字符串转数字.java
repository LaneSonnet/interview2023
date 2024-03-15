package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  20:49 2024/3/12
 */
public class Q18_整数反转_回文数_字符串转数字 {
    // 整数反转
    // https://leetcode-cn.com/problems/reverse-integer/
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int pop = x % 10;
            x = x / 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }
    // 回文数
    // https://leetcode-cn.com/problems/palindrome-number/
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int res = 0;
        int temp = x;
        while (temp != 0) {
            int pop = temp % 10;
            temp = temp / 10;
            res = res * 10 + pop;
        }
        return res == x;
    }
    // 字符串转数字
    // https://leetcode-cn.com/problems/string-to-integer-atoi/
    public int myAtoi(String s) {
        int n = s.length();
        int idx = 0;
        while (idx < n && s.charAt(idx) == ' ') {
            idx++;
        }
        if (idx == n) {
            return 0;
        }
        int sign = 1;
        if (s.charAt(idx) == '+') {
            idx++;
        } else if (s.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }
        int res = 0;
        while (idx < n) {
            char c = s.charAt(idx);
            if (c < '0' || c > '9') {
                break;
            }
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (c - '0') > 7)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (c - '0') > 8)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (c - '0');
            idx++;
        }
        return res;
    }
    // 分割回文串
    // https://leetcode-cn.com/problems/palindrome-partitioning/
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        dfs(s, 0, path, res);
        return res;
    }
    public void dfs(String s, int start, List<String> path, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                path.add(s.substring(start, i + 1));
                dfs(s, i + 1, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
    public boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


































}
