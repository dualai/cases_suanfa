package com.pig.make.linkedlist;

import com.pig.lib.LogUtil;

public class LinkedList<E> {

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

    private Node dummyHead; //为了方便处理第一个元素和其他便利性；
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
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
        add(e, 0);
    }

    public void addLast(E e) {
        add(e, size);
    }

    /***
     * 为了找到第index个的前面那个；
     * 插入到最后位置，就是size，第一个元素为null的位置；
     * @param e
     * @param index
     */
    private void add(E e, int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        /**
         * 为了找到第index - 1个；就是为了找到第index个的前一个；
         */
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }


    /***
     * 从dummyHead开始的话，为了遍历到第index个，需要for (int i = 0; i < index + 1; i++)
     * 从dummyHead.next 开始，为了遍历到第index个，for (int i = 0; i < index; i++)
     */

    /**
     * get 最大的索引是size - 1
     *
     * @param index ：注意这里的index是第index，不是size；size的长度，最大的index是size - 1；而第index就是index；
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed. Illegal index.");
        Node cur = dummyHead.next; //直接第一个就索引到head，那么为了取得第index个，for (int i = 0; i < index; i++)
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getlast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        // 不知道循环多少次；while
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
//        Node cur = dummyHead.next;
//        while (cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        //找到前一个
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;
        return retNode.e;
    }

    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素e

    /**
     * 用while循环找前一个，就需要这么来判断，while(prev.next != null);
     * @param e
     */
    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        /**
         * 如果这里prev.next == null 就表示这个列表已经遍历完了都没有找到；
         */
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }

    /**
     * 找到e元素，并且删掉，未知index，需要用while循环；首先找到前一个prev，
     * @param e
     */
    public void removeElement_bak(E e){
        Node prev = dummyHead;
        while (prev.next != null){
            if(prev.next.e.equals(e)){
                break;
            }
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }


    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(3);
        linkedList.addLast(5);
        linkedList.addLast(6);
        linkedList.addLast(10);
        linkedList.addLast(11);
        linkedList.add(100, 3);
//      linkedList.remove(3);
        linkedList.removeElement(100);
        linkedList.removeLast();
        LogUtil.d(linkedList.toString());
    }
}
