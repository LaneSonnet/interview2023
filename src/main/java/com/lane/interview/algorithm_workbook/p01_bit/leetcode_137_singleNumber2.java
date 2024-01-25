package com.lane.interview.algorithm_workbook.p01_bit;

/**
 * https://leetcode.cn/problems/single-number-ii/description/
 * <p>
 * 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * <p>
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 *
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class leetcode_137_singleNumber2 {
    public static int singleNumber(int[] nums) {

        int a = 0, b = 0;
        for (int num : nums) {
            System.out.println("------------");
            System.out.println("num=" + num);
            b = ~a & (b ^ num);
            System.out.println("b=" + b);
            a = ~b & (a ^ num);
            System.out.println("a=" + b);
            System.out.println("------------");
        }
        return b;

    }

    public static void main(String[] args) {
        int[] nums = new int[7];
        nums[0] = 2;
        nums[1] = 2;
        nums[2] = 4;
        nums[3] = 2;
        nums[4] = 3;
        nums[5] = 3;
        nums[6] = 3;
        singleNumber(nums);
    }
}
