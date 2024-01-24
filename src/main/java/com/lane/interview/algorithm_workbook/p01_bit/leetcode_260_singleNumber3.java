package com.lane.interview.algorithm_workbook.p01_bit;

/**
 * https://leetcode.cn/problems/single-number-iii/description/
 *
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 *
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class leetcode_260_singleNumber3 {
    public int[] singleNumber(int[] nums) {
        int eor = 0;
        for(int i = 0;i<nums.length;i++) {
            eor ^= nums[i];
        }
        int rightOne = eor & (~eor + 1);
        int[] ansArray = new int[2];
        int ans1 = 0;
        for (int j = 0;j<nums.length;j++) {
            if ((nums[j] & rightOne) == 0) {
                ans1 ^= nums[j];
            }
        }
        ansArray[0] = ans1;
        ansArray[1] = eor ^ ans1;
        return ansArray;
    }
}
