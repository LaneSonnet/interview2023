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
    // 电话号码
    class Solution {
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        public List<String> letterCombinations(String digits) {
            //注意边界条件
            if(digits==null || digits.length()==0) {
                return new ArrayList<>();
            }
            iterStr(digits, new StringBuilder(), 0);
            return res;
        }
        //最终输出结果的list
        List<String> res = new ArrayList<>();

        //递归函数
        void iterStr(String str, StringBuilder letter, int index) {
            //递归的终止条件，注意这里的终止条件看上去跟动态演示图有些不同，主要是做了点优化
            //动态图中是每次截取字符串的一部分，"234"，变成"23"，再变成"3"，最后变成""，这样性能不佳
            //而用index记录每次遍历到字符串的位置，这样性能更好
            if(index == str.length()) {
                res.add(letter.toString());
                return;
            }
            //获取index位置的字符，假设输入的字符是"234"
            //第一次递归时index为0所以c=2，第二次index为1所以c=3，第三次c=4
            //subString每次都会生成新的字符串，而index则是取当前的一个字符，所以效率更高一点
            char c = str.charAt(index);
            //map_string的下表是从0开始一直到9， c-'0'就可以取到相对的数组下标位置
            //比如c=2时候，2-'0'，获取下标为2,letter_map[2]就是"abc"
            int pos = c - '0';
            String map_string = letter_map[pos];
            //遍历字符串，比如第一次得到的是2，页就是遍历"abc"
            for(int i=0;i<map_string.length();i++) {
                //调用下一层递归，用文字很难描述，请配合动态图理解
                letter.append(map_string.charAt(i));
                //如果是String类型做拼接效率会比较低
                //iterStr(str, letter+map_string.charAt(i), index+1);
                iterStr(str, letter, index+1);
                letter.deleteCharAt(letter.length()-1);
            }
        }
    }

































}
