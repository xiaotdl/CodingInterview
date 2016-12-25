package arithmetic_slices_ii;

import java.util.*;

/**
 * Created by Xiaotian on 12/23/16.
 */
// tag: dp
// time: O(n^2)
// space: O(n^2)
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        Map<Integer, Integer>[] map = new Map[A.length];
        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long diff = (long) A[i] - A[j];
                if (diff >= Integer.MAX_VALUE || diff <= Integer.MIN_VALUE) continue;
                int d = (int) diff;
                int subSeqCounts = getOrDefault(map[j], d, 0);
                res += subSeqCounts;
                map[i].put(d, getOrDefault(map[i], d, 0) + subSeqCounts + 1);
            }
        }
        return res;
    }

    public int getOrDefault(Map<Integer, Integer> map, int key, int defaultValue) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return defaultValue;
    }
}
