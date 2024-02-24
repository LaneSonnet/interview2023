package com.lane.interview.algorithm_workbook.p01_bit;

/**
 * 二进制求和
 * <p>
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入:a = "11", b = "1"
 * 输出："100"
 * 示例 2：
 * <p>
 * 输入：a = "1010", b = "1011"
 * 输出："10101"
 *
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q01_二进制相加 {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");
        return ans.reverse().toString();
    }

    // https://leetcode.cn/problems/add-strings

    class Solution {
        public String addStrings(String num1, String num2) {
            StringBuilder ans = new StringBuilder();
            int tmp = 0;
            for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
                int sum = tmp;
                sum += i >=0 ? num1.charAt(i) - '0' : 0;
                sum += j >=0 ? num2.charAt(j) - '0' : 0;
                ans.append(sum % 10);
                tmp = sum / 10;
            }
            ans.append(tmp == 1 ? "1" : "");
            return ans.reverse().toString();
        }
    }
}
