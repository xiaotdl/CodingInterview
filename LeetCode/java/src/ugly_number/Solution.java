package ugly_number;

/**
 * Created by Xiaotian on 12/27/16.
 */
// tag: math
// time: O(logn)
// space: O(1)
public class Solution {
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;
        int[] uglyPrimeFactors = {2, 3, 5};
        for (int factor : uglyPrimeFactors) {
            while (num % factor == 0) {
                num /= factor;
            }
        }
        return num == 1;
    }
}
