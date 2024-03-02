package com.lane.interview.interview.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @ Author:  duenpu
 * @ Date  :  21:28 2024/3/1
 */
public class Q04_binarySearch {

    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 1;
        int right = x / 2;
        int mid = 0;
        int ans = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] tmp = new int[Math.min(nums1.length, nums2.length)];
        int p1 = 0;
        int p2 = 0;
        int index = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                tmp[index] = nums1[p1];
                p1++;
                p2++;
                index++;
            }
        }
        return Arrays.copyOfRange(tmp, 0, index);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                set.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (target < nums[0]) {
            return 0;
        }
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        int index = -1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                index = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return nums[left] >= target ? left : index;
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int left = 0;
        int sum = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

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
            mid = left + ((right - left >> 1));
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[0]) {
                if (target >= nums[0] && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return nums[left] == target ? left : -1;
    }
}
