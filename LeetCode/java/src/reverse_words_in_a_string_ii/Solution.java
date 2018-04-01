package reverse_words_in_a_string_ii;

/**
 * Created by Xiaotian on 7/12/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public String reverseWords(String s) {
        char[] S = s.toCharArray();
        int i = 0; // iterating ptr
        // skipping leading spaces
        while (i < S.length && S[i] == ' ') i++;
        int j = i; // points to starting index of the word
        while (i <= S.length) {
            if (i == S.length || S[i] == ' ') {
                reverse(S, j, i - 1);
                while (i < S.length && S[i] == ' ') i++;
                j = i;
            }
            i++;
        }
        return new String(S);
    }

    private void reverse(char[] S, int i, int j) {
        while (i < j) {
            char tmp = S[i];
            S[i] = S[j];
            S[j] = tmp;
            i++;
            j--;
        }
    }
}

class SolutionII {
    // Same as Solution
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public void reverseWords(char[] S) {
        for (int i = 0; i < S.length; i++) {
            if (S[i] == ' ') continue;
            int j = i;
            while (j + 1 < S.length && S[j + 1] != ' ') j++;
            reverse(S, i, j);
            i = j;
        }
        reverse(S, 0, S.length - 1);
    }

    private void reverse(char[] S, int l, int r) {
        while (l < r) {
            char tmp = S[l];
            S[l] = S[r];
            S[r] = tmp;
            l++;
            r--;
        }
    }
}
