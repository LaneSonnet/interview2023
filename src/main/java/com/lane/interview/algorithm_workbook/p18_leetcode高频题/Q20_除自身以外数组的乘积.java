package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

/**
 * @ Author:  duenpu
 * @ Date  :  00:16 2024/3/19
 */
public class Q20_除自身以外数组的乘积 {
    // https://leetcode-cn.com/problems/product-of-array-except-self/description/?company_slug=xiaohongshu

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];

        for (int i = 0, j = 1; i < len; i++) {
            ans[i] = j;
            j *= nums[i];
        }

        for (int i = len - 1, j = 1; i >= 0; i--) {
            ans[i] = ans[i] * j;
            j *= nums[i];
        }

        return ans;
    }

}
