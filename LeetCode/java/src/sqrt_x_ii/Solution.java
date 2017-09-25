package sqrt_x_ii;

/**
 * Created by Xiaotian on 9/25/17.
 */
public class Solution {
    // Note x < 1.0 case && double type comparison(r - l < eps)
    // tag: binary search
    // time: O(logn)
    // space: O(1)
    /*
     * @param x: a double
     * @return: the square root of x
     */
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
