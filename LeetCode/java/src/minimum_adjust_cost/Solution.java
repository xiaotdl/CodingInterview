package minimum_adjust_cost;

import java.util.*;

/**
 * Created by Xiaotian on 10/4/17.
 */
public class Solution {
    // tag: dp
    // time: O(n*100*100)
    // space: O(n*100)
    /**
     * @param A: An integer array.
     * @param maxDiff: An integer, up to 100.
     */

    public static int MinAdjustmentCost(ArrayList<Integer> A, int maxDiff) {
        if (A == null || A.size() == 0) {
            return 0;
        }

        // f[i][j]: 把index = i的值修改为j，所需要的最小花费
        int[][] f = new int[A.size()][101];

        for (int j = 0; j <= 100 ; j++) {
            f[0][j] = Math.abs(j - A.get(0));
        }

        for (int i = 1; i < A.size(); i++) {
            for (int j = 1; j <= 100; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = 1; k <= 100; k++) {
                    // j and k are neighbours, [..., k, j, ...]
                    if (Math.abs(j - k) > maxDiff) {
                        continue;
                    }

                    int cost = f[i - 1][k] + Math.abs(j - A.get(i));
                    min = Math.min(min, cost);
                }
                f[i][j] = min;
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 1; j <= 100; j++) {
            min = Math.min(min, f[A.size() - 1][j]);
        }

        return min;
    }
}
