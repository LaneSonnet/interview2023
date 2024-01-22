package com.lane.interview.algorithm.day1;

/**
 * @ Author:  duenpu
 * @ Date  :  23:04 2023/8/9
 */
public class Demo2_Sort {
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    public static void swap1(int[] arr, int i, int j) {
        // 不能使用异或运算，因为i和j可能是同一个位置,会等价于自己和自己异或
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /*
        选择排序
        时间复杂度O(n²)
    */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        // 0 ~ N-1
        // 1 ~ N-1
        // 2 ~ N-1
        // ...
        // 找到这个范围上最小值的下标，放到i位置上
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }
            swap1(arr, i, minIndex);
        }
    }

    /*
        冒泡排序
        时间复杂度O(n²)
    */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        // 0 ~ N-1
        // 0 ~ N-2
        // 0 ~ N-3
        // ...
        // 每次对相邻的两个数进行比较，把较大的数放到后面，每次都能确定一个最大值
        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap1(arr, j, j + 1);
                }
            }
        }
    }

    /*
        插入排序
        时间复杂度O(n²)
    */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int length = arr.length;
        // 0 ~ 0 有序的(跳过)
        // 0 ~ 1 有序的
        // 0 ~ 2 有序的
        // ...
        // 从end位置开始，往前看，如果前面的数比自己大，就往前交换，直到不比自己大或者到了0位置
        for (int end = 1; end < length; end++) {
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 5, 4};
//        selectSort(arr);
//        bubbleSort(arr);
        insertSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
