package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author:  duenpu
 * @ Date  :  00:18 2024/3/5
 */
public class Q13_和为K的子数组 {
    // https://leetcode.cn/problems/subarray-sum-equals-k/description/?envType=study-plan-v2&envId=top-100-liked
    public class Solution {

        public int subarraySum(int[] nums, int k) {
            // key：前缀和，value：key 对应的前缀和的个数
            Map<Integer, Integer> preSumFreq = new HashMap<>();
            // 对于下标为 0 的元素，前缀和为 0，个数为 1
            preSumFreq.put(0, 1);

            int preSum = 0;
            int count = 0;
            for (int num : nums) {
                preSum += num;

                // 先获得前缀和为 preSum - k 的个数，加到计数变量里
                if (preSumFreq.containsKey(preSum - k)) {
                    count += preSumFreq.get(preSum - k);
                }

                // 然后维护 preSumFreq 的定义
                preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
            }
            return count;
        }
    }
}
