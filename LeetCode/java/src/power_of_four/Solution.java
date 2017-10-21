package power_of_four;

/**
 * Created by Xiaotian on 10/17/17.
 */
class Solution {
    // tag: math, recursive
    // time: O(n)
    // space: O(1)
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n == 1 || n % 4 == 0 && isPowerOfFour(n / 4));
    }
}

class SolutionII {
    // tag: math, iterative
    // time: O(n)
    // space: O(1)
    public boolean isPowerOfFour(int n) {
        while (n > 1 && n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }
}

class SolutionIII {
    // radix 4
    // tag: math
    // time: O(1)
    // space: O(1)
    public boolean isPowerOfFour(int n) {
        return Integer.toString(n, 4).matches("10*");
    }
}

