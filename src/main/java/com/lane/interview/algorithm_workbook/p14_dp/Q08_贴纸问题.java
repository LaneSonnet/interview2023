package com.lane.interview.algorithm_workbook.p14_dp;

import java.util.HashMap;

/**
 * @ Author:  duenpu
 * @ Date  :  22:53 2024/2/16
 */
public class Q08_贴纸问题 {

    // 本题测试链接：https://leetcode.com/problems/stickers-to-spell-word

    /**
      * 贴纸拼单词
      * 题目描述：
      * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
      * arr中的字符串没有重复，且都是不同的。再给定一个字符串类型的aim，请问
      * 使用arr中的字符串最少可以生成多少个aim，arr中的每个字符串都可以使用无限次
      * （注意：使用arr中的某个字符串时，可以把其中的某些字符去掉，但必须保证每种字符
      * 都剩下至少一次。）
      * 举例：
      * str = "babac"，arr = {"ba","c","abcd"}
      * 要想组成str，arr至少要使用一次"ba"，一次"abcd"。所以返回2(或者两次"abcd"，答案也是2)
     * */

    class Solution {
        // 暴力递归
        public int minStickers1(String[] stickers, String target) {
            int ans = process1(stickers, target);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
        public int process1(String[] stickers, String target) {
            // base case
            if (target.length() == 0) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for (String first : stickers) {
                String rest = minus(target, first);
                if (rest.length() != target.length()) {
                    min = Math.min(min, process1(stickers, rest));
                }
            }
            return min + (min == Integer.MAX_VALUE ? 0 : 1);
        }

        public String minus(String s1, String s2) {
            char[] str1 = s1.toCharArray();
            char[] str2 = s2.toCharArray();
            int[] count = new int[26];// 统计次数
            for (char cha : str1) {
                count[cha - 'a']++;
            }
            for (char cha : str2) {
                count[cha - 'a']--;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    for (int j = 0; j < count[i]; j++) {
                        builder.append((char) (i + 'a'));
                    }
                }
            }
            return builder.toString();
        }

        // 动态规划
        public int minStickers(String[] stickers, String target) {
            int N = stickers.length;
            int[][] counts = new int[N][26];
            for (int i = 0; i < N; i++) {
                char[] str = stickers[i].toCharArray();
                for (char cha : str) {
                    counts[i][cha - 'a']++;
                }
            }
            HashMap<String, Integer> dp = new HashMap<>();
            dp.put("", 0);
            int ans = process3(counts, target, dp);
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

        public int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
            if (dp.containsKey(t)) {
                return dp.get(t);
            }
            char[] target = t.toCharArray();
            int[] tcounts = new int[26];
            for (char cha : target) {
                tcounts[cha - 'a']++;
            }
            int N = stickers.length;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                int[] sticker = stickers[i];
                if (sticker[target[0] - 'a'] > 0) {
                    StringBuilder builder = new StringBuilder();
                    for (int j = 0; j < 26; j++) {
                        if (tcounts[j] > 0) {
                            int nums = tcounts[j] - sticker[j];
                            for (int k = 0; k < nums; k++) {
                                builder.append((char) (j + 'a'));
                            }
                        }
                    }
                    String rest = builder.toString();
                    min = Math.min(min, process3(stickers, rest, dp));
                }
            }
            int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
            dp.put(t, ans);
            return ans;
        }
    }
}
