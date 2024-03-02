package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 * https://leetcode.cn/problems/sqrtx/
 *
 * @ Author:  duenpu
 * @ Date  :  00:10 2023/9/16
 */
public class Q04_平方根 {
    /**
     * 从题目的要求和示例我们可以看出，这其实是一个查找整数的问题，并且这个整数是有范围的。
     *
     * 如果这个整数的平方 恰好等于 输入整数，那么我们就找到了这个整数；
     * 如果这个整数的平方 严格大于 输入整数，那么这个整数肯定不是我们要找的那个数；
     * 如果这个整数的平方 严格小于 输入整数，那么这个整数 可能 是我们要找的那个数（重点理解这句话）。
     * 因此我们可以使用「二分查找」来查找这个整数，不断缩小范围去猜。
     *
     * 猜的数平方以后大了就往小了猜；
     * 猜的数平方以后恰恰好等于输入的数就找到了；
     * 猜的数平方以后小了，可能猜的数就是，也可能不是。
     * 很容易知道，题目要我们返回的整数是有范围的，
     * 直觉上一个整数的平方根肯定不会超过它自己的一半，
     *
     * 但是 0和 1除外，因此我们可以在 1到输入整数除以 2这个范围里查找我们要找的平方根整数。0单独判断一下就好。
     *
     */
    public static int mySqrt(int x) {
        // 特殊值判断
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int left = 1;
        int right = x / 2;
        // 在区间 [left..right] 查找目标元素
        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            // 注意：这里为了避免乘法溢出，改用除法
            if (mid > x / mid) {
                // 下一轮搜索区间是 [left..mid - 1]
                right = mid - 1;
            } else {
                // 下一轮搜索区间是 [mid..right]
                left = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int i = mySqrt(8);
        System.out.println(i);
    }
}
