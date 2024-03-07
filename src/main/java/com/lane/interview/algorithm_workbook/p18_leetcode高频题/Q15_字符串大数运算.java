package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

/**
 * @ Author:  duenpu
 * @ Date  :  08:52 2024/3/7
 */
public class Q15_字符串大数运算 {
    // 大数相加
    public String addStrings(String num1, String num2) {
        if ((num1 == null || num1.length() == 0) && (num2 == null || num2.length() == 0)) {
            return "";
        }
        StringBuffer ans = new StringBuffer();
        int cas = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = cas;
            sum += i >= 0 ? num1.charAt(i) - '0' : 0;
            sum += j >= 0 ? num2.charAt(j) - '0' : 0;
            cas = sum / 10;
            ans.append(sum % 10);
        }
        ans.append(cas == 1 ? 1 : "");
        return ans.reverse().toString();
    }

    // 大数相减
    public static String bigNumberSub(String f, String s) {
        // 确定两个数的正负关系，决定后续是做加法还是减法
        boolean isNegative = false; // 结果是否为负数
        if (f.length() < s.length() || (f.length() == s.length() && f.compareTo(s) < 0)) {
            String temp = f;
            f = s;
            s = temp;
            isNegative = true; // 如果f小于s，结果为负
        }
        // 将字符串反转，便于从最低位开始计算
        char[] a = new StringBuilder(f).reverse().toString().toCharArray();
        char[] b = new StringBuilder(s).reverse().toString().toCharArray();
        int[] result = new int[a.length];
        // 减法运算
        for (int i = 0; i < a.length; i++) {
            int diff = a[i] - '0' - (i < b.length ? (b[i] - '0') : 0);
            if (diff < 0) {
                diff += 10;
                a[i + 1]--; // 借位
            }
            result[i] = diff;
        }
        // 构建结果字符串，跳过前导零
        StringBuilder sb = new StringBuilder();
        if (isNegative) {
            sb.append('-');
        }
        boolean leadingZero = true;
        for (int i = result.length - 1; i >= 0; i--) {
            if (leadingZero && result[i] == 0) {
                continue;
            }
            leadingZero = false;
            sb.append(result[i]);
        }
        return sb.length() == 0 || sb.toString().equals("-") ? "0" : sb.toString();
    }

    // 大数相乘
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int num = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = num + res[p2];
                res[p2] = sum % 10;
                //此处的+=是为了处理进位用的，例如19*19，列出竖式看一下就知道了。
                res[p1] += sum / 10;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            //这里的i==0是因为只可能出现首位为0的情况，例如一个三位数乘一个两位数不可能出现结果是一个三位数的情况。所以只需要判断首位即可。
            if (res[i] == 0 && i == 0) {
                continue;
            }
            result.append(res[i]);
        }
        return result.toString();
    }
}
