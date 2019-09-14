package com.pig.struct.stack_and_queue;

import com.pig.struct.array.Array;

public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;
    public ArrayStack(){
        array = new Array<>();
    }

    public ArrayStack(int capacity){
        array = new Array<>(capacity);
    }


    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }


    // addLast 时间复杂度为O(1)，addFirst需要向右移位，时间复杂度为O(n),可能会触发resize操作，但是均摊时间复杂度依然是O(1);
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    // 同上，removeLast时间复杂度为O(1)
    @Override
    public E pop() {
        return array.removeLast(); //
    }

    public int getCapacity(){
        return array.getCapacity();
    }


    @Override
    public E peek(){
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < array.getSize() ; i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}
