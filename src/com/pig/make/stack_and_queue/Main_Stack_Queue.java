package com.pig.make.stack_and_queue;

import com.pig.lib.LogUtil;

public class Main_Stack_Queue {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

        ///////////
        LogUtil.d("pipei:"+solution1("{[{()}]}"));
        LogUtil.d("pipei2:"+solution1("{}[]()"));
    }


    /**
     * 括号匹配，
     * 思想：成对，
     */
    private static boolean solution1(String str){
        if(str == null || str.equals(""))return false;
        Stack<Character> stack = new ArrayStack<>();
        for(int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }else{
                if(stack.isEmpty())return false;
                char topCh = stack.pop();
                if(topCh == '(' && ch != ')'){
                    return false;
                }
                if(topCh == '[' && ch != ']'){
                    return false;
                }
                if(topCh == '{' && ch != '}'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
