package com.lane.interview.algorithm_workbook.p02_list;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author:  duenpu
 * @ Date  :  11:00 2024/2/25
 */
public class Q11_重排 {

    // https://leetcode.cn/problems/reorder-list/description/

    class Solution {
        //利用拆分两个链表，然后进行拼接。主要画图
        public void reorderList(ListNode head) {
            //当链表为空或只有一个节点或有两个节点的时候，不需要重排，直接返回即可。
            if(head == null || head.next ==null || head.next.next ==null){
                return;
            }
            //定义一个快慢指针来找到，中间节点
            ListNode fast = head;
            ListNode slow = head;
            //当快指针到达最后一个节点或者倒数第二个节点的时候，就停止循环，这时候慢指针的位置就是中间位置
            //这是因为要避免链表的元素有奇数或偶数个的情况。
            while(fast.next != null && fast.next.next != null){
                //快指针移动两步，
                fast= fast.next.next;
                //慢指针移动一步
                slow = slow.next;
            }
            //定义一个指向第二个链表的第一个节点
            ListNode newHead = slow.next;
            //断开两个链表的连接
            slow.next = null;
            //反转第二个链表
            newHead = reverse(newHead);
            //当第二个链表的头节点的指向空时，不进循环入
            while(newHead != null){
                //定义一个伪节点，存储第二个链表的第二节点
                ListNode temp = newHead.next;
                //将第二个链表的头节点，指向第一个链表链表的第二个节点。
                newHead.next = head.next;
                //将第一个链表的头节点，指向第二个链表的头节点。
                head.next = newHead;
                //将指向第一个链表的头节点的指针，移动到第一个链表的第二个节点
                head = newHead.next;
                //将指向第二个链表的头节点的指针，移动到第二个链表的第二个节点
                newHead = temp;
            }
        }
        //感觉我这种利用三个指针反转链表的方式，比较好理解，可以完美结合反转链表题的写法
        public ListNode reverse(ListNode head){
            //当链表为空的时候，直接返回
            if(head == null){
                return head;
            }
            //定义一个伪节点指向空值
            ListNode prev = null;
            //定义一个遍历链表的指针，指向第一个节点
            ListNode cur = head;
            //当遍历的节点不为空，就进入循环
            while(cur !=null){
                //定义一个指针，用于存储第二个节点
                ListNode next = cur.next;
                //断开第一个节点和第二个节点，然后将节点指向空
                //改变第一个链表的节点的指向
                cur.next = prev;
                //然后将指针移动到第二个节点，
                prev = cur;
                //将指针移动到第三个节点。
                cur = next;
            }
            //返回伪节点
            return prev;
        }
    }
}
