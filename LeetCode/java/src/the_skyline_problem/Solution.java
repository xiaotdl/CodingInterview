package the_skyline_problem;

import java.util.*;

/**
 * Created by Xiaotian on 9/27/17.
 */
public class Solution {
    // tag: sweep line, heap
    // time: O(nlogn)
    // space: O(n)
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return res;

        int[][] Points = new int[2 * buildings.length][2]; // Points[point index][building start/end pos, +/-height]
        TreeSet<Integer> Xs = new TreeSet<>();
        for (int i = 0, j = 0; i < buildings.length; i++) {
            int[] b = buildings[i]; // {xStart, xEnd, y}
            Points[j][0] = b[0]; // {xStart, -y}, building start point
            Points[j++][1] = -b[2];
            Points[j][0] = b[1]; // {xEnd, y}, building end point
            Points[j++][1] = b[2];
            Xs.add(b[0]);
            Xs.add(b[1]);
        }

        Arrays.sort(Points, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) return a[0] - b[0];
                return a[1] - b[1];
            }
        });

        // store all building heights so far
        Queue<Integer> maxHeap = new PriorityQueue<>(buildings.length, Collections.reverseOrder());
        maxHeap.offer(0);
        int prevMaxY = 0;

        // start sweeping line
        int i = 0;
        while (!Xs.isEmpty()) {
            int currX = Xs.pollFirst();
            while (i < Points.length && Points[i][0] == currX) {
                int currY = Points[i][1];
                if (currY < 0) {
                    maxHeap.offer(-currY);
                } else {
                    maxHeap.remove(currY);
                }
                i++;
            }
            int currMaxY = maxHeap.peek();
            if (currMaxY != prevMaxY) {
                res.add(new int[]{currX, currMaxY});
                prevMaxY = currMaxY;
            }
        }
        return res;

    }
}

class SolutionII {
    // credit: https://www.youtube.com/watch?v=GSBLe8cKu0s
    // tag: sweep line, heap
    // time: O(nlogn)
    // space: O(n)
    class Point {
        int x;
        int y;
        boolean isL;
        public Point(int x, int y, boolean isL) {
            this.x = x;
            this.y = y;
            this.isL = isL;
        }
        @Override
        public String toString() {
            return String.format("(%d, %d, %s)", x, y, isL);
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0) return res;

        Point[] points = new Point[2*buildings.length];
        for (int i = 0; i < points.length; i += 2) { // int[] b: {xLeft, xRight, y}
            int[] b = buildings[i/2];
            points[i] = new Point(b[0], b[2], true);
            points[i + 1] = new Point(b[1], b[2], false);
        }
        Arrays.sort(points, new Comparator<Point>(){
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.x != p2.x) return p1.x - p2.x;

                // upon same x:
                // LL => higher height building should be picked first
                // RR => lower height building should be picked first
                // LR || RL => L should appear before R
                if (p1.isL && p2.isL) {
                    return p2.y - p1.y;
                }
                else if (!p1.isL && !p2.isL) {
                    return p1.y - p2.y;
                }
                else {
                    return p1.isL ? -1 : 1;
                }
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(buildings.length, Collections.reverseOrder());
        pq.add(0);
        int prevMaxY = 0;
        for (Point p : points) {
            if (p.isL) {
                pq.offer(p.y);
            }
            else {
                pq.remove(p.y);
            }

            int currMaxY = pq.peek();
            if (currMaxY != prevMaxY) {
                res.add(new int[]{p.x, currMaxY});
                prevMaxY = currMaxY;
            }
        }
        return res;
    }
}


