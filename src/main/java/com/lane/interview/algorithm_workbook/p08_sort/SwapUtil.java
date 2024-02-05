package com.lane.interview.algorithm_workbook.p08_sort;

import com.lane.interview.algorithm.day2.Node;

/**
 * @ Author:  duenpu
 * @ Date  :  23:41 2024/2/3
 */
public class SwapUtil {
    public static void swap(int[] nums, int a, int b) {
        if (a == b) {
            return;
        }
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    public static void swapNode(Node[] nodeArr, int a, int b) {
        Node tmp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = tmp;
    }
}
