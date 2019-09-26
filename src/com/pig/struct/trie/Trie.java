package com.pig.struct.trie;

import java.util.TreeMap;

import javax.swing.plaf.TextUI;

public class Trie {
    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的单词数量
    public int getSize() {
        return size;
    }


    // 向Trie中添加一个新的单词word

    /**
     * @param word
     */
    public void add(String word) {
        if (word == null || word.equals("")) return;
        Node current = root;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            Character ch = word.charAt(i);
            if (!current.next.containsKey(ch)) {
                current.next.put(ch, new Node());
            }
            current = current.next.get(ch);
        }

        if (!current.isWord) {
            current.isWord = true;
            size++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word) {
        if (word == null || word.equals("")) return false;
        Node current = root;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            Character ch = word.charAt(i);
            if (!current.next.containsKey(ch)) {
                return false;
            }
            current = current.next.get(ch);
        }
        // 如果是没到叶子节点，判断中间节点是否是单词的结尾；
        return current.isWord;
    }


    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){

        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }



}
