package com.lane.interview.algorithm_workbook.p02_list;


import com.lane.interview.algorithm.day2.Node;
import com.lane.interview.algorithm_workbook.p08_sort.SwapUtil;

/**
 * 链表的荷兰国旗问题
 *
 * @ Author:  duenpu
 * @ Date  :  20:37 2024/2/5
 */
public class Q07_链表快排 {
    /**
     * 把链表放入数组中，然后用荷兰国旗问题的解法，最后再把数组转换成链表
     */
    public static Node listPartition1(Node head, int x) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, x);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int x) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < x) {
                SwapUtil.swapNode(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == x) {
                index++;
            } else {
                SwapUtil.swapNode(nodeArr, --big, index);
            }
        }
    }

    /**
     *
     * 分隔链表
     *
     * https://leetcode.cn/problems/partition-list/description/
     */
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
