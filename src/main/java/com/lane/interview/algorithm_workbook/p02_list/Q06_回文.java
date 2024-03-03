package com.lane.interview.algorithm_workbook.p02_list;

/**
 * 回文
 * https://leetcode.cn/problems/palindrome-linked-list/description/?envType=study-plan-v2&envId=top-100-liked
 *
 * @ Author:  duenpu
 * @ Date  :  20:37 2024/2/5
 */
public class Q06_回文 {
    // 判断回文
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return Boolean.TRUE;
        }
        // 找上中点
        ListNode mid = getMidNode(head);
        ListNode newHead = reverseList1(mid.next);
        mid.next = null;
        while (head != null && newHead != null) {
            if (head.val != newHead.val) {
                return Boolean.FALSE;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return Boolean.TRUE;
    }

    private ListNode getMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
