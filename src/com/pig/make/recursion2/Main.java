package com.pig.make.recursion2;

import com.pig.lib.LogUtil;

public class Main {
    public static void main(String[] args) {
       int total = new Sum().sum(new int[]{1,2,3,4,5,6,7,8,9});
        LogUtil.d("sum,"+total);
    }
}


class Sum{
    protected int sum(int arr[]){
        return sum(arr,0);
    }

    /**
     * 求出从startIndex开始到数组长度;
     * @param arr
     * @param startIndex
     * @return
     */
    protected int sum(int arr[],int startIndex){
        if(startIndex == arr.length){
            return 0;
        }
        int total = arr[startIndex] + sum(arr,startIndex + 1);
        return total;
    }
}