package com.pig.make.recursion2;

import com.pig.make.recursion.ListNode;


/***
 * 链表的天然递归性，
 * 一个链表可以看成是 头结点 + 去除头结点后的一个链表；
 *
 */
public class Solution {

    public ListNode removeElements(ListNode head, int val) {

        if(head == null)
            return null;

        ListNode res = removeElements(head.next, val);
        if(head.val == val)
            return res;
        else{
            head.next = res;
            return head;
        }
//        head.next = removeElements(head.next, val);
//        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head, 6);
        System.out.println(res);
    }
}
