package com.company;

import java.util.HashSet;
import java.util.Set;

public class wordLadder_2_127 {
    public int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);
        wordDict.add(endWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<String>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();

                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        System.out.println("each is " + each + " ch is " + ch + " word is "  + word);

                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            System.out.println("added word: " + word);
                            wordDict.remove(word);
                        }
                    }
                }
                System.out.println("******************");
            }
            distance++;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
        }
        return distance;
    }
}
