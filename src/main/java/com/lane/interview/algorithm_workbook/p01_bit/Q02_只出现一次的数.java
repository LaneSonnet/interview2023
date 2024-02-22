package com.lane.interview.algorithm_workbook.p01_bit;

/**
 * https://leetcode.cn/problems/single-number/description/
 *
 * 只出现一次的数字
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q02_只出现一次的数 {
    public int singleNumber(int[] nums) {

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
