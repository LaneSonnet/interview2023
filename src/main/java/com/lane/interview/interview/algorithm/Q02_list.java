package com.lane.interview.interview.algorithm;

import com.lane.interview.algorithm_workbook.p02_list.ListNode;
import com.lane.interview.algorithm_workbook.p08_sort.SwapUtil;

/**
 * @ Author:  duenpu
 * @ Date  :  18:56 2024/2/27
 */
public class Q02_list {
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList1(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode reverseList2(ListNode head, int n) {
        ListNode tmp = null;
        if (n == 1) {
            tmp = head.next;
            return head;
        }
        ListNode newHead = reverseList2(head.next, n - 1);
        head.next.next = head;
        head.next = tmp;
        return newHead;
    }

    public ListNode reverseList3(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseList2(head, n);
        }
        head.next = reverseList3(head.next, m - 1, n - 1);
        return head;
    }

    public ListNode reverseList4(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode a = head;
        ListNode b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = reverse(a, b);
        a.next = reverseList4(b, k);
        return newHead;
    }

    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return process(l1,l2,0);
    }

    private ListNode process(ListNode l1, ListNode l2, int c) {
        if (l1 == null && l2 == null && c == 0) {
            return null;
        }
        int sum = (l1.val == 0 ? 0 : l1.val) + (l2.val == 0 ? 0 : l2.val) + c;
        ListNode newNode = new ListNode(sum % 10);
        newNode.next = process((l1 != null ? l1.next : null), (l2 != null ? l2.next : null), sum / 10);
        return newNode;
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head !=null) {
            if (head.val != val) {
                break;
            }
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode record = new ListNode(0);
        record.next = head;
        ListNode fast = head;
        ListNode slow = record;
        for (int i = 0;i < n;i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return record.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode record = new ListNode(0);
        record.next = head;
        ListNode slow = record;
        ListNode fast = head;
        while (fast != null && fast.next !=null) {
            if (slow.next.val == fast.next.val) {
                while (fast != null && fast.next !=null && slow.next.val == fast.next.val) {
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

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }


    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head.next;
        ListNode fast = head.next;
        while (fast != null || fast.next!= null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return Boolean.TRUE;
        }
        // 找上中点
        ListNode mid = getMidNode(head);
        ListNode newHead = reverseList1(mid.next);
        mid.next = null;
        while (head != null && newHead != null) {
            if (head.val != newHead.val) {
                return Boolean.FALSE;
            }
            head = head.next;
            newHead = newHead.next;
        }
        return Boolean.TRUE;
    }

    private ListNode getMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode partition(ListNode head, int x) {
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


    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return Boolean.FALSE;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null || fast.next.next == null) {
                return Boolean.FALSE;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return Boolean.TRUE;
    }

    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        int diff = 0;
        // 先找长度差
        while (p1 != null) {
            diff++;
            p1 = p1.next;
        }
        while (p2 != null) {
            diff--;
            p2 = p2.next;
        }
        // 最后一个节点不一样，直接可以判断不相交
        if (p1 != p2) {
            return null;
        }
        // 判断哪个是长链表
        if (diff >=0) {
            p1 = headA;
            p2 = headB;
        } else {
            p1 = headB;
            p2 = headA;
        }
        // p1先走
        diff = Math.abs(diff);
        for (int i = 0;i < diff;i++) {
            p1 = p1.next;
        }
        // 再共同走
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return process1(lists,0, lists.length - 1);
    }

    private ListNode process1(ListNode[] lists, int l, int r) {
        if (l >= r) {
            return null;
        }
        int mid = l + ((r - l) >> 1);
        ListNode listNode1 = process1(lists, 0, mid);
        ListNode listNode2 = process1(lists, mid + 1, r);
        return merge(listNode1, listNode2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null){
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = merge(l1.next,l2);
            return l1;
        } else {
            l2.next = merge(l1,l2.next);
            return l2;
        }
    }









}
