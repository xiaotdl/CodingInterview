package first_unique_character_in_a_string;

import java.util.*;

/**
 * Created by Xiaotian on 1/25/18.
 */
public class Solution {
    // two pass
    // tag: hash
    // time: O(n)
    // space: O(1)
    /*
     * @param s: a string
     * @return: it's index
     */
    public int firstUniqChar(String s) {
        int[] charCnt = new int[256];

        for (char c : s.toCharArray()) {
            charCnt[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (charCnt[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;
    }
}

class SolutionII {
    // one pass
    // tag: hash
    // time: O(n)
    // space: O(1)
    public int firstUniqChar(String s) {
        char[] S = s.toCharArray();
        int[] charIdx = new int[256]; // -1: init, -2: repeated
        Arrays.fill(charIdx, -1);
        for (int i = 0; i < s.length(); i++) {
            if (charIdx[S[i]] == -1) { // first time
                charIdx[S[i]] = i;
            }
            else if (charIdx[S[i]] == -2) {
                continue;
            }
            else { // charIdx[S[i]] >= 0
                charIdx[S[i]] = -2;
            }
        }
        int minIdx = Integer.MAX_VALUE;
        for (int i = 0; i < 256; i++) {
            if (charIdx[i] >= 0) {
                minIdx = Math.min(minIdx, charIdx[i]);
            }
        }
        return minIdx != Integer.MAX_VALUE ? minIdx : - 1;
    }
}
