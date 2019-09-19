package com.pig.struct.priority_heap.leetcode;

/**
 * 1,000,000 N个元素中选出出现频率前100 M的数字
 * 排序：O(nlogn)
 * 基于堆的优先队列：O(nlogm)
 */

import com.pig.struct.priority_heap.PriorityQueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/***
 * 00-建立一个最小堆（优先队列），最小堆的大小控制在m之内，（最小堆也可以用最大堆来实现，重新定义Comparable接口）
 * 01-for 每个数：
 * 02-----if 这个数比最小堆的堆顶元素大：
 * 03---------弹出最小堆的最小元素
 * 04---------把这个数插入到最小堆
 * 05-最小堆中的m个元素就是所要求的元素
 * 06-其中最小堆的作用就是保持里面始终有m个最大元素，且m个元素中最小的元素在堆顶。
 *
 * 相当于，只要比一批数据的最小元素要大，那就用该数替换最小的元素，最后可以保证这批数据是所有数据中最大的一批元素；
 */
public class Selection {



    public static void main(String[] args) {
        int[] nums = {1, 2,2,2,2,2,2,2,2, 3,33,1,33,33,33,33,33,44,44,44,55,555,56};
        int k = 3;
        printList((new Selection()).solution(nums, k));
    }


    /**
     * 小和大的对比是相对的
     * 重要：用Comparable 接口 来定义，最小的元素反而处于最大堆的最高层
     */
    private final class Freq implements Comparable<Freq> {
        public int e, freq;
        public Freq(int e, int freq){
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq other) {
            if(this.freq < other.freq)
                return 1;
            else if(this.freq > other.freq)
                return -1;
            else
                return 0;
        }
    }
    /**
     * 统计频次
     * @param numbers
     */
    private LinkedList<Integer> solution(int[] numbers,int m){
        /**
         * 先统计频次
         */
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int num : numbers){
            if(!map.containsKey(num)){
                map.put(num,1);
            }else{
                map.put(num,map.get(num)+1);
            }
        }

        /**
         * 生成M的最大堆，其中M个数据中的最小元素置于顶部
         */
        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        for(int key: map.keySet()){
            if(priorityQueue.getSize() < m){ // 比如m = 100，在99的时候还可以再加一个，但是在m=100的时候，就不能再加了
                Freq freq = new Freq(key,map.get(key));
                priorityQueue.enqueue(freq);
            }else {
                int freq = map.get(key);
                Freq top = priorityQueue.getFront();
                if(freq > top.freq){
                    priorityQueue.dequeue();
                    priorityQueue.enqueue(new Freq(key,freq));
                }
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(!priorityQueue.isEmpty())
            res.add(priorityQueue.dequeue().e);
        return res;
    }

    private static void printList(List<Integer> nums){
        for(Integer num: nums)
            System.out.print(num + " ");
        System.out.println();
    }



}
