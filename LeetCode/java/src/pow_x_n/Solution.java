package pow_x_n;

/**
 * Created by Xiaotian on 9/25/17.
 */
public class Solution {
    // Note negative part
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
