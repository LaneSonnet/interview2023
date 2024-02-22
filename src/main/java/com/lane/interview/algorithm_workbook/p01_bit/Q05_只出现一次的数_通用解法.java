package com.lane.interview.algorithm_workbook.p01_bit;

/**
 * 请保证arr中，只有一种数出现了K次，其他数都出现了M次
 * K < M
 * M > 1
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q05_只出现一次的数_通用解法 {
    public int singleNumber(int[] nums, int k, int m) {
       int[] helpArr = new int[32];
       for (int num : nums) {
           for (int i = 0;i < 32; i++) {
               helpArr[i] += (num >> i ) & 1;
           }
       }
       int ans = 0;
       for (int i = 0;i<32;i++) {
           int bit = helpArr[i] % m;
           if (bit != 0){
               ans |= 1 << i;
           }
       }
       return ans;
    }
}
