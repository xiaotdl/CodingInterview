package power_of_two;

/**
 * Created by Xiaotian on 10/17/17.
 */
class Solution {
    // tag: bit
    // time: O(1)
    // space: O(1)
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
