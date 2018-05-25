package edit_distance;

import java.util.*;

/**
 * Created by xili on 5/18/16.
 */
public class Solution {
    // tag: str, dp
    // time: O(mn)
    // space: O(mn), used two dimensional matrix to save path
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // dp[i][j]: minDistance(""||word1[0..i-1], ""||word2[0..j-1])
        int[][] dp = new int[m + 1][n + 1];

        dp[0][0] = 0;
        for (int i = 1; i < m + 1; i++) dp[i][0] = i;
        for (int j = 1; j < n + 1; j++) dp[0][j] = j;
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    int insert = dp[i][j - 1] + 1;
                    int delete = dp[i - 1][j] + 1;
                    int replace = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Collections.min(Arrays.asList(insert, delete, replace));
                }
            }
        }
        return dp[m][n];
    }
}
