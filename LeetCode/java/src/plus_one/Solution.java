package plus_one;

/**
 * Created by Xiaotian on 12/30/16.
 */
public class Solution {
    // tag: array, math
    // time: O(n)
    // space: O(1)
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry == 1) {
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    }
}

class SolutionII {
    // Same as Solution
    // tag: math, 数位分离
    // time: O(n)
    // space: O(n)
    /*
     * @param digits: a number represented as an array of digits
     * @return: the result
     */
    public int[] plusOne(int[] digits) {
        int[] res = new int[digits.length];
        res[digits.length - 1] = 1;
        int carry = 0;
        int digit = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = res[i] + digits[i] + carry;
            digit = sum % 10;
            carry = sum / 10;
            res[i] = digit;
        }

        if (carry == 1) {
            res = new int[digits.length + 1];
            res[0] = 1;
        }
        return res;
    }
}