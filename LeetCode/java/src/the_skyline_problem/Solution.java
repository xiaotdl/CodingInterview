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
