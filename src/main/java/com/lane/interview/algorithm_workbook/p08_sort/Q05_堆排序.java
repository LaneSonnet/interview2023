package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * 堆排序
 *
 * 时间复杂度O(N*logN)
 * 额外空间复杂度O(1)
 * 无稳定性
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q05_堆排序 {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }
        int heapSize = nums.length;
        SwapUtil.swap(nums, 0, --heapSize);
        while (heapSize > 0) {
            heapify(nums, 0, heapSize);
            SwapUtil.swap(nums, 0, --heapSize);
        }
        return nums;
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        int left = (2 * index) + 1;
        while (left < heapSize) {
            int largerChildIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            int largerIndex = arr[largerChildIndex] > arr[index] ? largerChildIndex : index;
            if (largerIndex == index) {
                break;
            }
            SwapUtil.swap(arr, largerIndex, index);
            index = largerIndex;
            left = 2 * index + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, -1, -4, 19, 23, 2, 5, 74, -21};
        int[] ints = sortArray(arr);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
