package palindrome_number;

/**
 * Created by Xiaotian on 2/4/18.
 */
public class Solution {
    // tag: math, 数位分离
    // time: O(n)
    // space: O(1)
    /*
     * @param num: a positive number
     * @return: true if it's a palindrome or false
     */
    public boolean isPalindrome(int num) {
        if (num < 0) return false;

        return num == reversed(num);
    }

    private int reversed(int num) {
        int reversed = 0;
        while (num != 0) {
            int digit = num % 10;
            reversed = 10 * reversed + digit;
            num /= 10;
        }
        return reversed;
    }
}
