package range_addition;

/**
 * Created by Xiaotian on 4/8/18.
 */
class Solution {
    // tag: sweep line
    // time: O(k + n)
    // space: O(n)
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];

        int[] diff = new int[length];
        for (int[] update : updates) {
            int s = update[0];
            int e = update[1];
            int v = update[2];

            diff[s] += v;
            if (e + 1 < length) diff[e + 1] -= v;
        }

        int v = 0;
        for (int i = 0; i < length; i++) {
            v += diff[i];
            res[i] = v;
        }
        return res;
    }
}
