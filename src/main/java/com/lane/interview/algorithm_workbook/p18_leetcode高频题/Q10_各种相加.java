package com.lane.interview.algorithm_workbook.p18_leetcode高频题;

import com.lane.interview.algorithm_workbook.p02_list.ListNode;

import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  18:07 2024/3/3
 */
public class Q10_各种相加 {
    // 数组相加
    public class Solution {
        public int[] addArrays(int[] nums1, int[] nums2) {
            int n = Math.max(nums1.length, nums2.length);
            int[] result = new int[n];
            int carry = 0;
            for (int i = 0; i < n; i++) {
                int sum = (i < nums1.length ? nums1[i] : 0) + (i < nums2.length ? nums2[i] : 0) + carry;
                result[i] = sum % 10;
                carry = sum / 10;
            }
            if (carry > 0) {
                int[] newResult = new int[n + 1];
                newResult[0] = carry;
                for (int i = 1; i < n + 1; i++) {
                    newResult[i] = result[i - 1];
                }
                return newResult;
            }
            return result;
        }
    }

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
    // 链表相减
    public ListNode subtract(ListNode num1, ListNode num2) {
        int len1 = getLength(num1);
        int len2 = getLength(num2);

        // 补0，使得两个链表等长
        if (len1 < len2) {
            num1 = addZeros(num1, len2 - len1);
        } else if (len1 > len2) {
            num2 = addZeros(num2, len1 - len2);
        }

        ListNode result = new ListNode(0);
        ListNode current = result;

        // 相减操作
        while (num1 != null && num2 != null) {
            int diff = num1.val - num2.val;

            if (diff >= 0) {
                current.next = new ListNode(diff);
            } else {
                num1.val += 10;
                current.next = new ListNode(num1.val - num2.val);
            }

            current = current.next;
            num1 = num1.next;
            num2 = num2.next;
        }
        // 去掉前面多余的0
        result = result.next;
        // 返回结果链表
        return result;
    }

    private int getLength(ListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    private ListNode addZeros(ListNode node, int zeros) {
        while (zeros > 0) {
            ListNode zeroNode = new ListNode(0);
            zeroNode.next = node;
            node = zeroNode;
            zeros--;
        }
        return node;
    }
}
