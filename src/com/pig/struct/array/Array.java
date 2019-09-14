package com.pig.struct.array;


/***
 * 左移: a[i-1] = a[i];  a[i] = a[i++]
 * 右移：a[i] = a[i-1];  a[i++] = a[i];
 *
 *
 */
public class Array<E> {

    private E[] data;

    /**
     * 当前数据个数 && 并且指向第一个没有元素的位置；
     */
    private int size;

    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalStateException("参数不合法");
        }
        return data[index];
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size - 1) {
            throw new IllegalStateException("参数不合法");
        }
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    /***
     * 禁止断层，必须连续加
     * 添加的时候，需要data[i] = data[i-1]  从最高位开始，逐个移动,往右边移动
     * 不能小于0或者断开形式的加入，必须连续加入，所以 index > size 既断开了
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalStateException("参数不合法");
        }

        if (size == data.length) {
            resize(data.length * 2);
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }

        data[index] = e;
        size++;
    }

    /**
     * 向左移动,不需要处理最后一位data[size - 1];
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalStateException("参数不合法");
        }
        E delete = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        data[size] = null;
//
//        if(size == data.length / 2){
//            resize(data.length / 2);
//        }


        //如果data.length == 0 或者 data.length == 1,不处理扩容，因为不能初始化一个长度为0的数组
        if(size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }

        return delete;
    }

    private void resize(int newCapacity) {
        E[] newArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size;i++){
            newArray[i] = data[i];
        }
        data = newArray;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }

    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

}
