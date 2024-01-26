package com.lane.interview.algorithm_workbook.p02_list;

/**
 * @ Author:  duenpu
 * @ Date  :  00:43 2023/8/14
 */
public class ListNode {
    public int value;
    public int val;
    public ListNode next;

    public ListNode(int data) {
        this.value = data;
    }

    public ListNode(int data, ListNode next) {
        this.value = data;
        this.next = next;
    }
}
