package find_the_closest_palindrome;

/**
 * Created by Xiaotian on 7/12/17.
 */
public class Solution {
    // TLE
    // tag: str, ptr
    // time: O(n)
    // space: O(1)
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        for (long i = 1;;i++) {
            if (isPalindrome(Long.toString(num - i))) return Long.toString(num - i);
            if (isPalindrome(Long.toString(num + i))) return Long.toString(num + i);
        }
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) return false;
        }
        return true;
    }
}
