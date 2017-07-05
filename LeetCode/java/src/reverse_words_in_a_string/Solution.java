package reverse_words_in_a_string;

/**
 * Created by Xiaotian on 7/4/17.
 */
public class Solution {
    // credit: https://discuss.leetcode.com/topic/18189/clean-java-two-pointers-solution-no-trim-no-split-no-stringbuilder
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public String reverseWords(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;

        // step 1. reverse the whole string
        reverse(a, 0, n - 1);
        // step 2. reverse each word
        reverseWords(a, n);
        // step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++; // skip spaces
            while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
            reverse(a, i, j - 1);                      // reverse the word
        }
    }

    // trim leading, trailing and multiple spaces
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;             // skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
            while (j < n && a[j] == ' ') j++;             // skip spaces
            if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }

    // reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }
}

class SolutionII {
    // same as SolutionI
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";

        char[] S = s.toCharArray();
        int n = S.length;

        // reverse words
        int i = 0;
        int j = 0;
        while (i < n) {
            while (i < j || i < n && S[i] == ' ') i++;
            while (j < i || j < n && S[j] != ' ') j++;
            reverse(S, i, j - 1);
        }

        // reverse string
        reverse(S, 0, n - 1);

        // remove leading, trailing, additional in-between words spaces.
        i = 0;
        j = 0;
        while (j < n) {
            while (j < n && S[j] == ' ') j++;             // skip spaces
            while (j < n && S[j] != ' ') S[i++] = S[j++]; // keep non spaces
            while (j < n && S[j] == ' ') j++;             // skip spaces
            if (j < n) S[i++] = ' ';                      // keep only one space
        }
        // while (i < n) S[i++] = ' ';
        return new String(S).substring(0, i);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }
}
