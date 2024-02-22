package com.lane.interview.algorithm_workbook.p06_quickSort;

/**
 *
 * 移动0
 * https://leetcode.cn/problems/move-zeroes/
 *
 * @author duenpu
 * @date 2024/1/30 20:22
 */
public class Q02_移动0 {
    class Solution {
        public void moveZeroes(int[] nums) {
            if(nums==null) {
                return;
            }
            //两个指针i和j
            int j = 0;
            for(int i=0;i<nums.length;i++) {
                //当前元素!=0，就把其交换到左边，等于0的交换到右边
                if(nums[i]!=0) {
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j++] = tmp;
                }
            }
        }
    }
}
