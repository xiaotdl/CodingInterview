package frog_jump;

import java.util.*;

/**
 * Created by Xiaotian on 12/29/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n^2)
public class Solution {
    public boolean canCross(int[] stones) {
        if (stones.length == 0) return true;

        // dp[stone(not index)]: [steps can be taken from this stone]
        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>(stones.length);

        dp.put(0, new HashSet<Integer>());
        dp.get(0).add(1);
        for (int i = 1; i < stones.length; i++) {
            dp.put(stones[i], new HashSet<Integer>());
        }

        for (int i = 0; i < stones.length; i++) {
            for (int step : dp.get(stones[i])) {
                int reach = stones[i] + step;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                if (dp.containsKey(reach)) {
                    HashSet<Integer> steps = dp.get(reach);
                    steps.add(step);
                    if (step - 1 > 0) steps.add(step - 1);
                    steps.add(step + 1);
                }
            }
        }

        return false;
    }
}
