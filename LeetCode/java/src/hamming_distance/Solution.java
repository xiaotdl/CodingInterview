package hamming_distance;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // tag: bit
    // time: O(n)
    // space: O(1)
    public int hammingDistance(int x, int y) {
        return countOnes(x^y);
    }

    private int countOnes(int xor) {
        int cnt = 0;
        while (xor != 0) {
            xor = xor & (xor - 1);
            cnt++;
        }
        return cnt;
    }
}
