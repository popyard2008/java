package com.company;


import java.util.HashSet;
import java.util.Set;

public class wordLadder_3_127 {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList){
        Set<String > beginSet = new HashSet<>(), endSet = new HashSet<>();

        int len = 1;
        int strLen = beginWord.length();
        HashSet<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            if(beginSet.size() > endSet.size()){
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
                System.out.println("SWITCHED");

            }

            Set<String> temp = new HashSet<>();
            for (String word : beginSet){
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i ++){
                    for (char c= 'a'; c <= 'z'; c++){
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if(endSet.contains(target)){
                            return len + 1;
                        }
//                            System.out.println("target :" + target);
                        if (!visited.contains(target) && wordList.contains(target)){
                            System.out.println("*********target added:" + target);

                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }
            for(String forPrint: beginSet){
                System.out.print(" " + forPrint);
                System.out.println(" ");

            }
            beginSet = temp;
            len++;
        }
        return 0;

    }

}
