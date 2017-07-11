package output_contest_matches;

/**
 * Created by Xiaotian on 7/10/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(nlogn), 2^m = n
    // space: O(n)
    public String findContestMatch(int n) {
        String[] m = new String[n];
        for (int i = 0; i < n; i++) {
            m[i] = String.valueOf(i + 1);
        }

        while (n > 1) {
            int i = 0; // points to the current strongest team
            int j = n - 1; // points to the current weakest team
            while (i < j) {
                m[i] = "(" + m[i++] + "," + m[j--] + ")";
            }
            n /= 2;
        }
        return m[0];
    }
}
