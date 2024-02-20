package com.lane.interview.algorithm_workbook.p15_滑动窗口;

import java.util.HashSet;
import java.util.Set;

/**
 * @ Author:  duenpu
 * @ Date  :  23:41 2024/2/18
 */
public class Q01_无重复字符的最长子串 {

    // https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/227999/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/?company_slug=xiaohongshu
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 边界
            if (s == null || s.length() == 0) {
                return 0;
            }
            if (s.length() == 1) {
                return 1;
            }
            // set来去重
            Set<Character> set = new HashSet<>();
            // 滑动窗口
            // 左指针从左往右遍历每一个数
            // 当前数(左指针)固定不动，右指针从左往右移动，直到越界或者出现重复字符
            int right = -1;
            int ans = 0;
            for (int left = 0; left < s.length(); left++) {
                // 上面for循环移动了左指针，进来以后先把set左边元素剔除
                if (left != 0) {
                    set.remove(s.charAt(left - 1));
                }
                // 接下来移动右指针
                while (right < s.length() && !set.contains(s.charAt(right + 1))) {
                    set.add(s.charAt(++right));
                }
                ans = Math.max(ans, right - left + 1);
            }
            return ans;
        }
    }
}
