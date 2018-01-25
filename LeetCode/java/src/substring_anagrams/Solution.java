package substring_anagrams;

import java.util.*;

/**
 * Created by Xiaotian on 1/24/18.
 */
public class Solution {
    // tag: str, hash
    // time: O(n)
    // space: O(1)
    /*
     * @param s: a string
     * @param p: a string
     * @return: a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) return res;

        char[] S = s.toCharArray();
        char[] P = p.toCharArray();

        int[] charCnt = new int[256];
        int diffCnt = 0;

        for (int i = 0; i < p.length(); i++) {
            charCnt[S[i]]++;
            charCnt[P[i]]--;
        }

        for (int x : charCnt) {
            diffCnt += Math.abs(x);
        }

        if (diffCnt == 0) {
            res.add(0);
        }

        for (int l = 0, r = P.length; r < S.length; l++, r++) {
            diffCnt = diffCnt - Math.abs(charCnt[S[l]]) - Math.abs(charCnt[S[r]]);
            charCnt[S[l]]--;
            charCnt[S[r]]++;
            diffCnt = diffCnt + Math.abs(charCnt[S[l]]) + Math.abs(charCnt[S[r]]);

            if (diffCnt == 0) {
                res.add(l + 1);
            }
        }

        return res;
    }
}
