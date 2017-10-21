package reverse_bits;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // tag: bit
    // time: O(1)
    // space: O(1)
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res |= (n >> i & 1) << (31 - i);
        }
        return res;
    }
}
