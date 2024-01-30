package com.lane.interview.algorithm.day5;

import java.util.Arrays;

/**
 * 基数排序（桶排序的一种）
 * 1. 只能对非负整数进行排序(如果有负数，先找到最小的负数，所有数加这个数的绝对值,都变成正数，然后排序，再减回去)
 * 2. 时间复杂度O(N)
 * 3. 额外空间复杂度O(N)
 * 4. 稳定排序
 * 5. 有局限性，只能对整数进行排序，且只能对0~200范围内的整数进行排序
 *
 * 过程：
 * 先确定所有数中最多位的数有几位，其余数的高位补0
 * 1. 申请一个额外空间，大小为10
 * 2. 遍历数组，将数组中的元素的个位数作为下标，将对应下标的值+1
 * 3. 遍历额外空间，将额外空间中的元素依次放回原数组
 * 4. 申请一个额外空间，大小为10
 * 5. 遍历数组，将数组中的元素的十位数作为下标，将对应下标的值+1
 * 6. 遍历额外空间，将额外空间中的元素依次放回原数组
 * 以此类推
 *
 * 一句话，就是先比个位，再比十位，再比百位，以此类推
 */
public class Demo4_RadixSort {

	// only for no-negative value 只适用于非负整数
	public static void radixSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		radixSort(arr, 0, arr.length - 1, maxbits(arr));
	}

	public static int maxbits(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int res = 0;
		while (max != 0) {
			res++;
			max /= 10;
		}
		return res;
	}

	// arr[L..R]排序  ,  最大值的十进制位数digit
	// 看不懂的话，看视频09 1小时40分左右
	public static void radixSort(int[] arr, int L, int R, int digit) {
		final int radix = 10;
		int i = 0, j = 0;
		// 有多少个数准备多少个辅助空间
		int[] help = new int[R - L + 1];
		for (int d = 1; d <= digit; d++) { // 有多少位就进出几次，d是进出的次数
			// 10个空间
		    // count[0] 当前位(d位)是0的数字有多少个
			// count[1] 当前位(d位)是(0和1)的数字有多少个
			// count[2] 当前位(d位)是(0、1和2)的数字有多少个
			// count[i] 当前位(d位)是(0~i)的数字有多少个
			// 转换成累加和数组
			int[] count = new int[radix]; // count[0..9]
			for (i = L; i <= R; i++) {
				// 103  1   3
				// 209  1   9
				j = getDigit(arr[i], d);
				count[j]++;
			}
			// count数组变成累加和数组
			for (i = 1; i < radix; i++) {
				count[i] = count[i] + count[i - 1];
			}
			// 从右往左遍历，看当前位是d位的数字应该放在什么位置
			for (i = R; i >= L; i--) {
				j = getDigit(arr[i], d);
				help[count[j] - 1] = arr[i];
				count[j]--;
			}
			for (i = L, j = 0; i <= R; i++, j++) {
				arr[i] = help[j];
			}
		}
	}

	public static int getDigit(int x, int d) {
		return ((x / ((int) Math.pow(10, d - 1))) % 10);
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100000;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			radixSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		radixSort(arr);
		printArray(arr);

	}

}
