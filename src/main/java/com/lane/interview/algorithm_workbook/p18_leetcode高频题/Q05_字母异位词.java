package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.*;

/**
 * @ Author:  duenpu
 * @ Date  :  00:25 2024/2/27
 */
public class Q05_字母异位词 {

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
}
