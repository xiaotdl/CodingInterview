package first_position_unique_character;

/**
 * Created by Xiaotian on 1/25/18.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(1)
    /*
     * @param s: a string
     * @return: it's index
     */
    public int firstUniqChar(String s) {
        int[] charCnt = new int[256];

        for (char c : s.toCharArray()) {
            charCnt[c]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (charCnt[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;
    }
}
