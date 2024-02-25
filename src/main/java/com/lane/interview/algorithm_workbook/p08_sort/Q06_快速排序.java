package com.lane.interview.algorithm_workbook.p08_sort;

/**
 * 随机快速排序
 * <p>
 * 时间复杂度O(N*logN)
 * 额外空间复杂度O(logN)
 * 无稳定性
 *
 * @ Author:  duenpu
 * @ Date  :  22:06 2024/2/3
 */
public class Q06_快速排序 {
    public static int[] sortArray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        SwapUtil.swap(nums, (int) (l + (Math.random() * (r - l + 1))), r);
        int[] equalArea = netherlandFlag(nums, l, r);
        quickSort(nums, 0, equalArea[0] - 1);
        quickSort(nums, equalArea[1] + 1, r);
    }

    private static int[] netherlandFlag(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] == arr[r]) {
                index++;
            } else if (arr[index] < arr[r]) {
                SwapUtil.swap(arr, index++, ++less);
            } else {
                SwapUtil.swap(arr, index, --more);
            }
        }
        SwapUtil.swap(arr, r, more);
        return new int[]{less + 1, more};
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4, -1, -4, 19, 23, 2, 5, 74, -21};
        int[] ints = sortArray(arr);
        for (int i : ints) {
            System.out.println(i);
        }
    }
}
