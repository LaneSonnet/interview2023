package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

/**
 * @ Author:  duenpu
 * @ Date  :  13:44 2024/3/8
 */
public class Q16_字符串公因子 {
    // https://leetcode.cn/problems/greatest-common-divisor-of-strings/solutions
    class Solution {
        public String gcdOfStrings(String str1, String str2) {
            if (!(str1+str2).equals(str2+str1)){
                return "";
            }
            return str1.substring(0,gcd(str1.length(),str2.length()));
        }
        private int gcd(int len1,int len2){
            return len2==0?len1:gcd(len2,len1%len2);
        }
    }
}
