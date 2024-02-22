package com.lane.interview.algorithm_workbook.p04_merge;

/**
 *
 * 逆序对
 *
 * https://leetcode.cn/problems/reverse-pairs/description/
 *
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q05_逆序对 {
    class Solution {
        public int reversePairs(int[] record) {
            if (record == null || record.length < 2) {
                return 0;
            }
            return process(record, 0, record.length - 1);
        }

        private int process(int[] arr, int l, int r) {
            if (l == r) {
                return 0;
            }
            int m = l + ((r - l) >> 1);
            return process(arr, l, m)
                    + process(arr, m + 1, r)
                    + merge(arr, l, m, r);
        }

        private int merge(int[] arr, int l, int m, int r) {
            int[] help = new int[r - l + 1];
            int p1 = m;
            int p2 = r;
            int index = help.length - 1;
            int ans = 0;
            while (p1 >= l && p2 > m) {
                ans += arr[p1] > arr[p2] ? (p2 - m) : 0;
                help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
            }
            while (p1 >= l) {
                help[index--] = arr[p1--];
            }
            while (p2 > m) {
                help[index--] = arr[p2--];
            }
            for (int i = 0; i < help.length; i++) {
                arr[l + i] = help[i];
            }
            return ans;
        }
    }
}
