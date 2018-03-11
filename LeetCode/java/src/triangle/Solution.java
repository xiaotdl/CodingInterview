package triangle;

import java.util.*;

/**
 * Created by Xiaotian on 12/29/16.
 */
class Solution {
    // dfs+memo, bottom up, post-order
    // tag: dfs
    // time: O(n^2), n = h, h + (h-1) + (h-2) + ... + 1 = (1+h)*h/2
    // space: O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] memo = new int[triangle.size()][triangle.size()]; // without memo will render TLE
        return dfs(triangle, memo, 0, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int[][] memo, int x, int y, int sum) {
        int m = triangle.size();
        if (x == m) return 0;

        if (memo[x][y] != 0) return memo[x][y];

        int lSum = dfs(triangle, memo, x + 1, y, sum);
        int rSum = dfs(triangle, memo, x + 1, y + 1, sum);
        memo[x][y] = Math.min(lSum, rSum) + triangle.get(x).get(y);
        return memo[x][y];
    }
}

class SolutionII {
    // tag: dp
    // time: O(n^2)
    // space: O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[i][j]: min path sum from (0, 0) to (i, j)
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (i - 1 >= 0 && j <= i - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                }
                if (i - 1 >= 0 && j - 1 >= 0 && j - 1 <= i - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
                if (dp[i][j] == Integer.MAX_VALUE) dp[i][j] = 0;
                dp[i][j] += triangle.get(i).get(j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, dp[n - 1][j]);
        }
        return min;
    }
}

class SolutionIII {
    // %2 or use prevSum
    // tag: dp
    // time: O(n^2)
    // space: O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp[j]: min path sum from (0, 0) to (i, j)
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                int prevSum = Integer.MAX_VALUE;
                if (i - 1 >= 0 && j <= i - 1) {
                    prevSum = Math.min(prevSum, dp[j]);
                }
                if (i - 1 >= 0 && j - 1 >= 0 && j - 1 <= i - 1) {
                    prevSum = Math.min(prevSum, dp[j - 1]);
                }
                if (prevSum == Integer.MAX_VALUE) prevSum = 0;
                dp[j] = prevSum + triangle.get(i).get(j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            min = Math.min(min, dp[j]);
        }
        return min;
    }
}
