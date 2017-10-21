package single_number_ii;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // Ternary, 三进制加法
    // tag: bit
    // time: O(n)
    // space: O(1)
    /*
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumberII(int[] A) {
        if (A == null || A.length == 0) return 0;

        int res = 0;
        int[] digits = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                digits[i] += (A[j] >> i & 1);
                digits[i] %= 3;
            }
            res |= digits[i] << i;
        }
        return res;
    }
}
