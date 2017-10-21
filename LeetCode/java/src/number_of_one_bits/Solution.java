package number_of_one_bits;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // hamming weight
    // tag: bit
    // time: O(1)
    // space: O(1)
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) {
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }
}
