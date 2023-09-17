package com.lane.interview.algorithm_workbook.p02_list;

import com.lane.interview.algorithm.day2.Node;

/**
 * https://leetcode.cn/problems/reverse-linked-list-ii/solutions/37247/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
 *
 * @author duenpu
 * @date 2023/8/17 18:17
 */
public class mid_reverse_list {
    // 反转链表
    Node reverse(Node head) {
        if (head.next == null) return head;
        Node last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // 反转链表前N个节点
    Node successor = null;
    Node reverseN(Node head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        Node last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    // 反转链表的一部分
    Node reverseBetween(Node head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }
}
