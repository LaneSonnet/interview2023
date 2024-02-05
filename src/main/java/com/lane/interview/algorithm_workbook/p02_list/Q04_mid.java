package com.lane.interview.algorithm_workbook.p02_list;

/**
 * 链表中点
 *
 * https://leetcode.cn/problems/middle-of-the-linked-list/description/
 *
 * @ Author:  duenpu
 * @ Date  :  20:37 2024/2/5
 */
public class Q04_mid {
    // 链表上中点
    public ListNode middleNode1(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 链表下中点
    public ListNode middleNode2(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode slow = head.next;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 链表上中点的前一个节点
    public ListNode middleNode3(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    // 链表下中点的前一个节点
    public ListNode middleNode4(ListNode head) {
        if(head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
