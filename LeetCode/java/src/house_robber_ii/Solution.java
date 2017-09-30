package house_robber_ii;

/**
 * Created by Xiaotian on 7/21/16.
 */
public class Solution {
    // tag: dp
    // time: O(n), two iterations through A.
    // space: O(n), 2 x one dimensional additional space.
    public int rob(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        // dp[i]: max money that can be robbed from {} || A[0..i-1]

        // rob first house
        int[] dp1 = new int[A.length + 1];
        dp1[0] = 0;
        dp1[1] = A[0];
        for (int i = 2; i < A.length + 1; i++) {
            int a = dp1[i - 1];               // doesn't rob last house
            int b = dp1[i - 2] + A[i - 1]; // rob last house
            if (i == A.length) {
                dp1[i] = a;
            } else {
                dp1[i] = Math.max(a, b);
            }
        }

        // doesn't rob first house
        int[] dp2 = new int[A.length + 1];
        dp2[0] = 0;
        dp2[1] = 0;
        for (int i = 2; i < A.length + 1; i++) {
            int a = dp2[i - 1];               // doesn't rob last house
            int b = dp2[i - 2] + A[i - 1]; // rob last house
            dp2[i] = Math.max(a, b);
        }

        return Math.max(dp1[A.length], dp2[A.length]);
    }
}

class SolutionII {
    // same as SolutionI
    public int rob(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        if (A.length == 1) {
            return A[0];
        }

        return Math.max(robHelper(A, 0, A.length - 2), robHelper(A, 1, A.length - 1));
    }

    private int robHelper(int[] A, int start, int end) {
        int length = end - start + 1;
        if (A == null || length <= 0) {
            return 0;
        }

        // dp[i]: max money that can be robbed from A[start..i-1]
        int[] dp = new int[length + 1];

        dp[0] = 0;
        for (int i = 1; i < length + 1; i++) {
            // rob last house
            int a = A[start + i - 1] + (i - 2 >= 0 ? dp[i - 2] : 0);
            // doesn't rob last house
            int b = dp[i - 1];
            dp[i] = Math.max(a, b);
        }

        return dp[length];
    }
}

class SolutionIII {
    // same as SolutionI
    public int rob(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        if (A.length == 1) {
            return A[0];
        }
        int n = A.length;
        // dp[i]: max money robbed from house[0..i], with house[0] robbed, thus house[n - 1] can't be robbed
        int[] dp1 = new int[n];
        dp1[0] = A[0];
        dp1[1] = A[0];
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 2]  + A[i], dp1[i - 1]);
        }
        // dp[i]: max money robbed from house[0..i], with house[0] not robbed
        int[] dp2 = new int[n];
        dp2[0] = 0;
        dp2[1] = A[1];
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 2]  + A[i], dp2[i - 1]);
        }
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
}

class SolutionIV {
    // tag: dp
    // time: O(n)
    // space: O(n), space can be improved to O(1) using 滚动优化
    /*
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] A) {
        if (A == null || A.length == 0) return 0;
        if (A.length == 1) return A[0];

        return Math.max(houseRobber1(A, 0, A.length - 2),
                        houseRobber1(A, 1, A.length - 1));
    }

    public int houseRobber1(int[] A, int s, int e) {
        int length = e - s + 1;
        // dp[i]: houseRobber1 from {} || A[0..i-1]
        int[] dp = new int[length + 1];
        dp[0] = 0;
        dp[1] = A[s];
        for (int i = 2; i < length + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + A[s + i - 1]);
        }
        return dp[length];
    }
}
