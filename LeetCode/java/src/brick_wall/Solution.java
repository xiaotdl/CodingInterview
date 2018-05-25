package brick_wall;

import java.util.*;

/**
 * Created by Xiaotian on 5/5/18.
 */
class Solution {
    // tag: hash
    // time: O(n), n: number of bricks
    // space: O(k), k: sum of bricks
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> m = new HashMap<>(); // spacePos2cnt

        for (List<Integer> bricks : wall) {
            int spacePos = 0;
            for (int i = 0; i < bricks.size(); i++) { // row of bricks
                if (i == bricks.size() - 1) continue;
                spacePos += bricks.get(i);
                m.put(spacePos, m.getOrDefault(spacePos, 0) + 1);
            }
        }
        int maxSpaceCnt = 0;
        for (int spaceCnt : m.values()) {
            maxSpaceCnt = Math.max(maxSpaceCnt, spaceCnt);
        }
        return wall.size() - maxSpaceCnt;
    }
}
