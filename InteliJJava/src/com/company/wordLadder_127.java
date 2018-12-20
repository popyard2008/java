package com.company;

import java.util.*;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class wordLadder_127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null) return 0;
        String starWord = beginWord;
        HashMap<String, Integer> map = new HashMap<>(); //add
        map.put(starWord, 1);
        int cycle = 1; //flag to mark which round it scans the depth of matched pairs
        boolean bln = true;
        while (bln) {
            bln = false;
            HashMap<String, Integer> temp = new HashMap<>(); //Subtract
            for (String key : map.keySet()) {
//            Iterator<String> existed = map.keySet().iterator();
//            while (existed.hasNext()){
                starWord = key;
                if (map.get(starWord) != cycle) {continue;}
//                System.out.println("start word: " + starWord + " getKey " + map.get(starWord));
                List<String> toRemove = new ArrayList<>();
                for (String word : wordList) {
                    if (canTransform(starWord, word)) {
                        if (word.equals(endWord)) {
                            System.out.println("startWord: " + starWord + " word :" + word + " end word :" + endWord);
                            return map.get(starWord) + 1;
                        } else {
                            temp.put(word, map.get(key) + 1);
//                            System.out.println("remove: " + word );
                            toRemove.add(word);
                            bln = true;
                        }
                    }
                }
                wordList.removeAll(toRemove);
            }
            map.putAll(temp);
            cycle += 1;
        }
            return 0;
        }

        private boolean canTransform (String wordOne, String wordTwo){

            int transTime = 0;
            for (int i = 0; i < wordOne.length(); i++) {
//                System.out.println("i = " + i + " wordOne.charAt(i) =" + wordOne.charAt(i) + " wordTwo.charAt(i) = " + wordTwo.charAt(i) + " transTime = " + transTime  );
                if (wordOne.charAt(i) != wordTwo.charAt(i)) {
                    transTime += 1;
                }
                if (transTime > 1) {
                    return false;
                }
            }
            return transTime == 1;
        }
    }

/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */