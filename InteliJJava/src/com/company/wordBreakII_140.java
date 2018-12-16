package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class wordBreakII_140 {
    public List<String> wordBreaker(String s, Set<String> wordDict){
        return DFS(s, wordDict, new HashMap<>());//new HashMap<String, LinkedList<String>>()
    }

    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map){
                    //original string; word dicti0nary, hash map: key: string, word; value:
        if (map.containsKey(s)) {
            System.out.println("map.containsKey(" + s + ") is TRUE, and map.get(s) =  " + map.get(s));
            return map.get(s);
        }

        LinkedList<String>res = new LinkedList<>(); //new LinkedList<String>()
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)){
                System.out.println("s.startsWith(" + word + "))");
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub:sublist)
                    res.add(word +( sub.isEmpty() ? "" : " " ) + sub);
                System.out.println("s is \"" +  s + "\" and res is \"" + res + "\"");
                System.out.println(" ");
            }

        }

        map.put(s, res);

        return res;
    }
}

/*
Given a non-empty string s and a dictionary wordDict containing a list of
non-empty words, add spaces in s to construct a sentence where each word
is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */