package com.pig.struct.array;

import com.pig.lib.LogUtil;

/**
 * 最大的优势，快速查看
 */
public class Main_array {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        LogUtil.d(arr.toString());
        arr.add(1,99);
        LogUtil.d(arr.toString());
        arr.addFirst(-1);
        LogUtil.d(arr.toString());
        //[-1, 0, 99, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        arr.remove(1);
        LogUtil.d(arr.toString());

        arr.removeFirst();
        LogUtil.d(arr.toString());
        arr.removeLast();
        LogUtil.d(arr.toString());
        arr.removeElement(99);
        LogUtil.d(arr.toString());


    }
}
