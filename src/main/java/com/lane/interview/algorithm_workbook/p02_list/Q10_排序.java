package com.lane.interview.algorithm_workbook.p02_list;

/**
 * 排序链表
 * https://leetcode.cn/problems/sort-list/description/
 *
 * @author duenpu
 * @date 2024/1/30 20:04
 */
public class Q10_排序 {

    // https://leetcode.cn/problems/sort-list/solutions/437400/pai-xu-lian-biao-di-gui-die-dai-xiang-jie-by-cherr/

    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode mid = getMidNode(head);
            ListNode nextHead = mid.next;
            mid.next = null;
            ListNode head1 = sortList(head);
            ListNode head2 = sortList(nextHead);
            return merge(head1, head2);
        }

        public ListNode merge(ListNode h1, ListNode h2) { //合并两个有序链表
            if (h1 == null) {
                return h2;
            } else if (h2 == null) {
                return h1;
            }
            if (h1.val < h2.val) {
                h1.next = merge(h1.next, h2);
                return h1;
            } else {
                h2.next = merge(h1,h2.next);
                return h2;
            }
        }

        private ListNode getMidNode(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }
    }
}
