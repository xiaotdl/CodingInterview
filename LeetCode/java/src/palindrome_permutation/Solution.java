package palindrome_permutation;

/**
 * Created by Xiaotian on 3/28/18.
 */
public class Solution {
    // tag: hash
    // time: O(n)
    // space: O(1)
    public boolean canPermutePalindrome(String s) {
        int singleCharCnt = 0;
        int[] charCnt = new int[256];
        for (char c : s.toCharArray()) {
            if (charCnt[c] == 0) {
                charCnt[c]++;
                singleCharCnt++;
            }
            else {
                charCnt[c]--;
                singleCharCnt--;
            }
        }
        return singleCharCnt <= 1;
    }
}
