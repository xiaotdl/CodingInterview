package number_of_boomerangs;

import java.util.*;

/**
 * Created by Xiaotian on 2/8/18.
 */
public class Solution {
    // 计算所有其他点到A点的距离，统计离A点有某个相同距离d的点有几个，最后将所有个数代入k*(k-1)并相加，就得到了所有以A点为三元组中第一个点的回旋镖个数。
    // tag: hash
    // time: O(n^2)
    // space: O(n^2)
    public int numberOfBoomerangs(int[][] points) {
        if (points == null) return 0;

        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>(); // distance2cnt
            for (int j = 0; j < points.length; j++) {
                if (j == i) continue;
                int distance = getDistance(points[i], points[j]);
                map.putIfAbsent(distance, 0);
                map.put(distance, map.get(distance) + 1);
            }
            for (int cnt : map.values()) {
                res += cnt * (cnt - 1);
            }
        }
        return res;
    }

    private int getDistance(int[] a, int[] b) {
        return (a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]);
    }
}
