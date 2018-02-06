package k_closest_points;

import java.util.*;

/**
 * Created by Xiaotian on 2/5/18.
 */
class Point {
    int x;
    int y;
    Point() { x = 0; y = 0; }
    Point(int a, int b) { x = a; y = b; }
}

public class Solution {
    // tag: heap
    // time: O(n)
    // space: O(k)
    /*
     * @param points: a list of points
     * @param origin: a point
     * @param k: An integer
     * @return: the k closest points
     */
    public Point[] kClosest(Point[] points, final Point origin, int k) {
        Point[] res = new Point[k];
        // max heap
        PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>(){
            @Override
            public int compare(Point a, Point b) {
                int d1 = (int) Math.pow(a.x - origin.x, 2) + (int) Math.pow(a.y - origin.y, 2);
                int d2 = (int) Math.pow(b.x - origin.x, 2) + (int) Math.pow(b.y - origin.y, 2);
                if (d1 != d2) {
                    return d2 - d1;
                }
                if (a.x != b.x) {
                    return b.x - a.x;
                }
                return b.y - a.y;
            }
        });

        for (Point p : points) {
            pq.offer(p);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        int i = k - 1;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            res[i] = p;
            i--;
        }
        return res;
    }
}
