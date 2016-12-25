package counting_bits;

/**
 * Created by Xiaotian on 12/23/16.
 */
// tag: brutal force
// time: O(n*sizeof(num))
// space: O(1)
public class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            int bits = 0;
            for (int j = 0; j < 32; j++) {
                if (bitSet(i, j)) {
                    bits++;
                }
            }
            res[i] = bits;
        }
        return res;
    }

    public boolean bitSet(int num, int pos) {
        return (num & (1 << pos)) != 0;
    }
}
// tag: dp
// time: O(n)
// space: O(n)
class SolutionII {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        // dp[i]: countBits[i]
        int[] dp = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            if (i == 0) {
                dp[i] = 0;
            }
            else if (i == 1) {
                dp[i] = 1;
            }
            else {
                dp[i] = ((i & 1) == 0) ? dp[i >> 1] : dp[i >> 1] + 1;
            }
            res[i] = dp[i];
        }
        return res;
    }
}
