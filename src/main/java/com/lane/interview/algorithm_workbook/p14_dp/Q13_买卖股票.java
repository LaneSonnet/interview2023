package com.lane.interview.algorithm_workbook.p14_dp;

/**
 * @ Author:  duenpu
 * @ Date  :  00:47 2024/2/22
 */
public class Q13_买卖股票 {
    // https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/description
    /*
    * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
      你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
      返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
    * */
    class Solution1 {
        //dp[date][isHold]
        // date：0~prices.length - 1
        // isHold：0/1 0-当天持有股票 1-当天不持有股票
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            dp[0][0] = -prices[0];
            dp[0][1] = 0;
            for (int date = 1; date < prices.length; date++) {
                // 今天持有股票：①前一天持有股票，今天没动②今天买股票了
                dp[date][0] = Math.max(dp[date - 1][0], -prices[date]);
                // 今天不持有股票：①前一天有股票，今天卖了②前一天没股票，今天没动
                dp[date][1] = Math.max(dp[date - 1][0] + prices[date], dp[date - 1][1]);
            }
            return dp[prices.length - 1][1];
        }
    }

    // https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/
    /*
    * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
      在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
      返回 你能获得的 最大 利润 。
    * */
    class Solution2 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            dp[0][0] = -prices[0];
            dp[0][1] = 0;
            for (int date = 1; date < prices.length; date++) {
                // 今天持有股票：①前一天持有股票，今天没动②前一天没股票，有一定收益，今天买股票了
                dp[date][0] = Math.max(dp[date - 1][0], dp[date - 1][1] - prices[date]);
                // 今天不持有股票：①前一天有股票，今天卖了②前一天没股票，今天没动
                dp[date][1] = Math.max(dp[date - 1][0] + prices[date], dp[date - 1][1]);
            }
            return dp[prices.length - 1][1];
        }
    }

    // https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/
    /*
    * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
      设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
      注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    * */
    class Solution3 {
        /*
         * dp[date][status]
         * date:0~prices.length - 1
         * status:
         * 0-没操作
         * 1-第一次持有
         * 2-第一次卖出
         * 3-第二次持有
         * 4-第二次卖出
         * */
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[prices.length][5];
            // 初始化
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[0][2] = 0;
            dp[0][3] = -prices[0];
            dp[0][4] = 0;
            for (int date = 1; date < prices.length; date++) {
                // 今天没操作
                dp[date][0] = dp[date - 1][0];
                // 今天第一次持有股票：①前一天就第一次持有了②前一天啥也没干，今天刚买的
                dp[date][1] = Math.max(dp[date - 1][1], dp[date - 1][0] - prices[date]);
                // 今天第一次不持有股票：①前一天就第一次不持有了②前一天第一次持有，今天卖了
                dp[date][2] = Math.max(dp[date - 1][2], dp[date - 1][1] + prices[date]);
                // 今天第二次持有股票：①前一天就第二次持有了②前一天第一次不持有，今天买
                dp[date][3] = Math.max(dp[date - 1][3], dp[date - 1][2] - prices[date]);
                // 今天第二次不持有股票：①前一天就第二次不持有了②前一天第二次持有，今天卖了
                dp[date][4] = Math.max(dp[date - 1][4], dp[date - 1][3] + prices[date]);
            }
            // 返回第一次不持有或者第二次不持有，取最大值
            return Math.max(dp[prices.length - 1][2], dp[prices.length - 1][4]);
        }
    }


    // https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/description/
    /*
    * 给你一个整数数组 prices 和一个整数 k ，其中 prices[i] 是某支给定的股票在第 i 天的价格。
      设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。也就是说，你最多可以买 k 次，卖 k 次。
      注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    *
    * */
    class Solution4 {
        /*
         * dp[date][status]
         * date:0~prices.length - 1
         * status:奇数是持有，偶数是不持有，一共是2*k + 1（为0的情况单独算）
         * */
        public int maxProfit(int k, int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[prices.length][2 * k + 1];
            //初始化 只初始化奇数时买入就行
            for (int i = 1; i < 2 * k; i += 2) {
                dp[0][i] = -prices[0];
            }
            // 总结规律
            // 奇数时持有股票：①前一天就持有②前一天不持有，今天买入
            // 偶数时不持有：①前一天不持有②前一天持有，今天卖了
            for (int date = 1; date < prices.length; date++) {
                dp[date][0] = dp[date - 1][0];
                for (int j = 1; j < 2 * k; j += 2) {
                    dp[date][j] = Math.max(dp[date - 1][j], dp[date - 1][j - 1] - prices[date]);
                    dp[date][j + 1] = Math.max(dp[date - 1][j + 1], dp[date - 1][j] + prices[date]);
                }
            }
            int ans = 0;
            for (int j = 1; j < 2 * k; j += 2) {
                ans = Math.max(ans, dp[prices.length - 1][j + 1]);
            }
            return ans;
        }
    }

    // https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
    /*
    * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
      设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
      卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
      注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    * */
    /*
     * dp[date][status]
     * date:0~prices.length - 1
     * status:
     * 0-今天持有股票
     * 1-今天不持有股票
     * 2-今天卖出股票
     * 3-今天冷冻期
     * */
    class Solution5 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[prices.length][5];
            // 初始化
            dp[0][0] = -prices[0];
            int n = prices.length;
            for (int i = 1; i < prices.length; i++) {
                // 今天持有股票：①前一天就持有股票②前一天冷冻期，今天买③前一天不持有股票，今天买
                dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3], dp[i - 1][1]) - prices[i]);
                // 今天不持有股票：①前一天不持有股票②前一天是冷冻期
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
                // 今天卖出股票：①前一天持有股票，今天卖
                dp[i][2] = dp[i - 1][0] + prices[i];
                // 今天是冷冻期：①前一天卖出了股票
                dp[i][3] = dp[i - 1][2];
            }
            // 返回最后一行的最大值
            return Math.max(dp[n - 1][3], Math.max(dp[n - 1][1], dp[n - 1][2]));
        }
    }

    // https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
    /*
    * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
      你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
      返回获得利润的最大值。
      注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
    * */
    class Solution6 {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            dp[0][0] = -prices[0];
            for (int date = 1; date < prices.length; date++) {
                dp[date][0] = Math.max(dp[date - 1][0], dp[date - 1][1] - prices[date]);
                dp[date][1] = Math.max(dp[date - 1][1], dp[date - 1][0] + prices[date] - fee);
            }
            return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
        }
    }
}
