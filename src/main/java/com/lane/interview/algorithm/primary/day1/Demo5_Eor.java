package com.lane.interview.algorithm.primary.day1;

/**
 * 异或运算
 *
 * @ Author:  duenpu
 * @ Date  :  00:50 2023/8/13
 */
public class Demo5_Eor {

    /**
     * 异或→无进位相加
     * 110 ^ 111 = 001
     * 0 ^ N = N
     * N ^ N = 0
     *
     * 异或运算满足交换律和结合律
     * a ^ b ^ c ^ d = a ^ c ^ b ^ d = a ^ d ^ b ^ c
     * a ^ b = c → a ^ c = b → b ^ c = a
     */

    /**
     * 位运算骚操作
     *
     * n << 1 → n * 2
     * n >> 1 → n / 2
     * n << 1 ^ 1 → n * 2 + 1
     * n >> 1 ^ 1 → (n - 1) / 2
     */

    // 交换两个数
    public static void swap(int[] arr, int i, int j) {
        // 不能使用异或运算，因为i和j可能是同一个位置,会等价于自己和自己异或
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j]; // arr[i] ^ arr[j] ^ arr[j] = arr[i]
        arr[i] = arr[i] ^ arr[j]; // arr[i] ^ arr[j] ^ arr[i] = arr[j]
    }

    // 数组中只有一种数出现了奇数次，其他数都出现了偶数次，找到这个数
    public static int findOddTimesNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        return eor;
    }

    // 把一个int类型的数，提取出最右侧的1来
    public static int rightOne(int num) {
        return num & (~num + 1);
    }

    // 数组中只有两种数(两种数不相等)出现了奇数次，其他数都出现了偶数次，打印这两个数
    public static void printOddTimesNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // eor = a ^ b
        // eor != 0(两种数不相等)
        // eor必然有一个位置上是1
        // eor最右侧的1，提取出来
        int rightOne = eor & (~eor + 1); // 提取出最右侧的1
        // 假设第8位是1，整个数组的元素可以分成两类，一类第8位是1，一类第8位是0
        // 第8位是1的数，与上rightOne，肯定不等于0
        // 第8位是0的数，与上rightOne，肯定等于0
        // 找到所有第8位是1的数，异或在一起，就是a或者b
        int onlyOne = 0;
        for (int i = 0; i < arr.length; i++) {
            // arr[1] = 111100011110000
            // rightOne = 000000000010000
            // 两个数 & rightOne != 0
            if ((arr[i] & rightOne) != 0) { // 或者写成(arr[i] & rightOne) == rightOne 或者写成(arr[i] & rightOne) == 0(找另外一类数)
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    // 打印一个int的二进制中1的个数
    public static void printOneNum(int num) {
        int count = 0;
        while (num != 0) {
            int rightOne = num & (~num + 1);
            count++;
            //把最右侧的1抹掉
            // 1110111000
            // 0000001000
            // 1110110000
            num ^= rightOne;
        }
        System.out.println(count);
    }
}
