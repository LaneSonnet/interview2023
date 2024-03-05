package com.lane.interview.algorithm_workbook.p02_list;


/**
 * https://leetcode.cn/problems/reverse-linked-list-ii/solutions/37247/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
 *
 * @author duenpu
 * @date 2023/8/17 18:17
 */
public class Q01_反转 {
}

class a_反转单链表{
    // ------------------------------------------反转单链表------------------------------------------

    // https://leetcode.cn/problems/reverse-linked-list/

    // 非递归方式
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    // 递归方式
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tmp = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return tmp;
    }
}
class b_反转双链表{
    // ------------------------------------------反转双链表------------------------------------------

    // 非递归方式
    public DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
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

    // 递归方式
    public DoubleNode reverseDoubleList1(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode tmp = reverseDoubleList1(head.next);
        head.next.next = head;
        head.last = head.next;
        return tmp;
    }
}

class c_反转链表前N个节点{
    // ------------------------------------------反转链表前N个节点------------------------------------------
    ListNode pre = null;
    // 递归方式
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            pre = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = pre;
        return newHead;
    }
}

class d_反转链表一部分{
    // ------------------------------------------反转链表一部分------------------------------------------
    //https://leetcode.cn/problems/reverse-linked-list-ii
    // 递归方式
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween1(head.next, m - 1, n - 1);
        return head;
    }

    private ListNode successor = null;
    private ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }
}

class e_K个一组反转链表{
    // ------------------------------------------K个一组反转链表------------------------------------------
    // 递归实现
    // https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/151616/di-gui-java-by-reedfan-2/
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup1(b, k);
        return newHead;
    }

    public ListNode reverse(ListNode head, ListNode tail) {
        ListNode prev = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}

class f_两两一组反转链表{
    // ------------------------------------------两两一组反转链表------------------------------------------
    // https://leetcode.cn/problems/swap-nodes-in-pairs/
    class Solution {
        public ListNode swapPairs(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }
    }
}