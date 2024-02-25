package com.lane.interview.algorithm_workbook.p04_merge;

/**
 * 小和问题
 *
 * 在一个数组中，一个数左边比它小的数的总和，叫该数的小和
 * 所有数的小和累加起来，叫数组小和
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 * 给定一个数组arr，求数组小和
 *
 * @ Author:  duenpu
 * @ Date  :  22:28 2024/1/29
 */
public class Q03_小和问题 {
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    // arr[L..R]既要排好序，也要求小和返回
    // 所有merge时，产生的小和，累加
    // 左 排序   merge
    // 右 排序  merge
    // merge
    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        // l < r
        int mid = l + ((r - l) >> 1);
        return
                process(arr, l, mid)
                        +
                        process(arr, mid + 1, r)
                        +
                        merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int index = 0;
        int ans = 0;
        while (p1 <= m && p2 <= r) {
            /*
             * 这一行的意思：
             * 在执行这步操作的时候，左组和右组都已经有序了
             * 如果左组的元素A小于右组的元素B，那么代表右组从B开始往后所有的数都比A大
             * 这也就意味着，总的小和，需要加上X个A (X是右组从B开始往后一共有多少个数)
             * */
            ans += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            /*
             * 为什么是严格小于 不是小于等于
             * 因为左组和右组如果元素一致，必须先拷贝右组
             * 目的就是为了：先找到右组下一个大于当前左组数的元素
             * */
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return ans;
    }
}
