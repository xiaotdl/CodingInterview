package rotate_string;

/**
 * Created by Xiaotian on 3/10/18.
 */
public class Solution {
    // find substr
    // tag: str, ptr
    // time: O(n^2)
    // space: O(1)
    public boolean rotateString(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        char[] A = a.toCharArray();
        char[] B = b.toCharArray();
        int l = a.length();

        for (int i = 0; i < 2 * l; i++) {
            if (B[i % l] != A[0]) continue;
            for (int j = i; j < i + l; j++) {
                if (B[j % l] != A[j - i]) break;
                if (j == i + l - 1) return true;
            }
        }
        return false;
    }
}
