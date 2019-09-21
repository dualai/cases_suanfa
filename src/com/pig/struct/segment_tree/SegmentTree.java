package com.pig.struct.segment_tree;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr ,Merger<E> merger){

        data = (E[])new Object[arr.length];
        for(int i = 0 ; i < arr.length ; i ++)
            data[i] = arr[i];

        tree = (E[])new Object[4 * arr.length];

        this.merger = merger;
        buildSegmentTree(0,0,data.length - 1);
    }

    /**
     * 在treeIndex位置创建表示data数组区间[left,right]的线段树
     *
     * 区间对半分，对半到最后，可以使left = right
     *
     * @param treeIndex: 节点在整个tree数组中的index
     * @param left: 区间的开始
     * @param right: 区间的结束
     */
    private void buildSegmentTree(int treeIndex,int left,int right) {
        if(left == right){
            tree[treeIndex] = data[left];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = left+ (right - left) / 2;
        buildSegmentTree(leftChildIndex,left,mid);
        buildSegmentTree(rightChildIndex,mid+1,right);

        tree[treeIndex] = this.merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal.");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return 2*index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return 2*index + 2;
    }


}

