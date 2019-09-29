package com.pig.struct.hashtable;

public class Main {

    private final static int B = 26;
    private final static int M = 12345;

    public int strHashCode(String str){
        int hashcode = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            hashcode = (hashcode * B + str.charAt(i));
        }
        return hashcode;
    }

    public static void main(String[] args) {

    }
}
