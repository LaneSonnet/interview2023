package com.lane.interview.algorithm_workbook.p02_list;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  11:00 2024/2/25
 */
public class Q11_重排 {

    // https://leetcode.cn/problems/reorder-list/description/

    class Solution {

        public void reorderList(ListNode head) {
            if (head == null || head.next == null) {
                return;
            }

            // 找到链表的中间节点
            ListNode mid = findMiddle(head);

            // 将链表分为两部分，并反转后一部分
            ListNode secondHalf = reverse(mid.next);
            mid.next = null;

            // 合并两个链表
            merge(head, secondHalf);
        }

        // 找到链表的中间节点
        private ListNode findMiddle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        // 反转链表
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode next = null;
            while (head != null) {
                next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }

        int count = 0;
        // 合并两个链表
        private ListNode merge(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            } else if (l2 == null) {
                return l1;
            }
            if (count % 2 == 0) {
                count++;
                l1.next = merge(l1.next, l2);
                return l1;
            } else {
                count++;
                l2.next = merge(l1, l2.next);
                return l2;
            }
        }
    }
}
