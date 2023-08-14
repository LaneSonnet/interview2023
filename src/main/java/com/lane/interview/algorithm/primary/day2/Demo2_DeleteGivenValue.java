package com.lane.interview.algorithm.primary.day2;

/**
 * @ Author:  duenpu
 * @ Date  :  00:38 2023/8/15
 */
public class Demo2_DeleteGivenValue {
    // 单链表中去除所有值为num的节点
    public static Node removeValue(Node head, int num) {
        // head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // 1 ) head == null
        // 2 ) head != null
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    // 双向链表中去除所有值为num的节点
    public static DoubleNode removeValue(DoubleNode head, int num) {
        // head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
            head.last = null;
        }
        // 1 ) head == null
        // 2 ) head != null
        DoubleNode pre = head;
        DoubleNode cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
                if (cur.next != null) {
                    cur.next.last = pre;
                }
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

}
