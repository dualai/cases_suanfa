package com.pig.struct.segment_tree;

import com.pig.lib.LogUtil;

public class SegmentTree<E> {
    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int left, int right) {
        if (left == right) {
            tree[treeIndex] = data[left];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = left + (right - left) / 2;
        buildSegmentTree(leftChildIndex, left, mid); //left终点是mid
        buildSegmentTree(rightChildIndex, mid + 1, right); //right起始点是mid+1

        tree[treeIndex] = this.merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }


    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {

        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }


    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        tree = (E[]) new Object[4 * arr.length];

        this.merger = merger;
        buildSegmentTree(0, 0, data.length - 1);
    }
//
//    /**
//     * 在treeIndex位置创建表示data数组区间[left,right]的线段树
//     *
//     * 区间对半分，对半到最后，可以使left = right
//     *
//     * @param treeIndex: 节点在整个tree数组中的index
//     * @param left: 区间的开始
//     * @param right: 区间的结束
//     */

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }


    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) { //根据以下的搜索条件，随着递归，范围会逐渐缩小到l == queryL && r == queryR
            return tree[treeIndex];
        }

        /**
         * 三种可能
         * 要么区间在中间之前，要么区间在中间之后，要么区间就是跨越中间；
         */
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryR <= mid) { //数据区间完全集中在左子树，搜索左子树； 这里的左子树的终点和创建线段树时候要对应
            return query(leftTreeIndex, l, mid, queryL, queryR);
        } else if (queryL >= mid + 1) { //数据区间完全集中在右子树，搜索右子树；这里的右子树的起点和创建时候要对应
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }

        //数据区间跨越左子树和右子树，那么分批同时搜索左子树和右子树
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }


    // 将index位置的值，更新为e
    public void set(int index, E e) {

        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");

        data[index] = e;
        set(0, 0, data.length - 1, index, e,0);
    }

//    // 在以treeIndex为根的线段树中更新index的值为e
//    private void set(int treeIndex, int l, int r, int index, E e) {
//        if (l == r) {
//            tree[treeIndex] = e;
//            return;
//        }
//
//        int mid = l + (r - l) / 2;
//        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
//
//        int leftTreeIndex = leftChild(treeIndex);
//        int rightTreeIndex = rightChild(treeIndex);
//        if (index <= mid) {
//            set(leftTreeIndex, l, mid, index, e);
//        } else {
//            set(rightTreeIndex, mid + 1, r, index, e);
//        }
//        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
//    }


    private void set(int treeIndex, int l, int r, int index, E e,int depth) {
        if (l == r) {
            LogUtil.d(generateDepthStr(depth)+" done ");
            tree[treeIndex] = e;
            return;
        }

        LogUtil.d(generateDepthStr(depth)+" set ");
        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index <= mid) {
            set(leftTreeIndex, l, mid, index, e,depth+1);
        } else {
            set(rightTreeIndex, mid + 1, r, index, e,depth+1);
        }
        LogUtil.d(generateDepthStr(depth)+" merge");
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    private String generateDepthStr(int depth){
        StringBuilder sb = new StringBuilder();
        sb.append("depth:"+depth+" ");
        for(int i = 0;i<depth;i++){
            sb.append("--");
        }
        return sb.toString();
    }

    /**
     * 打印的肯定是merge好的结果，
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {

        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                (a, b) -> a + b);
        System.out.println(segTree);
        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));

        segTree.set(3,8);

        System.out.println(segTree.query(0, 5));
    }
}

