package bitwise_and_of_numbers_range;

/**
 * Created by Xiaotian on 10/22/17.
 */
public class Solution {
    // tag: bit
    // time: O(1)
    // space: O(1)
    public int rangeBitwiseAnd(int m, int n) {
        if (n == m) {
            return n;
        }
        if (n - m == 1) {
            return n & m;
        }
        return rangeBitwiseAnd(m >> 1, n >> 1) << 1;
    }
}

class SolutionII {
    // use a mask to find left same bits
    // tag: bit
    // time: O(1)
    // space: O(1)
    public int rangeBitwiseAnd(int m, int n) {
        int mask = Integer.MAX_VALUE;
        while ((m & mask) != (n & mask)) {
            mask <<= 1;
        }
        return m & mask;
    }
}
