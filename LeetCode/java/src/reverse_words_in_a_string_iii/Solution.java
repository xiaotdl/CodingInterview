package reverse_words_in_a_string_iii;

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
