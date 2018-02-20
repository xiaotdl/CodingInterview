package rectangle;

import java.util.*;

/**
 * Created by Xiaotian on 2/19/18.
 */
class Point {
    public int x;
    public int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    // tag: hash
    // time: O(n^2)
    // space: O(n)
    /**
     * @param P: The point set
     * @return: The answer
     */
    public String rectangle(Point[] P) {
        Set<String> points = new HashSet<>();
        for (Point point : P) {
            points.add(point.x + "," + point.y);
        }

        for (int i = 0; i < P.length; i++) { // top-left
            for (int j = 0; j < P.length; j++) { // bottom-right
                if (P[i].x < P[j].x && P[i].y > P[j].y) {
                    if (points.contains(P[i].x + "," + P[j].y)
                     && points.contains(P[j].x + "," + P[i].y)) {
                        return "YES";
                    }
                }
            }
        }
        return "NO";
    }
}
