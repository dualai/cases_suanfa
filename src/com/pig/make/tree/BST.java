package com.pig.make.tree;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root,e);
    }

    /**
     * 递归算法
     * @param node，向以node为根的二分搜索树总插入元素E，递归算法；
     * @param e
     */
    private Node add(Node node,E e){

        if(node == null){
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0){
            node.left = add(node.left,e); //添加后重新挂接到node.left
        }else if(e.compareTo(node.e) > 0){
            node.right = add(node.right,e);
        }else{
            //相等啥都不做
        }
        return node;
    }

    public boolean contains(E e){
        return contains(root,e);
    }

    private boolean contains(Node node ,E e){

        if(node == null){
            return false;
        }

        if(e.compareTo(node.e) < 0){
            return contains(node.left,e);
        }else if(e.compareTo(node.e) > 0){
            return contains(node.right,e);
        }else{
            return true;
        }
    }
}
