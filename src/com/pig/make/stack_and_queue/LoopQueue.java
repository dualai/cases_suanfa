package com.pig.make.stack_and_queue;

/**
 *
 * @param <E>
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front,tail;
    private int size; //数组大小


    public LoopQueue(int capacity){
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length - 1;
    }

    /***
     * 出队，一直到front == tail，都指向了第一个没有元素的位置，那么循环队列为空；
     * @return
     */
    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    // 下一小节再做具体实现

    /**
     * 其中，front指在不为空的元素上，
     * 但是tail指在第一个为空的位置上；相当于[Front,tail) 这种；
     *
     * (tail + 1) % length ： tail前进一位，然后取余
     *
     * index = (index + 1) % data.length;  //循环队列中，前进一个，然后折返的方式；
     * @param e
     */
    @Override
    public void enqueue(E e){
        if((tail + 1) % data.length == front){ // 循环数组满了；扩容
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        /**
         * 因为是循环队列，维护tail的时候，一定要折返，折返的方式就是index % data.length
         *
         */
        tail = (tail + 1) % data.length;
        size++;
    }


    /***
     * % length，好好理解这个东西，把循环队列的数据重新整理到新的扩容2倍的数组中；
     *          从front下标开始，偏移i，但是因为如果front不是0的话，数组肯定到头又从0开始了，也就是被循环了，所以，
     *          需要 % data.length折返从0开始；
     * @param newCapacity
     */

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity + 1];
        /**
         * data的起始点是front，然后加上偏移量i,并且到顶要折返，折返的方式就是 下标 % data.length
         */
        for(int i = 0;i < size ; i++){
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    // 下一小节再做具体实现
    @Override
    public E dequeue(){
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E rel = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size-- ;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return rel;
    }

    // 下一小节再做具体实现
    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }


    /***
     * 判断是否是最后一个元素(i+1) % data.length == tail;
     * @return
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front = "+ front + " [");

        /**
         * 因为tail可能比front小，直接就跳出了
         */
//        while (i != tail)

        for(int i = front; i != tail; i = (i+1) % data.length){
            res.append(data[i]);
            if((i+1) % data.length != tail){ //如果当前指向不是最后一个元素，那么append ,
                res.append(",");
            }
        }
        res.append("] tail = "+tail);
        return res.toString();
    }

    /**
     * 每隔n个来一次，写法是 i % n == n - 1;
     * @param args
     */
    public static void main(String[] args) {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
