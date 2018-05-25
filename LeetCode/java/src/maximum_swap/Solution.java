package maximum_swap;

import java.util.*;

/**
 * Created by Xiaotian on 9/14/17.
 */
class Solution {
    // tag: array, brutal force
    // time: O(n)
    // space: O(1)
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int max = num;
        for (int i = 0; i < digits.length; i++) {
            for (int j = i + 1; j < digits.length; j++) {
                swap(digits, i, j);
                max = Math.max(max, Integer.parseInt(new String(digits)));
                swap(digits, i, j);
            }
        }
        return max;
    }

    private void swap(char[] digits, int i, int j) {
        char tmp = digits[i];
        digits[i] = digits[j];
        digits[j] = tmp;
    }
}

class SolutionII {
    // credit: https://leetcode.com/problems/maximum-swap/solution/
    // tag: array
    // time: O(n)
    // space: O(1)
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] lowest = new int[10];
        Arrays.fill(lowest, -1);
        for (int i = 0; i < digits.length; i++) {
            lowest[digits[i] - '0'] = i; // the last/lowest occurrence of digit d is index i (if it exists).
        }

        // try to swap biggest digit into high digit..
        for (int i = 0; i < digits.length; i++) { // from high to low digit
            for (int d = 9; d > digits[i] - '0'; d--) { // d: 9 ~ digits[i]+1
                if (lowest[d] > i) {
                    swap(digits, i, lowest[d]);
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        return num;
    }

    private void swap(char[] digits, int i, int j) {
        char tmp = digits[i];
        digits[i] = digits[j];
        digits[j] = tmp;
    }
}
