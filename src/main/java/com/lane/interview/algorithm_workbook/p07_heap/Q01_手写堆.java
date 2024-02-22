package com.lane.interview.algorithm_workbook.p07_heap;

/**
 * 手写堆
 *
 * @ Author:  duenpu
 * @ Date  :  21:16 2024/1/31
 */
public class Q01_手写堆 {
    private int[] heap;

    private int heapSize;

    private final int limit;

    public Q01_手写堆(int limit) {
        this.heap = new int[limit];
        this.heapSize = 0;
        this.limit = limit;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public boolean isFull() {
        return heapSize == limit;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("堆满了！");
        }
        heap[heapSize] = value;
        heapInsert(heap, heapSize++);
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("堆空了！");
        }
        int ans = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return ans;
    }

    private void heapInsert(int[] arr, int index) {
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index, int heapSize) {
        int left = 2 * index + 1;
        while (left < heapSize) {
           int largerChildIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
           int largerIndex = arr[largerChildIndex] > arr[index] ? largerChildIndex : index;
           if (largerIndex == index) {
               break;
           }
           swap(arr, largerIndex, index);
           index = largerIndex;
           left = 2 * index + 1;
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
