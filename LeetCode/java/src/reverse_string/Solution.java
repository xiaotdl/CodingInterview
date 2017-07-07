package reverse_string;

/**
 * Created by Xiaotian on 5/14/16.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n), one pass through string
    // time: O(n), String is immutable in Java
    public String reverseString(String s) {
        if (s == null) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(s.length() - 1 - i));
        }

        return sb.toString();
    }
}

class SolutionII {
    // same as SolutionI
    // tag: str, ptr
    // time: O(n), one pass through string
    // time: O(n), String is immutable in Java
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) return s;

        char[] S = s.toCharArray();
        int l = 0;
        int r = S.length - 1;
        while (l < r) {
            char tmp = S[l];
            S[l] = S[r];
            S[r] = tmp;
            l++;
            r--;
        }
        return new String(S);
    }
}
