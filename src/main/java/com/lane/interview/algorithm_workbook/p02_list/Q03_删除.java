package com.lane.interview.algorithm_workbook.p02_list;

/**
 * @author duenpu
 * @date 2023/8/17 17:39
 */

import com.lane.interview.algorithm.day2.DoubleNode;
import com.lane.interview.algorithm.day2.Node;

public class Q03_删除 {
}
class a_删除指定元素 {
    // https://leetcode.cn/problems/remove-linked-list-elements/description/
    public ListNode removeElements1(ListNode head, int valueue) {
        while (head != null) {
            if (head.value != valueue) {
                break;
            }
            head = head.next;
        }
        ListNode cur = head;
        ListNode pre = head;
        while (cur != null) {
            if (cur.value == valueue) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public ListNode removeElements1Pro(ListNode head, int valueue) {
        // base case
        if (head == null) {
            return head;
        }
        head.next = removeElements1Pro(head.next, valueue);
        if (head.value == valueue) {
            return head.next;
        } else {
            return head;
        }
    }
}
class b_删除指定元素_双链表 {
    public static DoubleNode removevalueue(DoubleNode head, int num) {
        // head来到第一个不需要删的位置
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
            head.last = null;
        }
        // 1 ) head == null
        // 2 ) head != null
        DoubleNode pre = head;
        DoubleNode cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
                if (cur.next != null) {
                    cur.next.last = pre;
                }
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public DoubleNode removevalueuePro(DoubleNode head, int valueue) {
        // base case
        if (head == null) {
            return head;
        }
        head.next = removevalueuePro(head.next, valueue);
        if (head.value == valueue) {
            if (head.last != null) {
                head.last.next = head.next;
            }
            if (head.next != null) {
                head.next.last = head.last;
            }
            return head.next;
        } else {
            return head;
        }
    }
}

class c_删除倒数第N个元素{
    // https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode recordNode = new ListNode(0);
        recordNode.next = head;
        ListNode fast = head;
        ListNode slow = recordNode;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return recordNode.next;
    }
}
class d_去重{
    // https://leetcode.cn/problems/remove-duplicates-from-sorted-list/description/

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.value == cur.next.value) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}

class e_去重_重复的元素都不要{

    // https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode record = new ListNode(0);
        record.next = head;
        ListNode slow = record;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            if (slow.next.val == fast.next.val) {
                while (fast != null && fast.next != null && slow.next.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
                fast = fast.next;
            } else {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return record.next;
    }
}

class f_删除节点神器{
    // https://leetcode.cn/problems/delete-node-in-a-linked-list/description/

    /*
     * 对于本题，由于我们只知道待删除节点本身，同时该链表为单链表（无法访问前一节点），因此我们只能先将后一节点的值复制到当前节点，然后将后一节点当作「待删除节点」来进行常规删除。
     * */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
