package com.lane.interview.algorithm_workbook.p05_binarySearch;

/**
 *
 * 寻找两个正序数组的中位数
 *
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/description/
 *
 * @ Author:  duenpu
 * @ Date  :  13:45 2023/9/17
 */
public class Q10_寻找两个正序数组的中位数 {
    // https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/8999/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/

    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int n1 = nums1.length, n2 = nums2.length;
            if(n1 > n2) {
                return findMedianSortedArrays(nums2, nums1);
            }
            // n1 <= n2
            int cnt = (n1+n2+1)/2;
            int left = 0, right = n1;
            // 这里的left不是下标，而是取多少个元素
            while(left < right) {
                int mid1 = (left+right)/2;  // nums1取mid1个元素
                int mid2 = cnt-mid1-1;        // nums2取cnt-mid1个元素，下标为cnt-mid1-1
                if(nums1[mid1] >= nums2[mid2]){
                    right = mid1;
                }else{
                    left = mid1+1;
                }
            }
            int cnt1=left, cnt2=cnt-left;  // nums1取left个元素（cnt1）, nums2取cnt-left个元素（cnt2）
            int leftVal = Math.max(cnt1==0?Integer.MIN_VALUE:nums1[cnt1-1] , cnt2==0?Integer.MIN_VALUE:nums2[cnt2-1]);
            if((n1+n2)%2==1){
                return leftVal;
            }
            int rightVal = Math.min(cnt1==n1?Integer.MAX_VALUE:nums1[cnt1] , cnt2==n2?Integer.MAX_VALUE:nums2[cnt2]);
            return (leftVal+rightVal)/2.0;
        }
    }
}
