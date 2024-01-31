package com.lane.interview.algorithm_workbook.p07_heap;

/**堆排序
 *
 *
 * @ Author:  duenpu
 * @ Date  :  22:11 2024/1/31
 */
public class HeapSort {
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }
        int heapSize = nums.length;
        swap(nums, 0, --heapSize);
        while(heapSize > 0) {
            heapify(nums, 0, heapSize);
            swap(nums, 0, --heapSize);
        }
        return nums;
    }

    private void heapify(int[] arr, int index, int heapSize){
        int left = (2 * index) + 1;
        while (left < heapSize) {
            int largerChildIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            int largerIndex = arr[largerChildIndex] > arr[index] ? largerChildIndex : index;
            if (largerIndex == index) {
                break;
            }
            swap(arr, index, largerIndex);
            index = largerIndex;
            left = (2 * index) + 1;
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
