package com.allInOne;
import java.util.*;
public class wordBreakII_140_1 {

//I've been struggling with this problem for a long time, and I'd
// love to share three different strategies I have tried to solve it.
// All of them are ACed.

//Method 1: DP + DFS. Very similar to Word Break I, but instead of using
// a boolean dp array, I used an array of Lists to maintain all of the
// valid start positions for every end position. Then just do classic
// backtracking to find all solutions. The time complexity is O(n*m)
// + O(n * number of solutions), where n is the length of the input string,
// m is the length of the longest word in the dictionary. The run time was 6ms.
// It is very efficient because DP is used to find out all the valid answers,
// and no time is wasted on doing the backtracking.

    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<Integer>[] starts = new List[s.length() + 1]; // valid start positions
        starts[0] = new ArrayList<>();

        int maxLen = getMaxLen(wordDict);//max length of the words in the dictionary
        for (int i = 1; i <= s.length(); i++) { //scan the string from 1 to length
            for (int j = i - 1; j >= i - maxLen && j >= 0; j--) { //within the left string max length of dict
                System.out.println("000 i = " + i + "; j = " + j + " starts[" + j + "] = " + starts[j] + "; i - maxLen =" + (i - maxLen));
                if (starts[j] == null) continue; // to find left formed words, if =! null
                String word = s.substring(j, i);
                System.out.println("word = " + word);
                if (wordDict.contains(word)) {
                    if (starts[i] == null) {
                        starts[i] = new ArrayList<>();
                    }
                    starts[i].add(j);//add the index of the start character, all them together form the sentence
                    System.out.println("111 i = " + i + "; j = " + j + " starts[" + i + "] = " + starts[i]);
                }
            }
        }

        List<String> rst = new ArrayList<>();
        if (starts[s.length()] == null) {//the last one must have word matched
            return rst;
        }

        dfs(rst, "", s, starts, s.length());
        return rst;
    }


    private void dfs(List<String> rst, String path, String s, List<Integer>[] starts, int end) {
        if (end == 0) {
            rst.add(path.substring(1)); //after all connected; add the whole sentence with removing the first " "
            return;
        }

        for (Integer start: starts[end]) {
            String word = s.substring(start, end);
            System.out.println("start = " + start + "; end = " + end + "; word = " + word);
            dfs(rst, " " + word + path, s, starts, start);
        }
    }

    private int getMaxLen(Set<String> wordDict) {
        int max = 0;
        for (String s : wordDict) {
            max = Math.max(max, s.length());
        }
        return max;
    }
}
