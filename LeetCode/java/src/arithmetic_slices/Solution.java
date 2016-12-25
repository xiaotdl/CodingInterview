package arithmetic_slices;

/**
 * Created by Xiaotian on 12/23/16.
 */
// tag: dp
// time: O(n)
// space: O(n)
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int res = 0;
        // dp[i]: number of arithmetic sequences when A[i] is the tail
        int[] dp = new int[A.length + 1];
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
            else {
                dp[i] = 0;
            }
            res += dp[i];
        }
        return res;
    }
}
// tag: dp
// time: O(n)
// space: O(1)
class SolutionII {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        int res = 0;
        int cur = 0;
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur++;
            }
            else {
                cur = 0;
            }
            res += cur;
        }
        return res;
    }
}
