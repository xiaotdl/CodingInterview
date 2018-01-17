package dices_sum;

import java.util.*;

/**
 * Created by Xiaotian on 12/13/17.
 */
public class Solution {
    // tag: math, dp
    // time: O(n)
    // space: O(n^2)
    /**
     * @param n an integer
     * @return a list of Map.Entry<sum, probability>
     */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        List<Map.Entry<Integer, Double>> results =
                new ArrayList<Map.Entry<Integer, Double>>();

        // f[i][j]: the probability of i dices that sums to j
        double[][] f = new double[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; ++i)
            f[1][i] = 1.0 / 6;

        for (int i = 2; i <= n; ++i) {
            for (int j = i; j <= 6 * n; ++j) {
                for (int k = 1; k <= 6; ++k) {
                    if (j > k)
                        f[i][j] += f[i - 1][j - k];
                }
                f[i][j] /= 6.0;
            }
        }

        for (int x = n; x <= 6 * n; ++x)
            results.add(new AbstractMap.SimpleEntry<Integer, Double>(x, f[n][x]));

        return results;
    }
}
