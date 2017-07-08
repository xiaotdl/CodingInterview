package number_of_segments_in_a_string;

/**
 * Created by Xiaotian on 7/8/17.
 */
public class Solution {
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public int countSegments(String s) {
        int res = 0;
        int i = 0;
        while (i < s.length()) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                res++;
            }
            i++;
        }
        return res;
    }
}
