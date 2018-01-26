package fibonacci;

/**
 * Created by Xiaotian on 12/13/17.
 */
public class Solution {
    // tag: math, dp, iterative
    // time: O(n)
    // space: O(1)
    /*
     * @param n: an integer
     * @return: an integer f(n)
     */
    public int fibonacci(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n - 1; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return a;
    }
}

class SolutionII {
    // Same as Solution
    /*
     * @param n: an integer
     * @return: an ineger f(n)
     */
    public int fibonacci(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;

        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 0; i < n - 2; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
