package com.lane.interview.algorithm_workbook.p06_quickSort;

/**
 *
 * 颜色分类
 *
 * https://leetcode.cn/problems/sort-colors/description/
 *
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q03_颜色分类 {
    class Solution {
        public void sortColors(int[] nums) {
            nerherlansFlag(nums, 0, nums.length - 1, 1);
        }

        private void nerherlansFlag(int[] arr, int l, int r, int target) {
            if (l > r) {
                return;
            }
            if (l == r) {
                return;
            }
            int less = l - 1;
            int more = r + 1;
            int index = l;
            while (index < more) {
                if (arr[index] == target) {
                    index++;
                } else if (arr[index] < target) {
                    swap(arr, index++, ++less);
                } else {
                    swap(arr, index, --more);
                }
            }
        }

        private void swap(int[] arr, int a, int b) {
            if (a == b) {
                return;
            }
            arr[a] = arr[a] ^ arr[b];
            arr[b] = arr[a] ^ arr[b];
            arr[a] = arr[a] ^ arr[b];
        }
    }
}
