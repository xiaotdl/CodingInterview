package single_number;

/**
 * Created by Xiaotian on 10/17/17.
 */
public class Solution {
    // tag: bit
    // time: O(n)
    // space: O(1)
    /*
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] A) {
        if (A == null || A.length == 0) return 0;

        int res = 0;
        for (int x : A) {
            res ^= x;
        }
        return res;
    }
}
