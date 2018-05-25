package sqrt_x;

/**
 * Created by Xiaotian on 9/24/17.
 */
public class Solution {
    // tag: binary search, math
    // time: O(logn)
    // space: O(1)
    public int mySqrt(int x) {
        if (x <= 1) return x;

        long l = 0;
        long r = x;
        while (l + 1 < r) {
            long m = l + (r - l) / 2;
            if (m*m == x) {
                return (int) m;
            } else if (m*m < x) {
                l = m;
            } else {
                r = m;
            }
        }
        if (r*r <= x) return (int) r;
        return (int) l;
    }
}

class SolutionII {
    // both input and output are double
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    public double sqrt(double x) {
        double l = 0;
        double r = (x < 1.0) ? 1.0 : x;
        double eps = 1e-12;
        while (r - l > eps) {
            double m = l + (r - l) / 2;
            if (m*m < x) {
                l = m;
            } else if (m*m > x) {
                r = m;
            } else {
                return m;
            }
        }
        return r;
    }
}
