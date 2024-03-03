package com.lane.interview.algorithm_workbook.p19_贪心;

/**
 * @ Author:  duenpu
 * @ Date  :  00:39 2024/3/3
 */
public class Q03_跳跃游戏 {
    // https://leetcode.cn/problems/jump-game/description/?company_slug=xiaohongshu
    class Solution {
        public boolean canJump(int[] nums) {
            if(nums==null || nums.length==0) {
                return true;
            }
            int n = nums.length;
            int ans = 0;
            //遍历数组，尝试扩大最大可达范围
            for(int i=0;i<n;++i) {
                if(ans>=i) {
                    ans = Math.max(ans,i+nums[i]);
                }
            }
            if(ans>=n-1) {
                return true;
            }
            return false;
        }
    }
}
