package longest_repeating_character_replacement;

/**
 * Created by Xiaotian on 2/18/18.
 */
public class Solution {
    // tag: ptr, sliding window
    // time: O(n)
    // space: O(1)
    public int characterReplacement(String s, int k) {
        int[] charCnt = new int[256];
        char[] S = s.toCharArray();

        int maxLen = 0;
        int majorCnt = 1;
        int l, r;
        for (l = 0, r = 0; l < S.length && r < S.length; l++) {
            while (r < S.length) {
                int currMajorCnt = Math.max(majorCnt, charCnt[S[r]] + 1); // majorCnt including S[r]
                if (r - l + 1 - currMajorCnt <= k) {
                    charCnt[S[r]]++;
                    majorCnt = currMajorCnt;
                    r++;
                }
                else {
                    break;
                }
            }
            maxLen = Math.max(maxLen, r - l);
            charCnt[S[l]]--;
        }
        return maxLen;
    }
}
