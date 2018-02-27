package pow_x_n;

/**
 * Created by Xiaotian on 9/25/17.
 */
public class Solution {
    // Note when n is negative part
    // tag: math, divide and conquer
    // time: O(logn)
    // space: O(1)
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;

        double half = myPow(x, n/2);

        if (n % 2 == 0) {
            return half * half;
        } else if (n > 0) {
            return half * half * x;
        } else {
            return half * half / x;
        }
    }
}

class SolutionII {
    // n < 0 & n >= 0
    // tag: math, divide and conquer
    // time: O(logn)
    // space: O(1)
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x, n);
    }

    double fastPow(double x, int n) {
        if (n == 0) return 1.0;

        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
