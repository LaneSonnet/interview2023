package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 * @ Author:  duenpu
 * @ Date  :  23:44 2023/9/15
 */
public class Q02_nearestIndex {

    //有序数组中满足>=value的最左位置
    //1112244445566777
    public static int nearestIndexLeft(int[] sortedArr, int value) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        int index = -1;
        while (left < right) {
            mid = left + ((right - left) >> 1); // 这行如果写成right - ((right - left) >> 1),那么28行需要判断的是sortedArr[right] >= value
            if (sortedArr[mid] >= value) {
                right = mid - 1;
                index = mid;
            } else {
                left = mid + 1;
            }
        }
        return sortedArr[left] >= value ? left : index; //这行判断left还是right取决于20行
    }

    //有序数组中满足<=value的最右位置
    //1112244445566777
    public static int nearestIndexRight(int[] sortedArr, int value) {
        if (sortedArr == null || sortedArr.length == 0) {
            return  -1;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = 0;
        int index = -1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (sortedArr[mid] <= value) {
                left = mid + 1;
                index = mid;
            } else {
                right = mid - 1;
            }
        }
        return sortedArr[left] <= value ? left : index;
    }

}
