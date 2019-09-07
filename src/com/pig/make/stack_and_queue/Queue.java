package com.pig.make.stack_and_queue;

/**
 * 队列：FIFO
 *
 * 数组队列：出队的时候，移动整个数组，时间复杂度O(n)，
 *
 * 循环队列：纪录队首的位置，出队的时候，时间复杂度O(1)，front ，tail，front == tail，队列为空；
 *          入队，维护tail；出队，维护front
 */
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
