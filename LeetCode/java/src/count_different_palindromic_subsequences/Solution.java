package count_different_palindromic_subsequences;

import java.util.*;

/**
 * Created by Xiaotian on 4/10/18.
 */
class Solution {
    // O(26*log(n)*n^2), n^2 memo, 26*log(n) per memo search
    public final static int MOD = 1000000007;
    public int countPalindromicSubsequences(String s) {
        TreeSet<Integer>[] charIdxs = new TreeSet[26];
        int n = s.length();

        for (int i = 0; i < 26; i++) charIdxs[i] = new TreeSet<Integer>();

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            charIdxs[c].add(i);
        }
        int[][] memo = new int[n + 1][n + 1];
        return search(charIdxs, memo, 0, n);
    }

    public int search(TreeSet<Integer>[] charIdxs, int[][] memo, int l, int r) {
        if (l >= r) return 0;
        if (memo[l][r] != 0) return memo[l][r];

        long cnt = 0;
        for (int i = 0; i < 26; i++) {
            Integer newL = charIdxs[i].ceiling(l); // newL >= l
            Integer newR = charIdxs[i].lower(r);   // newR <  r
            if (newL == null || newL >= r) continue;
            cnt++;                                         // 1 char(newL) as palindrome
            if (newL != newR) cnt++;                       // 2 chars(newL+newR) as palindrome
            cnt += search(charIdxs, memo, newL + 1, newR); // >= 3 chars(newL+palindrome+newR) as palindrome
        }
        memo[l][r] = (int) (cnt % MOD);
        return memo[l][r];
    }
}
