package building_outline;

import java.util.*;

/**
 * Created by Xiaotian on 9/28/17.
 */
public class Solution {
    // tag: sweep line, heap
    // time: O(nlogn)
    // space: O(n)
    class Coordinate implements Comparable<Coordinate> {
        final static int LEFT = 0;
        final static int RIGHT = 1;
        int x, y;
        int edge;
        int building_id;

        public Coordinate(int x, int y, int edge, int building_id) {
            this.x = x;
            this.y = y;
            this.edge = edge;
            this.building_id = building_id;
        }

        @Override
        public int compareTo(Coordinate other) {
            if (this.x != other.x) return this.x - other.x;
            if (this.y != other.y) return this.y - other.y;
            return this.edge - other.edge;
        }
    }
    /*
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) return res;

        List<Coordinate> coordinates = new ArrayList<>(buildings.length*2);
        TreeSet<Integer> positions = new TreeSet<>();
        for (int i = 0; i < buildings.length; i++) {
            int[] building = buildings[i];
            coordinates.add(new Coordinate(building[0], building[2], Coordinate.LEFT, i));
            coordinates.add(new Coordinate(building[1], building[2], Coordinate.RIGHT, i));
            positions.add(building[0]);
            positions.add(building[1]);
        }

        Collections.sort(coordinates);

        TreeSet<Coordinate> maxHeap = new TreeSet<>(new Comparator<Coordinate>(){
            @Override
            public int compare(Coordinate a, Coordinate b) {
                if (a.y != b.y) return a.y - b.y;
                return a.building_id - b.building_id;
            }
        });
        maxHeap.add(new Coordinate(0, 0, 0, 0));

        // start sweeping line
        int i = 0;
        int prevX = positions.pollFirst();
        while (i < coordinates.size() && coordinates.get(i).x == prevX) {
            Coordinate c = coordinates.get(i);
            maxHeap.add(c);
            i++;
        }
        Coordinate prevMaxY = maxHeap.last();
        while (!positions.isEmpty()) {
            int currX = positions.pollFirst();
            while (i < coordinates.size() && coordinates.get(i).x == currX) {
                Coordinate c = coordinates.get(i);
                if (c.edge == Coordinate.LEFT) {
                    maxHeap.add(c);
                } else {
                    maxHeap.remove(c);
                }
                i++;
            }
            Coordinate currMaxY = maxHeap.last();
            if (currMaxY.y != prevMaxY.y) {
                if (prevMaxY.y != 0) {
                    res.add(new ArrayList<Integer>(Arrays.asList(prevX, currX, prevMaxY.y)));
                }
                prevX = currX;
                prevMaxY = currMaxY;
            }
        }
        return res;
    }
}
