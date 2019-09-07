package com.pig.make.recursion;

/**
 * 串节点
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }


    public ListNode(int[] arr){
        if(arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");

        this.val = arr[0];
        ListNode cur = this;
        /**
         * 把数组中的元素一个个窜起来，形成链表；new出下一个，然后把cur的next指针指向下一个，并且，再把cur的指针也指向下一个；
         */
        /**
         * 窜的方式：  先记录当前的第0个为cur； 然后循环的时候，从1开始作为循环的起点，每次循环的时候，创建下一个nextNode，
         * 并且让cur.next = nextNode； 最后，把cur的指针移到nextNode，再接着下一次循环；
         */
        for(int i = 1;i < arr.length;i++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        ListNode cur = this;
        while (cur != null){
            res.append(cur.val).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}