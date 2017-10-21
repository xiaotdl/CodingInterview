package swap_bits;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // tag: bit
    // time: O(n)
    // space: O(1)
    /*
     * @param x: An integer
     * @return: An integer
     */
    public int swapOddEvenBits(int x) {
        return ((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1);
    }
}
