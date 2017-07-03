package zigzag_conversion;

/**
 * Created by Xiaotian on 7/1/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(n)
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) return s;

        char[] S = s.toCharArray();
        StringBuffer[] sb = new StringBuffer[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuffer();
        }

        int i = 0;
        while (i < s.length()) {
            for (int r = 0; r < numRows && i < s.length(); r++) {
                sb[r].append(S[i++]);
            }
            for (int r = numRows - 2; r >= 1 && i < s.length(); r--) {
                sb[r].append(S[i++]);
            }
        }

        StringBuffer res = new StringBuffer();
        for (i = 0; i < sb.length; i++) {
            res.append(sb[i]);
        }
        return res.toString();
    }
}
