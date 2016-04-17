public class TrailingZeroes {
    /*
     * param n: As desciption
     * return: An integer, denote the number of trailing zeros in n!
     */

    // V1, O(logn)
    // Same as number of fives
    public long trailingZeros(long n) {
        long x = 5;
        long result = 0;
        while (n >= x) {
            result += n / x;
            x *= 5;
        }
        return result;
    }
};
