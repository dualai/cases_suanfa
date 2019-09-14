package com.pig.struct.stack_and_queue;

/**
 * LIFO
 * @param <E>
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    //取出栈顶，但是不移除；
    E peek();
}
