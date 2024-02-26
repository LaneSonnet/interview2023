package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  00:41 2024/2/27
 */
public class Q06_找到字符串中所有字母异位词 {
    // https://leetcode.cn/problems/find-all-anagrams-in-a-string/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution {
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
