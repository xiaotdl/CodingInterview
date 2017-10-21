package update_bits;

/**
 * Created by Xiaotian on 10/16/17.
 */
public class Solution {
    // use << (31 - j) >>> (31 - j) to clear high 31-j bits
    // use >>> i << i to clear low i bits
    // tag: bit
    // time: O(n)
    // space: O(1)
    /*
     * @param n: An integer
     * @param m: An integer
     * @param i: A bit position
     * @param j: A bit position
     * @return: An integer
     */
    public int updateBits(int n, int m, int i, int j) {
        return (n & ~(-1 << (31 - j) >>> (31 - j) >>> i << i)) | (m << i);
    }
}
