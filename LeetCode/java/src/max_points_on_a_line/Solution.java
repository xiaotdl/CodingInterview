package max_points_on_a_line;

import java.util.*;

/**
 * Created by Xiaotian on 12/13/17.
 */
class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class Solution {
    /**
     * @param points an array of point
     * @return an integer
     */
    public int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }

        HashMap<Double, Integer> map = new HashMap<Double, Integer>(); // slope2pointsExcludingSelf
        double slope = 0.0;
        int maxPoints = 0;
        int samePoints = 0;
        int verticalPoints = 0;

        for (int i = 0; i < points.length - 1; i++) {
            samePoints = 0; // exclude self(point i)
            verticalPoints = 0; // exclude self(point i)
            map.clear();

            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    samePoints++;
                    continue;
                }

                if (points[i].x == points[j].x) {
                    verticalPoints++;
                    continue;
                }

                slope = ((double)(points[i].y - points[j].y))/(points[i].x - points[j].x);

                if (map.containsKey(slope)) {
                    map.put(slope, map.get(slope) + 1);
                } else {
                    map.put(slope, 2);
                }
            }

            maxPoints = Math.max(maxPoints, verticalPoints + samePoints + 1);
            for (int sameSlopePoints : map.values()) {
                maxPoints = Math.max(maxPoints, sameSlopePoints + samePoints);
            }
        }

        return maxPoints;
    }
}

class SolutionII {
    // 辗转相除法, 找最大公约数
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int result = 1;
        for (int i = 0; i < points.length - 1; i++) {
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            int max = 0;
            int same = 1;
            for (int j = i + 1; j < points.length; j++) {
                int dy = points[j].y - points[i].y;
                int dx = points[j].x - points[i].x;
                if (dy == 0 && dx == 0) {
                    same++;
                } else {
                    int factor = getGCF(dx, dy);
                    dx /= factor;
                    dy /= factor;
                    map.putIfAbsent(dx, new HashMap<Integer, Integer>());
                    Map<Integer, Integer> countMap = map.get(dx);
                    int currentCount = countMap.getOrDefault(dy, 0) + 1;
                    max = Math.max(max, currentCount);
                    countMap.put(dy, currentCount);
                }
            }
            max += same;
            result = Math.max(result, max);
        }
        return result;
    }

    private int getGCF(int a, int b) { // greatest common factor/divisor
        if (b == 0) return a;
        else return getGCF(b, a % b);
    }
}
