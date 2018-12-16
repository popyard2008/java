package com.allInOne;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class wordBreakII_140_2 {

//Method 2: Memoization + Backtracking. Before I came up with Method 1, I also tried using a
// HashMap to memoize all the possible strings that can be formed starting from index i.
// I referred to this post from @Pixel_
//The time complexity is O(len(wordDict) ^ len(s / minWordLenInDict)) as @Pixel_ mentioned.
// The space complexity would be larger than other methods though. Here is my code:

    public List<String> wordBreak2(String s, Set<String> wordDict) {
        HashMap<Integer, List<String>> memo = new HashMap<>(); // <Starting index, rst list>
        return dfs(s, 0, wordDict, memo);
    }

    private List<String> dfs(String s, int start, Set<String> dict, HashMap<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> rst = new ArrayList<>();
        if (start == s.length()) {
            rst.add("");
            return rst;
        }

        String curr = s.substring(start);
        for (String word: dict) {
            if (curr.startsWith(word)) {
                List<String> sublist = dfs(s, start + word.length(), dict, memo);
                for (String sub : sublist) {
                    rst.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        memo.put(start, rst);
        return rst;
    }
}
