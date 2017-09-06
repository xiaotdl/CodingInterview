package triangle;

import java.util.List;

/**
 * Created by Xiaotian on 12/29/16.
 */
public class Solution {
    // tag: dp
    // time: O(mn)
    // space: O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null
                || triangle.size() == 0
                || triangle.get(0).size() == 0) {
            return 0;
        }

        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        // dp[j]: minimumTotal when last num on path is triangle[i][j]
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < m; i++) {
            for (int j = i; j >= 0; j--) {
                dp[j] = Math.min(j == i ? Integer.MAX_VALUE : dp[j],
                        j == 0 ? Integer.MAX_VALUE : dp[j - 1]);
                dp[j] += triangle.get(i).get(j);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}
