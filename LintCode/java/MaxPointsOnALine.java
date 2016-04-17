/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class MaxPointsOnALine {
    /**
     * @param points an array of point
     * @return an integer
     */
    
    // V1, O(n^2), O(n)
    // Hash, Math
    public int maxPoints(Point[] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        
        HashMap<Double, Integer> map = new HashMap<Double, Integer>();
        double slope = 0.0;
        int maxPoints = 0;
        int samePoints = 0;
        int parallelPoints = 0;
        
        for (int i = 0; i < points.length - 1; i++) {
            samePoints = 0; // exclude point i
            parallelPoints = 0; // exclude point i
            map.clear();
            
            for (int j = i + 1; j < points.length; j++) {
                if (isSamePoint(points[i], points[j])) {
                    samePoints++;
                    continue;
                }
                if (points[i].x == points[j].x) {
                    parallelPoints++;
                    continue;
                }
                
                slope = ((double)(points[i].y - points[j].y))/(points[i].x - points[j].x);
                if (map.containsKey(slope)) {
                    map.put(slope, map.get(slope) + 1);
                } else {
                    map.put(slope, 2); // include point i
                }
            } 
    
            maxPoints = Math.max(maxPoints, samePoints + 1);
            maxPoints = Math.max(maxPoints, parallelPoints + 1);
            for (int currPoints : map.values()) {
                maxPoints = Math.max(maxPoints, currPoints + samePoints);
            }

        }
        
        return maxPoints;
    }
    private boolean isSamePoint(Point p1, Point p2) {
        return p1.x == p2.x && p1.y == p2.y;
    }
}