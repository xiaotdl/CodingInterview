package paint_house_ii;

/**
 * Created by Xiaotian on 12/23/16.
 */
// tag: dp
// time: O(n*colors^2)
// space: O(1)
public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int houses = costs.length;
        int colors = costs[0].length;
        for (int i = 1; i < houses; i++) {
            for (int j = 0; j < colors; j++) {
                costs[i][j] += _min(costs[i - 1], j);
            }
        }
        return _min(costs[costs.length - 1]);
    }

    int _min(int[] list, int except) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.length; i++) {
            if (i == except) continue;
            min = Math.min(min, list[i]);
        }
        return min;
    }

    int _min(int[] list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.length; i++) {
            min = Math.min(min, list[i]);
        }
        return min;
    }
}

// tag: dp
// time: O(n*colors^2)
// space: O(1)
class SolutionII {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int houses = costs.length;
        int colors = costs[0].length;
        int prevMin1 = 0;
        int prevMin2 = 0;
        int prevMin1Index = -1;
        for (int i = 0; i < houses; i++) {
            int currMin1 = Integer.MAX_VALUE;
            int currMin2 = Integer.MAX_VALUE;
            int currMin1Index = -1;
            for (int j = 0; j < colors; j++) {
                costs[i][j] += (j == prevMin1Index ? prevMin2 : prevMin1);
                if (costs[i][j] < currMin1) {
                    currMin2 = currMin1;
                    currMin1 = costs[i][j];
                    currMin1Index = j;
                }
                else if (costs[i][j] < currMin2) {
                    currMin2 = costs[i][j];
                }
            }
            prevMin1 = currMin1;
            prevMin2 = currMin2;
            prevMin1Index = currMin1Index;
        }
        return prevMin1;
    }

}
