package com.pig.struct.set_map.leetcode1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 数组交集
 */
public class IntersectionArray {
    public static void main(String[] args) {

    }


    /**
     * 示例 1:
     *
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2]
     * 示例 2:
     *
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [9,4]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection349Set(int[] nums1, int[] nums2) {

        TreeSet<Integer> treeSet = new TreeSet<>();
        for(int num : nums1){
            treeSet.add(num);
        }

        List<Integer> list = new ArrayList<>();
        for(int num: nums2){
            if(treeSet.contains(num)){
                list.add(num);
                treeSet.remove(num);
            }
        }
        int[] res = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i ++)
            res[i] = list.get(i);
        return res;
    }


    /**
     * 数组交集，包含重复
     * Map 来解决
     * @param nums1
     * @param nums2
     *
     * key number   value 出现数量
     * @return
     */
    public int[] intersection350(int[] nums1, int[] nums2) {
        TreeMap<Integer,Integer> map = new TreeMap<>();

        for(int num: nums1){
            if(!map.containsKey(num)){
                map.put(num,1);
            }else{
                map.put(num,map.get(num)+1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for(int num: nums2){
            if(map.containsKey(num)){
                list.add(num);
                map.put(num,map.get(num) - 1);
                if(map.get(num) == 0){
                    map.remove(num);
                }
            }
        }

        int[] res = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i ++)
            res[i] = list.get(i);
        return res;
    }
}
