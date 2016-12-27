package unique_binary_search_trees;

/**
 * Created by Xiaotian on 12/26/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n)
public class Solution {
    public int numTrees(int n) {
        // dp[i]: numTrees of i nodes
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // j is root node
            for (int j = 1; j <= i; j++) {
                int left = dp[j - 1] == 0 ? 1 : dp[j - 1];
                int right = dp[i - j] == 0 ? 1: dp[i - j];
                dp[i] += left * right;
            }
        }
        return dp[n];
    }
}
