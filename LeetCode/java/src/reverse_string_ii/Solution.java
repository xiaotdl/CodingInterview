package reverse_string_ii;

/**
 * Created by Xiaotian on 7/10/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public String reverseStr(String s, int k) {
        char[] S = s.toCharArray();
        int n = S.length;
        int i = 0; // points to the start index of char[] to be reversed
        while (i < n) {
            int j = Math.min(i + k - 1, n - 1); // points to the end index of char[] to be reversed
            reverse(S, i, j);
            i += 2*k;
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
