package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import com.lane.interview.algorithm_workbook.p02_list.ListNode;

import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  18:07 2024/3/3
 */
public class Q10_各种相加 {
    // 字符串相加
    // https://leetcode.cn/problems/add-strings/description/
    public String addStrings(String num1, String num2) {
        if ((num1 == null || num1.length() == 0) && (num2 == null || num2.length() == 0)) {
            return "";
        }
        StringBuffer ans = new StringBuffer();
        int cas = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = cas;
            sum += i >= 0 ? num1.charAt(i) - '0' : 0;
            sum += j >= 0 ? num2.charAt(j) - '0' : 0;
            cas = sum / 10;
            ans.append(sum % 10);
        }
        ans.append(cas == 1 ? 1 : "");
        return ans.reverse().toString();
    }

    // 链表相加
    // https://leetcode.cn/problems/add-two-numbers/description/
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        return process(l1, l2, 0);
    }

    private ListNode process(ListNode l1, ListNode l2, int cas) {
        if (l1 == null && l2 == null && cas == 0) {
            return null;
        }
        int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + cas;
        ListNode newHead = new ListNode(sum % 10);
        cas = sum / 10;
        newHead.next = process((l1 != null ? l1.next : null), (l2 != null ? l2.next : null), cas);
        return newHead;
    }

    // 链表相加2
    // https://leetcode.cn/problems/add-two-numbers-ii/description/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        l1 = reverse(l1);
        l2 = reverse(l2);
        // 链表相加
        ListNode newHead = process1(l1, l2, 0);
        return reverse(newHead);
    }

    private ListNode process1(ListNode l1, ListNode l2, int cas) {
        if (l1 == null && l2 == null && cas == 0) {
            return null;
        }
        int sum = cas;
        sum += l1 != null ? l1.val : 0;
        sum += l2 != null ? l2.val : 0;
        ListNode newHead = new ListNode(sum % 10);
        cas = sum / 10;
        newHead.next = process1((l1 != null ? l1.next : null), (l2 != null ? l2.next : null), cas);
        return newHead;
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
}
