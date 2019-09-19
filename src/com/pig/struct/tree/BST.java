package com.pig.struct.tree;

import com.pig.lib.LogUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
        root = add(root, e);
    }

    /**
     * 递归算法
     *
     * @param node，向以node为根的二分搜索树总插入元素E，递归算法；
     * @param e
     */
    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e); //添加后重新挂接到node.left
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        } else {
            //相等啥都不做
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {
            return true;
        }
    }

    public void preOrder() {
        preOrder(this.root);
    }


    // 二分搜索树的非递归前序遍历

    /***
     * 模拟系统栈，记录执行位置，
     * https://blog.csdn.net/meng_lemon/article/details/82192424
     * 先pop A，访问A，然后push A的left， 再push A的right
     * 为什么算法里头，先push右分支，再push左分支，因为先保存后使用的原则，右节点后访问；
     * 左分支先pop，然后循环回到 pop； 这样就再以左子树节点为根节点往下遍历重复
     * 并且，粗犷的理解下，pop了一个元素以后，一定要立刻去push 这个元素的左右分支，
     * 否则进入下一轮循环后这个被pop的变量就没了，所以找不到后续的了
     */
    public void preOrderNR() {
        if (root == null)
            return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            LogUtil.d(node.e);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    // 二分搜索树的层序遍历
    public void levelOrder() {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            LogUtil.d(node.e);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        LogUtil.d("" + node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        LogUtil.d(node.e);
        inOrder(node.right);
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    // 后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        LogUtil.d(node.e);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        res.append("depth:").append(depth);
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int num : nums)
            bst.add(num);

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
//        bst.inOrder();
//        bst.postOrder();
//        bst.preOrderNR();
        bst.levelOrder();
        LogUtil.ln();

        LogUtil.d(bst.toString());
    }


    /**
     * 寻找二分搜索树的最小元素
     */
    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");
        Node minNode = minimum(root);
        return minNode.e;
    }

    /**
     * 一直沿着左节点找
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        //node.left 上面判断过了，不可能为空
        return minimum(node.left);
    }


    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty");

        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值所在的节点
    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    //https://blog.csdn.net/pp4514951/article/details/82892094
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /***
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) { // 递归删除左边的节点,size-- 在找到后处理
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {  // 递归删除右边的节点
            node.right = remove(node.right, e);
            return node;
        }

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        //左右都不为空
        Node successor = maximum(node.right);
        successor.right = removeMin(node.right); // 因为这里已经有size--了，所以下面node.left = node.right = null 不执行
        successor.left = node.left;
        node.left = node.right = null;
        return successor;

    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }


    // 从二分搜索树中删除最大值所在节点
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }


//    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
//    // 返回删除节点后新的二分搜索树的根
//    private Node remove(Node node, E e){
//
//        if( node == null )
//            return null;
//
//        if( e.compareTo(node.e) < 0 ){
//            node.left = remove(node.left , e);
//            return node;
//        }
//        else if(e.compareTo(node.e) > 0 ){
//            node.right = remove(node.right, e);
//            return node;
//        }
//        else{   // e.compareTo(node.e) == 0
//
//            // 待删除节点左子树为空的情况
//            if(node.left == null){
//                Node rightNode = node.right;
//                node.right = null;
//                size --;
//                return rightNode;
//            }
//
//            // 待删除节点右子树为空的情况
//            if(node.right == null){
//                Node leftNode = node.left;
//                node.left = null;
//                size --;
//                return leftNode;
//            }
//
//            // 待删除节点左右子树均不为空的情况
//
//            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
//            // 用这个节点顶替待删除节点的位置
//            Node successor = minimum(node.right);
//            successor.right = removeMin(node.right);
//            successor.left = node.left;
//
//            node.left = node.right = null;
//
//            return successor;
//        }
//    }


}
