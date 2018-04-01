package rectangle_overlap;

/**
 * Created by Xiaotian on 1/17/18.
 */
class Point {
    public int x, y;
    public Point() { x = 0; y = 0; }
    public Point(int a, int b) { x = a; y = b; }
}

public class Solution {
    // tag: geometry
    // time: O(1)
    // space: O(1)
    /*
     * @param l1: top-left coordinate of first rectangle
     * @param r1: bottom-right coordinate of first rectangle
     * @param l2: top-left coordinate of second rectangle
     * @param r2: bottom-right coordinate of second rectangle
     * @return: true if they are overlap or false
     */
    public boolean doOverlap(Point l1, Point r1, Point l2, Point r2) {
        if (r1.x < l2.x || l1.x > r2.x) {
            return false;
        }

        if (l1.y < r2.y || r1.y > l2.y) {
            return false;
        }

        return true;
    }
}
