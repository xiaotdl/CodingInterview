package longest_uncommon_subsequence_i;

/**
 * Created by Xiaotian on 7/8/17.
 */
public class Solution {
    // tag: str
    // time: O(n)
    // space: O(1)
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) return -1;
        return Math.max(a.length(), b.length());
    }
}
