package number_of_corner_rectangles;

import java.util.*;

/**
 * Created by Xiaotian on 5/1/18.
 */
class Solution {
    // credit: https://leetcode.com/problems/number-of-corner-rectangles/solution/
    // tag: matrix, hash
    // time: O(r*c*c)
    // space: O(c*c)
    public int countCornerRectangles(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>(); // (x*200+y)2cnt
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int r = 0; r < m; r++) {
            for (int i = 0; i < n; i++) {
                if (grid[r][i] == 0) continue;
                for (int j = i + 1; j < n; j++) {
                    if (grid[r][j] == 0) continue;

                    int hash = i*200 + j;
                    int cnt = map.getOrDefault(hash, 0); // new rectangles
                    res += cnt;
                    map.put(hash, cnt + 1);
                }
            }
        }
        return res;
    }
}
