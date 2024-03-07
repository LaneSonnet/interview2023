package com.lane.interview.algorithm_workbook.p06_quickSort;

/**
 *
 * 手写快排
 *
 * @author duenpu
 * @date 2024/1/30 20:22
 */
public class Q01_随机快排 {
    class Solution {
        public int[] sortArray(int[] nums) {
            if (nums == null || nums.length < 2) {
                return nums;
            }
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        private void quickSort(int[] arr, int l, int r) {
            if (l > r || l == r) {
                return;
            }
            swap(arr, (l + (int) (Math.random() * (r - l + 1))), r);
            int[] equalArea = netherLandsFlag(arr, l, r);
            quickSort(arr, l, equalArea[0] - 1);
            quickSort(arr, equalArea[1] + 1, r);
        }

        private int[] netherLandsFlag(int[] arr, int l, int r) {
            int less = l - 1;
            int more = r;
            int index = l;
            while (index < more) {
                if (arr[index] == arr[r]) {
                    index++;
                } else if (arr[index] < arr[r]) {
                    swap(arr, index++, ++less);
                } else {
                    swap(arr, index, --more);
                }
            }
            swap(arr, more, r);
            return new int[] { less + 1, more };
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
