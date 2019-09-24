package com.pig.struct.segment_tree;

/**
 * 如果是求区间[i,j]的和，
 *
 */
public class SumRange {
    /**
     * sum 的数组长度是nums的数组长度+1
     */
    private int[] sum;
    // sum[i]存储前i个元素和, sum[0] = 0
    // 即sum[i]存储nums[0...i-1]的和
    // sum(i, j) = sum[j + 1] - sum[i]


    /**
     * @param nums 原始数组
     */
    public SumRange(int[] nums){
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for(int i = 1;i<sum.length;i++){
            sum[i] = sum[i-1] + nums[i-1];
        }
    }

    /**
     * sum[i] 存储 nums[0]~nums[i-1]
     * sum[j] 存储 nums[0]~nums[j-1]
     * 所以 sum(i, j) = sum[j + 1] - sum[i]
     */
    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

}
