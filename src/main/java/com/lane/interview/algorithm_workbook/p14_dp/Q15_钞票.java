package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  01:04 2024/2/22
 */
public class Q15_钞票 {

    // https://leetcode.cn/problems/target-sum/description/
    // 目标和(返回方法总数)
    class Solution1 {
        public int findTargetSumWays(int[] nums, int target) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            //如果target过大 sum将无法满足
            if ( target < 0 && sum < -target) {
                return 0;
            }
            if ((target + sum) % 2 != 0) {
                return 0;
            }
            int size = (target + sum) / 2;
            if(size < 0) {
                size = -size;
            }
            int[] dp = new int[size + 1];
            dp[0] = 1;
            for (int i = 0; i < nums.length; i++) {
                for (int j = size; j >= nums[i]; j--) {
                    dp[j] += dp[j - nums[i]];
                }
            }
            return dp[size];
        }
    }

    // https://leetcode.cn/problems/coin-change/?envType=study-plan-v2&envId=top-100-liked
    // 返回最少的硬币个数
    class Solution2 {
        public int coinChange(int[] coins, int amount) {
            int max = Integer.MAX_VALUE;
            int[] dp = new int[amount + 1];
            //初始化dp数组为最大值
            for (int j = 0; j < dp.length; j++) {
                dp[j] = max;
            }
            //当金额为0时需要的硬币数目为0
            dp[0] = 0;
            for (int i = 0; i < coins.length; i++) {
                //正序遍历：完全背包每个硬币可以选择多次
                for (int j = coins[i]; j <= amount; j++) {
                    //只有dp[j-coins[i]]不是初始最大值时，该位才有选择的必要
                    if (dp[j - coins[i]] != max) {
                        //选择硬币数目最小的情况
                        dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                    }
                }
            }
            return dp[amount] == max ? -1 : dp[amount];
        }
    }

    // https://leetcode.cn/problems/coin-change-ii/description/
    // 返回一共多少种
    class Solution3 {
        public int change(int amount, int[] coins) {
            //递推表达式
            int[] dp = new int[amount + 1];
            //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
            dp[0] = 1;
            for (int i = 0; i < coins.length; i++) {
                for (int j = coins[i]; j <= amount; j++) {
                    dp[j] += dp[j - coins[i]];
                }
            }
            return dp[amount];
        }
    }
}
