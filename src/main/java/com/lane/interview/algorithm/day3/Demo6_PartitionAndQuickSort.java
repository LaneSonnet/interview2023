package com.lane.interview.algorithm.day3;


/**
 * 快排
 *
 * 荷兰国旗问题
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，
 * 等于num的数放在数组的中间，大于num的数放在数组的右边。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 *
 */
public class Demo6_PartitionAndQuickSort {

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// arr[L..R]上，以arr[R]位置的数做划分值

	/**
	 * 右边界一开始为-1
	 * 遍历数组，如果当前值小于等于划分值，就把当前值和右边界的下一个值交换，右边界向右扩一个位置，当前数向右移动
	 * 如果当前值大于划分值，就不做任何操作，当前数继续向右移动
	 * 遍历结束后，把右边界的下一个值和划分值交换，返回右边界
	 *
	 * 本质上就是把小于等于划分值的数挪到左边
	 */
	public static int partition(int[] arr, int L, int R) {
		if (L > R) {
			return -1;
		}
		if (L == R) {
			return L;
		}
		int lessEqual = L - 1;
		int index = L;
		while (index < R) {
			if (arr[index] <= arr[R]) {
				swap(arr, index, ++lessEqual);
			}
			index++;
		}
		// 最后一个数再跟右边界的下一个数做交换，右边界再推一位
		swap(arr, ++lessEqual, R);
		// 返回右边界
		return lessEqual;
	}

	// arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
	// <arr[R] ==arr[R] > arr[R]

	/**
	 * 三个区域，小于区域，等于区域，大于区域
	 * 当前数小于目标数，当前数和小于区域的下一个数交换，小于区域向右扩一个位置，当前数向右移动
	 * 当前数等于目标数，当前数向右移动
	 * 当前数大于目标数，当前数和大于区域的前一个数交换，大于区域向左扩一个位置，当前数不动
	 * 当前数和大于区域撞上了，遍历结束
	 * 把大于区域的前一个数和目标数交换，返回等于区域的左边界和右边界
	 *
	 * [1,4,5,2,6,7,3,4,6,4] 最后一个数为4，4就是目标数
	 * 排序后
	 * [1,2,3,|4,4,4,|5,6,6,7]
	 * 最后返回数组[3,4,5]，也就是“等于区域”的下标
	 */
	public static int[] netherlandsFlag(int[] arr, int L, int R) {
		if (L > R) { // L...R L>R
			return new int[] { -1, -1 };
		}
		if (L == R) {
			return new int[] { L, R };
		}
		// 先把整个数组最后一个数剔除做操作，也就是L到(R-1)这个范围
		// 左边界往左推一个，那左边界就是L-1
		// 右边界往右推一个，那右边界就是R
		int less = L - 1; // < 区 右边界
		int more = R; // > 区 左边界
		int index = L;
		while (index < more) { // 当前位置，不能和 >区的左边界撞上
			if (arr[index] == arr[R]) {
				index++;
			} else if (arr[index] < arr[R]) {
//				swap(arr, less + 1, index);
//				less++;
//				index++;						
				swap(arr, index++, ++less);
			} else { // >
				swap(arr, index, --more);
			}
		}
		// 最后一个数再跟>区域的第一个数做交换
		swap(arr, more, R); // <[R]   =[R]   >[R]
		return new int[] { less + 1, more };
	}

	/**
	 * 快排 1.0
	 * O(n²)
	 */
	public static void quickSort1(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process1(arr, 0, arr.length - 1);
	}

	public static void process1(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// L..R partition arr[R] [ <=arr[R] arr[R] >arr[R] ]
		int M = partition(arr, L, R);
		process1(arr, L, M - 1);
		process1(arr, M + 1, R);
	}

	/**
	 * 快排 2.0
	 * O(n²)
	 */
	public static void quickSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process2(arr, 0, arr.length - 1);
	}

	// arr[L...R] 排有序，快排2.0方式
	public static void process2(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// [ equalArea[0]  ,  equalArea[0]]
		int[] equalArea = netherlandsFlag(arr, L, R);
		process2(arr, L, equalArea[0] - 1);
		process2(arr, equalArea[1] + 1, R);
	}

	/**
	 * 快排 3.0
	 * O(N*logN)期望概率收敛
	 * 额外空间复杂度 最好情况O(logN) 最差O(N) 最终收敛于O(logN)
	 */
	public static void quickSort3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process3(arr, 0, arr.length - 1);
	}

	public static void process3(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// 改变思路，不用最右侧的数作为每次的划分值了，因为极有可能造成数据倾斜
		// 每次随机选一个数，和最右侧的数交换，这样每次的划分值都是区域内随机一个数
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] equalArea = netherlandsFlag(arr, L, R);
		process3(arr, L, equalArea[0] - 1);
		process3(arr, equalArea[1] + 1, R);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr1);
			quickSort1(arr1);
			quickSort2(arr2);
			quickSort3(arr3);
			if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
				succeed = false;
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Oops!");

	}

}
