package com.pig.struct.set_map.leetcode1;


import java.util.TreeSet;

public class Morse {
    /**
     * 摩斯密码，单词统计；
     * 自己的思路：
     */
    public class Solution0{
        public int uniqueMorseRepresentations(String[] words) {
            String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
            TreeSet<String> set = new TreeSet<>();
            for(String word : words){
                StringBuilder res = new StringBuilder();
                for(int i = 0;i<word.length();i++){
                    int codeIndex = word.charAt(i) - 'a';
                    res.append(codes[codeIndex]);
                }
                set.add(res.toString());
            }
            return set.size();
        }
    }


}
