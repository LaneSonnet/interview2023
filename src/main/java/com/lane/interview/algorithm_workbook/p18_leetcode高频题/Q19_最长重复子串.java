package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author:  duenpu
 * @ Date  :  22:43 2024/3/12
 */
public class Q19_最长重复子串 {
    // 最大重复子串
    // 返回重复的次数
    // https://leetcode-cn.com/problems/maximum-repeating-substring/
    public int maxRepeating(String sequence, String word) {
        int ans = 0;
        int n = sequence.length();
        int m = word.length();
        for (int i = 0; i + m <= n; i++) {
            int j = i;
            int k = 0;
            while (j < n && sequence.charAt(j) == word.charAt(k)) {
                j++;
                k++;
                if (k == m) {
                    ans++;
                    k = 0;
                }
            }
        }
        return ans;
    }
    // 最大重复子串
    // 返回子串
    public String longestRepeatingSubstring(String S) {
        int len = S.length();
        int left = 1, right = len;
        // 哈希表用于存储子串及其出现次数
        Map<String, Integer> map = new HashMap<>();
        // 循环遍历子串长度
        while (left <= right) {
            int mid = left + (right - left) / 2;
            boolean found = false;
            // 遍历字符串 S，找出长度为 mid 的子串
            for (int i = 0; i + mid <= len; i++) {
                String sub = S.substring(i, i + mid);
                // 如果哈希表中已经存在该子串，则找到了重复子串
                if (map.containsKey(sub)) {
                    found = true;
                    break;
                } else {
                    // 否则将子串放入哈希表中
                    map.put(sub, 1);
                }
            }
            // 根据当前子串长度是否找到重复子串，调整二分查找的范围
            if (found) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 返回最长重复子串
        return S.substring(0, left - 1);
    }
}
