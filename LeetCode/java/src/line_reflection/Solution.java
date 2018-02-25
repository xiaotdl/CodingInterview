package line_reflection;

import java.util.*;

/**
 * Created by Xiaotian on 2/25/18.
 */
public class Solution {
    // tag: hash, set
    // time: O(n)
    // space: O(n)
    public boolean isReflected(int[][] points) {
        Set<Integer> set = new HashSet<>();
        int maxX = Integer.MIN_VALUE;
        int minX = Integer.MAX_VALUE;
        for (int[] p : points) {
            int x = p[0];
            int y = p[1];
            maxX = Math.max(maxX, x);
            minX = Math.min(minX, x);
            set.add(hash(x, y));
        }

        for (int[] p : points) {
            int x = p[0];
            int y = p[1];
            int reflectedX = maxX + minX - x;
            int reflectedY = y;
            if (!set.contains(hash(reflectedX, reflectedY))) return false;
        }
        return true;
    }

    private int hash(int x, int y) {
        return (1<<16) * x + y;
    }
}
