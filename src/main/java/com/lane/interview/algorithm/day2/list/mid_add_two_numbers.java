package com.lane.interview.algorithm.day2.list;

/**
 * @author duenpu
 * @date 2023/8/17 17:39
 */

import com.lane.interview.algorithm.day2.Node;

/**
 * https://leetcode.cn/problems/add-two-numbers
 */
public class mid_add_two_numbers {
    public static Node add_two_numbers (Node l1, Node l2) {
        Node pre = new Node(0);
        Node cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.value;
            int y = l2 == null ? 0 : l2.value;
            int sum = x + y + carry;
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new Node(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            if (carry == 1) {
                cur.next = new Node(carry);
            }
        }
        return pre.next;
    }

    // 写一个main方法检测add_two_numbers方法是否正确
    public static void main(String[] args) {
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(4);

        Node result = add_two_numbers(l1, l2);
        while (result != null) {
            System.out.println(result.value);
            result = result.next;
        }
    }
}
