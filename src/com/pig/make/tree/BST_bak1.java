package com.pig.make.tree;

public class BST_bak1<E extends Comparable<E>> {

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

    public BST_bak1() {
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
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root,e);
        }
    }

    /**
     * 递归算法
     * @param node，向以node为根的二分搜索树总插入元素E，递归算法；
     * @param e
     */
    private void add(Node node,E e){
        //递归终止条件
        if(e.equals(node.e)){
            return;
        }else if(e.compareTo(node.e) < 0 && node.left == null){ //元素小于节点的e
            node.left = new Node(e);
            size++;
            return;
        }else if(e.compareTo(node.e) > 0 && node.right == null){ //元素大于节点的e
            node.right = new Node(e);
            size++;
            return;
        }

        //递归逻辑
        if(e.compareTo(node.e) < 0){ //小于node，并且node不为空
            add(node.left,e);
        }else{ //大于node，并且node不为空;
            add(node.right,e);
        }
    }


}
