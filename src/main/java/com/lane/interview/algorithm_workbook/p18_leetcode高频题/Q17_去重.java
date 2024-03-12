package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import com.lane.interview.algorithm_workbook.p02_list.ListNode;

/**
 * @ Author:  duenpu
 * @ Date  :  20:33 2024/3/12
 */
public class Q17_去重 {
    // 有序数组去重
    // https://leetcode.cn/problems/remove-duplicates-from-sorted-array
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1;
    }
    // 有序链表去重
    // https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.value == cur.next.value) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
    // 有序链表去重(重复的元素都不要)
    // https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode record = new ListNode(0);
        record.next = head;
        ListNode slow = record;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            if (slow.next.val == fast.next.val) {
                while (fast != null && fast.next != null && slow.next.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
                fast = fast.next;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return record.next;
    }
}
