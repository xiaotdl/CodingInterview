package length_of_last_word;

/**
 * Created by Xiaotian on 5/15/16.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n), one pass through string
    // space: O(1), no additional space used
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int count = 0;
        int i = s.length() - 1;
        // skip tailing spaces
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        // count last word's length
        while (i >= 0 && s.charAt(i) != ' ') {
            count++;
            i--;
        }

        return count;
    }
}

class SolutionII {
    // same as SolutionI
    // tag: str, ptr
    // time: O(n), one pass through string
    // space: O(1), no additional space used
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        char[] S = s.toCharArray();
        int i = n - 1;
        int cnt = 0;
        while (i >= 0 && S[i] == ' ') i--;
        while (i >= 0 && S[i] != ' ') {i--; cnt++;}
        return cnt;
    }
}
