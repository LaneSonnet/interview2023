package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 *
 * 数组先升后降
 *
 * @ Author:  duenpu
 * @ Date  :  21:43 2024/3/11
 */
public class Q12_半有序数组 {
    // 找target
    // 先升序后降序数组中查找目标值的代码
    public static int searchInAscDescArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1; // 数组为空，返回-1表示未找到
        }
        // 找到数组中最大值所在的索引
        int maxIndex = findMaxIndex(nums);
        // 在最大值的左侧部分进行二分查找
        int leftResult = binarySearchAsc(nums, 0, maxIndex, target);
        // 在最大值的右侧部分进行二分查找
        int rightResult = binarySearchDesc(nums, maxIndex + 1, nums.length - 1, target);
        // 返回左右两侧查找结果的最终结果
        return leftResult != -1 ? leftResult : rightResult;
    }
    // 二分查找函数
    private static int binarySearchAsc(int[] nums, int left,int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid; // 找到目标值，返回索引
            } else if (nums[mid] < target) {
                left = mid + 1; // 目标值在右侧部分，更新左边界
            } else {
                right = mid - 1; // 目标值在左侧部分，更新右边界
            }
        }
        return -1; // 未找到目标值，返回-1
    }
    private static int binarySearchDesc(int[] nums, int left,int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid; // 找到目标值，返回索引
            } else if (nums[mid] > target) {
                left = mid + 1; // 目标值在右侧部分，更新左边界
            } else {
                right = mid - 1; // 目标值在左侧部分，更新右边界
            }
        }
        return -1; // 未找到目标值，返回-1
    }
    // 找到数组中最大值所在的索引
    private static int findMaxIndex(int[] arr) {
        if (arr.length < 2) {
            return arr[0];
        }
        int left = 0, right = arr.length - 1;
        int mid = 0 ;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            } else if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                if (arr[left] > arr[right]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return left;
    }
    // 先降序后升序数组中查找目标值的代码
    public static int searchInDescAscArray(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1; // 数组为空，返回-1表示未找到
        }
        // 找到数组中最小值所在的索引
        int minIndex = findMinIndex(nums); // 这里调用找最小值索引的方法
        // 在最小值的左侧部分进行二分查找
        int leftResult = binarySearchDesc(nums, 0, minIndex, target);
        // 在最小值的右侧部分进行二分查找
        int rightResult = binarySearchAsc(nums, minIndex + 1, nums.length - 1, target);
        // 返回左右两侧查找结果的最终结果
        return leftResult != -1 ? leftResult : rightResult;
    }
    // 先降序后升序数组,找到数组中最小值所在的索引
    private static int findMinIndex(int[] arr) {
        if (arr.length < 2) {
            return 0; // 如果数组长度小于2，返回第一个元素的索引
        }
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                // 如果当前元素小于后一个元素，说明处于先降序的部分，将右边界移动到 mid
                right = mid;
            } else if (arr[mid] > arr[mid + 1]) {
                // 如果当前元素大于后一个元素，说明处于后升序的部分，将左边界移动到 mid + 1
                left = mid + 1;
            } else {
                // 如果相等，则需要考虑平原部分
                if (arr[left] < arr[right]) {
                    // 如果左边界元素小于右边界元素，则右边界左移一位
                    right--;
                } else {
                    // 否则，左边界右移一位
                    left++;
                }
            }
        }
        return left; // 返回最小值所在的索引
    }

    public static void main(String[] args) {
        int[] testArr = new int[]{1,9,8,7};
        int[] testArr1 = new int[]{9,8,7,6,10,11};
        int i = searchInDescAscArray(testArr, 7);
        int minIndex = findMinIndex(testArr1);
        int maxIndex = findMaxIndex(testArr);
        System.out.println(i);
        System.out.println(minIndex);
        System.out.println(maxIndex);
    }
}
