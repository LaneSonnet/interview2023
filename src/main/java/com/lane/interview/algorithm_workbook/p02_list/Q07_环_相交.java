package com.lane.interview.algorithm_workbook.p02_list;

import com.lane.interview.algorithm.day2.Node;
import com.lane.interview.algorithm.day6.Demo5_FindFirstIntersectNode;

/**
 * 环&相交问题
 *
 *
 * @ Author:  duenpu
 * @ Date  :  20:37 2024/2/5
 */
public class Q07_环_相交 {
    /**
     * 给定两个可能有环也可能无环的单链表，头节点head1和head2。 请实现一个函数， 如果两个链表相交，请返回相交的第一个节点； 如果不相交，返回null 即可。
     * 【要求】 如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
     */

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        // 两个无环链表
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        // 两个有环链表
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        // 一个有环，一个无环，不可能相交
        return null;
    }

    // 找到链表第一个入环节点，如果无环，返回null
    public static Node getLoopNode(Node head) {
        // 至少保证三个节点，这样下面不会报错
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        // slow 慢  fast 快
        // slow步长1，fast步长2
        Node slow = head.next; // n1 -> slow
        Node fast = head.next.next; // n2 -> fast
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow fast  相遇
        fast = head; // n2 -> walk again from head
        // slow步长1，fast步长1
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // 快慢指针相遇的节点就是入环节点
        return slow;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        // n是为了记录两个链表的长度差
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        // 两个链表最后一个节点不相等，说明不相交
        if (cur1 != cur2) {
            return null;
        }
        // n  :  链表1长度减去链表2长度的值
        cur1 = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
        n = Math.abs(n);
        //长的链表先走n步
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个有环链表，返回第一个相交节点，如果不想交返回null
    // 三种情况：
    // 1. 不相交
    // 2. 两个链表入环节点相同
    // 3. 两个链表入环节点不同
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        // 情况2 与无环节点找交点一模一样
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // 情况1或情况3
            // 链表1一步一步走，直到遇到loop2(情况3)，如果没有遇到，说明不相交(情况1)
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    //此处返回loop1或loop2都可以
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
}
