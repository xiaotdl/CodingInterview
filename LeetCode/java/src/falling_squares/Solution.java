package falling_squares;

import java.util.*;

/**
 * Created by Xiaotian on 3/31/18.
 */
class Solution {
    // credit: Approach #1: Offline Propagation [Accepted]
    // https://leetcode.com/problems/falling-squares/solution/
    // For each square positions[i], the maximum height will get higher by the size of the square we drop.
    // Then, for any future squares that intersect the interval [left, right)
    // (where left = positions[i][0], right = positions[i][0] + positions[i][1]),
    // we'll update the maximum height of that interval.

    // tag: brutal force
    // time: O(n^2)
    // space: O(n)
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0) return res;

        int n = positions.length; // num of squares
        int[] maxHeights = new int[n]; // maxHeight for each square
        for (int i = 0; i < n; i++) {
            int[] sq = positions[i]; // [l, r)
            int l = sq[0];
            int sideLen = sq[1];
            int r = l + sideLen;
            maxHeights[i] += sideLen;

            // calculate effects on future squares
            for (int j = i + 1; j < n; j++) {
                int[] sq2 = positions[j];
                int l2 = sq2[0];
                int sideLen2 = sq2[1];
                int r2 = l2 + sideLen2;
                if (r2 <= l || l2 >= r) continue;
                // overlap
                maxHeights[j] = Math.max(maxHeights[j], maxHeights[i]);
            }
        }


        int maxSoFar = 0;
        for (int h : maxHeights) {
            maxSoFar = Math.max(maxSoFar, h);
            res.add(maxSoFar);
        }
        return res;
    }
}
