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
    public ListNode partitionSort(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        // 链表转数组
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        ListNode[] nodeArr = new ListNode[size];
        cur = head;
        for (int i = 0;i < size;i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        // 数组快排
        netherlandFlag(nodeArr, 0, size,x);
        ListNode ansHead = nodeArr[0];
        for (int i = 1;i< nodeArr.length;i++) {
            ansHead.next = nodeArr[i];
            ansHead = ansHead.next;
        }
        return ansHead;
    }

    private void netherlandFlag(ListNode[] nodeArr, int left, int right, int x){
        int less = left - 1;
        int more = right;
        int index = left;
        while (index < more) {
            if (nodeArr[index].val == x) {
                index++;
            } else if (nodeArr[index].val < x) {
                SwapUtil.swapListNode(nodeArr, index++,++less);
            } else {
                SwapUtil.swapListNode(nodeArr, index,--more);
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
