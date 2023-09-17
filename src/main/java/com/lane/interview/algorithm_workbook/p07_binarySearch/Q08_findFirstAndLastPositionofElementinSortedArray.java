package com.lane.interview.algorithm_workbook.p07_binarySearch;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 *
 * @ Author:  duenpu
 * @ Date  :  13:28 2023/9/17
 */
public class Q08_findFirstAndLastPositionofElementinSortedArray {

    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[2];
        int leftIndex = nearestIndexLeft(nums, target);
        int rightIndex = nearestIndexRight(nums, target);
        ans[0] = leftIndex;
        ans[1] = rightIndex;
        return ans;
    }

    //有序数组中满足>=value的最左位置
    //1112244445566777
    public int nearestIndexLeft(int[] sortedArr, int value) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        int index = -1;
        int ans = -1;
        while (left < right) {
            mid = left + ((right - left) >> 1); // 这行如果写成right - ((right - left) >> 1),那么28行需要判断的是sortedArr[right] >= value
            if (sortedArr[mid] >= value) {
                right = mid - 1;
                index = mid;
            } else {
                left = mid + 1;
            }
        }
        ans = sortedArr[left] >= value ? left : index; //这行判断left还是right取决于20行
        if (ans == -1 || sortedArr[ans] != value) {
            return -1;
        }
        return ans;
    }

    //有序数组中满足<=value的最右位置
    //1112244445566777
    public int nearestIndexRight(int[] sortedArr, int value) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        int index = -1;
        int ans = -1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] <= value) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        ans = sortedArr[left] <= value ? left : index;
        if (ans == -1 || sortedArr[ans] != value) {
            return -1;
        }
        return ans;
    }
}
