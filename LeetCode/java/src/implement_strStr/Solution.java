package implement_strStr;

/**
 * Created by Xiaotian on 5/19/16.
 */
public class Solution {
    // tag: string, two pointers
    // time: O(n^2), two for loops
    // space: O(1), no additional space used
    public int strStr(String str, String subStr) {
        if (str == null || subStr == null) {
            return -1;
        }

        if (subStr.length() == 0) {
            return 0;
        }

        for (int i = 0; i < str.length() - subStr.length() + 1; i++) {
            for (int j = 0; j < subStr.length(); j++) {
                if (str.charAt(i + j) != subStr.charAt(j)) break;
                if (j == subStr.length() - 1) {
                    return i;
                }
            }
        }

        return -1;
    }
}
