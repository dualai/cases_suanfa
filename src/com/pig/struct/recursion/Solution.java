package com.pig.struct.recursion;

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 如果不做dummyHead，那么传入的head既是第0个节点，这种情况下，
        // 防止如果前面几个节点连续含有val，先要删除前面几个节点，用while
        while (head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        //说明全部包含val
        if(head == null){
            return null;
        }

        ListNode prev = head; //此时head.value 一定不是需要删除的
        while (prev.next != null){
            if(prev.next.val == val){ //这个分支不移动prev
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else{
                prev = prev.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {

        int[] nums = {1,1, 2, 6, 3, 4, 5,1, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 1);
        System.out.println(res);
    }
}
