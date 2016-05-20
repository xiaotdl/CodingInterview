package edit_distance;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by xili on 5/18/16.
 */
public class Solution {
    // tag: string, dp
    // time: O(m + n), one pass through each string
    // space: O(mn), used two dimensional matrix to save path
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }

        // dp[i][j]: tracks minDistance(word1[:i], word2[:j])
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        // when word1 is empty
        for (int j = 0; j < word2.length() + 1; j++) {
            dp[0][j] = j;
        }
        // when word2 is empty
        for (int i = 0; i < word1.length() + 1; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i < word1.length() + 1; i++) {
            for (int j = 1; j < word2.length() + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int replace = dp[i - 1][j - 1] + 1;
                    int insert = dp[i - 1][j] + 1;
                    int delete = dp[i][j - 1] + 1;
                    dp[i][j] = Collections.min(Arrays.asList(replace, insert, delete));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
