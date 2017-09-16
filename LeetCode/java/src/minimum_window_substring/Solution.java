package minimum_window_substring;

import java.util.*;

/**
 * Created by Xiaotian on 6/15/17.
 */
public class Solution {
    // sliding window
    // tag: str, hash, ptr
    // time: O(n)
    // space: O(t), t:length of str t
    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || s.length() < t.length()) return "";

        Map<Character, Integer> tChar2cnt = new HashMap<>(); // positive cnt to be matched by str s
        for (char c: t.toCharArray()) {
            tChar2cnt.put(c, tChar2cnt.containsKey(c) ? tChar2cnt.get(c) + 1 : 1);
        }

        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        int l = 0;
        int r = 0;
        int minWinLeft = 0;
        int minWinLen = Integer.MAX_VALUE;
        int cnt = 0; // t chars matched so far
        while (r < S.length) {
            if (tChar2cnt.containsKey(S[r])) {
                tChar2cnt.put(S[r], tChar2cnt.get(S[r]) - 1);
                // do nothing if S[r] is not in str T
                if (tChar2cnt.get(S[r]) >= 0) {
                    cnt++;
                }
                while (cnt == T.length) {
                    if (r - l + 1 < minWinLen) {
                        minWinLen = r - l + 1;
                        minWinLeft = l;
                    }
                    if (tChar2cnt.containsKey(S[l])) {
                        tChar2cnt.put(S[l], tChar2cnt.get(S[l]) + 1);
                        if (tChar2cnt.get(S[l]) > 0) {
                            cnt--;
                        }
                    }
                    l++;
                }
            }
            r++;
        }

        if (minWinLen == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(minWinLeft, minWinLeft + minWinLen);
    }
}


class SolutionII {
    // Same as Solution
    public String minWindow(String S, String T) {
        int[] ascii = new int[256]; // save T's char count
        for (char c : T.toCharArray()) {
            ascii[c]++;
        }
        int matchedCnt = 0; // t chars matched so far
        int minWin = Integer.MAX_VALUE;
        String minStr = "";
        int l, r;
        for(l = 0, r = 0; l < S.length(); l++) {
            while(r < S.length() && matchedCnt < T.length()) {
                if (ascii[S.charAt(r)] > 0) {
                    matchedCnt++;
                }
                ascii[S.charAt(r)]--;
                r++;
            }

            if (matchedCnt == T.length()) {
                int currWin = r - l;
                if (currWin < minWin) {
                    minWin = currWin;
                    minStr = S.substring(l, r);
                }
            }

            if (ascii[S.charAt(l)] >= 0) {
                matchedCnt--;
            }
            ascii[S.charAt(l)]++;
        }
        return minStr;
    }
}
