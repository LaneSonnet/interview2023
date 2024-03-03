package com.lane.interview.algorithm_workbook.p02_list;

import java.util.HashMap;

// 测试链接 : https://leetcode.com/problems/copy-list-with-random-pointer/

/**
 * 复制含有随机指针节点的链表
 */
public class Q09_复制 {

    public static class Node {
        int value;
        Node next;
        Node random;

        public Node(int val) {
            this.value = val;
            this.next = null;
            this.random = null;
        }
    }
    public static  Node copyRandomList1( Node head) {
        // key 老节点
        // value 新节点
        HashMap<Node,  Node> map = new HashMap< Node,  Node>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new  Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // cur 老
            // map.get(cur) 新
            // 新.next ->  cur.next克隆节点找到
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

}
