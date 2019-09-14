package com.pig.struct.linkedlist;

import com.pig.lib.LogUtil;

public class LinkedList_bak<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedList_bak() {
        head = null;
        size = 0;
    }

    // 获取链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
//        Node node = new Node(e);
//        node.next = head;
//        head = node;
        head = new Node(e, head); //head = null 也适用
        size++;
    }

    public void addLast(E e) {
        add(e, size);
    }

    private void add(E e, int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        if (index == 0)
            addFirst(e);
        else {

            /**
             * 为了找到第index个的前面那个，也就是index - 1个；
             *
             * 先找到第index - 1个节点，然后执行插入操作
             */
            /***
             * 因为已经直接拿到head作为prev，
             * 第一次 prev = prev.next（也就是i = 0），相当于找到了index = 1 的元素；
             * 第二次 prev = prev.next（也就是i = 1），相当于找到了index = 2 的元素；
             * 所以，为了找到index - 1的元素，需要执行的次数是 index - 1 次，也就是 for(int i= 0; i< index - 1 ;i++)
             *
             * 执行n次，那么就是 for(int i = 0;i<n - 1;i++)
             * 从i = index 位置开始执行n次，那就是 for(int i = index ; i < n - 1 + index ;i++)
             */

            /**
             * 如果是个数组的索引，为了找到下标index - 1，那么需要 arr[index - 1],也就是 for(int i= 0; i< index;i++)；
             * 但是这里每次执行一次，prev = prev.next，相当于找到了后面一个元素，所以，for(int i= 0; i< index - 1;i++)；
             */
            /**
             * 所以，综合来说，链表的索引，因为有个next指针，所以，找到第index - 1个节点，然后 node = node.next 就相当于找到了第index个；
             */
            // 记住这里的index不是size；
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

//            int i = 0;
//            Node prev = head;
//            while (i < index - 1){
//                prev = prev.next;
//                i++;
//            }

            //此时拿到的prev就是 下标index - 1；
//            Node insertNode = new Node(e);
//            insertNode.next = prev.next;
//            prev.next = insertNode;
            prev.next = new Node(e, prev.next);
            size++;
        }
    }



    public void show() {
        String msg = "";
        if(getSize() == 0){
            LogUtil.d("链表为空...");
        }else{
            Node current = head;
            for(int i = 0;i < size - 1; i++){
                current = current.next;
            }
            LogUtil.d("最后一个元素 "+current.toString());

            current = head;
            int index = 0;
            while (current != null){
                LogUtil.d("第"+index+"个，"+current.toString());
//                current = current.next;
                Node tmp = current.next;
                if(tmp == null){
                    LogUtil.d("最后一个元素 "+current);
                }
                current = tmp;
                index++;
            }

        }
    }


    public static void main(String[] args) {
        LinkedList_bak<Integer> linkedList = new LinkedList_bak<>();
        linkedList.addLast(3);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.addLast(10);
        linkedList.addLast(11);
        linkedList.show();
    }
}
