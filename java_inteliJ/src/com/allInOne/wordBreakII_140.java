package com.allInOne;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class wordBreakII_140 {
    public List<String> wordBreaker(String s, Set<String> wordDict){
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map){
        if (map.containsKey(s)) {
            System.out.println("get map : " + map.get(s) + " s = " + s);

            return map.get(s);
        }

        LinkedList<String>res = new LinkedList<>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (String word : wordDict) {
//            System.out.println("word is : " + word);

            if (s.startsWith(word)){
//                System.out.println("word contained : " + word);

                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
//                System.out.println("sublist = " + sublist.toString());
                for (String sub:sublist)
                    res.add(word +( sub.isEmpty() ? "" : " " ) + sub);
//                System.out.println(res.toString());
            }
        }
//        System.out.println("");
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


