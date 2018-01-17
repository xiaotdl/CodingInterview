package trailing_zeroes;

/**
 * Created by Xiaotian on 12/13/17.
 */
public class Solution {
    // Same as number of fives
    // tag: math
    // time: O(logn)
    // space: O(1)
    /*
     * @param n: An integer
     * @return: An integer, denote the number of trailing zeros in n!
     */
    public long trailingZeros(long n) {
        int cnt = 0;
        while (n != 0) {
            cnt += n / 5;
            n /= 5;
        }
        return cnt;
    }
}
