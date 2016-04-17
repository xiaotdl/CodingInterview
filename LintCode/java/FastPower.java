/**
 * Created by Xiaotian on 7/16/15.
 */
public class FastPower {
    /*
     * @param a, b, n: 32bit integers
     * @return: An integer
     */

    // V1, O(logn)
    // Recursion
    public int fastPower(int a, int b, int n) {
        if (a < 0 || b < 0 || n < 0) {
            return 0;
        }

        if (n == 0) {
            return 1 % b;
        }

        if (n == 1) {
            return a % b;
        }

        long product = fastPower(a, b, n / 2);
        product = (product * product) % b;
        if (n % 2 == 1) {
            product = (product * a) % b;
        }

        return (int) product;
    }
};

