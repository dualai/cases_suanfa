package com.pig.struct;
public class Main{
    public static void main(String[] args) {

        int year = 100,month = 100,day = 100,M = 100,B = 100;
        int hashIndex = (( (year % M) * B + month ) % M * B + day ) % M;
    }
}