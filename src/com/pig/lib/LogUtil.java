package com.pig.lib;

public class LogUtil {
    private final static String TAG = "suanfa";
    public static void d(Object msg){
        System.out.println(TAG+"---"+ msg.toString());
    }

    public static void d(String msg){
        System.out.println(TAG+"---"+ msg);
    }

    public static void d(int msg){
        System.out.println(TAG+"---"+ msg);
    }

    public static void ln(){
        System.out.println("\n");
    }

}
