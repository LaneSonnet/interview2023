package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.*;

/**
 * @ Author:  duenpu
 * @ Date  :  00:25 2024/2/27
 */
public class Q05_字母异位词 {

    // 按异位词分组
    // https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs == null || strs.length == 0) {
                return new ArrayList<>();
            }
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                String key = Arrays.toString(charArray);
                if (map.containsKey(key)) {
                    map.get(key).add(str);
                } else {
                    List<String> newList = new ArrayList<>();
                    newList.add(str);
                    map.put(key, newList);
                }
            }
            return new ArrayList<>(map.values());
        }
    }

    // 找出所有异位词的起始索引
    // https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution1 {
        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> ans = new ArrayList<>();
            int m = s.length();
            int n = p.length();

            if (m < n) {
                // 如果s没p长，直接返回空列表
                return ans;
            }

            int[] sCnt = new int[26];
            int[] pCnt = new int[26];

            for (int i = 0; i < n; i++) {
                sCnt[s.charAt(i) - 'a']++;
                pCnt[p.charAt(i) - 'a']++;
            }

            if (Arrays.equals(sCnt, pCnt)) {
                // 初始窗口就相等
                ans.add(0);
            }

            for (int i = 0; i < m - n; i++) {
                // 窗口右移
                sCnt[s.charAt(i) - 'a']--;
                sCnt[s.charAt(i + n) - 'a']++;

                if (Arrays.equals(sCnt, pCnt)) {
                    ans.add(i + 1);
                }
            }

            return ans;
        }
    }
}
