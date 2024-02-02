package com.lane.interview.algorithm_workbook.p07_heap;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 前k个高频元素
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * https://leetcode.cn/problems/top-k-frequent-elements/description/
 *
 * @author duenpu
 * @date 2024/1/30 20:04
 */
public class leetcode_347_topKFrequent {
    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for(int n:nums){
                map.put(n,map.getOrDefault(n,0)+1);
            }
            //从大到小排序，这样后面取前k个元素就可以了
            PriorityQueue<Integer> pq = new PriorityQueue((e1, e2) -> map.get(e2) - map.get(e1));
            for(int key:map.keySet()){
                pq.add(key);
            }

            int[] res=new int[k];
            for (int i = 0; i < k; i++) {
                res[i]=pq.poll();
            }
            return res;
        }
    }
}
