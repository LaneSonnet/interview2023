package com.lane.interview.algorithm_workbook.p02_list;

/**
 * @author duenpu
 * @date 2023/8/17 17:39
 */

import com.lane.interview.algorithm.day2.Node;

import java.util.Stack;

/**
 * 两数相加
 */
public class Q02_相加 {

    /*
     * 递归版本
     * */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return dfs(l1, l2, 0);
    }

    private ListNode dfs(ListNode l, ListNode r, int i) {
        // base case
        if (l == null && r == null && i == 0) {
            return null;
        }
        int sum = ((l != null) ? l.value : 0) + ((r != null) ? r.value : 0) + i;
        ListNode newNode = new ListNode(sum % 10);
        newNode.next = dfs(((l != null) ? l.next : null), ((r != null) ? r.next : null), sum / 10);
        return newNode;
    }

    /*
     * https://leetcode.cn/problems/add-two-numbers-ii/description/
     * */

    // 递归实现
    public ListNode addTwoNumbersPro2(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2); // l1 和 l2 反转后，就变成【2. 两数相加】了
        ListNode l3 = process(l1, l2, 0);
        return reverseList(l3);
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head; // 把下一个节点指向自己
        head.next = null; // 断开指向下一个节点的连接，保证最终链表的末尾节点的 next 是空节点
        return newHead;
    }

    private ListNode process(ListNode l, ListNode r, int i) {
        // base case
        if (l == null && r == null && i == 0) {
            return null;
        }
        int sum = ((l != null) ? l.value : 0) + ((r != null) ? r.value : 0) + i;
        ListNode newNode = new ListNode(sum % 10);
        newNode.next = dfs(((l != null) ? l.next : null), ((r != null) ? r.next : null), sum / 10);
        return newNode;
    }
}
