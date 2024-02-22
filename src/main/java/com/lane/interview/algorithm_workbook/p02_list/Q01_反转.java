package com.lane.interview.algorithm_workbook.p02_list;


/**
 * https://leetcode.cn/problems/reverse-linked-list-ii/solutions/37247/bu-bu-chai-jie-ru-he-di-gui-di-fan-zhuan-lian-biao/
 *
 * @author duenpu
 * @date 2023/8/17 18:17
 */
public class Q01_反转 {

    // ------------------------------------------反转单链表------------------------------------------

    // https://leetcode.cn/problems/reverse-linked-list/

    // 非递归方式
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

    // ------------------------------------------反转链表前N个节点------------------------------------------

    // 递归方式
    ListNode pre = null;

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

    // ------------------------------------------反转链表一部分------------------------------------------
    //https://leetcode.cn/problems/reverse-linked-list-ii

    // 非递归，双指针
    // https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/10416/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
    // 这道题的变形
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 定义一个dummyHead, 方便处理
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 初始化指针
        ListNode pre = dummyHead;
        ListNode end = dummyHead.next;

        // 将指针移到相应的位置
        for (int step = 1; step < m; step++) {
            pre = pre.next;
            end = end.next;
        }

        for (int i = 0; i < (n-m) && end != null; i++) {
            end = end.next;
        }
        ListNode start = pre.next;
        ListNode next = end.next;
        end.next = null;
        pre.next = reverse(start);
        start.next = next;
        pre = start;
        end = pre;

        return dummyHead.next;
    }

    // 递归方式
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseBetween1(head.next, m - 1, n - 1);
        return head;
    }

    // ------------------------------------------K个一组反转链表------------------------------------------

    // 非递归，双指针
    // https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/10416/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            ListNode start = pre.next;
            ListNode next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }

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



    // 递归实现
    // https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/151616/di-gui-java-by-reedfan-2/
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
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

    public ListNode reverse(ListNode head, ListNode tail){
        ListNode prev =null;
        ListNode cur = head;
        //只需要把原来判断尾节点为空的，改为在传入节点就行。
        while(cur !=tail){
            ListNode next = cur.next;
            cur.next = prev;
            prev =cur;
            cur = next;
        }
        return prev;
    }

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
