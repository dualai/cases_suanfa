package com.pig.struct.recursion2;

import com.pig.struct.recursion.ListNode;

public class Solution2 {
    /***
     *
     * @param head
     * @param val
     * @return
     */
    protected ListNode removeElements(ListNode head,int val,int depth){

        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);

        if(head == null){
            System.out.print(depthString);
            System.out.println("Return: null");
            return null;
        }
        /***
         * 为了对整条链表删除带val的节点，相当于把链表分成两部分，head部分和剩下的子链表部分，
         * 处理子链表完成后（递归处理），如果head部分也含有val，那么返回剩下的部分，如果head部分没有含有val，那么返回head——>剩余的
         */

        //处理完的子链表结果
        System.out.print(depthString);
        System.out.println("before remove " + val + ": " + head.next);
        ListNode rel = removeElements(head.next,val,depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + rel);

        ListNode result;
        if(head.val == val){
            result = rel;
        }else{
            head.next = rel;
            result = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + result);
        return result;
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        res.append("depth ").append(depth);
        for(int i = 0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {

        int[] nums = {6, 2, 6, 3, 4, 5, 6,100,6,33,6};
//        int[] nums = {6};
        ListNode head = new ListNode(nums);

        ListNode res = (new Solution2()).removeElements(head, 6,0);
        System.out.println(res);
    }
}
