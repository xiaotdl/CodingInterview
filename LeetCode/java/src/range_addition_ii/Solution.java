package range_addition_ii;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Solution {
    // tag: math
    // time: O(k), k: len(ops)
    // space: O(1)
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op: ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}

