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

    // https://leetcode.cn/problems/add-two-numbers

    /*
     * 迭代版本
     * */
    public static Node add_two_numbers(Node l1, Node l2) {
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

    /*
     * 递归版本
     *
     * 因为两个数字相加会产生进位，所以使用i来保存进位。
     * 则当前位的值为(l1.val + l2.val + i) % 10
     * 则进位值为(l1.val + l2.val + i) / 10
     * 建立新node，然后将进位传入下一层。
     *
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

    // 迭代实现
    public ListNode addTwoNumbersPro(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            sum += stack1.isEmpty()? 0: stack1.pop();
            sum += stack2.isEmpty()? 0: stack2.pop();
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
            carry = sum / 10;
        }
        return head;
    }

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
