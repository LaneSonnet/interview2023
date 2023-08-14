package com.lane.interview.algorithm.primary.day2;

/**
 * @ Author:  duenpu
 * @ Date  :  00:42 2023/8/14
 */
public class Demo1_ReverseList {
    // 反转单链表
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            // 保存下一个节点
            next = head.next;
            // 当前节点指向前一个节点
            head.next = pre;
            // 前一个节点指向当前节点
            pre = head;
            // 当前节点指向下一个节点
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
