package permutation_in_string;

/**
 * Created by Xiaotian on 6/15/17.
 */
public class Solution {
    // credit: https://discuss.leetcode.com/topic/87884/8-lines-slide-window-solution-in-java
    // tag: ptr
    // time: O(n)
    // space: O(1)
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return true;
        if (s2 == null || s2.length() == 0 || s2.length() < s1.length()) return false;

        char[] S1 = s1.toCharArray();
        char[] S2 = s2.toCharArray();
        int[] cnt = new int[128];
        for (int i = 0; i < S1.length; i++) cnt[S1[i]]--;

        int l = 0;
        int r = 0;
        while (r < S2.length) {
            if (++cnt[S2[r]] > 0)
                while(--cnt[S2[l++]] != 0) { /* do nothing */ }
            if (r - l + 1 == S1.length) {
                return true;
            }
            r++;
        }
        return false;

    }
}
