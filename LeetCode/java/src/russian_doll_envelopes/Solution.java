package russian_doll_envelopes;

import java.util.*;

/**
 * Created by Xiaotian on 12/27/16.
 */
// tag: dp
// time: O(nlogn)
// space: O(n)
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;

        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] e1, int[] e2) {
                if (e1[0] == e2[0]) {
                    return e2[1] - e1[1]; // height: decreasing order
                } else {
                    return e1[0] - e2[0]; // width: increasing order
                }
            }
        });

        // calculate lenOfLIS based on height
        int[] dp = new int[envelopes.length];
        int lenOfLIS = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int height = envelopes[i][1];
            int index = Arrays.binarySearch(dp, 0, lenOfLIS, height);
            if (index < 0) index = -(index + 1);
            dp[index] = height;
            if (index == lenOfLIS) lenOfLIS++;
        }

        return lenOfLIS;
    }
}

class SolutionII {
    // tag: dp
    // time: O(n^2)
    // space: O(n)
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        int n = envelopes.length;
        // dp[i]: longest increasing envelopes from envelopes[0..i] ending with envelopes[i]
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = 1;

        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) return e1[0] - e2[0];
                return e1[1] - e2[1];
            }
        });

        int maxLen = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0]
                 && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
