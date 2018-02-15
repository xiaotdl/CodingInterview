package maximum_number_of_arrows_to_burst_balloons;

import java.util.*;

/**
 * Created by Xiaotian on 2/13/18.
 */
public class Solution {
    // tag: greedy
    // time: O(nlogn)
    // space: O(1)
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) return 0;

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] p1, int[] p2) {
                if (p1[1] != p2[1]) return p1[1] - p2[1];
                return p1[0] - p2[0];
            }
        });

        int prevEnd = points[0][1];
        int minArrows = 1;
        for (int i = 0; i < points.length; i++) {
            int currStart = points[i][0];
            int currEnd = points[i][1];
            if (currStart > prevEnd) {
                minArrows++;
                prevEnd = currEnd;
            }
        }
        return minArrows;
    }
}
