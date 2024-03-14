package com.lane.interview.algorithm_workbook.p15_滑动窗口;


import java.util.HashMap;

/**
 *
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * @ Author:  duenpu
 * @ Date  :  23:21 2024/3/4
 */
public class Q03_最小覆盖子串 {
    class Solution {
        public String minWindow(String s, String t) {
            HashMap<Character, Integer> hs = new HashMap<Character, Integer>();
            HashMap<Character, Integer> ht = new HashMap<Character, Integer>();
            // 统计t中的字符频次
            for (int i = 0; i < t.length(); i++) {
                ht.put(t.charAt(i), ht.getOrDefault(t.charAt(i), 0) + 1);
            }
            String ans = "";
            int len = Integer.MAX_VALUE;
            int cnt = 0;  //有多少个元素符合
            for (int i = 0, j = 0; i < s.length(); i++) {
                hs.put(s.charAt(i), hs.getOrDefault(s.charAt(i), 0) + 1);
                if (ht.containsKey(s.charAt(i)) && hs.get(s.charAt(i)) <= ht.get(s.charAt(i))) {
                    cnt++;
                }
                while (j < i && (!ht.containsKey(s.charAt(j)) || hs.get(s.charAt(j)) > ht.get(s.charAt(j)))) {
                    int count = hs.get(s.charAt(j)) - 1;
                    hs.put(s.charAt(j), count);
                    j++;
                }
                if (cnt == t.length() && i - j + 1 < len) {
                    len = i - j + 1;
                    ans = s.substring(j, i + 1);
                }
            }
            return ans;
        }
    }

}
