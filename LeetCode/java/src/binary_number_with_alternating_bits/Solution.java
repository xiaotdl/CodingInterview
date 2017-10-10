package binary_number_with_alternating_bits;

/**
 * Created by Xiaotian on 10/10/17.
 */
public class Solution {
    // tag: bit
    // time: O(n)
    // space: O(1)
    public boolean hasAlternatingBits(int n) {
        int prevBit = n & 1;
        while (n > 0) {
            n >>= 1;
            int currBit = n & 1;
            if (prevBit == currBit) {
                return false;
            }
            prevBit = currBit;
        }
        return true;
    }
}
