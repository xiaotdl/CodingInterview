package flip_bits;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // tag: bit
    // time: O(1)
    // space: O(1)
    /*
     * @param a: An integer
     * @param b: An integer
     * @return: An integer
     */
    public int bitSwapRequired(int a, int b) {
        return countOnes(a ^ b);
    }

    private int countOnes(int n) {
        int cnt = 0;
        while (n != 0) {
            n &= n - 1;
            cnt++;
        }
        return cnt;
    }
}
