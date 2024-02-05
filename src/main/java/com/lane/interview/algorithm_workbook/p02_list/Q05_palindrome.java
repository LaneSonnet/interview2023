package com.lane.interview.algorithm_workbook.p02_list;

/**
 * 回文
 *
 * @ Author:  duenpu
 * @ Date  :  20:37 2024/2/5
 */
public class Q05_palindrome {
    // 判断回文
    public static boolean isPalindrome3(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode n1 = head;
        ListNode n2 = head;
        while (n2.next != null && n2.next.next != null) { // find mid ListNode
            n1 = n1.next; // n1 -> mid
            n2 = n2.next.next; // n2 -> end
        }
        // n1 中点

        n2 = n1.next; // n2 -> right part first ListNode
        n1.next = null; // mid.next -> null
        ListNode n3 = null;
        while (n2 != null) { // right part convert
            n3 = n2.next; // n3 -> save next ListNode
            n2.next = n1; // next of right ListNode convert
            n1 = n2; // n1 move
            n2 = n3; // n2 move
        }
        n3 = n1; // n3 -> save last ListNode
        n2 = head;// n2 -> left first ListNode
        boolean res = true;
        while (n1 != null && n2 != null) { // check palindrome
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next; // left to mid
            n2 = n2.next; // right to mid
        }
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // recover list
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }

    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        if(fast != null) {
            slow = slow.next;
        }
        while(pre != null && slow != null) {
            if(pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }
}
