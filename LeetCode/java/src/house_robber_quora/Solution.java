package house_robber_quora;

/**
 * Created by Xiaotian on 3/20/18.
 */
public class Solution {
    // Ref: http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=187199&highlight=quora
    // 给个int array contain number from 1 to N, 不能连续选相差1的项，求最大和。
    // eg: [1,1,2,3,4,4,5,5,6] 如果选了4就不能选3或者5，但是4可以重复选
    // tag: dp
    // time: O(n)
    // space: O(n)
    public int rob(int[] A) {
        // dp[i]: max money robbed so far
        int[] dp = new int[A.length];
        dp[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            if (Math.abs(A[i] - A[i - 1]) != 1) {
                dp[i] = dp[i - 1] + A[i];
                continue;
            }

            int j = i - 1; // points to first previous number that has abs diff > 1
            while (j >= 0 && Math.abs(A[j] - A[i]) == 1) j--;
            dp[i] = Math.max(
                        (j != -1 ? dp[j] : 0) + A[i], // pick current
                        dp[i - 1]                     // doesn't pick current
                    );
        }
        return dp[A.length - 1];
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,1,2,3,4,4,5,5,6};
        System.out.println(new Solution().rob(A));
    }
}
