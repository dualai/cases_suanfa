package com.pig.recursion.rec_01;

import com.pig.lib.LogUtil;

import java.util.jar.Manifest;

/**
 * 递归计算型
 * 1、层层计算型
 * 2、并列计算
 */

public class Main {


    /**
     * 层层计算型
     * 计算年龄
     */
    public int age(int n) {
        if (n == 0) {
            return 10;
        }
        return age(n - 1) + 2;
    }


    public static final class LinkedNode{
        int number;
        LinkedNode next;

        public LinkedNode(int number) {
            this.number = number;
        }
    }

    /***
     * 单链表反转打印
     * 每个子问题都是，先打印下一个，再打印自己；一直递归到结尾;
     */
    public void printNodeReverse(LinkedNode node){
        if(node == null){
            return;
        }
        printNodeReverse(node.next);
        LogUtil.d("num:"+node.number);
    }


    /**
     * 并列计算
     * 斐波那契数列
     * @param
     */

    public int fbnq(int n){
        if(n == 0)return 0;
        if(n == 1)return 1;
        return fbnq(n-1) + fbnq(n - 2);
    }

    //一只青蛙一次可以跳上 1 级台阶，也可以跳上2 级。求该青蛙跳上一个n 级的台阶总共有多少种跳法。
    // 第一次跳一级，就等同于后面 taijie(n-1)的跳法总数； 第一次跳二级，就等同于后面 taijie(n-2)的跳法总数，
    // 因为一次要全部跳完才算一种跳法，所以，第一次跳一步和第一次跳两步会带来两个系列维度的跳法，需要把这两个维度的
    //跳法累加起来才是最终的总共的跳法;
    public int taijie(int n){
        if(n <= 0)throw new RuntimeException("stub");
        if(n == 1)return 1;
        if(n == 2)return 2;
        return taijie(n-1) + taijie(n - 2);
    }


    public static void main(String[] args) {
        LinkedNode node0 = new LinkedNode(19);
        LinkedNode node1 = new LinkedNode(17);
        LinkedNode node2 = new LinkedNode(14);
        LinkedNode node3 = new LinkedNode(11);
        LinkedNode node4 = new LinkedNode(9);

        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Main main = new Main();
        main.printNodeReverse(node0);

        LinkedNode linkedNode = main.reverseLinkedList(node0);
        main.printNodeReverse(linkedNode);
    }


    /***
     * 链表反转的递归解法
     * https://zhuanlan.zhihu.com/p/59389994
     */

    /***
     * 1—>2—>3->4
     * @param node
     * @return
     */
    private LinkedNode reverseLinkedList(LinkedNode node){
        if(node == null || node.next == null){
            return node;
        }
        //画个图，假设翻转完以后变成了4—>3->2  1—>2
        LinkedNode newLinkedNode = reverseLinkedList(node.next);
        LinkedNode node1 = node.next;
        node1.next = node;
        node.next = null;
        return newLinkedNode;
    }


}


