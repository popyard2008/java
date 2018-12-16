package com.allInOne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class wordBreakII_140_3 {
    //Method 3: DP Prunning + Backtracking. My very first solution is like this:
    // using a boolean array to memoize whether a substring starting from position
    // i to the end is breakable. This works well for worst cases like: s = "aaaaaaaaaaaab", dict = ["a", "aa", "aaa", "aaaa"]. However, for cases like: s = "aaaaaaaaaaaaa", dict = ["a", "aa", "aaa", "aaaa"], the time complexity is still O(2^n). Here is the code:

    public List<String> wordBreak3(String s, Set<String> wordDict) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() == 0 || wordDict == null) {
            return rst;
        }

        boolean[] canBreak = new boolean[s.length()];
        Arrays.fill(canBreak, true);
        StringBuilder sb = new StringBuilder();
        dfs(rst, sb, s, wordDict, canBreak, 0);
        return rst;
    }

    private void dfs(List<String> rst, StringBuilder sb, String s, Set<String> dict,
                     boolean[] canBreak, int start) {
        if (start == s.length()) {
            rst.add(sb.substring(1));
            return;
        }

        if (!canBreak[start]) {
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if (!dict.contains(word)) continue;

            int sbBeforeAdd = sb.length();
            sb.append(" " + word);

            int rstBeforeDFS = rst.size();
            dfs(rst, sb, s, dict, canBreak, i);
            if (rst.size() == rstBeforeDFS) {
                canBreak[i] = false;
            }
            sb.delete(sbBeforeAdd, sb.length());
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
