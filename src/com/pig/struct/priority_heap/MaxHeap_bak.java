package com.pig.struct.priority_heap;

import com.pig.struct.array.Array;

import java.util.Random;

public class MaxHeap_bak<E extends Comparable<E>> {
    /**
     * 借助线性结构，动态数组来形成二叉堆
     */
    private Array<E> data;

    public MaxHeap_bak(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap_bak(){
        data =new Array<>();
    }

    public MaxHeap_bak(E[] arr){
        data = new Array<>(arr);
        for(int i = parent(arr.length - 1) ; i >= 0 ; i --)
            siftDown(i);
    }

    // 返回堆中的元素个数
    public int size(){
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉堆的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if(index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    // 返回完全二叉堆的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    // 返回完全二叉堆的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 先添加到最后一个，然后逐步向父节点上移;  最终可以形成一颗完美二叉树
     * @param index 数组索引
     */
    private void siftUp(int index) {
        while (index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0){ //如果子元素大于他的父元素，那么交换位置
            data.swap(index,parent(index));
            index = parent(index);
        }
    }


//    // 向堆中添加元素
//    public void add(E e){
//        data.addLast(e);
//        siftUp(data.getSize() - 1);
//    }
//
//    private void siftUp(int k){
//
//        while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0 ){
//            data.swap(k, parent(k));
//            k = parent(k);
//        }
//    }


    // 看堆中的最大元素
    public E findMax(){
        if(data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    /***
     * 从二叉堆中取出一个元素，一般就是取出第一个，然后重新维护该二叉堆,先交换第一个和最后一个，
     * 然后从第一个开始，逐步下沉
     * @return
     */
    public E extractMax(){
        E rel = findMax();
        data.swap(0,data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return rel;
    }

    /***
     * 当前节点的索引，和下面的左右节点对比，然后交换；
     * 因为是完美二叉树，所以，如果计算出左边节点的索引已经大于size-1，那么说明没有子元素了，因为右节点的索引值
     * 肯定大于左边节点；
     * @param k
     */
//    private void siftDown(int k) {
//        int leftChildIndex = leftChild(k);
//        while(leftChildIndex < data.getSize()){
////            int rightChildIndex = rightChild(k);
//            //或者
//            int rightChildIndex = leftChildIndex + 1;
//            int largerIndex = leftChildIndex;
//            /**
//             * 如果存在右节点，并且右节点元素的大小大于左元素
//             */
//            if(rightChildIndex < data.getSize() && data.get(rightChildIndex).compareTo(data.get(leftChildIndex)) > 0){
//                largerIndex = rightChildIndex;
//            }
//
//            if(data.get(largerIndex).compareTo(data.get(k)) >= 0){
//                continue;
//            }
//
//            data.swap(largerIndex,k);
//            k = largerIndex;
//        }
//    }


    private void siftDown(int k){

        while(leftChild(k) < data.getSize()){
            int j = leftChild(k); // 在此轮循环中,data[k]和data[j]交换位置
            if( j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0 )
                j ++;
            // data[j] 是 leftChild 和 rightChild 中的最大值

            if(data.get(k).compareTo(data.get(j)) >= 0 )
                break;

            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e, 如果分开处理，时间复杂度是2*logn
    // 直接data.set, 时间复杂度可以是logn
    public E replace(E e){

        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }



//    public static void main(String[] args) {
//
//        int n = 1000000;
//
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
//        Random random = new Random();
//        for(int i = 0 ; i < n ; i ++)
//            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
//
//        int[] arr = new int[n];
//        for(int i = 0 ; i < n ; i ++)
//            arr[i] = maxHeap.extractMax();
//
//        for(int i = 1 ; i < n ; i ++)
//            if(arr[i-1] < arr[i])
//                throw new IllegalArgumentException("Error");
//
//        System.out.println("Test MaxHeap completed.");
//    }


    private static double testHeap(Integer[] testData, boolean isHeapify){

        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else{
            maxHeap = new MaxHeap<>();
            for(int num: testData)
                maxHeap.add(num);
        }

        int[] arr = new int[testData.length];
        for(int i = 0 ; i < testData.length ; i ++)
            arr[i] = maxHeap.extractMax();

        for(int i = 1 ; i < testData.length ; i ++)
            if(arr[i-1] < arr[i])
                throw new IllegalArgumentException("Error");
        System.out.println("Test MaxHeap completed.");

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int n = 1000000;

        Random random = new Random();
        Integer[] testData = new Integer[n];
        for(int i = 0 ; i < n ; i ++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1 + " s");

        double time2 = testHeap(testData, true);
        System.out.println("With heapify: " + time2 + " s");
    }

}
