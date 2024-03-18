package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 * @ Author:  duenpu
 * @ Date  :  22:12 2024/2/19
 */
public class Q11_旋转排序数组 {
    // https://leetcode.cn/problems/search-in-rotated-sorted-array
    class Solution {
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) {
                return -1;
            }
            if (nums.length == 1) {
                return target == nums[0] ? 0 : -1;
            }
            int left = 0;
            int right = nums.length - 1;
            int mid = 0;
            while (left < right) {
                mid = left + ((right - left) >> 1);
                if (nums[mid] == target) {
                    return mid;
                } else if (nums[mid] >= nums[0]) {
                    if (target >= nums[0] && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    if (target > nums[mid] && target <= nums[nums.length - 1]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            return nums[left] == target ? left : -1;
        }
    }

    // 寻找旋转排序数组中的最小值
    // https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/?envType=study-plan-v2&envId=top-100-liked
    class Solution1 {
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            int mid = 0;
            while (left < right) {
                mid = left + ((right - left) >> 1);
                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else if (nums[mid] < nums[right]) {
                    right = mid;
                }
            }
            return nums[left];
        }
    }

    // 寻找旋转排序数组中的最大值
    public static int findMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left < right) {
            mid = left + ((right - left + 1) >> 1);
            if (nums[left] > nums[mid]) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return nums[right];
    }

    // 有重复元素，求最小值
    public int findMin1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                right--;
            }
        }
        return nums[left];
    }

    // 有重复元素，求最大值
    public static int findMax1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (nums[left] > nums[mid]) {
                right = mid - 1;
            } else if (nums[mid] > nums[left]) {
                left = mid;
            } else {
                left++;
            }
        }
        return nums[right];
    }


    // https://leetcode.cn/problems/rotate-array/solutions/683855/shu-zu-fan-zhuan-xuan-zhuan-shu-zu-by-de-5937/?envType=study-plan-v2&envId=top-100-liked
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }


}
