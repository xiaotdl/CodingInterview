package escape_the_ghosts;

/**
 * Created by Xiaotian on 2/24/18.
 */
public class Solution {
    // tag: graph
    // time: O(n)
    // space: O(1)
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int minDist = Integer.MAX_VALUE;
        for (int[] g : ghosts) {
            minDist = Math.min(minDist, manhattanDistance(g, target));
        }
        return manhattanDistance(new int[]{0, 0}, target) < minDist;
    }

    private int manhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}
