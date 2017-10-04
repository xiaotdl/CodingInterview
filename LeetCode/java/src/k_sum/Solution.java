package k_sum;

/**
 * Created by Xiaotian on 10/4/17.
 */
public class Solution {
    // combination can also solve this, but takes more time, O(2^n)
    // tag: dp
    // time: O(n*k*t)
    // space: O(n*k*t)
    /**
     * @param A: an integer array.
     * @param k: a positive integer (k <= length(A))
     * @param target: a integer
     * @return an integer
     */

    public int  kSum(int A[], int k, int target) {
        // f[i][j][t]: from first i nums, pick j nums, equal t
        int[][][] f = new int[A.length + 1][k + 1][target + 1];

        for (int i = 0; i < A.length + 1; i++) {
            f[i][0][0] = 1;
        }

        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < k + 1 && j <= i; j++) {
                for (int t = 1; t < target + 1; t++) {
                    if (t >= A[i - 1]) {
                        // pick i-th item
                        f[i][j][t] = f[i - 1][j][t] + f[i - 1][j - 1][t - A[i - 1]];
                    } else {
                        // doesn't pick i-th item
                        f[i][j][t] = f[i - 1][j][t];
                    }
                }
            }
        }

        return f[A.length][k][target];
    }
}


