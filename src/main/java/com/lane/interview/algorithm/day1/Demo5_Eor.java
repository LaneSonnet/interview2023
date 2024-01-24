package com.lane.interview.algorithm.day1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

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
     * <p>
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

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        int ans = 0;
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    public static HashMap<Integer, Integer> map = new HashMap<>();

    // 请保证arr中，只有一种数出现了K次，其他数都出现了M次
    // K < M
    // M > 1
    public static int onlyKTimes(int[] arr, int k, int m) {
        if (map.size() == 0) {
            mapCreater(map);
        }
        int[] t = new int[32];
        // t[0] 0位置的1出现了几个
        // t[i] i位置的1出现了几个
        for (int num : arr) {
            while (num != 0) {
                int rightOne = num & (-num);
                t[map.get(rightOne)]++;
                num ^= rightOne;
            }
        }
        int ans = 0;
        // 如果这个出现了K次的数，就是0
        // 那么下面代码中的 : ans |= (1 << i);
        // 就不会发生
        // 那么ans就会一直维持0，最后返回0，也是对的！
        for (int i = 0; i < 32; i++) {
            if (t[i] % m != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void mapCreater(HashMap<Integer, Integer> map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            map.put(value, i);
            value <<= 1;
        }
    }

    // 更简洁的写法
    public static int km(int[] arr, int k, int m) {
        int[] help = new int[32];
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                help[i] += (num >> i) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            help[i] %= m;
            if (help[i] != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        // 真命天子出现的次数
        int times = k;
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // 为了测试
    // [-range, +range]
    public static int randomNumber(int range) {
        return (int) (Math.random() * (range + 1)) - (int) (Math.random() * (range + 1));
    }

    // 为了测试
    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimes(arr, k, m);
            int ans3 = km(arr, k, m);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println(ans1);
                System.out.println(ans3);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
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
