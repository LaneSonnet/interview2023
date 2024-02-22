package com.lane.interview.algorithm_workbook.p06_quickSort;

import com.lane.interview.algorithm_workbook.p02_list.ListNode;

/**
 *
 * 分隔链表
 *
 * https://leetcode.cn/problems/partition-list/description/
 *
 * @author duenpu
 * @date 2024/1/24 20:08
 */
public class Q04_分隔链表 {
    class Solution {
        public ListNode partition(ListNode head, int x) {
            if(head == null || head.next == null) {
                return head;
            }
            ListNode sH = new ListNode(0);
            ListNode smallHead = sH;
            ListNode bH = new ListNode(0);
            ListNode bigHead = bH;
            while (head != null) {
                if (head.val < x) {
                    sH.next = head;
                    sH = sH.next;
                } else {
                    bH.next = head;
                    bH = bH.next;
                }
                head = head.next;
            }
            bH.next = null;
            sH.next = bigHead.next;
            return smallHead.next;
        }
    }
}
