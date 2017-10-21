package power_of_three;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // tag: math, recursive
    // time: O(n)
    // space: O(1)
    public boolean isPowerOfThree(int n) {
        return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree(n / 3)));
    }
}

class SolutionII {
    // tag: math, iterative
    // time: O(n)
    // space: O(1)
    public boolean isPowerOfThree(int n) {
        while (n > 1 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}

class SolutionIII {
    // radix 3
    // tag: math
    // time: O(1)
    // space: O(1)
    public boolean isPowerOfThree(int n) {
        return Integer.toString(n, 3).matches("10*");
    }
}
